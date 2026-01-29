package ljApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ljBusinessLogic.ljExoBot;
import ljBusinessLogic.ljExoMedico;
import ljDataAccess.ljExoBotDAO;

/**
 * Interfaz Gráfica de Usuario para ExoTrooper.
 * Implementación basada en Swing.
 */
public class ljExoTrooperGUI extends JFrame {
    private JTable tablaExobots;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> comboTipoExobot;
    private List<ljExoBot> listaExobots;
    private int contadorId = 1;

    public ljExoTrooperGUI() {
        ljExoBotDAO.inicializarBD(); // Ensure DB exists
        listaExobots = ljExoBotDAO.loadAll(); // Load persistent data
        if (listaExobots == null)
            listaExobots = new ArrayList<>();

        // Update ID counter based on loaded data
        if (!listaExobots.isEmpty()) {
            // Simple max ID finding
            int maxId = 0;
            for (ljExoBot b : listaExobots) {
                if (b.getIdExobot() > maxId)
                    maxId = b.getIdExobot();
            }
            contadorId = maxId + 1;
        }

        configurarVentana();
        inicializarComponentes();

        // Initial table load
        // Note: inicializarComponentes creates table model which is empty, so we need
        // to refresh it.
        // But components are not created yet in this order. Let's rely on standard
        // flow.
    }

    private void configurarVentana() {
        setTitle("ExoTrooper - Lopez John");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        // --- Panel Superior: Alumno ---
        JPanel panelAlumno = new JPanel();
        panelAlumno.setLayout(new BoxLayout(panelAlumno, BoxLayout.Y_AXIS));
        panelAlumno.setBorder(BorderFactory.createTitledBorder("Alumno(s):"));

        JLabel lblDatos = new JLabel("1751330158 | LOPEZ JOHN");
        lblDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAlumno.add(lblDatos);

        add(panelAlumno, BorderLayout.NORTH);

        // --- Panel Central: Controles y Tabla ---
        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Controles de Agregación
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] tipos = { "ExoMedico", "ExoAsalto", "ExoExplorador", "ExoInfanteria", "ExoComando" };
        comboTipoExobot = new JComboBox<>(tipos);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> agregarExobot());

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarExobot());

        panelControles.add(new JLabel("TipoExobot:"));
        panelControles.add(comboTipoExobot);
        panelControles.add(btnAgregar);
        panelControles.add(btnBuscar);

        panelCentral.add(panelControles, BorderLayout.NORTH);

        // Tabla
        String[] columnas = { "IdExobot", "TipoExobot", "Entreno", "No. Accion" };
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaExobots = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaExobots);

        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // Populate table with loaded data
        actualizarTabla(null);

        // --- Panel Inferior: Botones de Acción ---
        JPanel panelAcciones = new JPanel(new FlowLayout());

        JButton btnEntrenar = new JButton("Entrenar \"AcciónArma\"");
        btnEntrenar.addActionListener(e -> entrenarExobotSeleccionado());

        JButton btnAccion = new JButton("AcciónArma");
        btnAccion.addActionListener(e -> accionarExobotSeleccionado());

        panelAcciones.add(btnEntrenar);
        panelAcciones.add(btnAccion);

        add(panelAcciones, BorderLayout.SOUTH);
    }

    /**
     * Agrega un nuevo Exobot a la lista y tabla.
     */
    private void agregarExobot() {
        String tipoSeleccionado = (String) comboTipoExobot.getSelectedItem();

        ljExoBot nuevoBot;
        // Solo instanciamos Logic real para ExoMedico por ahora,
        // o genérico para los demás si no hay clases específicas.
        if ("ExoMedico".equals(tipoSeleccionado)) {
            nuevoBot = new ljExoMedico();
        } else {
            // Placeholder para otros tipos si no se implementan clases específicas
            // Usamos ExoMedico como base genérica o idealmente tendríamos una Factory
            nuevoBot = new ljExoMedico();
            nuevoBot.setTipoExobot(tipoSeleccionado);
        }

        nuevoBot.setIdExobot(contadorId++);
        listaExobots.add(nuevoBot);
        ljExoBotDAO.saveAll(listaExobots); // Save on Add
        actualizarTabla(null);
    }

    /**
     * Filtra la tabla por el tipo de Exobot seleccionado.
     */
    private void buscarExobot() {
        String tipoSeleccionado = (String) comboTipoExobot.getSelectedItem();
        actualizarTabla(tipoSeleccionado);
    }

    /**
     * Ejecuta el entrenamiento del bot seleccionado.
     */
    private void entrenarExobotSeleccionado() {
        int fila = tablaExobots.getSelectedRow();
        if (fila >= 0) {
            int botId = (int) modeloTabla.getValueAt(fila, 0);
            ljExoBot bot = buscarBotPorId(botId);

            if (bot != null) {
                // Datos quemados o simulados según requerimiento:
                // "SoldadoExperto" y Acciones. Para ExoMedico es "asistir".
                bot.entrenar("SoldadoX", bot.getArmaPrincipal(), bot.getAccionPrincipal());
                ljExoBotDAO.saveAll(listaExobots); // Save on Train
                actualizarTabla(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Exobot.");
        }
    }

    /**
     * Ejecuta la acción del bot seleccionado.
     */
    private void accionarExobotSeleccionado() {
        int fila = tablaExobots.getSelectedRow();
        if (fila >= 0) {
            int botId = (int) modeloTabla.getValueAt(fila, 0);
            ljExoBot bot = buscarBotPorId(botId);

            if (bot != null) {
                bot.usarArma(bot.getArmaPrincipal(), bot.getAccionPrincipal());
                ljExoBotDAO.saveAll(listaExobots); // Save on Action (Update Counter)
                actualizarTabla(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Exobot.");
        }
    }

    /**
     * Helper para encontrar bot por ID en la lista completa.
     */
    private ljExoBot buscarBotPorId(int id) {
        for (ljExoBot bot : listaExobots) {
            if (bot.getIdExobot() == id) {
                return bot;
            }
        }
        return null;
    }

    /**
     * Refresca la tabla con los datos de la lista.
     * 
     * @param filtroTipo Tipo de Exobot a mostrar. Si es null o vacio, muestra
     *                   todos.
     */
    private void actualizarTabla(String filtroTipo) {
        modeloTabla.setRowCount(0);
        for (ljExoBot bot : listaExobots) {
            if (filtroTipo == null || filtroTipo.isEmpty() || bot.getTipoExobot().equalsIgnoreCase(filtroTipo)) {
                Object[] fila = {
                        bot.getIdExobot(),
                        bot.getTipoExobot(),
                        bot.getEntreno(),
                        bot.getNumeroAccion()
                };
                modeloTabla.addRow(fila);
            }
        }
    }
}
