package Proxy;

public class Main {
    public static void main(String[] args) {
        // ===== Prueba 1: SIN Proxy (código original) =====
        System.out.println("=== Sin Proxy ===");
        System.out.println("Application started.");
        // La imagen se carga inmediatamente, aunque no la mostremos aún.
        Image imageSinProxy = new RealImage("photo.jpg");
        System.out.println("Image object created.");
        // Nunca se llamó a display(), y aun así la carga ya ocurrió.

        // ===== Prueba 2 y 3: CON Proxy =====
        System.out.println("\n=== Con Proxy ===");
        System.out.println("Application started.");

        // Usando Proxy - la carga no ocurre inmediatamente
        Image image = new ProxyImage("photo.jpg");
        System.out.println("Proxy created, but RealImage not loaded yet.");

        // La carga solo ocurre aquí, cuando realmente necesitamos mostrar la imagen
        image.display();

        // Segunda llamada - ya está cargada, no se vuelve a cargar
        System.out.println("Second call:");
        image.display();
    }
}
