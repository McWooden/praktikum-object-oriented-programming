package tugas4;

public class Pekerja extends Manusia {
    private double gaji;

    // Constructor
    public Pekerja(String nama, int usia, String pekerjaan, double gaji) {
        super(nama, usia, pekerjaan);
        this.gaji = gaji;
    }

    // Getter dan Setter untuk gaji
    public double getGaji() {
        return gaji;
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    // Override toString() untuk format tampilan rapi
    @Override
    public String toString() {
        return "Data Pekerja:\n" +
                "- Nama      : " + getNama() + "\n" +
                "- Usia      : " + usia + " tahun\n" +
                "- Pekerjaan : " + pekerjaan + "\n" +
                "- Gaji      : Rp " + String.format("%,.2f", gaji);
    }
}