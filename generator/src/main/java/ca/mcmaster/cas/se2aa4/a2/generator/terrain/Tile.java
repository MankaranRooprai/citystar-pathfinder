package ca.mcmaster.cas.se2aa4.a2.generator.terrain;

public enum Tile {
    LAND("235,223,167"), OCEAN("54,141,255"), LAGOON("129,173,125"), BEACH("245,243,152");


    public final String color;

    private Tile(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

}
