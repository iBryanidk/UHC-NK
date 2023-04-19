package UHC.utils;

public class Time {

    public static String intToString(int time) {
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
}
