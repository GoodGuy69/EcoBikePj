/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecobike.user.payment;

import ecobike.bean.Bike;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author duongtq
 */
public class CalculationMethodTest {
    private Date startTime0;
    private Date endTime0;
    private Date startTime1;
    private Date endTime1;
    private Date startTime2;
    private Date endTime2;
    private Date startTime3;
    private Date endTime3;
    private Date startTime4;
    private Date endTime4;

    @Before
    public void init() {
        startTime0 = CalculationMethod.stringToDate("2020-12-22 19:20:00");
        endTime0 = CalculationMethod.stringToDate("2020-12-22 22:20:00");

        startTime1 = CalculationMethod.stringToDate("2020-12-22 14:56:49");
        endTime1 = CalculationMethod.stringToDate("2020-12-22 17:13:30");

        startTime2 = CalculationMethod.stringToDate("2020-12-22 00:12:59");
        endTime2 = CalculationMethod.stringToDate("2020-12-22 23:21:10");

        startTime3 = CalculationMethod.stringToDate("2020-12-22 00:10:00");
        endTime3 = CalculationMethod.stringToDate("2020-12-22 00:20:00");

        startTime4 = CalculationMethod.stringToDate("2020-12-22 10:10:00");
        endTime4 = CalculationMethod.stringToDate("2020-12-22 10:38:00");
    }

    @Test
    public void testCalculate() {
        Assert.assertEquals(40000, CalculationMethod.calculate(Bike.NORMAL, startTime0, endTime0));
        Assert.assertEquals(60000, CalculationMethod.calculate(Bike.ECO, startTime0, endTime0));
        Assert.assertEquals(60000, CalculationMethod.calculate(Bike.TWIN, startTime0, endTime0));

        Assert.assertEquals(34000, CalculationMethod.calculate(Bike.NORMAL, startTime1, endTime1));
        Assert.assertEquals(51000, CalculationMethod.calculate(Bike.ECO, startTime1, endTime1));
        Assert.assertEquals(51000, CalculationMethod.calculate(Bike.TWIN, startTime1, endTime1));

        Assert.assertEquals(283000, CalculationMethod.calculate(Bike.NORMAL, startTime2, endTime2));
        Assert.assertEquals(424500, CalculationMethod.calculate(Bike.ECO, startTime2, endTime2));
        Assert.assertEquals(424500, CalculationMethod.calculate(Bike.TWIN, startTime2, endTime2));

        Assert.assertEquals(0, CalculationMethod.calculate(Bike.NORMAL, startTime3, endTime3));
        Assert.assertEquals(0, CalculationMethod.calculate(Bike.ECO, startTime3, endTime3));
        Assert.assertEquals(0, CalculationMethod.calculate(Bike.TWIN, startTime3, endTime3));

        Assert.assertEquals(10000, CalculationMethod.calculate(Bike.NORMAL, startTime4, endTime4));
        Assert.assertEquals(15000, CalculationMethod.calculate(Bike.ECO, startTime4, endTime4));
        Assert.assertEquals(15000, CalculationMethod.calculate(Bike.TWIN, startTime4, endTime4));
    }

    @Test
    public void testGetTotalTime() {
        String correctInterval0 = "3 hours 0 minute";
        Assert.assertEquals(correctInterval0, CalculationMethod.getTotalTime(startTime0, endTime0));

        String correctInterval1 = "2 hours 16 minutes";
        Assert.assertEquals(correctInterval1, CalculationMethod.getTotalTime(startTime1, endTime1));

        String correctInterval2 = "23 hours 8 minutes";
        Assert.assertEquals(correctInterval2, CalculationMethod.getTotalTime(startTime2, endTime2));

        String correctInterval3 = "0 hour 10 minutes";
        Assert.assertEquals(correctInterval3, CalculationMethod.getTotalTime(startTime3, endTime3));

        String correctInterval4 = "0 hour 28 minutes";
        Assert.assertEquals(correctInterval4, CalculationMethod.getTotalTime(startTime4, endTime4));
    }

    @Test
    public void testCurrencyFormat() {
        int fee = 100000;

        String ukLanguage = "en";
        String ukCountry = "GB";

        String usLanguage = "en";
        String usCountry = "US";

        String ukCorrectFormat = "£100,000.00";
        String usCorrectFormat = "$100,000.00";

        Assert.assertEquals(ukCorrectFormat, CalculationMethod.currencyFormat(fee, ukLanguage, ukCountry));
        Assert.assertEquals(usCorrectFormat, CalculationMethod.currencyFormat(fee, usLanguage, usCountry));
    }

    @Test
    public void testStringToDate() throws ParseException {
        String dateString0 = "2020-12-22 12:12:19";
        String dateString1 = "1998-9-12 22:37:52";
        String dateString2 = "1554-7-8 07:59:43";

        Date correctDate0 = new SimpleDateFormat(DateFormat.DATE_FORMAT).parse(dateString0);
        Assert.assertEquals(correctDate0, CalculationMethod.stringToDate(dateString0));

        Date correctDate1 = new SimpleDateFormat(DateFormat.DATE_FORMAT).parse(dateString1);
        Assert.assertEquals(correctDate1, CalculationMethod.stringToDate(dateString1));

        Date correctDate2 = new SimpleDateFormat(DateFormat.DATE_FORMAT).parse(dateString2);
        Assert.assertEquals(correctDate2, CalculationMethod.stringToDate(dateString2));
    }
}
