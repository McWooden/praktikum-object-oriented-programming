package model.transaksi;

import model.lomba.CabangLomba;
import model.user.PerwakilanRombel;
import payment.Pembayaran;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pendaftaran {
    private String idPendaftaran;
    private LocalDate tanggal;
    private String statusVerifikasi; // MENUNGGU_VERIFIKASI, LUNAS, DITOLAK
    private double totalBiaya;
    private PerwakilanRombel pendaftar;
    private List<CabangLomba> daftarLomba;
    private Pembayaran metodeBayar;

    public Pendaftaran(String idPendaftaran, PerwakilanRombel pendaftar) {
        this.idPendaftaran = idPendaftaran;
        this.tanggal = LocalDate.now();
        this.statusVerifikasi = "MENUNGGU_VERIFIKASI";
        this.pendaftar = pendaftar;
        this.daftarLomba = new ArrayList<>();
        this.totalBiaya = 0.0;
    }

    public void tambahLomba(CabangLomba lomba) {
        this.daftarLomba.add(lomba);
        kalkulasiTotal();
    }

    public double kalkulasiTotal() {
        this.totalBiaya = 0.0;
        for (CabangLomba lomba : daftarLomba) {
            this.totalBiaya += lomba.hitungBiaya();
        }
        return this.totalBiaya;
    }

    public void setMetodePembayaran(Pembayaran metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public void konfirmasiLunas() {
        this.statusVerifikasi = "LUNAS";
        for (CabangLomba lomba : daftarLomba) {
            lomba.kurangiKuota();
        }
    }

    public void tolakPendaftaran() {
        this.statusVerifikasi = "DITOLAK";
    }

    public String getIdPendaftaran() {
        return idPendaftaran;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getStatusVerifikasi() {
        return statusVerifikasi;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public PerwakilanRombel getPendaftar() {
        return pendaftar;
    }

    public List<CabangLomba> getDaftarLomba() {
        return daftarLomba;
    }

    public Pembayaran getMetodeBayar() {
        return metodeBayar;
    }

    public String cetakRingkasan() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Pendaftaran   : ").append(idPendaftaran).append("\n");
        sb.append("Tanggal          : ").append(tanggal).append("\n");
        sb.append("Pendaftar        : ").append(pendaftar.getNamaRombel()).append(" (").append(pendaftar.getNama()).append(")\n");
        sb.append("Cabang Lomba     : \n");
        for (CabangLomba l : daftarLomba) {
            sb.append("  - ").append(l.getNamaLomba()).append(" (Rp").append(String.format("%,.0f", l.hitungBiaya())).append(")\n");
        }
        sb.append("Total Tagihan    : Rp").append(String.format("%,.0f", totalBiaya)).append("\n");
        sb.append("Metode Bayar     : ").append(metodeBayar != null ? metodeBayar.getStatusBukti() : "Belum memilih").append("\n");
        sb.append("Status           : ").append(statusVerifikasi);
        return sb.toString();
    }
}
