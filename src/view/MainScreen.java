package view;

import javax.swing.*;

public class MainScreen extends JFrame implements NavigationObserver {
    private JPanel mainPanel;
    private JButton addProductBtn;
    private JButton viewProductBtn;
    private final FramesManger framesManger = new FramesManger();

    public MainScreen() {
        this.setTitle("Store manger");
        this.setSize(500, 300);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        addProductBtn.setFocusable(false);
        viewProductBtn.setFocusable(false);

        addProductBtn.addActionListener(e -> addProductScreen());
        viewProductBtn.addActionListener(e -> viewProductScreen());

        framesManger.displayFrame(this);
    }

    public static void main(String[] args) {
        new MainScreen();
    }

    private void addProductScreen() {
        NavigationObserver navigationObserver = new AddProductScreen(framesManger);
        framesManger.displayFrame(navigationObserver);
    }

    private void viewProductScreen() {
        NavigationObserver navigationObserver = new ViewProductScreen(framesManger);
        framesManger.displayFrame(navigationObserver);
    }

    @Override
    public void openFrame() {
        this.setVisible(true);
    }

    @Override
    public void hideFrame() {
        this.setVisible(false);
    }

    @Override
    public void closeFrame() {
        this.dispose();
    }
}
