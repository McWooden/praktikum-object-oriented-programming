package model.user;

public class Panitia extends Pengguna {
    private String nipNim;
    private String divisi;

    public Panitia(String id, String nama, String username, String password,
                   String nipNim, String divisi) {
        super(id, nama, username, password);
        this.nipNim = nipNim;
        this.divisi = divisi;
    }

    @Override
    public String getProfil() {
        return "Panitia ISL 2026: " + nama + " (NIM/NIP: " + nipNim + " | Divisi: " + divisi + ")";
    }

    public String getNipNim() {
        return nipNim;
    }

    public String getDivisi() {
        return divisi;
    }
}
