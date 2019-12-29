package Additional_Homework_02;


public class Master {

    String name;
    String[] catsName;

    public Master(String name) {
        this.name = name;
    }

    public void listCatsName (Cat_Light...cats) {
        catsName = new String[cats.length];
        for (int i = 0; i < cats.length; i++) {
            catsName[i]  = cats[i].getName();
        }
    }

    public void info() {
        System.out.println("Хозяин: " + name);
        System.out.print("Имена котов: ");
        for (String cat : catsName) {
            System.out.print(cat + " ");
        }
    }
}
