package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Mesh {
    private int width = 500;
    private int height = 500;
    private int square_size = 20;

    List<Vertex> vertices = new ArrayList<>();
    List<Vertex> verticesColored = new ArrayList<>();

    List<Segment> segments = new ArrayList<>();
    List<Segment> segmentsColored = new ArrayList<>();

    HashMap<Vertex, Vertex> xy = new HashMap<>();

    int count = 0;

    public double getX(Vertex v) {
        double valuex = v.getX();
        return valuex;
    }

    public double getY(Vertex v) {
        double valuey = v.getY();
        return valuey;
    }

    // public int getV1Idx(Segment s) {
    // return s.getV1Idx();
    // }

    // public int getV2Idx(Segment s) {
    // return s.getV2Idx();
    // }

    public void setX(Vertex v, double x) {
        v.newBuilderForType().setY(x);
    }

    public void setY(Vertex v, double y) {
        v.newBuilderForType().setY(y);
    }

    public Property getProperty(Vertex v, int index) {
        return v.getProperties(index);
    }

    public Segment getSegment(Segment s) {
        return s;
    }

    public String getValue(Property p) {
        return p.getValue();
    }

    public void createVertex(int x, int y) {
        vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
    }

    public void createSegment(int vertindex1, int vertindex2) {
        if (!segments.contains(Segment.newBuilder().setV1Idx(vertindex1).setV2Idx(vertindex2).build())) {
            segments.add(Segment.newBuilder().setV1Idx(vertindex1).setV2Idx(vertindex2).build());
        }
    }

    public Property createProperty(String colorCode) {
        Property p = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return p;
    }

    public void createVertexColor(Vertex v, String colorCode) {
        verticesColored.add(Vertex.newBuilder(v)
                .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build()).build());
    }

    public void createSegmentColor(Segment s, String colorCode) {
        segmentsColored.add(Segment.newBuilder(s)
                .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build()).build());
    }

    public void createSegments() {
        for (int i = 0; i < verticesColored.size() - 2; i++) {
            int temp = 0;
            segments.add(Segment.newBuilder().setV1Idx(temp).setV2Idx(temp + i).build());
            segments.add(Segment.newBuilder().setV1Idx(temp).setV2Idx(temp + i).build());
        }
    }

    public Structs.Mesh generate(List<Vertex> verticesWithColors, List<Segment> segmentsWithColors) {
        System.out.println("SIZE: " + vertices.size());
        System.out.println("SIZE SEGMENTS: " + segments.size());

        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments)
                .addAllSegments(segmentsWithColors).build();
        return mesh;
    }

}