package Proxy;

public class ProxyImage implements Image {
    private RealImage realImage; // Objeto costoso: null hasta que se necesite
    private String filename;

    public ProxyImage(String filename) {
        // Solo se guarda el nombre; NO se carga la imagen todavía
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Carga perezosa: solo la primera vez
        }
        realImage.display(); // Delega al objeto real
    }
}
