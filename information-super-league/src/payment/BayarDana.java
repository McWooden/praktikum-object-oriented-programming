package payment;

public class BayarDana implements Pembayaran {
    private String nomorHpDana;
    private String noTransaksi;
    private boolean terverifikasi;

    public BayarDana(String nomorHpDana, String noTransaksi) {
        this.nomorHpDana = nomorHpDana;
        this.noTransaksi = noTransaksi;
        this.terverifikasi = false;
    }

    @Override
    public boolean prosesBayar(double nominal) {
        if (noTransaksi != null && !noTransaksi.isBlank() && nominal > 0) {
            this.terverifikasi = true;
            return true;
        }
        return false;
    }

    @Override
    public String getStatusBukti() {
        return "DANA (Nomor: " + nomorHpDana + " | Ref: " + noTransaksi + ")";
    }

    @Override
    public String getMetode() {
        return "Transfer DANA";
    }

    public String getNomorHpDana() {
        return nomorHpDana;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }
}
