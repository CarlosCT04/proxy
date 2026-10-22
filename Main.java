package Proxy;

public class Main {
    public static void main(String[] args) {
        System.out.println("Application started.");
        // La imagen se carga inmediatamente, aunque no la mostremos aún.
        Image image = new RealImage("photo.jpg");
        System.out.println("Image object created.");
        // image.display(); // Si lo comentamos, la carga ya ocurrió innecesariamente.
    }
}