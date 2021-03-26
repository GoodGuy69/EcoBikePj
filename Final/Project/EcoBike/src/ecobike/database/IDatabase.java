package ecobike.database;

import ecobike.bean.Bike;
import ecobike.bean.Rent;
import ecobike.bean.Station;

import java.util.ArrayList;

public interface IDatabase {

    public ArrayList<Station> searchStation(String StationName, ArrayList<Station> allStation);
    public boolean updateStation(Station station);
    public boolean addStation(Station station);
    public boolean deleteStation(Station station);
    public ArrayList<Bike> searchBike(Bike bike);
    public void updateBike(Bike bike);
    public void addBike(Bike bike);
    public void deleteBike(int bikeID);
    public void addRecord(Rent rent);
    public void deleteStation(int stationID);
}
