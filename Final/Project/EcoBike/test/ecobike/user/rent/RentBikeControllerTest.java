/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecobike.user.rent;

import ecobike.bean.Bike;
import ecobike.bean.NormalBike;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author duclt
 */
public class RentBikeControllerTest {
    RentBikeController rent; 
    public RentBikeControllerTest() {
        
        rent = new RentBikeController();
    }

    @Test
    public void testGetBike() {
        JTextField tf = new JTextField("12123");
       
        Bike bike = rent.getBike(tf);
        Bike exp = null;
        assertEquals(exp, bike);
        
        tf.setText("12");
        Bike bike2 = rent.getBike(tf);
        Bike exp2 = new NormalBike();
        assertEquals(exp, bike);
        
        
    }

    @Test
    public void testCheckBalance() {
        int check = rent.checkBalance("NormalBike", 100000);
        int exp = 1;
        assertEquals(exp, check);
    }
    
}
