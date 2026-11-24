package model.user;

public class PerwakilanRombel extends Pengguna {
    private String namaRombel;
    private String programStudi;
    private String nomorKontak;

    public PerwakilanRombel(String id, String nama, String username, String password,
                             String namaRombel, String programStudi, String nomorKontak) {
        super(id, nama, username, password);
        this.namaRombel = namaRombel;
        this.programStudi = programStudi;
        this.nomorKontak = nomorKontak;
    }

    @Override
    public String getProfil() {
        return "Perwakilan Rombel: " + namaRombel + " (" + nama + " | " + programStudi + " | HP: " + nomorKontak + ")";
    }

    public String getNamaRombel() {
        return namaRombel;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public String getNomorKontak() {
        return nomorKontak;
    }
}
