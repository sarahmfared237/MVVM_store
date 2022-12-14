package view;

import viewmodel.AddProductViewModel;
import viewmodel.ViewProductViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewProductScreen {
    private JPanel mainPanel;
    private final ViewProductViewModel viewProductViewModel = new ViewProductViewModel();
    private JTable productTable;
    static JFrame frame;

    ViewProductScreen(){
        fillTable();
    }
    public void fillTable() {
        productTable.setModel(new DefaultTableModel(viewProductViewModel.displayProduct(), new String[]{"ID", "Name", "Price"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

    }

    public static void main(String[] args) {
        frame = new JFrame("Display Product");
        frame.setSize(500, 300);
        frame.setContentPane(new ViewProductScreen().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }
}