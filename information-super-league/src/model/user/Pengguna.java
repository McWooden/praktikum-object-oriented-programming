package model.user;

public abstract class Pengguna {
    protected String id;
    protected String nama;
    protected String username;
    protected String password;

    public Pengguna(String id, String nama, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public abstract String getProfil();

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }
}
