import java.awt.*;
import java.util.ArrayList;

public class Blob {
    public static Picture picture = new Picture("frame00000.jpg");
    public static double tau = 180;
    //public static double min = 25;
    private double m_centerX;
    private double m_centerY;
    private int m_mass;

    // Constructor
    public Blob(){
        m_mass = 0;
    }

    // Method to add a pixel to a Blob
    public void add(int x, int y){

        m_centerX = (m_centerX * m_mass + x) / (m_mass + 1);
        m_centerY = (m_centerY * m_mass + y) / (m_mass + 1);
        m_mass++;
    }

    // Get the mass of the Blob
    public int mass(){
        return m_mass;
    }

    // Calculate the distance between two Blobs
    public double distanceTo(Blob that){

        double thatCenterX = that.m_centerX;
        double thatCenterY = that.m_centerY;

        double dX = m_centerX - thatCenterX;
        double dY = m_centerY - thatCenterY;

        return Math.sqrt(Math.pow(dX,2) + Math.pow(dY, 2));

    }
    // Return a string representation of Blob
    public String toString(){

        return String.format("%d (%.4f, %.4f)", m_mass, m_centerX, m_centerY);
    }

    public static void main(String[] args) {

        Color foregroundColor = Color.WHITE;
        Color backgroundColor = Color.BLACK;

        for (int x = 0; x < picture.width(); x++) {
            for (int y = 0; y < picture.height(); y++) {

                Color pixel = picture.get(x, y);
                double luminance = 0.299 * pixel.getRed() + 0.587 * pixel.getGreen() + 0.114 * pixel.getBlue();
                if (luminance < tau) {
                    picture.set(x, y, backgroundColor);
                } else {
                    picture.set(x, y, foregroundColor);
                }
            }
        }
        //picture.show();

        // Find Blobs in the image
        ArrayList<Blob> blobs = new ArrayList<>();
        boolean[][] marked = new boolean[picture.width()][picture.height()];

        for (int x = 0; x < picture.width(); x++) {
            for (int y = 0; y < picture.height(); y++) {
                if (!marked[x][y] && picture.get(x, y).equals(foregroundColor)) {
                    Blob blob = new Blob();
                    traverseBlob(x, y, marked, foregroundColor, blob);

                    blobs.add(blob);
                }
            }
        }

        for (Blob blob : blobs) {
            System.out.println(blob.toString());
        }
    }
    // Recursive function to traverse a Blob and add its pixels to the Blob object
    public static void traverseBlob(int x, int y, boolean[][] marked, Color foregroundColor, Blob blob) {

        // If the current pixel is out of bounds, has already been visited, or is not the foreground color, return and do not add it to the blob
        if (x < 0 || x >= picture.width() || y < 0 || y >= picture.height() || marked[x][y] || !picture.get(x, y).equals(foregroundColor)) {
            return;
        }
        marked[x][y] = true;
        blob.add(x, y);

        // Traverse neighboring pixels in all four directions to build the blob
        traverseBlob(x-1, y, marked, foregroundColor, blob);
        traverseBlob(x+1, y, marked, foregroundColor, blob);
        traverseBlob(x, y-1, marked, foregroundColor, blob);
        traverseBlob(x, y+1, marked, foregroundColor, blob);
    }
}