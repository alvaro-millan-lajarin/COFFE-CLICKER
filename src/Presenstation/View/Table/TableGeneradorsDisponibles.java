package Presenstation.View.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TableGeneradorsDisponibles extends JPanel {
    private ArrayList<String> quantitats;
    private ArrayList<String> produccioUnitat;
    private ArrayList<String> produccioTotal;
    private ArrayList<String> produccioGlobal;

    public TableGeneradorsDisponibles() {
        inicializarValores();

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
                {"Cafetera", quantitats.get(0), produccioUnitat.get(0), produccioTotal.get(0), produccioGlobal.get(0)},
                {"CafeCheta", quantitats.get(1), produccioUnitat.get(1), produccioTotal.get(1), produccioGlobal.get(1)},
                {"CafeGod", quantitats.get(2), produccioUnitat.get(2), produccioTotal.get(2), produccioGlobal.get(2)},
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
    public void inicializarValores() {
        quantitats = new ArrayList<>();
        produccioUnitat = new ArrayList<>();
        produccioTotal = new ArrayList<>();
        produccioGlobal = new ArrayList<>();
        quantitats.add("0");
        quantitats.add("0");
        quantitats.add("0");

        produccioUnitat.add("0.2 c/1s");
        produccioUnitat.add("0.5 c/0.7s");
        produccioUnitat.add("30 c/1.3s");

        produccioTotal.add("24.6 c/s");
        produccioTotal.add("30.0 c/3s");
        produccioTotal.add("92.3 c/s");

        produccioGlobal.add("30.0 c/3s");
        produccioGlobal.add("92.3 c/3s");
        produccioGlobal.add("30.0 c/3s");
    }

    public void setValores(ArrayList<Integer> quantitatsInput, ArrayList<String> produccioUnitatInput) {
        this.quantitats = new ArrayList<>();
        this.produccioUnitat = new ArrayList<>(produccioUnitatInput);
        this.produccioTotal = new ArrayList<>();
        this.produccioGlobal = new ArrayList<>();

        ArrayList<Double> produccionsTotals = new ArrayList<>();
        double sumaTotal = 0.0;


        for (int i = 0; i < quantitatsInput.size(); i++) {
            int quantitat = quantitatsInput.get(i);
            String prodUnit = produccioUnitatInput.get(i);


            String[] parts = prodUnit.split(" ");
            double cicles = Double.parseDouble(parts[0]);
            String temps = parts[1].replace("s", "").replace("/", ""); // por si hay espacios o formato raro
            double segons = Double.parseDouble(temps);


            double produccioPerUnitat = cicles / segons;


            double total = produccioPerUnitat * quantitat;
            produccionsTotals.add(total);
            sumaTotal += total;


            this.quantitats.add(String.valueOf(quantitat));
            this.produccioTotal.add(String.format("%.2f c/s", total));
        }


        for (double total : produccionsTotals) {
            double percentatge = (total / sumaTotal) * 100;
            this.produccioGlobal.add(String.format("%.1f %%", percentatge));
        }
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

