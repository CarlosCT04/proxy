# Ejercicio: Patrón Proxy en Java

## 🎭 Contexto del Problema

Tenemos un servicio `Image` que carga una imagen desde disco, una operación costosa. Queremos postponer esta carga hasta que sea absolutamente necesario (carga perezosa - lazy loading). Además, queremos controlar el acceso al objeto real.

## 🎯 Tu Tarea

Transformar el código usando el **Patrón Proxy** para implementar carga perezosa (lazy loading) y controlar el acceso a los objetos costosos.

## 📋 Instrucciones Paso a Paso

### Paso 1: Crea la Clase Proxy

Crea una clase `ProxyImage` que implemente la interfaz `Image`:

```java
public class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    
    public ProxyImage(String filename) {
        this.filename = filename;
    }
}
```
### Paso 2: Implementa la Lógica de Carga Perezosa

En el método display() del ProxyImage, implementa la carga perezosa:
```java
@Override
public void display() {
    if (realImage == null) {
        realImage = new RealImage(filename); // Carga solo cuando es necesario
    }
    realImage.display(); // Delega al objeto real
}
```
### Paso 3: Modifica el Código Cliente

En la clase Main, cambia la creación de objetos para usar el Proxy en lugar del objeto real directamente:

```java
public class Main {
    public static void main(String[] args) {
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
```
### 🤔 Preguntas de Análisis

* Carga Perezosa: ¿Cuándo se ejecuta el constructor de RealImage en el código original vs en el código con Proxy?
* Transparencia: ¿El código cliente necesita saber si está trabajando con un Proxy o con el objeto real? ¿Por qué?
* Rendimiento: ¿Qué ventajas de rendimiento ofrece el patrón Proxy en este escenario?
* Control de Acceso: Además de la carga perezosa, ¿qué otros tipos de control podríamos implementar en el Proxy? (Ej: verificación de permisos, logging, cache)
* Principio SOLID: ¿Qué principio SOLID se aplica aquí al permitir que el Proxy y el RealImage implementen la misma interfaz?

### 🧪 Pruebas a Realizar

Ejecuta el código para verificar que:

Sin Proxy: El mensaje "Loading image..." aparece inmediatamente al crear el objeto
Con Proxy: El mensaje "Loading image..." aparece solo al llamar a display()
Múltiples llamadas: La carga solo ocurre una vez, incluso con múltiples llamadas a display()

### 💡 Escenarios de Extensión

Considera cómo extenderías este patrón para:

Proxy de Protección: Verificar permisos antes de cargar/mostrar la imagen
Proxy de Cache: Almacenar en cache imágenes ya cargadas
Proxy de Logging: Registrar cada vez que se accede a una imagen
Proxy Remoto: Acceder a imágenes en servidores remotos