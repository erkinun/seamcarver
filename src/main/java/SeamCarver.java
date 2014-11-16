/**
 * Created by unlue on 16/11/14.
 */
public class SeamCarver {

    private Picture picture;

    public SeamCarver(Picture picture) {
        // create a seam carver object based on the given picture
        this.picture = picture;
    }
    public Picture picture() {
            // current picture
        return picture;
    }
    public int width() {
            // width of current picture
        throw new IllegalStateException("Not Implemented");
    }
    public int height() {
            // height of current picture
        throw new IllegalStateException("Not Implemented");
    }

    public double energy(int x, int y) {
            // energy of pixel at column x and row y
        throw new IllegalStateException("Not Implemented");
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
        throw new IllegalStateException("Not Implemented");
    }
    public void removeVerticalSeam(int[] seam) {
            // remove vertical seam from current picture
        throw new IllegalStateException("Not Implemented");
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}
