package ecobike.user.station.detail;

import ecobike.main.MainController;
import ecobike.user.bike.list.UserBike;
import ecobike.user.bike.list.UserBikeController;
import ecobike.user.station.list.UserStation;
import ecobike.user.station.list.UserStationController;


public class StationInforController {

    private StationInfor stationInfor;

    public StationInforController(StationInfor stationInfor) {
        this.stationInfor = stationInfor;
    }

    public void btnViewBike(int stationId) {
        stationInfor.getBtnViewBike().addActionListener(e -> {
            UserBike userBike = new UserBike("Bike In Station", Integer.toString(stationId));
            userBike.setListPaneController(new UserBikeController(userBike));
            userBike.init();
            MainController.GetInstance().navigate(userBike.getUserStation());

        });
    }

    public void btnBack() {
        stationInfor.getBtnBack().addActionListener(e -> {
            UserStation userStation = new UserStation("Station");
            userStation.setListPaneController(new UserStationController(userStation));
            userStation.init();
            MainController.GetInstance().navigate(userStation.getListPane());
        });
    }
}
