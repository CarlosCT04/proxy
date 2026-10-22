# Respuestas – Patrón Proxy en Java

## Implementación
- `ProxyImage` implementa `Image` (la misma interfaz que `RealImage`), guarda solo el `filename` y crea el `RealImage` dentro de `display()` la primera vez que se necesita (carga perezosa).
- `Main` ahora usa `new ProxyImage("photo.jpg")` en lugar de `new RealImage("photo.jpg")`.

## Pruebas realizadas
1. **Sin Proxy:** "Loading image (very expensive operation)" aparece de inmediato al hacer `new RealImage(...)`, aunque nunca se llame a `display()`.
2. **Con Proxy:** al crear el proxy solo aparece "Proxy created, but RealImage not loaded yet." El mensaje de carga aparece hasta la primera llamada a `display()`.
3. **Múltiples llamadas:** la carga ocurre una sola vez; en la segunda llamada solo se imprime "Displaying image".

## Preguntas de análisis

**1. Carga perezosa: ¿cuándo se ejecuta el constructor de `RealImage`?**
- En el código original: en el momento de crear el objeto (`new RealImage("photo.jpg")`), antes de saber si se va a mostrar.
- Con Proxy: dentro del primer `display()` del proxy, solo si `realImage == null`. Si nunca se llama a `display()`, nunca se construye.

**2. Transparencia: ¿el cliente necesita saber si usa un Proxy o el objeto real?**
No. El cliente trabaja con la interfaz `Image` (`Image image = ...`) y solo llama a `display()`. Como `ProxyImage` y `RealImage` implementan la misma interfaz, son intercambiables y el cliente no cambia al sustituir uno por otro.

**3. Rendimiento: ¿qué ventajas ofrece el Proxy aquí?**
- Evita la carga costosa (1 segundo simulado de lectura de disco) si la imagen nunca se muestra.
- Mejora el tiempo de arranque de la aplicación, porque la carga se posterga.
- Evita cargar la imagen varias veces: se carga una sola vez y se reutiliza.
- Ahorra memoria cuando hay muchas imágenes y solo se usan algunas.

**4. Control de acceso: ¿qué otros controles se podrían implementar en el Proxy?**
- **Proxy de protección:** verificar permisos o rol del usuario antes de cargar o mostrar.
- **Proxy de caché:** guardar las imágenes ya cargadas (por ejemplo, en un `Map<String, RealImage>`) y reutilizarlas.
- **Proxy de logging:** registrar cada acceso (quién, cuándo, qué archivo).
- **Proxy remoto:** representar localmente una imagen que está en otro servidor.
- También: contar accesos, limitar la frecuencia de uso, o validar el nombre del archivo.

**5. Principio SOLID**
- **Sustitución de Liskov (LSP):** `ProxyImage` puede sustituir a `RealImage` sin alterar el comportamiento esperado por el cliente.
- **Inversión de Dependencias (DIP):** el cliente depende de la abstracción `Image`, no de una clase concreta.
- También se cumple **Abierto/Cerrado (OCP)**: se agrega comportamiento (lazy loading, permisos, caché) sin modificar `RealImage` ni el cliente, y **Responsabilidad Única (SRP)**: `RealImage` solo carga/muestra y el Proxy se encarga de controlar el acceso.

## Escenarios de extensión (cómo se harían)
- **Proxy de protección:** agregar un campo `usuario`/`rol` y, en `display()`, `if (!tienePermiso()) { System.out.println("Acceso denegado"); return; }` antes de crear o mostrar el `RealImage`.
- **Proxy de caché:** un `static Map<String, RealImage> cache`; en `display()` buscar `filename` en el mapa y solo crear el `RealImage` si no está.
- **Proxy de logging:** imprimir (o guardar en un archivo) un registro con fecha y nombre de archivo en cada llamada a `display()`, y luego delegar al objeto real.
- **Proxy remoto:** `display()` pediría la imagen al servidor (HTTP/sockets) y la mostraría; el cliente seguiría usando `Image` sin saber que es remota.
