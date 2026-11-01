package hewan;

// Kelas abstrak Hewan
abstract class Hewan {
    abstract void suara();
}

// Kelas turunan Kucing
class Kucing extends Hewan {
    @Override
    void suara() {
        System.out.println("Kucing mengeluarkan suara: Meow");
    }
}

// Kelas turunan Anjing
class Anjing extends Hewan {
    @Override
    void suara() {
        System.out.println("Anjing mengeluarkan suara: Rorr");
    }
}