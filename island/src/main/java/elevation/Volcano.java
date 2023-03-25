package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Island;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.StringUtil;
import whitaker.WhitakerDiagram;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Volcano implements Elevation{
    private RectangularShape island;
    private String biome;
    private Tile tile;
    private TileColor tileRaise;
    public Volcano(RectangularShape island, String biome, Tile tile){
        this.island = island;
        this.biome = biome;
        this.tile = tile;
    }
    
    public void setBiome(String biome) {
        this.biome = biome;
    }

    @Override
    public String gradient() {
        String colour = "";
        if (island.contains(tile.centroidX, tile.centroidY)) {
            if (biome.equals("Arctic")) {
                colour = tileRaise.ARCTIC.color;
                System.out.println("Here right after getting arctic.color: "+ colour);
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[0] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[0]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));                colour = String.join(",", colourArray);
                colour = String.join(",", colourArray);
                System.out.println("After converting colour to string: "+colour);
            } else if (biome.equals("Desert")) {
                colour = tileRaise.DESERT.color;
                System.out.println("Here right after getting arctic.color: "+ colour);
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[2] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[2]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colour = String.join(",", colourArray);
                System.out.println("After converting colour to string: "+colour);
            } else if (biome.equals("Tropical")) {
                colour = tileRaise.TROPICAL.color;
                System.out.println("Here right after getting arctic.color: "+ colour);
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/10), 255));
                colourArray[2] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[2]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/10), 255));
                colourArray[0] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[0]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/10), 255));                colour = String.join(",", colourArray);
                colour = String.join(",", colourArray);
                System.out.println("After converting colour to string: "+colour);
            } else if (biome.equals("Rocky")) {
                colour = tileRaise.ROCKY.color;
            } else if (biome.equals("Muddy")) {
                colour = tileRaise.MUDDY.color;
            } else if (biome.equals("Savannah")) {
                colour = tileRaise.SAVANNAH.color;
            } else if (biome.equals("Taiga")) {
                colour = tileRaise.TAIGA.color;
            } else if (biome.equals("Beach")) {
                colour = tileRaise.BEACH.color;
            } else if (biome.equals("Glacier")) {
                colour = tileRaise.GLACIER.color;
            }
        } else {
            colour = tileRaise.OCEAN.color;
        }

        return colour;

    }

    @Override
    public void temperature() {

    }

    @Override
    public int assignElevation() {
        int baseelevation = 10000;
//        if (this.biome.equals("Arctic")) {
//            baseelevation = 10000;
//        } else if (this.biome.equals("Desert")) {
//            baseelevation =100;
//        } else if (this.biome.equals("Tropical")) {
//            baseelevation = 1000;
//        }
        int elevation = (int) (baseelevation/Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX));
        return elevation;
    }
}