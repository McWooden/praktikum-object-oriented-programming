package tugas10;

// Kelas Elektronik dengan pajak 10%
public class Elektronik implements Pembayaran {
    @Override
    public double hitungPajak(double harga) {
        return harga * 0.10;
    }
}
