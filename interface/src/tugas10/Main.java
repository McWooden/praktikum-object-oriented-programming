package tugas10;

public class Main {
    public static void main(String[] args) {
        Pembayaran laptop = new Elektronik();
        Pembayaran mieAyam = new Makanan();

        double hargaLaptop = 5000000;
        double hargaMieAyam = 20000;

        System.out.println("Pajak Elektronik (10%): Rp" + laptop.hitungPajak(hargaLaptop));
        System.out.println("Pajak Makanan (5%): Rp" + mieAyam.hitungPajak(hargaMieAyam));
    }
}