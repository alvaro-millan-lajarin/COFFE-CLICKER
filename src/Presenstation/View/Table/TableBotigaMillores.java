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

public class TableBotigaMillores extends JPanel {
    private JTable table;
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

        // Update the model with new data
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, new String[]{"Nom", "Coste", "Multiplicador"});
        table.getColumn("Nom").setCellRenderer(new ButtonRenderer());
        model.fireTableDataChanged(); // Refresh the table to show updated data
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(245, 222, 179));
            setBorderPainted(false);
            setFocusPainted(false);
            setFont(new Font("Arial", Font.PLAIN, 10));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
    }
}

