# palma_macas — Evaluación del Progreso 1

Gestión de proyectos con prioridad (Java Swing, IntelliJ GUI Designer).

Integrantes: Alex Palma, Amelio Macas

## Clases
- `Proyecto`: código, nombre, tipo (Tecnológico/Social/Ambiental), costo estimado y prioridad (1–10). Dos constructores e implementa `Comparable` (mayor prioridad primero).
- `ColaProyectos`: `PriorityQueue<Proyecto>` con 5 proyectos predefinidos, validación de códigos duplicados, costo ajustado y totales por tipo.
- `Ventana` / `Ventana.form`: interfaz gráfica.
- `Main`: abre la ventana.

## Uso
Abrir la carpeta en IntelliJ IDEA y ejecutar `Main`.
- **Agregar**: valida campos vacíos, código numérico y no repetido, costo numérico y positivo.
- **Ajustar presupuesto**: muestra todos los proyectos con su costo ajustado (Tecnológico +15 %, Social −10 %, Ambiental −5 %).
- **Totales**: pide un tipo y muestra en ventana emergente el costo estimado total y el ajustado total.

Los proyectos predefinidos son solo Tecnológicos y Sociales, para poder comprobar que una categoría sin objetos (Ambiental) da 0.00.
