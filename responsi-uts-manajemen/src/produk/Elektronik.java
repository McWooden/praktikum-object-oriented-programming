package produk;

public class Elektronik extends Produk {
    private int garansi;

    public Elektronik(String namaProduk, double harga, int garansi) {
        super(namaProduk, harga);
        this.garansi = garansi;
    }

    // Overload Constructor: membuat produk elektronik tanpa menentukan garansi (default 0 tahun)
    public Elektronik(String namaProduk, double harga) {
        this(namaProduk, harga, 0);
    }

    public int getGaransi() {
        return garansi;
    }

    public void setGaransi(int garansi) {
        this.garansi = garansi;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Garansi: " + garansi + " tahun");
    }

    // Overload Method: menampilkan info dengan catatan/keterangan tambahan
    public void tampilkanInfo(String catatan) {
        tampilkanInfo();
        System.out.println("Catatan: " + catatan);
    }
}

