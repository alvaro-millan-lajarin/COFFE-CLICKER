package Presenstation.View.Table;

import Presenstation.Controller.GameController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase que representa una tabla de resumen con los generadores disponibles.
 * Muestra la cantidad, producción por unidad, producción global y el porcentaje de producción total.
 */
public class TableGeneradorsDisponibles extends JPanel {
    private ArrayList<String> quantitats;
    private ArrayList<String> produccioUnitat;
    private ArrayList<String> produccioTotal;
    private ArrayList<String> produccioGlobal;
    private JTable table;

    /**
     * Constructor que inicializa la tabla con valores por defecto y asigna el controlador.
     *
     * @param gameController Controlador del juego que maneja eventos sobre esta tabla.
     */
    public TableGeneradorsDisponibles(GameController gameController) {
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

        this.table = new JTable(model);
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

    /**
     * Inicializa los valores por defecto de todas las listas asociadas a la tabla.
     * Establece todo en "0" para evitar valores nulos al renderizar.
     */
    public void inicializarValores() {
        quantitats = new ArrayList<>();
        produccioUnitat = new ArrayList<>();
        produccioTotal = new ArrayList<>();
        produccioGlobal = new ArrayList<>();
        quantitats.add("0");
        quantitats.add("0");
        quantitats.add("0");

        produccioUnitat.add("0");
        produccioUnitat.add("0");
        produccioUnitat.add("0");

        produccioTotal.add("0");
        produccioTotal.add("0");
        produccioTotal.add("0");

        produccioGlobal.add("0");
        produccioGlobal.add("0");
        produccioGlobal.add("0");
    }

    /**
     * Actualiza todos los valores de la tabla (cantidad, producción unitaria, global y % total).
     * Realiza los cálculos internos basándose en las unidades y los multiplicadores actuales.
     *
     * @param quantitatsInput        Lista con las cantidades de cada generador.
     * @param proudccioUnitatInput   Lista con las producciones unitarias en texto.
     * @param multiplicadoresInput   Lista con los multiplicadores de cada generador (no usados directamente).
     */
    public void setUpdateValores(ArrayList<Integer> quantitatsInput, ArrayList<String> proudccioUnitatInput, ArrayList<Integer> multiplicadoresInput) {



        // Actualizar cantidades
        quantitats.clear();
        quantitats.add(String.valueOf(quantitatsInput.get(0)));
        quantitats.add(String.valueOf(quantitatsInput.get(1)));
        quantitats.add(String.valueOf(quantitatsInput.get(2)));

        ArrayList<Double> produccioUnitat = new ArrayList<>();
        ArrayList<Double> produccioGlobal = new ArrayList<>();
        ArrayList<Double> produccioTotal = new ArrayList<>();

        double sumaTotal = 0.0;

        // Parsear producció per unitat
        for (int i = 0; i < proudccioUnitatInput.size(); i++) {
            String input = proudccioUnitatInput.get(i);

            try {
                String[] parts = input.split("cafès /");
                if (parts.length < 2) throw new IllegalArgumentException("Formato inválido");

                double cafes = Double.parseDouble(parts[0].trim().replace(",", "."));
                double temps = Double.parseDouble(parts[1].replace("s", "").trim().replace(",", "."));


                if (temps == 0) {
                    // Evitar división por cero
                    produccioUnitat.add(0.0);
                    produccioGlobal.add(0.0);
                    continue;
                }

                double cafesPerSegon = cafes / temps;
                produccioUnitat.add(cafesPerSegon);

                double totalGenerador = cafesPerSegon * quantitatsInput.get(i);
                produccioGlobal.add(totalGenerador);

                sumaTotal += totalGenerador;

            } catch (Exception e) {
                // En caso de error de formato o número inválido
                produccioUnitat.add(0.0);
                produccioGlobal.add(0.0);
            }
        }

        // Calcular % de producció total
        for (int i = 0; i < produccioGlobal.size(); i++) {
            if (sumaTotal == 0) {
                produccioTotal.add(0.0);
            } else {
                double percentatge = (produccioGlobal.get(i) / sumaTotal) * 100;
                produccioTotal.add(percentatge);
            }
        }

        // Crear tabla
        Object[][] data = {
                {"Cafetera", quantitats.get(0), proudccioUnitatInput.get(0), String.format("%.2f", produccioGlobal.get(0))+" c/s", String.format("%.2f%%", produccioTotal.get(0))},
                {"CafeCheta", quantitats.get(1), proudccioUnitatInput.get(1), String.format("%.2f", produccioGlobal.get(1))+" c/s", String.format("%.2f%%", produccioTotal.get(1))},
                {"CafeGod", quantitats.get(2), proudccioUnitatInput.get(2), String.format("%.2f", produccioGlobal.get(2))+" c/s", String.format("%.2f%%", produccioTotal.get(2))},
        };

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, new String[]{"Nom", "Quantitat", "Produccio unitat", "Produccio global", "% Produccio total"});
        table.getColumn("Nom").setCellRenderer(new ButtonRenderer());
        model.fireTableDataChanged();
    }


    /**
     * Renderiza los botones en la columna "Nom" de la tabla como celdas interactivas estilizadas.
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {
        /**
         * Constructor clase interna
         */
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(245, 222, 179));
            setBorderPainted(false);
            setFocusPainted(false);
            setFont(new Font("Arial", Font.PLAIN, 10));
        }

        /**
         * Método sobrescrito que proporciona el botón estilizado para renderizar en la celda.
         *
         * @param table      Tabla objetivo.
         * @param value      Texto del botón.
         * @param isSelected Si está seleccionada la celda.
         * @param hasFocus   Si la celda tiene foco.
         * @param row        Fila.
         * @param column     Columna.
         * @return Componente renderizado.
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
    }
}

