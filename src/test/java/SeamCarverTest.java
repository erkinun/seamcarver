import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeamCarverTest {

    private SeamCarver seamCarver;

    @Before
    public void setUp() {
        seamCarver = new SeamCarver(new Picture("files/3x7.png"));

        Assert.assertNotNull(seamCarver);
    }

    @Test
    public void testPicture() throws Exception {

    }

    @Test
    public void testWidth() throws Exception {

    }

    @Test
    public void testHeight() throws Exception {

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

    @Test
    public void testRemoveHorizontalSeam() throws Exception {

    }

    @Test
    public void testRemoveVerticalSeam() throws Exception {

    }
}