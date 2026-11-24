package payment;

public class BayarTunai implements Pembayaran {
    private String namaPanitiaPenerima;
    private String noKuitansi;
    private boolean terverifikasi;

    public BayarTunai(String namaPanitiaPenerima, String noKuitansi) {
        this.namaPanitiaPenerima = namaPanitiaPenerima;
        this.noKuitansi = noKuitansi;
        this.terverifikasi = false;
    }

    @Override
    public boolean prosesBayar(double nominal) {
        if (noKuitansi != null && !noKuitansi.isBlank() && nominal > 0) {
            this.terverifikasi = true;
            return true;
        }
        return false;
    }

    @Override
    public String getStatusBukti() {
        return "Tunai Panitia: " + namaPanitiaPenerima + " (Kuitansi: " + noKuitansi + ")";
    }

    @Override
    public String getMetode() {
        return "Tunai / Cash";
    }

    public String getNamaPanitiaPenerima() {
        return namaPanitiaPenerima;
    }

    public String getNoKuitansi() {
        return noKuitansi;
    }
}
