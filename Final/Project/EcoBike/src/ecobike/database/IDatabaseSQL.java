package ecobike.database;

import ecobike.bean.*;

import java.util.ArrayList;


public interface IDatabaseSQL  extends IDatabase{
    //station
    public ArrayList<String> getEmptyStationID();
    public ArrayList<Station> getStation(String query);
    public Station getAStation(String id);
    
    // bike 
    public Boolean ifBikeIsRented(int bikeID);
    public Boolean ifBikeNameExisted(String name);
    public Boolean ifBikeLicenseExisted(String license);
    public Bike getBikeById(int bikeId);
    public ArrayList<Bike> getAllBike(String query);
    public Bike getABike(String id);
    public int getNewBikeID();
    public int countBikeInStation(String type, String id);
    public int getNewStationID();
    
    //User 
    public User getUserById(int userID);
    
    //Account
    public Account getUserAcc(int id);
    public int updateBalance(Rent rent, int fee);
    public Account refundDeposit(Rent rent, int check);
    
    
    // Record
    public Rent getRent(int rendID);
    public int countRecord();
}
