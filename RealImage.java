package Proxy;

public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk(); // Operación costosa en el constructor
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image (very expensive operation): " + filename);
        // Simulación de carga pesada
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}
