package UHC.session.utils;

public class DeviceModel {

    public static String fromId(int id){
        return switch(id){
            case 0 -> "Unknown";
            case 1 -> "Keyboard";
            case 2 -> "Touch";
            case 3 -> "Controller";
            default -> "Unrecognized";
        };
    }
}
