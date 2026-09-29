import produk.*;
import pegawai.*;

public class Main {
    public static void main(String[] args) {
        // 1. Output Produk
        System.out.println("1. Output Produk");
        Produk produk1 = new Elektronik("Laptop", 15000000, 2);
        produk1.tampilkanInfo();

        System.out.println();

        // 2. Output Pegawai
        System.out.println("2. Output CEO");
        Pegawai pegawai1 = new PegawaiTetap("Sholahuddin Ahmad", 5000000, 1000000);
        pegawai1.tampilkanInfo();

        System.out.println();

        // 3. Output Polimorfisme
        System.out.println("3. Output Polimorfisme");
        Produk produk2 = new Makanan("Snack", 15000, "2023-12-30");
        produk2.tampilkanInfo();

        System.out.println();

        Pegawai pegawai2 = new PegawaiKontrak("Andi", 3000000, 12);
        pegawai2.tampilkanInfo();
    }
}