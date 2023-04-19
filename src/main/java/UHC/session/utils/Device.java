package UHC.session.utils;

public class Device {

    public static String fromId(int id) {
        return switch(id){
            case 0 -> "Unknown";
            case 1 -> "Android";
            case 2 -> "iOS";
            case 3 -> "OSX";
            case 4 -> "Amazon";
            case 5 -> "GearVR";
            case 6 -> "Hololens";
            case 7 -> "Win10";
            case 8 -> "Win32";
            case 9 -> "Dedicated";
            case 10 -> "TVOS";
            case 11 -> "PlayStation";
            case 12 -> "Nintendo";
            case 13 -> "Xbox";
            case 14 -> "Windows Phone";
            default -> "Unrecognized";
        };
    }
}
