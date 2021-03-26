package ecobike.bean;

public class EcoBike extends Bike {

    public EcoBike() {
        super();
        // TODO Auto-generated constructor stub
    }

    public EcoBike(int bikeID, String name, float weight, String licensePlate, String manufacturingDate,
            String producer, int stationID) {
        super(bikeID, name, weight, licensePlate, manufacturingDate, producer, stationID);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void show() {
        super.show();
    }
}
