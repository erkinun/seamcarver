import java.awt.Color;

/**
 * Created by unlue on 16/11/14.
 */
public class SeamCarver {

    private static final int BORDER_ENERGY = 195075;

    private Picture picture;
    private int[][] energyTable;

    public SeamCarver(Picture picture) {
        // create a seam carver object based on the given picture
        this.picture = new Picture(picture);
        this.energyTable = new int[picture.width()][picture.height()];

        //calculate the energy table
        //0,0 is upper left

        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {

                if (indexesOnBorder(i, j)) {
                    energyTable[i][j] = BORDER_ENERGY;
                }
                else {
                    //do the calculation here
                    int energy = calculateGradient(i, j);
                    energyTable[i][j] = energy;
                }

            }
        }
    }

    public Picture picture() {
            // current picture
        return picture;
    }
    public int width() {
        // width of current picture
        return picture.width();
    }
    public int height() {
        // height of current picture
        return picture.height();
    }

    public double energy(int x, int y) {
        // energy of pixel at column x and row y

        if (outsideX(x)) {
            throw new IndexOutOfBoundsException("x is out of bounds");
        }

        if (outsideY(y)) {
            throw new IndexOutOfBoundsException("y is out of bounds");
        }

        return energyTable[x][y];
    }

    public int[] findHorizontalSeam() {
            // sequence of indices for horizontal seam
        throw new IllegalStateException("Not Implemented");
    }
    public int[] findVerticalSeam() {

        double[][] distTo = new double[picture.width()][picture.height()];
        int[][] edgeTo = new int[picture.width()][picture.height()];

        for (int j = 0; j < picture.height(); j++) {
            for (int i = 0; i < picture.width(); i++) {
                distTo[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int j = 0; j < picture.width(); j++) {
            distTo[j][0] = energy(j, 0);
        }

        for (int j = 0; j < picture.height() - 1; j++) {
            for (int i = 0; i < picture.width(); i++) {
                for (int v : adjacent(i)) {

                    StdOut.println("working on row: " + j + " col: " + i);
                    StdOut.println("checking energy for row: " + (j+1) + " col: " + v);

                    relax(v, j + 1, i, distTo[i][j], distTo, edgeTo);
                }
            }
        }

        //find the shortest path

        //we have dist[row-1][] array for shortest paths calculations

        //find the min in dist[row-1][]
        //from the min, trace back to dist[0][] array

        double min = Double.MAX_VALUE;
        int minIndex = -1;
        int lastRowIndex = picture.height()-1;
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i][lastRowIndex] < min) {
                min = distTo[i][lastRowIndex];
                minIndex = i;
            }
        }

        int[] seam = new int[picture.height()];
        seam[lastRowIndex] = minIndex;
        int colIndex = minIndex;
        for (int i = lastRowIndex - 1; i >= 0; i--) {
            int index = edgeTo[colIndex][i+1];
            colIndex = index % picture.width();
            seam[i] = colIndex;
        }

        return seam;

    }

    public void removeHorizontalSeam(int[] seam) {
        // remove horizontal seam from current picture

        checkSeam(seam, SeamAlignment.HORIZONTAL);

        if (picture.height() <= 1) {
            throw new IllegalArgumentException("picture height is small: " + picture.height());
        }

        throw new IllegalStateException("Not Implemented");
    }

    public void removeVerticalSeam(int[] seam) {
        // remove vertical seam from current picture

        checkSeam(seam, SeamAlignment.VERTICAL);

        if (picture.width() <= 1) {
            throw new IllegalArgumentException("picture width is small: " + picture.width());
        }

        throw new IllegalStateException("Not Implemented");
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }

    private boolean outsideY(int y) {
        return y < 0 || y >= picture.height();
    }

    private boolean outsideX(int x) {
        return x < 0 || x >= picture.width();
    }

    private void checkSeam(int[] seam, SeamAlignment seamAlignment) {
        if (seam == null) {
            throw new NullPointerException("seam array is null");
        }

        if (seamAlignment.equals(SeamAlignment.HORIZONTAL)) {
            if (seam.length != picture.width()) {
                throw new IllegalArgumentException("seam length different from width");
            }
        }

        if (seamAlignment.equals(SeamAlignment.VERTICAL)) {
            if (seam.length != picture.height()) {
                throw new IllegalArgumentException("seam length different from height");
            }
        }

        checkValidity(seam);
    }

    private void checkValidity(int[] seam) {
        throw new IllegalStateException("Not implemented");
    }

    private boolean indexesOnBorder(int i, int j) {
        return (i == 0 || i == picture.width() -1) || (j == 0 || j == picture.height() - 1);
    }

    private int calculateGradient(int i, int j) {

        Color left;
        Color right;

        left = picture.get(i - 1, j);
        right = picture.get(i + 1, j);

        int energyH = computeEnergy(left, right);

        left = picture.get(i, j - 1);
        right = picture.get(i, j + 1);

        int energyV = computeEnergy(left, right);

        return energyH + energyV;
    }

    public int computeEnergy(Color left, Color right) {
        int rDiff = left.getRed() - right.getRed();
        int gDiff = left.getGreen() - right.getGreen();
        int bDiff = left.getBlue() - right.getBlue();

        return (rDiff*rDiff) + (gDiff*gDiff) + (bDiff*bDiff);
    }

    public int[] adjacent(int i) {

        int[] adj;

        if (i == 0 || i == picture.width() - 1) {
            adj = new int[2];

            if (i == 0) {
                adj[0] = i;
                adj[1] = i+1;
            }
            else {
                adj[0] = i-1;
                adj[1] = i;
            }
        }
        else {
            adj = new int[3];

            adj[0] = i-1;
            adj[1] = i;
            adj[2] = i+1;
        }

        return adj;

    }


    private void relax(int i, int j, int parentCol, double distParent, double[][] distTo, int[][] edgeTo) {
        if (distTo[i][j] > distParent + energy(i, j)) {
            distTo[i][j] = distParent + energy(i, j);
            StdOut.println("assigning: " + parentCol + " for row: " + i + " col: " + j);
            edgeTo[i][j] = (picture.width() * (j-1)) + parentCol;
        }
    }

    private static enum  SeamAlignment {
        HORIZONTAL,
        VERTICAL;
    }
}
