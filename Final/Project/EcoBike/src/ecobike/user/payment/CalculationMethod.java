package ecobike.user.payment;

import ecobike.bean.Bike;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalculationMethod {

    private static final int FIRST_INTERVAL_TIME = 30;
    private static final int MAX_FREE_TIME = 10;
    private static final int EACH_INTERVAL = 15;

    public static int calculate(String bikeType, Date startTime, Date endTime) {
        int totalMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(endTime.getTime() - startTime.getTime());
        
        if(totalMinutes <= MAX_FREE_TIME){
            return 0;
        }
        
        if (totalMinutes <= FIRST_INTERVAL_TIME) {
            switch (bikeType) {
                case Bike.NORMAL:
                    return FeeType.NormalBikeFeeForFirst30Mins;
                case Bike.ECO:
                    return FeeType.EcoBikeFeeForFirst30Mins;
                case Bike.TWIN:
                    return FeeType.TwinBikeFeeForFirst30Mins;
            }
        }

        int numberOfMinutesAfterFirst30Mins = totalMinutes - FIRST_INTERVAL_TIME;

        int numberOfEach15MinsInterval;

        int totalFee = 0;

        if (numberOfMinutesAfterFirst30Mins % EACH_INTERVAL == 0) {
            numberOfEach15MinsInterval = numberOfMinutesAfterFirst30Mins / EACH_INTERVAL;
        } else {
            numberOfEach15MinsInterval = (numberOfMinutesAfterFirst30Mins / EACH_INTERVAL) + 1;
        }

        switch (bikeType) {
            case Bike.NORMAL:
                totalFee = FeeType.NormalBikeFeeForFirst30Mins + FeeType.NormalBikeFeeForEach15MinAfter * numberOfEach15MinsInterval;
                break;
            case Bike.ECO:
                totalFee = FeeType.EcoBikeFeeForFirst30Mins + FeeType.EcoBikeFeeForEach15MinAfter * numberOfEach15MinsInterval;
                break;
            case Bike.TWIN:
                totalFee = FeeType.TwinBikeFeeForFirst30Mins + FeeType.TwinBikeFeeForEach15MinAfter * numberOfEach15MinsInterval;
                break;
            default:
                break;
        }
        return totalFee;
    }

    public static String getTotalTime(Date startTime, Date endTime) {
        int totalMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(endTime.getTime() - startTime.getTime());
        int numberOfHours = totalMinutes / 60;
        int numberOfMinutes = totalMinutes - numberOfHours * 60;
        return numberOfHours + " hour" + (numberOfHours > 1 ? "s" : "") + " " + numberOfMinutes + " minute" + (numberOfMinutes > 1 ? "s" : "");
    }

    public static String currencyFormat(int fee, String language, String country) {
        Locale locale = new Locale(language, country);
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(fee);
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.DATE_FORMAT);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
