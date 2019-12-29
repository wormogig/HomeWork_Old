public class Task_03 {

    public boolean arrOneFour (int[] arr) {
        boolean one = false, four = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                one = true;
            } else if (arr[i] == 4) {
                four = true;
            } else {
                throw new RuntimeException();
            }
        }
        return (one && four);

    }
}
