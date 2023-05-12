import java.awt.*;
import java.util.ArrayList;

public class BeadFinder {

    private int m_width;
    private int m_height;
    private double m_tau;
    private Picture m_picture;

    // Constructor
    public BeadFinder(Picture picture, double tau) {
        m_picture = picture;
        m_width = picture.width();
        m_height = picture.height();
        m_tau = tau;
    }
    // Method to get the beads using minimum mass
    public Blob[] getBeads(int min) {

        ArrayList<Blob> blobs = new ArrayList<>();
        boolean[][] marked = new boolean[m_width][m_height];

        for (int i = 0; i < m_width; i++) {
            for (int j = 0; j < m_height; j++) {
                if (marked[i][j]) {
                    continue;
                }

                Blob blob = new Blob();
                traverseBlob(i, j, blob, marked);

                if (blob.mass() >= min) {
                    blobs.add(blob);
                }
            }
        }
        // Convert the Arraylist of blobs to an array of blobs and return it
        Blob[] result = new Blob[blobs.size()];
        for (int i = 0; i < blobs.size(); i++) {
            result[i] = blobs.get(i);
        }
        return result;
    }

    private void traverseBlob(int x, int y, Blob blob, boolean[][] marked) {

        if (x < 0 || x >= m_width || y < 0 || y >= m_height || marked[x][y]) {
            return;
        }

        Color color = m_picture.get(x, y);
        double luminance = color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114;

        if (luminance > m_tau) {
            blob.add(x, y);
            marked[x][y] = true;

            // Recursively traverse the neighboring pixels
            traverseBlob(x-1, y, blob, marked);
            traverseBlob(x+1, y, blob, marked);
            traverseBlob(x,y-1, blob, marked);
            traverseBlob(x,y+1, blob, marked);
        }
    }

    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        String fileName = args[2];

        Picture picture = new Picture(fileName);
        BeadFinder beadFinder = new BeadFinder(picture, tau);
        Blob[] blobs = beadFinder.getBeads(min);

        for (Blob blob : blobs) {
            System.out.println(blob.toString());
        }
    }
}
