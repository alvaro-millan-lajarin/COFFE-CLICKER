package Presenstation.View.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableGenerators extends JFrame {
    public TableGenerators() {
        setTitle("Generadores");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Columnas de la tabla
        String[] columnNames = {"Nom", "Cost", "Producció"};
        Object[][] data = {
                {"Cafetera", 10, "0.2 cafes/1s"},
                {"CafeCheta", 150, "0.5 cafes/0.7s"},
                {"CafeGod", 2000, "30 cafes/1.3s"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Solo los botones son editables
            }
        };

        JTable table = new JTable(model);
        table.getColumn("Nom").setCellRenderer(new ButtonRenderer());
        table.getColumn("Nom").setCellEditor(new ButtonEditor(new JTextField()));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Renderizador para mostrar los botones
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isClicked;

        public ButtonEditor(JTextField textField) {
            super(textField);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isClicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isClicked) {
                JOptionPane.showMessageDialog(button, "Seleccionaste: " + label);
            }
            isClicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isClicked = false;
            return super.stopCellEditing();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TableGenerators().setVisible(true));
    }

}
