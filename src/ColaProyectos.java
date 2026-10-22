import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Administra una cola de prioridad de proyectos.
 * 10 es la prioridad más alta y 1 la más baja.
 */
public class ColaProyectos {

    private PriorityQueue<Proyecto> colaProyectos;

    public ColaProyectos() {
        colaProyectos = new PriorityQueue<>();
        cargarPredefinidos();
    }

    // 5 objetos predefinidos que se muestran al ejecutar el programa
    private void cargarPredefinidos() {
        // Se dejan sin proyectos "Ambiental" para poder comprobar que una categoría vacía da 0.00
        colaProyectos.add(new Proyecto(101, "Plataforma de telemedicina", "Tecnológico", 25000.00f, 9));
        colaProyectos.add(new Proyecto(102, "Comedor comunitario", "Social", 8000.00f, 7));
        colaProyectos.add(new Proyecto(103, "Sistema de inventario", "Tecnológico", 15000.00f, 5));
        colaProyectos.add(new Proyecto(104, "Alfabetización de adultos", "Social", 6000.00f, 3));
        colaProyectos.add(new Proyecto(105, "Red de sensores IoT", "Tecnológico", 12000.00f, 10));
    }

    // Verifica si un código ya está registrado en la cola
    public boolean existeCodigo(int codigo) {
        for (Proyecto p : colaProyectos) {
            if (p.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    // Agrega un proyecto; devuelve false si el código está repetido
    public boolean agregar(Proyecto proyecto) {
        if (proyecto == null || existeCodigo(proyecto.getCodigo())) {
            return false;
        }
        colaProyectos.add(proyecto);
        return true;
    }

    // Transforma la cola a un ArrayList y lo ordena (de mayor a menor prioridad)
    public List<Proyecto> listarOrdenado() {
        List<Proyecto> lista = new ArrayList<>(colaProyectos);
        Collections.sort(lista);
        return lista;
    }

    // R1: muestra todos los proyectos ordenados por prioridad
    public String mostrarProyectos() {
        List<Proyecto> lista = new ArrayList<>(colaProyectos);
        Collections.sort(lista);

        StringBuilder sb = new StringBuilder();
        sb.append("PROYECTOS ORDENADOS DE MAYOR A MENOR PRIORIDAD\n\n");
        for (Proyecto p : lista) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }

    // R2: muestra todos los proyectos con sus datos y al final el costo ajustado
    public String mostrarCostosAjustados() {
        List<Proyecto> lista = new ArrayList<>(colaProyectos);
        Collections.sort(lista);

        StringBuilder sb = new StringBuilder();
        sb.append("PROYECTOS CON COSTO AJUSTADO\n");
        sb.append("(Tecnológico +15% | Social -10% | Ambiental -5%)\n\n");
        for (Proyecto p : lista) {
            sb.append(p)
              .append(" | Costo ajustado: ")
              .append(String.format("%.2f", p.calcularCostoAjustado()))
              .append("\n");
        }
        return sb.toString();
    }

    // R3: costo estimado total de los proyectos de un tipo
    public double totalEstimadoPorTipo(String tipo) {
        double total = 0;
        for (Proyecto p : colaProyectos) {
            if (p.getTipo().equals(tipo)) {
                total += p.getCostoEstimado();
            }
        }
        return total;
    }

    // R3: costo ajustado total de los proyectos de un tipo
    public double totalAjustadoPorTipo(String tipo) {
        double total = 0;
        for (Proyecto p : colaProyectos) {
            if (p.getTipo().equals(tipo)) {
                total += p.calcularCostoAjustado();
            }
        }
        return total;
    }

    public PriorityQueue<Proyecto> getColaProyectos() {
        return colaProyectos;
    }
}
