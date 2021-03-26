package ecobike.bean;

public abstract class Bike {

    public static final String NORMAL = "NormalBike";
    public static final String ECO = "EcoBike";
    public static final String TWIN = "TwinBike";
    private int bikeID;
    private String name;
    private float weight;
    private String licensePlate;
    private String manufacturingDate;
    private String producer;
    private int stationID;

    public Bike() {

    }

    public Bike(int bikeID, String name, float weight, String licensePlate, String manufacturingDate,
            String producer, int stationID) {
        super();
        this.bikeID = bikeID;
        this.name = name;
        this.weight = weight;
        this.licensePlate = licensePlate;
        this.manufacturingDate = manufacturingDate;
        this.producer = producer;
        this.stationID = stationID;
    }

    public void show() {
        System.out.println(this.bikeID + "/" + this.name + "/" + this.weight + "\n");
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getBikeName() {
        return name;
    }

    public void setBikeName(String bikeName) {
        this.name = bikeName;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getStationID() {
        return stationID;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
}
