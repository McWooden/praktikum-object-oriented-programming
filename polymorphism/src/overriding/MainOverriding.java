package overriding;

public class MainOverriding {
    public static void main(String[] args) {
        // Objek bertipe Hewan, tetapi diisi instance Kucing dan Sapi
        Hewan hewan1 = new Kucing();
        Hewan hewan2 = new Sapi();

        // Output ditentukan saat runtime berdasarkan objek nyatanya
        hewan1.bersuara(); // Menjalankan method milik Kucing
        hewan2.bersuara(); // Menjalankan method milik Sapi
    }
}
