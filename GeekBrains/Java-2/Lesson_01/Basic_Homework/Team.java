package GB_Java2_01;

public class Team {
    final int NUM_OF_MEMBERS = 4;

    String teamName;
    Competitor[] team = new Competitor[4];



    public Team(String teamName, Competitor...team) {
        this.teamName = teamName;
        for (int i = 0; i < NUM_OF_MEMBERS; i++) {
            this.team[i] = team[i];
        }
    }

    public void info() {
        System.out.println("--------------------------------");
        System.out.println("Команда " + this.teamName);
        for (Competitor c : this.team) {
            c.info();
        }
        System.out.println("--------------------------------");

    }

    public void teamOnDistance() {
        System.out.println("--------------------------------");
        System.out.println("В команде " + this.teamName + " на дистанции остались:");
        for (Competitor c : this.team) {
            if (c.isOnDistance()) {
                c.info();
            }
        }
        System.out.println("--------------------------------");

    }





}
