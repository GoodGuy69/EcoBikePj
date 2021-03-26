package ecobike.login;

import ecobike.database.SQLDatabase;
import ecobike.main.MainController;
import ecobike.user.station.list.UserStation;
import ecobike.user.station.list.UserStationController;

import javax.swing.*;

public class HomePanelController {

    private HomePanel homePanel;
//    private SQLDatabase sql = new SQLDatabase();

    public HomePanelController(HomePanel screen) {
        this.homePanel = screen;
    }

    public JPanel getScreen() {
        return homePanel.homescreen;
    }

    public void changeUserScreen() {
        homePanel.getBtnUserScreen().addActionListener(e -> {
            UserStation userStation = new UserStation("Station");
            userStation.setListPaneController(new UserStationController(userStation));
            userStation.init();
            MainController.GetInstance().setUserID(1);

            MainController.GetInstance().setRecordID(SQLDatabase.GetInstance().getRecord(1) != null ? SQLDatabase.GetInstance().getRecord(1).getId() : 0);
            MainController.GetInstance().navigate(userStation.getListPane());

        });
    }

    public void changeAdminScreen() {
        homePanel.getBtnAdminScreen().addActionListener(e -> {
            AdminPane adminPane = new AdminPane();
            adminPane.init();
            MainController.GetInstance().navigate(adminPane.getPane());
        });
    }
}
