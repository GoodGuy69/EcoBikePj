package ecobike.login;

import javax.swing.*;

public class HomePanel {

    public JPanel homescreen = new JPanel();
    private JButton btnUserScreen = new JButton();
    private JButton btnAdminScreen = new JButton();
    private HomePanelController homePanelController;

    public JPanel getHomescreen() {
        return homescreen;
    }

    public void setHomescreen(JPanel homescreen) {
        this.homescreen = homescreen;
    }

    public JButton getBtnUserScreen() {
        return btnUserScreen;
    }

    public void setBtnUserScreen(JButton btnUserScreen) {
        this.btnUserScreen = btnUserScreen;
    }

    public JButton getBtnAdminScreen() {
        return btnAdminScreen;
    }

    public void setBtnAdminScreen(JButton btnAdminScreen) {
        this.btnAdminScreen = btnAdminScreen;
    }

    public HomePanelController getHomePanelController() {
        return homePanelController;
    }

    public void setHomePanelController(HomePanelController homePanelController) {
        this.homePanelController = homePanelController;
    }

    public HomePanel() {

    }

    public void init() {
        homePanelController = new HomePanelController(this);
        homescreen.setLayout(null);
        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setBounds(0, 0, 540, 200);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("../image/logo.png")));
        homescreen.add(jLabel1);

        btnUserScreen.setText("User");
        btnUserScreen.setSize(100, 50);
        btnUserScreen.setBounds(200, 390, 140, 50);
        homescreen.add(btnUserScreen);

        btnAdminScreen.setText("Admin");
        btnAdminScreen.setSize(100, 50);
        btnAdminScreen.setBounds(200, 300, 140, 50);
        homescreen.add(btnAdminScreen);

        jLabel3.setSize(540, 200);
        jLabel3.setIcon(new ImageIcon(getClass().getResource("../image/background.png")));
        jLabel3.setBounds(0, 360, 540, 360);
        homescreen.add(jLabel3);

        homePanelController.changeUserScreen();
        homePanelController.changeAdminScreen();

    }
}
