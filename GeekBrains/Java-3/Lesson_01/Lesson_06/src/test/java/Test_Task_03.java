import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_Task_03 {
    Task_03 task03;

    @Before
    public void init() {
        task03 = new Task_03();
    }

    @Test
    public void test01() {
        int[] aIn = {1,1,4,4,4,4,4,1,1};
        Assert.assertTrue(task03.arrOneFour(aIn));
    }

    @Test
    public void test02() {
        int[] aIn = {1,1,1,1};
        Assert.assertFalse(task03.arrOneFour(aIn));
    }

    @Test
    public void test03() {
        int[] aIn = {4,4,4,4,4};
        Assert.assertFalse(task03.arrOneFour(aIn));
    }

    @Test (expected = RuntimeException.class)
    public void test04() {
        int[] aIn = {1,2,3,4,5};
        task03.arrOneFour(aIn);
    }

}
