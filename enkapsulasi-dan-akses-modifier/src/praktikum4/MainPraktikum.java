package praktikum4;

public class MainPraktikum {
    public static void main(String[] args) {
        // Ganti Civic jadi Porsche 911 GT3 RS (top speed ~296 km/h, mesin 4.0L Boxer, 2 pintu)
        Mobil mobilSaya = new Mobil("Porsche 911 GT3 RS", 296, "4.0L Naturally Aspirated Boxer-6", 2);

        System.out.println("=== INFO KENDARAAN ===");
        mobilSaya.tampilkanInfoKendaraan();

        System.out.println("\n=== INFO KHUSUS MOBIL ===");
        mobilSaya.tampilkanInfoMobil();
    }
}