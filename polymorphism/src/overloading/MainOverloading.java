package overloading;

public class MainOverloading {
    public static void main(String[] args) {
        Hewan hewan = new Hewan();

        // Memanggil versi 1 parameter
        hewan.makan("rumput");

        // Memanggil versi 2 parameter (overloaded)
        hewan.makan("rumput", 3);
    }
}