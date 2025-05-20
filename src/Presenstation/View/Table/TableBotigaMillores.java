package Presenstation.View.Table;
import Presenstation.Controller.GameController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Panel que muestra una tabla con las mejoras disponibles para los generadores.
 * Permite al usuario activar mejoras mediante botones en la columna de nombre.
 */
public class TableBotigaMillores extends JPanel {
    private JTable table;

    /**
     * Constructor del panel de tabla de mejoras.
     *
     * @param gameController Controlador del juego que gestiona las acciones al pulsar en los botones de mejora.
     */
    public TableBotigaMillores(GameController gameController) {
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Botiga de millores", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(new Color(139, 69, 16));


        JPanel titlePanel = new JPanel();

        titlePanel.setBackground(new Color(210, 180, 140));
        titlePanel.setPreferredSize(new Dimension(200, 24));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);


        String[] columnNames = {"Nom", "Coste", "Multiplicador"};
        Object[][] data = {
                {"Cafetera", 10, "x2"},
                {"CafeCheta", 150, "x2"},
                {"CafeGod", 2000, "x2"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(20); // Filas más pequeñas
        table.setOpaque(true);
        table.setBackground(Color.WHITE);


        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 10));
        header.setBackground(new Color(210, 180, 140));
        header.setForeground(Color.BLACK);


        table.setPreferredScrollableViewportSize(new Dimension(250, 80));


        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);


        table.getColumn("Nom").setCellRenderer(new ButtonRenderer());


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (column == 0) {
                    String buttonText = table.getValueAt(row, column).toString();
                    ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, buttonText+"Mejora");
                    gameController.actionPerformed(event);
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        setOpaque(false);
    }

    /**
     * Actualiza los valores de la tabla de mejoras.
     *
     * @param costMultiplicadores Lista con los costes de mejora actualizados para cada tipo de generador.
     * @param multiplicadores     Lista con los multiplicadores actuales, se incrementan visualmente en la tabla.
     */
    public void setUpdateValores(ArrayList<Integer> costMultiplicadores, ArrayList<Integer> multiplicadores) {
        ArrayList<Integer> multiplicadoress = new ArrayList<>();
        multiplicadoress.add(multiplicadores.get(0)+1);
        multiplicadoress.add(multiplicadores.get(1)+1);
        multiplicadoress.add(multiplicadores.get(2)+1);


        Object[][] data = {
                {"Cafetera", costMultiplicadores.get(0), "x"+multiplicadoress.get(0)},
                {"CafeCheta", costMultiplicadores.get(1), "x"+multiplicadoress.get(1)},
                {"CafeGod", costMultiplicadores.get(2), "x"+multiplicadoress.get(2)},
        };


        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, new String[]{"Nom", "Coste", "Multiplicador"});
        table.getColumn("Nom").setCellRenderer(new ButtonRenderer());
        model.fireTableDataChanged();
    }

    /**
     * Clase interna que actúa como renderizador para mostrar botones en las celdas de la columna "Nom".
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {
        /**
         * Contructor clase interna
         */
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(245, 222, 179));
            setBorderPainted(false);
            setFocusPainted(false);
            setFont(new Font("Arial", Font.PLAIN, 10));
        }

        /**
         * Personaliza la celda de la tabla para mostrarse como un botón.
         *
         * @param table      Tabla a la que pertenece la celda.
         * @param value      Valor que se mostrará en la celda.
         * @param isSelected Si la celda está seleccionada.
         * @param hasFocus   Si la celda tiene foco.
         * @param row        Fila de la celda.
         * @param column     Columna de la celda.
         * @return Componente que se renderizará (el botón).
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
    }
}

