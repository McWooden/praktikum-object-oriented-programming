package pegawai;

public class PegawaiTetap extends Pegawai {
    private double tunjangan;

    public PegawaiTetap(String namaPegawai, double gaji, double tunjangan) {
        super(namaPegawai, gaji);
        this.tunjangan = tunjangan;
    }

    public double getTunjangan() {
        return tunjangan;
    }

    public void setTunjangan(double tunjangan) {
        this.tunjangan = tunjangan;
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        if (tunjangan % 1 == 0) {
            System.out.println("Tunjangan: " + (long) tunjangan);
        } else {
            System.out.println("Tunjangan: " + tunjangan);
        }
    }
}
