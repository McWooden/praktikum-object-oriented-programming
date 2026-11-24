package model.lomba;

import java.util.List;

public class PaketBundling extends CabangLomba {
    private double tarifPaket;
    private List<String> daftarLombaTermasuk;

    public PaketBundling(String kodeLomba, String namaLomba, int kuota, double tarifPaket, List<String> daftarLombaTermasuk) {
        super(kodeLomba, namaLomba, kuota);
        this.tarifPaket = tarifPaket;
        this.daftarLombaTermasuk = daftarLombaTermasuk;
    }

    @Override
    public double hitungBiaya() {
        return tarifPaket;
    }

    public double getTarifPaket() {
        return tarifPaket;
    }

    public List<String> getDaftarLombaTermasuk() {
        return daftarLombaTermasuk;
    }
}
