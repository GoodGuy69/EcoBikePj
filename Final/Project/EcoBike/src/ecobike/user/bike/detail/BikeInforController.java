package ecobike.user.bike.detail;

import ecobike.main.MainController;
import ecobike.user.bike.list.UserBike;
import ecobike.user.bike.list.UserBikeController;
import ecobike.user.rent.RentBike;

public class BikeInforController {

    private BikeInfor bikeInfor;

    public BikeInforController(BikeInfor bikeInfor) {

        this.bikeInfor = bikeInfor;
    }

    public void btnBack(String stationID) {
        bikeInfor.getBtnBack().addActionListener(e -> {
            UserBike userBike = new UserBike("Bike In Station", stationID);
            userBike.setListPaneController(new UserBikeController(userBike));
            userBike.init();
            MainController.GetInstance().navigate(userBike.getUserStation());

        });
    }

    public void rentBikeScreen(int bikeID) {
        bikeInfor.getBtnViewBike().addActionListener(e -> {
            RentBike rentBike = new RentBike(bikeID);
            rentBike.init();
            MainController.GetInstance().navigate(rentBike.getStationInfor());

        });
    }
}
