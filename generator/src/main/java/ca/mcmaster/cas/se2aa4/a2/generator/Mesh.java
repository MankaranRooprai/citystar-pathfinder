package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    List<Vertex> vertices = new ArrayList<>();
    List<Vertex> verticesColored = new ArrayList<>();

    List<Segment> segments = new ArrayList<>();
    List<Segment> segmentsColored = new ArrayList<>();

    List<Polygon> polygons = new ArrayList<>();
    List<Polygon> polygonsColored = new ArrayList<>();

    List<Vertex> centroids = new ArrayList<>();
    List<Vertex> centroidsColored = new ArrayList<>();

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
        segments.add(Segment.newBuilder().setV1Idx(vertindex1).setV2Idx(vertindex2).build());
    }

    public void createPolygon(int segment1, int segment2, int segment3, int segment4) {
        polygons.add(Polygon.newBuilder().addSegmentIdxs(segment1).addSegmentIdxs(segment2).addSegmentIdxs(segment3)
                .addSegmentIdxs(segment4)
                .build());
    }

    public void createCentroid(int centroid_x, int centroid_y) {
        centroids.add(Vertex.newBuilder().setX((double) centroid_x).setY((double) centroid_y).build());
    }

    public void createPolygon(int segment1, int segment2, int segment3, int segment4, List neighbours) {
        polygons.add(Polygon.newBuilder().addSegmentIdxs(segment1).addSegmentIdxs(segment2).addSegmentIdxs(segment3)
                .addSegmentIdxs(segment4).addAllNeighborIdxs(neighbours)
                .build());
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

    public void createPolygonColor(Polygon p, String colorCode) {
        polygonsColored
                .add(Polygon.newBuilder(p)
                        .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build())
                        .build());
    }

    public void createCentroidColor(Vertex c, String colorCode) {
        centroidsColored.add(Vertex.newBuilder(c)
                .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build()).build());
    }

    public void setCentroidIdx() {
        int counter = 0;

        for (Polygon p : polygonsColored) {
            polygonsColored.set(counter, Polygon.newBuilder(p).setCentroidIdx(counter + 625).build());
            ++counter;
        }

        System.out.println("UPDATED: " + polygonsColored);

    }

    public Structs.Mesh generate(List<Vertex> verticesWithColors, List<Segment> segmentsWithColors,
            List<Polygon> polygonsColored, List<Vertex> centroidsColored) {
        System.out.println("SIZE: " + vertices.size());
        System.out.println("SIZE SEGMENTS: " + segments.size());
        System.out.println("SIZE POLYGONS: " + polygons.size());

        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(verticesWithColors)
                .addAllSegments(segmentsWithColors).addAllPolygons(polygonsColored).addAllVertices(centroidsColored)
                .build();
        return mesh;
    }

}