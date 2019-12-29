package Additional_Homework_02;

public class Main {

    public static void main(String[] args) {
        Cat_Light[] cats = {new Cat_Light("Томас"),
                new Cat_Light("Маруся"),
                new Cat_Light("Васька"),
                new Cat_Light("Тиша"),
                new Cat_Light("Морис")};
        Master master = new Master("Боб");
        master.listCatsName(cats);
        master.info();

    }
}
