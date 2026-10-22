import javax.swing.*;

public class Ventana {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JComboBox cmbTipo;
    private JTextField tsxtCosto;
    private JComboBox cmbPrioridad;
    private JButton agregarButton;
    private JTextArea textArea1;
    private JButton ajustarPresupuestoButton;
    private JButton totalesButton;
    private JPanel Ventana;

    private final ColaProyectos cola = new ColaProyectos();

    public Ventana() {
        textArea1.setEditable(false);

        // Al iniciar el programa se muestran los 5 proyectos predefinidos
        textArea1.setText(cola.mostrarProyectos());

        agregarButton.addActionListener(e -> agregarProyecto());
        ajustarPresupuestoButton.addActionListener(e -> mostrarCostosAjustados());
        totalesButton.addActionListener(e -> mostrarTotalesPorTipo());
    }

    // R1. Ingreso de proyectos con validaciones
    private void agregarProyecto() {
        String codigoTexto = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String costoTexto = tsxtCosto.getText().trim();

        // Validar que todos los campos obligatorios estén completos
        if (codigoTexto.isEmpty() || nombre.isEmpty() || costoTexto.isEmpty()
                || cmbTipo.getSelectedItem() == null || cmbPrioridad.getSelectedItem() == null) {
            mostrarError("Todos los campos son obligatorios.");
            return;
        }

        // Validar que el código sea numérico
        int codigo;
        try {
            codigo = Integer.parseInt(codigoTexto);
        } catch (NumberFormatException ex) {
            mostrarError("El código debe ser un número entero.");
            txtCodigo.requestFocus();
            return;
        }

        if (codigo <= 0) {
            mostrarError("El código debe ser un número mayor a cero.");
            txtCodigo.requestFocus();
            return;
        }

        // Validar que el código no esté repetido
        if (cola.existeCodigo(codigo)) {
            mostrarError("El código " + codigo + " ya existe. No se permiten códigos duplicados.");
            txtCodigo.requestFocus();
            return;
        }

        // Validar que el costo estimado sea un número válido y positivo
        float costo;
        try {
            costo = Float.parseFloat(costoTexto.replace(',', '.'));
        } catch (NumberFormatException ex) {
            mostrarError("El costo estimado debe ser un número válido.");
            tsxtCosto.requestFocus();
            return;
        }

        if (Float.isNaN(costo) || Float.isInfinite(costo)) {
            mostrarError("El costo estimado debe ser un número válido.");
            tsxtCosto.requestFocus();
            return;
        }

        if (costo <= 0) {
            mostrarError("El costo estimado debe ser mayor a cero.");
            tsxtCosto.requestFocus();
            return;
        }

        String tipo = cmbTipo.getSelectedItem().toString();
        int prioridad = Integer.parseInt(cmbPrioridad.getSelectedItem().toString());

        // Incorporar el proyecto a la cola de prioridad
        Proyecto proyecto = new Proyecto(codigo, nombre, tipo, costo, prioridad);
        if (!cola.agregar(proyecto)) {
            mostrarError("No se pudo registrar: el código ya existe.");
            return;
        }

        textArea1.setText(cola.mostrarProyectos());
        limpiarCampos();
        JOptionPane.showMessageDialog(Ventana, "Proyecto registrado correctamente.",
                "Registro", JOptionPane.INFORMATION_MESSAGE);
    }

    // R2. Mostrar todos los proyectos con su costo ajustado
    private void mostrarCostosAjustados() {
        textArea1.setText(cola.mostrarCostosAjustados());
    }

    // R3. Seleccionar un tipo y mostrar los totales en una ventana emergente
    private void mostrarTotalesPorTipo() {
        String[] tipos = {"Tecnológico", "Social", "Ambiental"};
        Object seleccion = JOptionPane.showInputDialog(Ventana,
                "Seleccione el tipo de proyecto:", "Totales por tipo",
                JOptionPane.QUESTION_MESSAGE, null, tipos, cmbTipo.getSelectedItem());

        if (seleccion == null) {
            return; // el usuario canceló
        }

        String tipo = seleccion.toString();
        double totalEstimado = cola.totalEstimadoPorTipo(tipo);
        double totalAjustado = cola.totalAjustadoPorTipo(tipo);

        JOptionPane.showMessageDialog(Ventana,
                "Tipo de proyecto: " + tipo + "\n" +
                "Costo estimado total: " + String.format("%.2f", totalEstimado) + "\n" +
                "Costo ajustado total: " + String.format("%.2f", totalAjustado),
                "Totales por tipo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(Ventana, mensaje, "Error de validación",
                JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        tsxtCosto.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbPrioridad.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }

    public JPanel getPanel() {
        return Ventana;
    }
}
