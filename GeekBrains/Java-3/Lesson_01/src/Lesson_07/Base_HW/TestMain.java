package Lesson_07.Base_HW;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestMain {

    public static void start(String str) {
        try {
            start(Class.forName(str));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class c) {
        Method beforeSuite = null;
        Method afterSuite = null;
        ArrayList<Method> tests = new ArrayList<>();

        TestClass tc = new TestClass();
        
        Method[] methods = c.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuite == null) {
                    beforeSuite = m;
                } else {
                    throw new RuntimeException();
                }
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuite == null) {
                    afterSuite = m;
                } else {
                    throw new RuntimeException();
                }
            } else if (m.isAnnotationPresent(Test.class)) {
                tests.add(m);
            }
        }
        sortByPriority(tests);
        for (Method m : tests) {
            try {
                if (beforeSuite != null){
                    beforeSuite.invoke(tc);
                }
                m.invoke(tc);
                if (afterSuite != null) {
                    afterSuite.invoke(tc);
                }

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    public static void sortByPriority (ArrayList<Method> list) {
        Method tempMethod;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).getAnnotation(Test.class).priority() < list.get(j + 1).getAnnotation(Test.class).priority()) {
                    tempMethod = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tempMethod);
                }
            }
        }
    }
}
