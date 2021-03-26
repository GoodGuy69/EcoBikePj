
package ecobike.admin.station;

import ecobike.database.*;
import ecobike.bean.Station;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStationControllerTest {
    private ArrayList<Station> lstStation;
    private ArrayList<Station> lstTest1;
    private ArrayList<Station> lstTest2;
    private ArrayList<Station> lstTest3;
    IDatabase sql = new SQLDatabase();

    boolean compareStationList(ArrayList<Station> lstStation1, ArrayList<Station> lstStation2) {
        if (lstStation1.size() != lstStation2.size()) return false;
        for (Station station : lstStation1) {
            if(station==null){continue;}
        	boolean ok = false;
            for (Station value : lstStation2) {
                if(value == null){continue;}
            	if (station.getStationName().equals(value.getStationName())) {
                    ok = true;
                    break;
                }
            }
            if (!ok) return false;
        }
        return true;
     }
    @Before
    public void setUp() {
        Station station1 = new Station(1,"Station-1","1 Dai Co Viet - Ha Noi", 15);
        Station station2 = new Station(2,"Station-2","286 Nguyen Xien- Ha Noi", 15);
        Station station3 = new Station(3,"Station-3","92 Dao Tan - Ha Noi", 15);
        Station station4 = new Station(4,"Station-4","192 Le Trong Tan - Ha Noi", 15);
        Station station5 = new Station(5,"Station-5","48 Minh Khai - Ha Noi", 15);
        lstStation = new ArrayList<>();
        lstStation.add(station1);
        lstStation.add(station2);
        lstStation.add(station3);
        lstStation.add(station4);
        lstStation.add(station5);


        lstTest1 = sql.searchStation("Station",lstStation);
        lstTest2 = sql.searchStation("Station-2",lstStation);
        lstTest3 = sql.searchStation("hahaha",lstStation);
    }

    @Test
    public void searchStationTest1() {
        ArrayList<Station> lstTestStation = new ArrayList<>();
        lstTestStation.add(new Station("Station-1"));
        lstTestStation.add(new Station("Station-2"));
        lstTestStation.add(new Station("Station-3"));
        lstTestStation.add(new Station("Station-4"));
        lstTestStation.add(new Station("Station-5"));
        assertTrue(compareStationList(lstTest1, lstTestStation));
    }

    @Test
    public void searchStationTest2() {
        ArrayList<Station> lstTestStation = new ArrayList<>();
        lstTestStation.add(new Station("Station-2"));
        assertTrue(compareStationList(lstTest2, lstTestStation));
    }

    @Test
    public void searchStationTest3() {
        ArrayList<Station> lstTestStation = new ArrayList<>();
        assertTrue(compareStationList(lstTest3, lstTestStation));
    }
}