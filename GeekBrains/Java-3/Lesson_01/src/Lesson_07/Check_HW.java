package Lesson_07;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Check_HW {

    public static void main(String[] args) {
        try {
            check_HW("C:/ForCheck");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void check_HW (String path) throws MalformedURLException, ClassNotFoundException {
        File dir = new File(path);
        String[] files = dir.list();
        for (String file:files) {
            String className = file.split("\\.")[0];
            Class cl = URLClassLoader.newInstance(new URL[]{dir.toURL()}).loadClass(className);
            checkClass(cl);
        }
    }

    public static void checkClass(Class cl) {

        System.out.println("Тестирование класса: " + cl);
        Constructor[] cons = cl.getConstructors();
        System.out.println("Количество конструкторов: " + cons.length);
        System.out.println();
        Constructor conForUse = null;
        for (Constructor con: cons
             ) {
            Class[] types = con.getParameterTypes();
            System.out.println("У конструктора " + con.getName() + " следующие параметры:");
            for (Class c: types
                 ) {
                System.out.println(c);
            }
            System.out.println();
            if (types.length == 1 && types[0] == String.class) {
                try {
                    conForUse = cl.getConstructor(String.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if (conForUse != null) {
                Object objForTest = conForUse.newInstance("Tomas");
                Method[] methods =  cl.getDeclaredMethods();
                for (Method m : methods
                        ) {
                    if (m.getParameterTypes().length == 0) {
                        System.out.println("Метод без параметров: " + m);
                        m.invoke(objForTest);
                    }
                    if (m.getParameterTypes().length == 1 && m.getParameterTypes()[0] == int.class) {
                        System.out.println("Метод с одним параметром int: " + m);
                        m.invoke(objForTest, 5);
                    }

                }
            }



        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------");
    }
}
