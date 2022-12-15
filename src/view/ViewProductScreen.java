package view;

import viewmodel.ViewProductViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewProductScreen extends JFrame implements NavigationObserver {
    private JPanel mainPanel;
    private final ViewProductViewModel viewProductViewModel = new ViewProductViewModel();
    private JTable productTable;
    private final FramesManger framesManger;

    ViewProductScreen(FramesManger framesManger) {
        this.framesManger = framesManger;
        this.setTitle("Add product");

        this.setSize(500, 300);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                framesManger.closeFrame();
            }
        });
        fillTable();
    }

    public void fillTable() {
        Object[][] data = viewProductViewModel.displayProduct();
        productTable.setModel(new DefaultTableModel(data,
                new String[]{"ID", "Name", "Price", "Image"}) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 3: return ImageIcon.class;
                    default: return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        for (int row = 0; row < productTable.getRowCount(); row++)
        {
            int rowHeight = productTable.getRowHeight();

            for (int column = 0; column < productTable.getColumnCount(); column++)
            {
                Component comp = productTable.prepareRenderer(productTable.getCellRenderer(row, column),
                        row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            productTable.setRowHeight(row, rowHeight);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        productTable.setDefaultRenderer(String.class, centerRenderer);
    }

    @Override
    public void openFrame() {
        this.setVisible(true);
    }

    @Override
    public void closeFrame() {
        this.dispose();
    }

    @Override
    public void hideFrame() {
        this.setVisible(false);
    }
}