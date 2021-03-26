package ecobike.main;

import javax.swing.*;

public class MainController {

    private JFrame mainFrame;
    private int userID;
    private int recordID;

    private static MainController mainController;

    public MainController() {
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public MainController(JFrame main) {
        mainFrame = main;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void navigate(JPanel panel) {
        mainFrame.setContentPane(panel);
        mainFrame.revalidate();
    }

    public static MainController GetInstance() {
        if (mainController == null) {
            mainController = new MainController();
        }
        return mainController;
    }
}
