/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecobike.user.returns;

import ecobike.bean.Station;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author duclt
 */
public class ReturnBikeControllerTest {
    ReturnBikeController re;
    public ReturnBikeControllerTest() {
        re= new ReturnBikeController();
    }

    @Test
    public void testGetNoDock() {
        //test 1
        Station station1 = new Station(9,"Station-9 ", "12 Tran Dai Nghia", 0);
        int count = re.getNoDock(station1);
        int exp = 0;
        assertEquals(exp, count);
        
        // test 2
        Station station2 = new Station(10,"Station-10 ", "12 Ta Quang Buu", 5);
        count = re.getNoDock(station2);
        exp = 5;
        assertEquals(exp, count);
    }
    

    
    
}
