package GB_Java2_01;

import GB_Java2_01.Animal.Cat;
import GB_Java2_01.Animal.Dog;
import GB_Java2_01.Human.Human;
import GB_Java2_01.Obstacle.Cross;
import GB_Java2_01.Obstacle.Wall;
import GB_Java2_01.Obstacle.Water;

public class Main {
    public static void main(String[] args) {

        Course course = new Course(new Cross(80),
                new Wall(2),
                new Wall(1),
                new Cross(120),
                new Water(5));
        Team alpha = new Team("Альфа",
                new Human("Боб"),
                new Cat("Том"),
                new Dog("Рекс"),
                new Dog("Бобик"));
        course.doIt(alpha);
        alpha.info();
        alpha.teamOnDistance();
    }
}
