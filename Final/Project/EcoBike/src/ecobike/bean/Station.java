package ecobike.bean;

public class Station {

    private int stationID;
    private String stationName;
    private String staionAddress;
    private int noDock;

    public Station(int stationID) {
        super();
        this.stationID = stationID;
    }
    public Station(String stationName) {
        super();
        this.stationName = stationName;
    }


    public Station(int stationID, String stationName, String staionAddress, int noDock) {
        super();
        this.stationID = stationID;
        this.stationName = stationName;
        this.staionAddress = staionAddress;
        this.noDock = noDock;
    }

    public void showAStation() {
        System.out.println(this.stationName);
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return staionAddress;
    }

    public void setStationAddress(String staionAddress) {
        this.staionAddress = staionAddress;
    }

    public int getNoDock() {
        return noDock;
    }

    public void setNoDock(int noDock) {
        this.noDock = noDock;
    }

}
