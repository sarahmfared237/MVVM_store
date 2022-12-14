package view;

import viewmodel.AddProductViewModel;
import viewmodel.ValidationObserver;

import javax.swing.*;

public class AddProductScreen implements ValidationObserver {
    private final AddProductViewModel addProductViewModel = new AddProductViewModel();
    private JPanel mainPanel;
    private JTextField nameField;
    private JButton addProductBtn;
    private JTextField priceField;
    static JFrame frame;
    AddProductScreen() {
        addProductBtn.setFocusable(false);
        addProductBtn.addActionListener(e -> addProduct());
        addProductViewModel.addObserver(this);
        frame.getRootPane().setDefaultButton(addProductBtn);
    }

    public static void main(String[] args) {
        frame = new JFrame("Add Product");
        frame.setSize(500, 300);
        frame.setContentPane(new AddProductScreen().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void addProduct() {
        addProductViewModel.validateThenAdd(nameField.getText(), priceField.getText());
    }

    @Override
    public void update(boolean valid) {
        if (valid) {
            JOptionPane.showMessageDialog(null, "Add!");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong!", "Wrong!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
