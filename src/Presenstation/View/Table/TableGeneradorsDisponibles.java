package Presenstation.View.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableGeneradorsDisponibles extends JPanel {
    public TableGeneradorsDisponibles() {
        setLayout(new BorderLayout());


        JLabel title = new JLabel("Generadors disponibles", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(new Color(139, 69, 16));


        JPanel titlePanel = new JPanel();

        titlePanel.setBackground(new Color(210, 180, 140));
        titlePanel.setPreferredSize(new Dimension(200, 24));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);


        String[] columnNames = {"Nom", "Quantitat", "Produccio unitat", "% Produccio total", "Produccio global"};
        Object[][] data = {
                {"A", 123, "0.2 c/1s", "24.6 c/s", "16.7 %"},
                {"B", 42, "0.5 c/0.7s ", "30.0 c/s", "20.4 %"},
                {"C", 4, "30 c/1.3s", "92.3 c/s", "62.9 %"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(20);
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
                    JOptionPane.showMessageDialog(null, "Seleccionaste: " + buttonText);
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
}

