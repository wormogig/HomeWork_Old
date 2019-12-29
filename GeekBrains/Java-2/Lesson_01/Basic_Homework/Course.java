package GB_Java2_01;

import GB_Java2_01.Obstacle.Obstacle;

public class Course {

    Obstacle[] course;

    public Course(Obstacle...course) {
        this.course = course;
    }

    public void doIt (Team team) {
        for (Competitor c : team.team) {
            for (Obstacle o : this.course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }


}
