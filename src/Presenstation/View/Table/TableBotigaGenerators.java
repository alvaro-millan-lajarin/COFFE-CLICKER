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

public class TableBotigaGenerators extends JPanel {
    private JTable table;
    public TableBotigaGenerators(GameController gameController) {
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Botiga de generadors", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(new Color(139, 69, 16));


        JPanel titlePanel = new JPanel();

        titlePanel.setBackground(new Color(210, 180, 140));
        titlePanel.setPreferredSize(new Dimension(200, 24));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);


        String[] columnNames = {"Nom", "Cost", "Producció", "Increment cost"};
        Object[][] data = {
                {"Cafetera", 10, "0.2 cafes/1s", 1.07},
                {"CafeCheta", 150, "0.5 cafes/0.7s", 1.15},
                {"CafeGod", 2000, "30 cafes/1.3s",1.12}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.table = new JTable(model);
        table.setRowHeight(20);
        table.setOpaque(true);
        table.setBackground(Color.WHITE);


        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 10));
        header.setBackground(new Color(210, 180, 140));
        header.setForeground(Color.BLACK);

        // Ajusto el tamaño de la tabla
        table.setPreferredScrollableViewportSize(new Dimension(250, 80)); // Reducir tamaño

        // Ajusto tamaño de las columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(60); // "Nom"
        table.getColumnModel().getColumn(1).setPreferredWidth(50); // "Cost"
        table.getColumnModel().getColumn(2).setPreferredWidth(140); // "Producció"


        table.getColumn("Nom").setCellRenderer(new ButtonRenderer());


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (column == 0) {
                    String buttonText = table.getValueAt(row, column).toString();


                    ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, buttonText);
                    gameController.actionPerformed(event);
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        setOpaque(false);
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
    public void setUpdateValores(ArrayList<Integer> precioBase, ArrayList<String> produccionUnitat) {

        Object[][] data = {
                {"Cafetera", precioBase.get(0), produccionUnitat.get(0), 1.07},
                {"CafeCheta", precioBase.get(1), produccionUnitat.get(1), 1.15},
                {"CafeGod", precioBase.get(2), produccionUnitat.get(2), 1.12}
        };

        // Update the model with new data
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, new String[]{"Nom", "Cost", "Producció", "Increment cost"});
        model.fireTableDataChanged(); // Refresh the table to show updated data
    }

}

