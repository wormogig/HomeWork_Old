package Lesson_04;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Additional_Homework_02 {
    public static void main(String[] args) {
        new MyWindow();
    }
}


class MyWindow extends JFrame {
    // Количество строк в таблице
    private int NUM_ROW = 4;


    public MyWindow() {

        setTitle("Сортировка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 200);

        // Инициализация массива с данными и названием столбцов
        String[][] data = new String[NUM_ROW][3];
        String[] header = {"Имя","Возраст","Зарплата"};

        // Инициализация таблицы
        final JTable jTab = new JTable(data, header);
        final JTableHeader jth = jTab.getTableHeader();
        JScrollPane jscr = new JScrollPane(jTab);

        // Добавление таблицы
        add(jscr, BorderLayout.CENTER);
        setVisible(true);

        // Первоначальное заполение таблицы, чтобы при тестах не заполнять ее полностью
        Human[] humansInit = {new Human("Алексей",30,51500),
                new Human("Ольга",32,80000),
                new Human("Екатерина",31,70000),
                new Human("Григорий", 70,15000)};
        fillTable(humansInit, jTab);

        // Слушатель на заголовок таблицы
        jth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Определение выбранного столбца
                int x = jth.columnAtPoint(e.getPoint());
                // Заполнение массива данными из таблицы
                Human[] humans = fillHumans(jTab);
                // Выбор действия в зависимости от выбранного столбца
                switch (x){
                    case 0:
                        sortByName(humans);
                        break;
                    case 1:
                        sortByAge(humans);
                        break;
                    case 2:
                        sortBySalary(humans);
                        break;
                }
                // Заполнение таблицы информацией из массива
                fillTable(humans, jTab);
            }
        });
    }

    // Заполнение массива из таблицы
    public Human[] fillHumans (JTable jTab) {
        Human[] humans = new Human[NUM_ROW];
        for (int i = 0; i < NUM_ROW; i++) {
            humans[i] = fillHuman(jTab, i);
        }
        return humans;
    }
    public Human fillHuman (JTable jTab, int row) {
        String name = jTab.getValueAt(row, 0).toString();
        int age = Integer.parseInt(jTab.getValueAt(row, 1).toString()) ;
        int salary = Integer.parseInt(jTab.getValueAt(row, 2).toString());
        return new Human(name, age, salary);
    }


    // Заполнение таблицы из массива
    public void fillTable (Human[] humans, JTable jTab) {
        for (int i = 0; i < NUM_ROW; i++) {
            fillRow(humans[i], jTab, i);
        }
    }
    public void fillRow (Human human, JTable jTab, int row) {
        jTab.setValueAt(human.getName(), row, 0);
        jTab.setValueAt(String.valueOf(human.getAge()), row, 1);
        jTab.setValueAt(String.valueOf(human.getSalary()), row, 2);
    }


    // Перемещение объектов внутри массива
    public void changeHum (Human[] humans, int num1, int num2) {
        Human humanTemp;
        humanTemp = humans[num1];
        humans[num1] = humans[num2];
        humans[num2] = humanTemp;
    }

    // Сортировка по именам
    public void sortByName (Human[] humans) {
        for (int i = 1; i < NUM_ROW; i++) {
            for (int j = i; j >= 1; j--) {
                if (compare(humans[j-1].getName(), humans[j].getName())) {
                    changeHum(humans,j,j-1);
                }
            }
        }
    }

    // Сравнение String'ов
    public boolean compare (String name1, String name2) {
        int l = name1.length() < name2.length() ? name1.length() : name2.length();
        name1 = name1.toLowerCase();
        name2 = name2.toLowerCase();
        for (int i = 0; i < l; i++) {
            if (name1.charAt(i) > name2.charAt(i)) {
                return true;
            } else if (name1.charAt(i) < name2.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    // Сортировка по возрасту
    public void sortByAge (Human[] humans) {
        for (int i = 1; i < NUM_ROW; i++) {
            for (int j = i; j >= 1; j--) {
                if (humans[j].getAge() < humans[j-1].getAge()) {
                    changeHum(humans,j,j-1);
                }
            }

        }
    }


    // Сортировка по зарплате
    public void sortBySalary (Human[] humans) {
        for (int i = 1; i < NUM_ROW; i++) {
            for (int j = i; j >= 1; j--) {
                if (humans[j].getSalary() < humans[j-1].getSalary()) {
                    changeHum(humans,j,j-1);
                }
            }
        }
    }

}



// Класс Человек
class Human {
    private String name;
    private int age;
    private int salary;

    public Human(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

}