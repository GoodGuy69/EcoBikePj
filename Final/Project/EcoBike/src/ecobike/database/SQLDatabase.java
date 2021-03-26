package ecobike.database;

import ecobike.bean.*;
import ecobike.user.payment.DepositType;

import java.sql.*;
import java.util.ArrayList;

public class SQLDatabase implements IDatabaseSQL {

    protected Connection connection;
    protected Statement statement;
    protected ResultSet rs = null;
    protected PreparedStatement ppsm;

    private static SQLDatabase sql;

    public static SQLDatabase GetInstance() {
        if (sql == null) {
            sql = new SQLDatabase();
        }
        return sql;
    }
    public SQLDatabase() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ITSS?useSSL=false", Credentials.USERNAME, Credentials.PASSWORD);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
           // e.printStackTrace();
        }

    }

    // =============================== Station ====================================
    @Override
    public ArrayList<Station> getStation(String query) {

        ArrayList<Station> stations = new ArrayList<>();
        ResultSet rs;
        try {
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                Station station = new Station(rs.getInt("SID"), rs.getString("name"), rs.getString("address"), rs.getInt("total_dock"));
                stations.add(station);
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return stations;
    }

    @Override
    public Station getAStation(String id) {
        Station station = null;
        try {
            rs = this.statement.executeQuery("SELECT * FROM `stations` where `SID` = " + id);
            while (rs.next()) {
                station = new Station(Integer.parseInt(rs.getString("SID")), rs.getString("name"), rs.getString("address"), Integer.parseInt(rs.getString("total_dock")));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            return null;
        }
        return station;
    }

    public boolean compareStationforAdd(Station station) {
        Station station_token;
        boolean check = true;
        try {
            rs = this.statement.executeQuery("SELECT * FROM `stations`");
            while (rs.next()) {
                station_token = new Station(Integer.parseInt(rs.getString("SID")), rs.getString("name"), rs.getString("address"), Integer.parseInt(rs.getString("total_dock")));
                if ((station.getStationName().equals(station_token.getStationName())) && (station.getStationAddress().equals(station_token.getStationAddress()))) {
                    check = false;
                }
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            return check;
        }
        return check;
    }
    public boolean compareStationforUpdate(Station station) {
        Station station_token;
        boolean check = true;
        try {
            rs = this.statement.executeQuery("SELECT * FROM `stations`");
            while (rs.next()) {
                station_token = new Station(Integer.parseInt(rs.getString("SID")), rs.getString("name"), rs.getString("address"), Integer.parseInt(rs.getString("total_dock")));
                if ((station.getStationName().equals(station_token.getStationName())) && (station.getStationAddress().equals(station_token.getStationAddress()))) {
                    if(station.getNoDock()==station_token.getNoDock()){check = false;}
                }
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            return check;
        }
        return check;
    }

    public Station getStationByName(String name) {
        Station station = null;
        try {
            rs = this.statement.executeQuery("SELECT * FROM `stations` where `name` = " + name);
            while (rs.next()) {
                station = new Station(Integer.parseInt(rs.getString("SID")), rs.getString("name"), rs.getString("address"), Integer.parseInt(rs.getString("total_dock")));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
            return null;
        }
        return station;
    }

    @Override
    public boolean updateStation(Station station) {
        if (!compareStationforUpdate(station)) {
            return false;
        }

        String query = "UPDATE `stations` SET `name` = ?, "
                + "`address` = ?, "
                + "`total_dock` = ? "
                + "WHERE `SID` = ?;";
        try {
            ppsm = connection.prepareStatement(query);
            ppsm.setString(1, station.getStationName());
            ppsm.setString(2, station.getStationAddress());
            ppsm.setInt(3, station.getNoDock());
            ppsm.setInt(4, station.getStationID());

            ppsm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addStation(Station station) {
        if (getAStation(String.valueOf(station.getStationID())) != null) {
            return false;
        } else {
            if (!compareStationforAdd(station)) {
                return false;
            }
        }

        String query = "INSERT INTO `stations` VALUES (?, ?, ?, ?);";
        try {
            ppsm = connection.prepareStatement(query);
            ppsm.setInt(1, station.getStationID());
            ppsm.setString(2, station.getStationName());
            ppsm.setString(3, station.getStationAddress());
            ppsm.setInt(4, station.getNoDock());

            ppsm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteStation(Station station) {
        if (getAStation(String.valueOf(station.getStationID())) == null) {
            return false;
        } else {
            if (getAStation(String.valueOf(station.getStationID())).getNoDock() != 0) {
                int countNoTwinBike = countBikeInStation("TwinBike", Integer.toString(station.getStationID()));
                int countNoEcoBike = countBikeInStation("EcoBike", Integer.toString(station.getStationID()));
                int countNoBike = countBikeInStation("NormalBike", Integer.toString(station.getStationID()));
                if ((countNoBike != 0) || (countNoEcoBike != 0) || (countNoTwinBike != 0)) {
                    return false;
                }
            }
            String query = "DELETE FROM `stations` WHERE `SID` = ?;";
            try {
                ppsm = connection.prepareStatement(query);
                ppsm.setInt(1, station.getStationID());

                ppsm.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
                return false;
            }
            return true;
        }
    }

    @Override
    public ArrayList<Station> searchStation(String StationName, ArrayList<Station> allStation) {
        ArrayList<Station> stationList = new ArrayList<>();
        for (Station station0 : allStation) {
            if(station0==null){continue;}
        	String name = station0.getStationName();
            if (name.contains(StationName)) {
                stationList.add(getStationByName(name));
            }
        }
        return stationList;
    }

    @Override
    public ArrayList<String> getEmptyStationID() {
        ArrayList<String> stationIDs = new ArrayList<>();
        ArrayList<Station> allStation = getStation("SELECT * FROM stations");
        for (Station station : allStation) {
            String sid = Integer.toString(station.getStationID());
            int countNoTwinBike = countBikeInStation("TwinBike", sid);
            int countNoEcoBike = countBikeInStation("EcoBike", sid);
            int countNoBike = countBikeInStation("NormalBike", sid);
            int countEmptyDock = getAStation(sid).getNoDock() - countNoBike - countNoEcoBike - countNoTwinBike;
            if (countEmptyDock != 0) {
                stationIDs.add(sid);
            }
        }
        return stationIDs;
    }

    public ArrayList<String> getAllStationID() {
        String QUERY = "SELECT * FROM `stations`";
        ArrayList<String> stationIDs = new ArrayList<>();
        ResultSet rs;
        try {
            rs = this.statement.executeQuery(QUERY);
            while (rs.next()) {
                stationIDs.add(rs.getString("SID"));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return stationIDs;
    }

    @Override
    public void deleteStation(int stationID) {
        String query = "DELETE FROM `stations` WHERE `SID` = ?;";
        try {
            ppsm = connection.prepareStatement(query);
            ppsm.setInt(1, stationID);

            ppsm.executeUpdate();
        } catch (SQLException e1) {
          //  e1.printStackTrace();
        }
    }

    @Override
    public int getNewStationID() {
        int newID = -1;
        try {
            String query = "SELECT MAX(SID) FROM stations;";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                newID = rs.getInt("MAX(SID)") + 1;
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }

        return newID;
    }

    //===============================  bike ===============================
    @Override
    public ArrayList<Bike> getAllBike(String query) {
        ArrayList<Bike> bikes = new ArrayList<>();

        try {
            rs = this.statement.executeQuery(query);
            Bike bike;
            while (rs.next()) {
                bike = initBikeType(rs);
                bikes.add(bike);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bikes;
    }

    public Bike initBikeType(ResultSet rs) {
        try {
            Bike bike = null;
            switch (rs.getString("type")) {
                case Bike.NORMAL:
                    bike = new NormalBike(rs.getInt("BID"), rs.getString("name"), rs.getFloat("weight"), rs.getString("license_plate"), rs.getString("manufacturing_date"), rs.getString("producer"), rs.getInt("SID"));
                    break;
                case Bike.ECO:
                    bike = new EcoBike(rs.getInt("BID"), rs.getString("name"), rs.getFloat("weight"), rs.getString("license_plate"), rs.getString("manufacturing_date"), rs.getString("producer"), rs.getInt("SID"));
                    break;
                case Bike.TWIN:
                    bike = new TwinBike(rs.getInt("BID"), rs.getString("name"), rs.getFloat("weight"), rs.getString("license_plate"), rs.getString("manufacturing_date"), rs.getString("producer"), rs.getInt("SID"));
                    break;
                default:
                    break;
            }
            return bike;
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bike getABike(String id) {
        Bike bike = null;
        try {
            rs = this.statement.executeQuery("SELECT * FROM `bikes` WHERE `BID` = " + id);
            rs.next();
            bike = initBikeType(rs);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return bike;
    }

    @Override
    public int getNewBikeID() {
        int newID = -1;
        try {
            String query = "SELECT MAX(BID) FROM `bikes`;";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                newID = rs.getInt("MAX(BID)") + 1;
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }

        return newID;
    }

    @Override
    public int countBikeInStation(String type, String id) {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) AS count FROM bikes WHERE `SID` = " + id + " AND `type` ='" + type + "'";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                count = Integer.parseInt(rs.getString("count"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return count;
    }

    @Override
    public ArrayList<Bike> searchBike(Bike bike) {
        return null;
    }

    @Override
    public void updateBike(Bike bike) {
        String query = "UPDATE `bikes` SET `name` = ?, "
                + "`type` = ?, "
                + "`weight` = ?, "
                + "`license_plate` = ?, "
                + "`manufacturing_date` = ?,"
                + "`producer` = ?"
                + "WHERE `BID` = ?;";
        try {
            ppsm = connection.prepareStatement(query);
            ppsm.setString(1, bike.getBikeName());
            ppsm.setString(2, bike.getClass().getSimpleName());
            ppsm.setFloat(3, bike.getWeight());
            ppsm.setString(4, bike.getLicensePlate());
            ppsm.setString(5, bike.getManufacturingDate());
            ppsm.setString(6, bike.getProducer());
            ppsm.setInt(7, bike.getBikeID());

            ppsm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void addBike(Bike bike) {
        String query = "INSERT INTO `bikes` VALUES (?, ?, ?, ?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?);";
        try {
            ppsm = connection.prepareStatement(query);
            ppsm.setInt(1, bike.getBikeID());
            ppsm.setString(2, bike.getBikeName());
            ppsm.setString(3, bike.getClass().getSimpleName());
            ppsm.setFloat(4, bike.getWeight());
            ppsm.setString(5, bike.getLicensePlate());
            ppsm.setString(6, bike.getManufacturingDate());
            ppsm.setString(7, bike.getProducer());
            ppsm.setInt(8, bike.getStationID());

            ppsm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteBike(int bikeID) {
        String query = "DELETE FROM `bikes` WHERE `BID` = ?;";
        try {
            ppsm = connection.prepareStatement(query);
            ppsm.setInt(1, bikeID);

            ppsm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Bike getBikeById(int bikeId) {
        String QUERY = "SELECT * FROM `bikes` WHERE `BID` = " + bikeId;
        Bike bike = null;
        ResultSet rs;
        try {
            rs = this.statement.executeQuery(QUERY);
            rs.next();
            bike = initBikeType(rs);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return bike;
    }

    @Override
    public Boolean ifBikeIsRented(int bikeID) {
        boolean bikeIsRented = false;

        String query = "SELECT * FROM `records` WHERE `BID` = " + bikeID;
        try {
            rs = this.statement.executeQuery(query);
            if (rs.next()) {
                bikeIsRented = true;
            }

        } catch (SQLException e) {
           // e.printStackTrace();
        }

        return bikeIsRented;
    }

    @Override
    public Boolean ifBikeNameExisted(String name) {
        boolean bikeNameExisted = false;
        Bike bikeToken;
        String query = "SELECT * FROM `bikes` where name = '" + name + "'";

        try {
            rs = this.statement.executeQuery(query);
            if (rs.next()) {
                bikeNameExisted = true;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return bikeNameExisted;
    }

    @Override
    public Boolean ifBikeLicenseExisted(String license) {
        boolean lisenceExisted = false;
        Bike bikeToken;
        String query = "SELECT * FROM `bikes` where license_plate = '" + license + "'";

        try {
            rs = this.statement.executeQuery(query);
            if (rs.next()) {
                lisenceExisted = true;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return lisenceExisted;
    }

    public void updateBikeInStation(int id, int station) {
        String QUERY;
        if (station == 0) {
            QUERY = "UPDATE `bikes` SET `SID` = null" + " WHERE `BID` = " + id;
        } else {
            QUERY = "UPDATE `bikes` SET `SID` = " + station + " WHERE `BID` = " + id;
        }
        try {
            this.statement.executeUpdate(QUERY);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public ArrayList<String> getRentedBikeIDs() {
        String query = "Select BID from `records` natural join `bikes`";
        ArrayList<String> bikeIDs = new ArrayList<>();
        try {
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                bikeIDs.add(rs.getString("BID"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return bikeIDs;
    }

    public ArrayList<String> getUnrentedBikeIDs() {
        String query = "Select BID from `bikes` where BID not in (Select BID from `records`)";
        ArrayList<String> bikeIDs = new ArrayList<>();
        try {
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                bikeIDs.add(rs.getString("BID"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return bikeIDs;
    }

    public ArrayList<String> getAllBikeNames() {
        String query = "Select name from `bikes`";
        ArrayList<String> bikeNames = new ArrayList<>();
        try {
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                bikeNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return bikeNames;
    }

    public ArrayList<String> getAllBikeLicenses() {
        String query = "Select license_plate from `bikes`";
        ArrayList<String> bikeLicenses = new ArrayList<>();
        try {
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                bikeLicenses.add(rs.getString("license_plate"));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return bikeLicenses;
    }

    // ==================================== User ================================
    public ResultSet getUser(int id) {
        try {
            rs = this.statement.executeQuery("SELECT * FROM `users` WHERE `UID` = " + id);
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return rs;
    }

    @Override
    public User getUserById(int userId) {
        User user = new User();

        String QUERY = "SELECT * FROM `users` WHERE `users`.`UID` = " + userId;

        ResultSet rs;
        try {
            rs = this.statement.executeQuery(QUERY);
            while (rs.next()) {
                user = new User(rs.getInt("UID"), rs.getString("name"), rs.getString("phone"),
                        rs.getString("email"), rs.getString("gender"));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return user;
    }

    // ==================================== Accout ================================
    @Override
    public Account getUserAcc(int id) {
        Account userAcc = null;
        try {
            rs = this.statement.executeQuery("SELECT * FROM `accounts` WHERE `UID` = " + id);
            rs.next();
            int aid = Integer.parseInt(rs.getString("AID"));
            int uid = Integer.parseInt(rs.getString("UID"));
            String cardNumber = rs.getString("card_number");
            rs = this.statement.executeQuery("SELECT * FROM `cards` WHERE `card_number` = " + cardNumber);
            rs.next();
            int balance = Integer.parseInt(rs.getString("balance"));
            String issueBank = rs.getString("issuing_bank");
            String expiratioDate = rs.getString("expiration_date");

            userAcc = new Account(aid, uid, cardNumber, balance, issueBank, expiratioDate);

        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return userAcc;
    }

    public String getBalance(String card) {
        String balance = "";
        try {
            String query = "SELECT * FROM `cards` WHERE `card_number` = " + card;
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                balance = rs.getString("balance");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return balance;

    }

    public void updataAccount(String card, String blance, int id) {

        String QUERY = "UPDATE `accounts` SET `card_number` = '" + card + "' WHERE `AID` = " + id;
        try {
            this.statement.executeUpdate(QUERY);
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    // ==================================== Record ===============================
    @Override
    public Rent getRent(int rentID) {
        Rent rent = new Rent();
        String QUERY = "SELECT * FROM `records` WHERE `RID` = " + rentID;
        ResultSet rs;
        try {
            rs = this.statement.executeQuery(QUERY);
            while (rs.next()) {
                rent = new Rent(rs.getInt("RID"), rs.getInt("AID"), rs.getInt("BID"),
                        rs.getString("start_time"), rs.getString("end_time"));

            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return rent;
    }

    @Override
    public int updateBalance(Rent rent, int fee) {
        Account account = this.getUserAcc(rent.getAccountId());
        int newBalance = account.getBalance() - fee;
        account.setBalance(newBalance);

        String QUERY2 = "UPDATE `cards` SET  `balance` = " + account.getBalance() + " WHERE `card_number` = " + account.getCardNumber();
        try {
            this.statement.executeUpdate(QUERY2);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return newBalance;
    }

    @Override
    public Account refundDeposit(Rent rent, int check) {
        Account account = this.getUserAcc(rent.getAccountId());
        Bike bike = this.getBikeById(rent.getBikeId());

        int deposit = 0;
        switch (bike.getClass().getSimpleName()) {
            case Bike.ECO:
                deposit = DepositType.ECOBIKE_DEPOSIT;
                break;
            case Bike.NORMAL:
                deposit = DepositType.NORMALBIKE_DEPOSIT;
                break;
            case Bike.TWIN:
                deposit = DepositType.TWINBIKE_DEPOSIT;
                break;
            default:
                break;
        }
        if (check == 1) {
            account.setBalance(account.getBalance() + deposit);
        } else {
            account.setBalance(account.getBalance() - deposit);
        }
        String QUERY2 = "UPDATE `cards` SET  `balance` = " + account.getBalance() + " WHERE `card_number` = " + account.getCardNumber();
        try {
            this.statement.executeUpdate(QUERY2);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return account;
    }

    @Override
    public void addRecord(Rent rent) {

        String QUERY = "INSERT INTO `records` VALUES("
                + rent.getId() + ", "
                + rent.getAccountId() + ", "
                + rent.getBikeId() + ", "
                + "'" + rent.getStartTime() + "'" + ", "
                + "'" + rent.getEndTime() + "'"
                + ");";
        try {
            this.statement.executeUpdate(QUERY);
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

    @Override
    public int countRecord() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) AS `count` FROM `records`";
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                count = Integer.parseInt(rs.getString("count"));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }

        return count;
    }

    public Rent getRecord(int userID) {
        Rent rent = null;
        try {
            String query = "SELECT * FROM `records` AS r, `users` AS u, `accounts` AS a WHERE a.AID=u.UID and r.AID= a.AID and r.end_time='null' and u.UID = " + userID;
            rs = this.statement.executeQuery(query);
            while (rs.next()) {
                rent = new Rent(rs.getInt("RID"), rs.getInt("AID"), rs.getInt("BID"), rs.getString("start_time"), rs.getString("end_time"));
            }
        } catch (SQLException e) {
           // e.printStackTrace();
        }
        return rent;
    }

    public void updateRecord(int id, String endTime) {

        String QUERY = "UPDATE `records` SET `end_time` = ' " + endTime + "' WHERE `RID` = " + id;

        try {
            this.statement.executeUpdate(QUERY);
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

}
