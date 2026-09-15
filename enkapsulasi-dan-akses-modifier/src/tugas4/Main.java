package tugas4;

public class Main {
    public static void main(String[] args) {
        // 1. Inisialisasi Objek Pekerja
        Pekerja pekerja1 = new Pekerja("Ahmad", 21, "Web Developer", 8500000);

        // 2. Tampilkan Info Awal dengan toString()
        System.out.println("=== DATA AWAL ===");
        System.out.println(pekerja1.toString());

        // 3. Ubah Nama Menggunakan Setter
        pekerja1.setNama("Sholahuddin Ahmad");
        System.out.println("\n=== SETELAH UBAH NAMA (SETTER) ===");
        System.out.println(pekerja1.toString());

        // 4. Eksperimen Akses Langsung Atribut
        System.out.println("\n=== PERCOBAAN AKSES LANGSUNG ===");

        // [SUKSES] public bisa diakses langsung
        System.out.println("Akses pekerjaan (public) : " + pekerja1.pekerjaan);

        // [SUKSES] protected bisa diakses karena masih 1 package (praktikum4)
        System.out.println("Akses usia (protected)   : " + pekerja1.usia);

        // [ERROR KALAU DI-UNCOMMENT]
         pekerja1.nama = "Udin"; // ERROR: nama has private access in praktikum4.Manusia
         System.out.println(pekerja1.gaji); // ERROR: gaji has private access in praktikum4.Pekerja
    }
}