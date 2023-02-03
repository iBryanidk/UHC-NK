package UHC.api.scoreboard.packet.data;

public enum DisplaySlot {
    SIDEBAR("sidebar"),
    LIST("list"),
    DUMMY("dummy"),
    BELOW_NAME("belowname");

    private final String name;

    DisplaySlot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
