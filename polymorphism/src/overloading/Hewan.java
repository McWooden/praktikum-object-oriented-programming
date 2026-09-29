package overloading;

public class Hewan {

    // Metode makan dengan 1 parameter
    public void makan(String makanan) {
        System.out.println("Hewan makan " + makanan);
    }

    // Overloading metode makan dengan 2 parameter
    public void makan(String makanan, int jumlah) {
        System.out.println("Hewan makan " + jumlah + " porsi " + makanan);
    }
}
