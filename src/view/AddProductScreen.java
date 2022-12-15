package view;

import viewmodel.AddProductViewModel;
import viewmodel.ValidationObserver;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class AddProductScreen extends JFrame implements ValidationObserver, NavigationObserver {
    private final AddProductViewModel addProductViewModel = new AddProductViewModel();
    private JPanel mainPanel;
    private JTextField nameField;
    private JButton addProductBtn;
    private JTextField priceField;
    private JButton addImageBtn;
    private JLabel fileName;
    private final FramesManger framesManger;
    String imagePath = "";

    AddProductScreen(FramesManger framesManger) {
        this.framesManger = framesManger;

        addProductBtn.setFocusable(false);
        addImageBtn.setFocusable(false);
        addProductBtn.addActionListener(e -> addProduct());
        addImageBtn.addActionListener(e -> addImage());
        addProductViewModel.addObserver(this);

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
        this.getRootPane().setDefaultButton(addProductBtn);
    }

    private void addImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                return f.getName().toLowerCase().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "PNG file (*.png)";
            }
        });

        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            imagePath = fileChooser.getSelectedFile().getPath();
            fileName.setText(fileChooser.getSelectedFile().getName());
        }
    }

    private void addProduct() {
        addProductViewModel.validateThenAdd(nameField.getText(), priceField.getText(), imagePath);
        imagePath = "";
        fileName.setText("No image");
    }

    @Override
    public void update(boolean valid) {
        if (valid) {
            JOptionPane.showMessageDialog(null, "Add!");
            framesManger.closeFrame();
        } else {
            JOptionPane.showMessageDialog(null, "Wrong!", "Wrong!",
                    JOptionPane.ERROR_MESSAGE);
        }
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
