import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_Task_02 {
    Task_02 task02;

    @Before
    public void init() {
        task02 = new Task_02();
    }

    @Test
    public void test01() {
        int[] aOut = {1,7};
        int[] aIn = {1,2,4,4,2,3,4,1,7};
        Assert.assertArrayEquals(aOut,task02.after4(aIn));
    }

    @Test
    public void test02() {
        int[] aOut = {5,9,2,7};
        int[] aIn = {4,5,9,2,7};
        Assert.assertArrayEquals(aOut,task02.after4(aIn));
    }

    @Test
    public void test03() {
        int[] aOut = {};
        int[] aIn = {1,7,4,3,2,1,4};
        Assert.assertArrayEquals(aOut,task02.after4(aIn));
    }

    @Test (expected = RuntimeException.class)
    public void test04() {
        int[] aIn = {1,2,2,3,6,1,7};
        task02.after4(aIn);
    }


}
