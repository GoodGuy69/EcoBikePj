package ecobike.main;

import ecobike.login.HomePanel;

import javax.swing.*;

public class Main extends JFrame {

    public static final int WINDOW_WIDTH = 540;
    public static final int WINDOW_HEIGHT = 720;

    public Main(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 200, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {

            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            JFrame main = new Main("EcoBikeRental App");
            MainController.GetInstance().setMainFrame(main);
            HomePanel loginScreen = new HomePanel();
            loginScreen.init();
            MainController.GetInstance().navigate(loginScreen.homescreen);

        });
    }
}
