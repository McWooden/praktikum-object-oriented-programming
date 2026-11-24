package model.lomba;

public class LombaSatuan extends CabangLomba {
    private double tarifDasar;

    public LombaSatuan(String kodeLomba, String namaLomba, int kuota, double tarifDasar) {
        super(kodeLomba, namaLomba, kuota);
        this.tarifDasar = tarifDasar;
    }

    @Override
    public double hitungBiaya() {
        return tarifDasar;
    }

    public double getTarifDasar() {
        return tarifDasar;
    }

    public void setTarifDasar(double tarifDasar) {
        this.tarifDasar = tarifDasar;
    }
}
