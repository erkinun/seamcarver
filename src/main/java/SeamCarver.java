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
            // sequence of indices for vertical seam
        throw new IllegalStateException("Not Implemented");
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

    private static enum  SeamAlignment {
        HORIZONTAL,
        VERTICAL;
    }
}
