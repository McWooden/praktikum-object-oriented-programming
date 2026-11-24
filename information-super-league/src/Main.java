import model.lomba.CabangLomba;
import model.lomba.LombaSatuan;
import model.lomba.PaketBundling;
import model.transaksi.Pendaftaran;
import model.user.Panitia;
import model.user.PerwakilanRombel;
import payment.BayarDana;
import payment.BayarTunai;
import payment.Pembayaran;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<CabangLomba> masterLomba = new ArrayList<>();
    private static final List<Pendaftaran> masterPendaftaran = new ArrayList<>();
    private static final List<PerwakilanRombel> masterRombel = new ArrayList<>();
    private static Panitia adminPanitia;

    public static void main(String[] args) {
        initData();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=============================================================");
        System.out.println("  SISTEM INFORMASI PENDAFTARAN TURNAMEN ISL 2026 (JAVA 25)");
        System.out.println("            Program Studi S1 Teknologi Informasi             ");
        System.out.println("=============================================================");

        while (running) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Masuk sebagai Perwakilan Rombel (Peserta)");
            System.out.println("2. Masuk sebagai Panitia Pelaksana (Admin)");
            System.out.println("3. Lihat Katalog Lomba & Sisa Kuota");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi (1-4): ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    menuRombel(scanner);
                    break;
                case "2":
                    menuPanitia(scanner);
                    break;
                case "3":
                    tampilkanKatalogLomba();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nTerima kasih telah menggunakan sistem pendaftaran ISL 2026.");
                    break;
                default:
                    System.out.println("[PERINGATAN] Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    private static void initData() {
        adminPanitia = new Panitia("ADM-01", "Fauzan Fahmi Basyir", "admin", "admin123", "2505060001", "Kesekretariatan & Keuangan");

        masterLomba.add(new LombaSatuan("L01", "Futsal", 16, 80000));
        masterLomba.add(new LombaSatuan("L02", "Badminton (Ganda)", 16, 40000));
        masterLomba.add(new LombaSatuan("L03", "Mobile Legends", 24, 50000));
        masterLomba.add(new LombaSatuan("L04", "PlayStation (PS)", 16, 20000));
        masterLomba.add(new LombaSatuan("L05", "Hackathon", 12, 40000));
        masterLomba.add(new LombaSatuan("L06", "Capture The Flag (CTF)", 16, 20000));
        masterLomba.add(new LombaSatuan("L07", "Crimping Kabel", 20, 10000));

        List<String> lombaBundling = List.of("Futsal", "Badminton (Ganda)", "Mobile Legends", "PS", "Hackathon", "CTF", "Crimping Kabel");
        masterLomba.add(new PaketBundling("B01", "Paket Bundling Semua Cabang", 10, 250000, lombaBundling));

        masterRombel.add(new PerwakilanRombel("R-01", "Ahmad", "rombel_a", "pass123", "Rombel TI 2026 A", "S1 Teknologi Informasi", "081234567891"));
        masterRombel.add(new PerwakilanRombel("R-02", "Budi", "rombel_b", "pass123", "Rombel TI 2026 B", "S1 Teknologi Informasi", "081234567892"));
    }

    private static void tampilkanKatalogLomba() {
        System.out.println("\n=============================================================");
        System.out.println("               DAFTAR CABANG LOMBA & KUOTA                   ");
        System.out.println("=============================================================");
        System.out.printf("%-4s %-6s %-30s %-12s %-10s\n", "No", "Kode", "Nama Cabang Lomba", "Biaya", "Sisa Slot");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < masterLomba.size(); i++) {
            CabangLomba l = masterLomba.get(i);
            System.out.printf("%-4d %-6s %-30s Rp%-10.0f %-10d\n",
                    (i + 1), l.getKodeLomba(), l.getNamaLomba(), l.hitungBiaya(), l.getSisaKuota());
        }
        System.out.println("=============================================================");
    }

    private static void menuRombel(Scanner scanner) {
        System.out.println("\n--- LOGIN PERWAKILAN ROMBEL ---");
        System.out.print("Username (contoh: rombel_a): ");
        String username = scanner.nextLine().trim();
        System.out.print("Password (contoh: pass123) : ");
        String password = scanner.nextLine().trim();

        PerwakilanRombel user = null;
        for (PerwakilanRombel r : masterRombel) {
            if (r.login(username, password)) {
                user = r;
                break;
            }
        }

        if (user == null) {
            System.out.println("[GAGAL] Kredensial salah atau akun belum terdaftar.");
            return;
        }

        System.out.println("\n[BERHASIL LOGIN] Selamat datang, " + user.getProfil());
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- MENU PERWAKILAN ROMBEL ---");
            System.out.println("1. Daftarkan Tim ke Cabang Lomba / Paket Bundling");
            System.out.println("2. Cek Riwayat & Status Pendaftaran Rombel");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih opsi (1-3): ");

            String opsi = scanner.nextLine().trim();
            switch (opsi) {
                case "1":
                    prosesPendaftaranRombel(scanner, user);
                    break;
                case "2":
                    cekStatusPendaftaran(user);
                    break;
                case "3":
                    inMenu = false;
                    break;
                default:
                    System.out.println("[PERINGATAN] Opsi tidak valid.");
            }
        }
    }

    private static void prosesPendaftaranRombel(Scanner scanner, PerwakilanRombel rombel) {
        System.out.println("\n--- FORMULIR PENDAFTARAN TIM ---");
        tampilkanKatalogLomba();

        Pendaftaran pendaftaran = new Pendaftaran("REG-" + (masterPendaftaran.size() + 101), rombel);

        boolean lanjutPilih = true;
        while (lanjutPilih) {
            System.out.print("\nMasukkan Nomor Lomba yang ingin diikuti (1-" + masterLomba.size() + "): ");
            try {
                int no = Integer.parseInt(scanner.nextLine().trim());
                if (no < 1 || no > masterLomba.size()) {
                    System.out.println("[PERINGATAN] Nomor lomba tidak tersedia.");
                    continue;
                }

                CabangLomba pilihan = masterLomba.get(no - 1);

                // Validasi Keunikan Rombel (KF-03): Mencegah duplikasi pendaftaran
                if (apakahRombelSudahDaftar(rombel, pilihan)) {
                    System.out.println("[VALIDASI DITOLAK] Rombel " + rombel.getNamaRombel() +
                            " sudah terdaftar pada cabang: " + pilihan.getNamaLomba() + "!");
                } else if (pilihan.getSisaKuota() <= 0) {
                    System.out.println("[VALIDASI DITOLAK] Kuota untuk " + pilihan.getNamaLomba() + " sudah penuh!");
                } else {
                    pendaftaran.tambahLomba(pilihan);
                    System.out.println("[BERHASIL DITAMBAHKAN] " + pilihan.getNamaLomba() + " dimasukkan ke formulir.");
                }

            } catch (NumberFormatException e) {
                System.out.println("[PERINGATAN] Harap masukkan angka yang valid.");
            }

            System.out.print("Tambah cabang lomba lainnya? (y/n): ");
            String jawab = scanner.nextLine().trim();
            lanjutPilih = jawab.equalsIgnoreCase("y");
        }

        if (pendaftaran.getDaftarLomba().isEmpty()) {
            System.out.println("[BATAL] Tidak ada cabang lomba yang dipilih. Pendaftaran dibatalkan.");
            return;
        }

        System.out.println("\n-------------------------------------------------------------");
        System.out.println("Total Biaya yang Harus Dibayar: Rp" + String.format("%,.0f", pendaftaran.getTotalBiaya()));
        System.out.println("-------------------------------------------------------------");
        System.out.println("Pilih Metode Pembayaran:");
        System.out.println("1. Transfer Dompet Digital DANA (085362511010 a.n Nabila)");
        System.out.println("2. Tunai Langsung ke Panitia (Fauzan Fahmi Basyir)");
        System.out.print("Pilih metode (1/2): ");

        String pMetode = scanner.nextLine().trim();
        Pembayaran pembayaran;

        if (pMetode.equals("1")) {
            System.out.print("Masukkan Nomor HP Akun DANA Anda: ");
            String noHp = scanner.nextLine().trim();
            System.out.print("Masukkan Kode / Nomor Transaksi DANA: ");
            String ref = scanner.nextLine().trim();
            pembayaran = new BayarDana(noHp, ref);
        } else {
            System.out.print("Masukkan Nomor Kuitansi Fisik dari Panitia: ");
            String kuitansi = scanner.nextLine().trim();
            pembayaran = new BayarTunai("Fauzan Fahmi Basyir", kuitansi);
        }

        boolean bayarOk = pembayaran.prosesBayar(pendaftaran.getTotalBiaya());
        if (bayarOk) {
            pendaftaran.setMetodePembayaran(pembayaran);
            masterPendaftaran.add(pendaftaran);
            System.out.println("\n[SUKSES] Formulir pendaftaran berhasil dikirim!");
            System.out.println("ID Registrasi: " + pendaftaran.getIdPendaftaran());
            System.out.println("Status saat ini: " + pendaftaran.getStatusVerifikasi() + " (Menunggu verifikasi panitia)");
        } else {
            System.out.println("[GAGAL] Data bukti pembayaran tidak lengkap. Pendaftaran dibatalkan.");
        }
    }

    private static boolean apakahRombelSudahDaftar(PerwakilanRombel rombel, CabangLomba lomba) {
        for (Pendaftaran p : masterPendaftaran) {
            if (p.getPendaftar().getId().equals(rombel.getId()) && !p.getStatusVerifikasi().equals("DITOLAK")) {
                for (CabangLomba l : p.getDaftarLomba()) {
                    if (l.getKodeLomba().equals(lomba.getKodeLomba())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void cekStatusPendaftaran(PerwakilanRombel rombel) {
        System.out.println("\n=============================================================");
        System.out.println("            STATUS PENDAFTARAN: " + rombel.getNamaRombel());
        System.out.println("=============================================================");
        boolean ada = false;

        for (Pendaftaran p : masterPendaftaran) {
            if (p.getPendaftar().getId().equals(rombel.getId())) {
                ada = true;
                System.out.println(p.cetakRingkasan());
                System.out.println("-------------------------------------------------------------");
            }
        }

        if (!ada) {
            System.out.println("Belum ada riwayat pendaftaran untuk rombel ini.");
        }
    }

    private static void menuPanitia(Scanner scanner) {
        System.out.println("\n--- LOGIN PANITIA (ADMIN) ---");
        System.out.print("Username (admin): ");
        String u = scanner.nextLine().trim();
        System.out.print("Password (admin123): ");
        String p = scanner.nextLine().trim();

        if (!adminPanitia.login(u, p)) {
            System.out.println("[GAGAL] Autentikasi panitia gagal.");
            return;
        }

        System.out.println("\n[BERHASIL LOGIN] " + adminPanitia.getProfil());
        boolean inMenu = true;

        while (inMenu) {
            System.out.println("\n--- PANEL ADMIN PANITIA ISL 2026 ---");
            System.out.println("1. Verifikasi Pembayaran Peserta Masuk");
            System.out.println("2. Laporan Rekapitulasi Peserta & Pemasukan Kas");
            System.out.println("3. Perbarui Batas Kuota Cabang Lomba");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih opsi (1-4): ");

            String opsi = scanner.nextLine().trim();
            switch (opsi) {
                case "1":
                    verifikasiPembayaran(scanner);
                    break;
                case "2":
                    laporanRekapitulasi();
                    break;
                case "3":
                    updateKuotaLomba(scanner);
                    break;
                case "4":
                    inMenu = false;
                    break;
                default:
                    System.out.println("[PERINGATAN] Opsi tidak valid.");
            }
        }
    }

    private static void verifikasiPembayaran(Scanner scanner) {
        System.out.println("\n--- DAFTAR TRANSAKSI MENUNGGU VERIFIKASI ---");
        List<Pendaftaran> pendingList = new ArrayList<>();

        for (Pendaftaran p : masterPendaftaran) {
            if (p.getStatusVerifikasi().equals("MENUNGGU_VERIFIKASI")) {
                pendingList.add(p);
            }
        }

        if (pendingList.isEmpty()) {
            System.out.println("Tidak ada pendaftaran yang sedang menunggu verifikasi.");
            return;
        }

        for (int i = 0; i < pendingList.size(); i++) {
            Pendaftaran p = pendingList.get(i);
            System.out.println("\n[" + (i + 1) + "] ID: " + p.getIdPendaftaran() + " | Rombel: " + p.getPendaftar().getNamaRombel());
            System.out.println("    Total: Rp" + String.format("%,.0f", p.getTotalBiaya()) + " | Kanal: " + p.getMetodeBayar().getStatusBukti());
        }

        System.out.print("\nPilih nomor transaksi yang ingin diproses (1-" + pendingList.size() + ") atau 0 untuk batal: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (idx >= 0 && idx < pendingList.size()) {
                Pendaftaran selected = pendingList.get(idx);
                System.out.print("Tetapkan status (1. LUNAS / 2. TOLAK): ");
                String aksi = scanner.nextLine().trim();
                if (aksi.equals("1")) {
                    selected.konfirmasiLunas();
                    System.out.println("[BERHASIL] Pendaftaran " + selected.getIdPendaftaran() + " disahkan LUNAS. Kuota lomba otomatis diperbarui.");
                } else if (aksi.equals("2")) {
                    selected.tolakPendaftaran();
                    System.out.println("[DITOLAK] Pendaftaran " + selected.getIdPendaftaran() + " telah ditolak.");
                } else {
                    System.out.println("[BATAL] Opsi tidak valid.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("[PERINGATAN] Input harus berupa angka.");
        }
    }

    private static void laporanRekapitulasi() {
        System.out.println("\n=============================================================");
        System.out.println("         LAPORAN REKAPITULASI TURNAMEN ISL 2026             ");
        System.out.println("=============================================================");

        double totalKasMasuk = 0.0;
        int totalTimLunas = 0;

        System.out.printf("%-6s %-30s %-12s %-10s\n", "Kode", "Nama Cabang Lomba", "Terdaftar", "Sisa Slot");
        System.out.println("-------------------------------------------------------------");
        for (CabangLomba l : masterLomba) {
            System.out.printf("%-6s %-30s %-12d %-10d\n",
                    l.getKodeLomba(), l.getNamaLomba(), l.getTerdaftar(), l.getSisaKuota());
        }

        System.out.println("\n--- DAFTAR TRANSAKSI LUNAS ---");
        for (Pendaftaran p : masterPendaftaran) {
            if (p.getStatusVerifikasi().equals("LUNAS")) {
                totalKasMasuk += p.getTotalBiaya();
                totalTimLunas++;
                System.out.println("- [" + p.getIdPendaftaran() + "] " + p.getPendaftar().getNamaRombel() +
                        " | Total: Rp" + String.format("%,.0f", p.getTotalBiaya()) +
                        " | Metode: " + p.getMetodeBayar().getMetode());
            }
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("Total Tim Terverifikasi Lunas : " + totalTimLunas);
        System.out.println("Total Penerimaan Kas Turnamen : Rp" + String.format("%,.0f", totalKasMasuk));
        System.out.println("=============================================================");
    }

    private static void updateKuotaLomba(Scanner scanner) {
        System.out.println("\n--- PERBARUI KUOTA CABANG LOMBA ---");
        tampilkanKatalogLomba();
        System.out.print("Masukkan nomor lomba yang ingin diubah kuotanya (1-" + masterLomba.size() + "): ");
        try {
            int no = Integer.parseInt(scanner.nextLine().trim());
            if (no >= 1 && no <= masterLomba.size()) {
                CabangLomba lomba = masterLomba.get(no - 1);
                System.out.print("Masukkan batas kuota baru untuk " + lomba.getNamaLomba() + " (saat ini: " + lomba.getKuota() + "): ");
                int kBaru = Integer.parseInt(scanner.nextLine().trim());
                if (kBaru >= lomba.getTerdaftar()) {
                    lomba.setKuota(kBaru);
                    System.out.println("[BERHASIL] Kuota " + lomba.getNamaLomba() + " berhasil diubah menjadi: " + kBaru);
                } else {
                    System.out.println("[GAGAL] Kuota baru tidak boleh lebih kecil dari jumlah tim yang sudah terdaftar (" + lomba.getTerdaftar() + ").");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("[PERINGATAN] Input harus angka valid.");
        }
    }
}
