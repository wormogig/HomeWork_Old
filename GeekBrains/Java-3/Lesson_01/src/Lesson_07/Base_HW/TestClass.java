package Lesson_07.Base_HW;


public class TestClass {

    @Test
    public void test1() {
        System.out.println("Test_01, prior = 5");
    }

    @Test(priority = 4)
    public void test2() {
        System.out.println("Test_02, prior = 4");
    }

    @Test(priority = 7)
    public void test3() {
        System.out.println("Test_03, prior = 7");
    }

    @Test
    public void test4() {
        System.out.println("Test_04, prior = 5");
    }

    @Test(priority = 10)
    public void test5() {
        System.out.println("Test_05, prior = 10");
    }

    @BeforeSuite
    public void bs() {
        System.out.println("Before test");
    }
    @AfterSuite
    public void as() {
        System.out.println("After test");
        System.out.println();
    }

//    @BeforeSuite
//    public void bs1() {
//        System.out.println("Before test");
//    }

    public static void main(String[] args) {
        TestMain.start("Lesson_07.Base_HW.TestClass");
    }
}
