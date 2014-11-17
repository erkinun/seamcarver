import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class SeamCarverTest {

    private SeamCarver seamCarver;

    @Before
    public void setUp() {
        seamCarver = new SeamCarver(new Picture("files/3x7.png"));

        Assert.assertNotNull(seamCarver);
    }

    @Test
    public void testPicture() throws Exception {
        Assert.assertNotNull(seamCarver.picture());
    }

    @Test
    public void testWidth() throws Exception {
        Assert.assertEquals(3, seamCarver.width());
    }

    @Test
    public void testHeight() throws Exception {
        Assert.assertEquals(7, seamCarver.height());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnergyXOutOfBounds() throws Exception {
        seamCarver.energy(20, 20);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnergyYOutOfBounds() throws Exception {
        seamCarver.energy(20, 20);
    }

    @Test
    public void testEnergy() throws Exception {

    }

    @Test
    public void testFindHorizontalSeam() throws Exception {

    }

    @Test
    public void testFindVerticalSeam() throws Exception {

    }


    @Test(expected = NullPointerException.class)
    public void testHorizontalSeamWithNullArray() throws Exception {
        seamCarver.removeHorizontalSeam(null);
    }

    @Test(expected = NullPointerException.class)
    public void testVerticalSeamWithNullArray() throws Exception {
        seamCarver.removeVerticalSeam(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHorizontalSeamLengthDifferent() throws Exception {

        int[] seam = new int[4];

        seamCarver.removeHorizontalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHorizontalSeamLengthDifferent2() throws Exception {

        int[] seam = new int[0];

        seamCarver.removeHorizontalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHorizontalSeamLengthDifferentVertical() throws Exception {

        int[] seam = new int[10];

        seamCarver.removeVerticalSeam(seam);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHorizontalSeamLengthDifferentVertical2() throws Exception {

        int[] seam = new int[0];

        seamCarver.removeVerticalSeam(seam);
    }

    @Test
    public void testRemoveHorizontalSeam() throws Exception {

    }

    @Test
    public void testRemoveVerticalSeam() throws Exception {

    }

    @Test
    public void testComputeEnergy() throws Exception {
        Color c1 = new Color(255, 203, 51);
        Color c2 = new Color(255, 205, 255);

        int energy = seamCarver.computeEnergy(c1, c2);

        Assert.assertEquals(41620, energy);
    }
}