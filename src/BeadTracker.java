public class BeadTracker {

    public static void main(String[] args) {

        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        // Creates an object of picture and Blob to clarify images
        Picture[] pictures = new Picture[args.length - 3];
        Blob[][] blobs = new Blob[args.length - 3][];

        // Creates an object of BeadFinder to find blobs that are in range (bigger than "min") and consider them as bead
        for (int i = 0; i < args.length - 3; i++) {
            pictures[i] = new Picture(args[i + 3]);
            BeadFinder beadFinder = new BeadFinder(pictures[i], tau);
            blobs[i] = beadFinder.getBeads(min);
        }

        // Analyses blobs tracking one by one between two images in order to store all possible tracks and
        // checks if the shortest track distance is between minDistance and delta, then print it
        for (int i = 0; i < blobs.length - 1; i++) {

            Blob[] currentBlobs = blobs[i];
            Blob[] nextBlobs = blobs[i + 1];

            for (Blob nextBlob : nextBlobs) {

                Blob nearestBlob = null;
                double minDistance = Double.MAX_VALUE;

                for (Blob currentBlob : currentBlobs) {
                    double distance = currentBlob.distanceTo(nextBlob);
                    if (distance < minDistance && distance <= delta) {
                        minDistance = distance;
                        nearestBlob = currentBlob;
                    }
                }
                if (nearestBlob != null) {
                    String str = String.format("%.4f", minDistance);
                    System.out.println(str);
                }
            }
        }
    }
}