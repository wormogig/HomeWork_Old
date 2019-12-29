package Lesson_02;

public class Additional_Homework_02_01 {

    public static void main(String[] args) {
        int hour;
        for (DayOfWeek day : DayOfWeek.values()) {
            hour = getWorkingHours(day);
            if (hour > 0) {
                System.out.println(day.name() + " - " + hour + " часов до конца рабочей недели");
            } else {
                System.out.println(day.name() + " - выходной");
            }

        }
    }

    public static int getWorkingHours (DayOfWeek day) {
        int hours = 40 - 8 * day.ordinal();
        return hours >= 0 ? hours : 0;
    }
}

enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SARTURDAY, SUNDAY
}

