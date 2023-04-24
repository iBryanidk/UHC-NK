package UHC.utils;

import java.util.concurrent.TimeUnit;

public class Time {

    protected static String UPTIME_FORMAT = "%d" + " days " + "%d" + " hours " + "%d" + " minutes " + "%d" + " seconds";

    public static String formatString(int time) {
        String strTemp;

        int minutes = time / 60;
        int seconds = time % 60;

        if(minutes < 10){
            strTemp = "0" + minutes + ":";
        }else{
            strTemp = minutes + ":";
        }
        if(seconds < 10){
            strTemp = strTemp + "0" + seconds;
        }else{
            strTemp = strTemp + seconds;
        }
        return strTemp;
    }

    public static String formatUptime(long uptime) {
        long days = TimeUnit.MILLISECONDS.toDays(uptime);
        uptime -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);

        return String.format(UPTIME_FORMAT, days, hours, minutes, seconds);
    }
}
