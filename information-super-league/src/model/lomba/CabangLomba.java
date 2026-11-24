package model.lomba;

public abstract class CabangLomba {
    protected String kodeLomba;
    protected String namaLomba;
    protected int kuota;
    protected int terdaftar;

    public CabangLomba(String kodeLomba, String namaLomba, int kuota) {
        this.kodeLomba = kodeLomba;
        this.namaLomba = namaLomba;
        this.kuota = kuota;
        this.terdaftar = 0;
    }

    public abstract double hitungBiaya();

    public boolean kurangiKuota() {
        if (terdaftar < kuota) {
            terdaftar++;
            return true;
        }
        return false;
    }

    public int getSisaKuota() {
        return kuota - terdaftar;
    }

    public String getKodeLomba() {
        return kodeLomba;
    }

    public String getNamaLomba() {
        return namaLomba;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    public int getTerdaftar() {
        return terdaftar;
    }
}
