package payment;

public interface Pembayaran {
    boolean prosesBayar(double nominal);
    String getStatusBukti();
    String getMetode();
}
