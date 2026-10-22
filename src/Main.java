import javax.swing.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // Punto decimal en los valores mostrados (ej. 0.00)
        Locale.setDefault(Locale.US);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de proyectos con prioridad");
            frame.setContentPane(new Ventana().getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
