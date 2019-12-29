public class Task_02 {

    //
    // Метод для задания 2
    //
    public int[] after4 (int[] arr) {
        int last4 = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) 
                last4 = i;
        }
        if (last4 == -1) throw new RuntimeException();
        int[] finArr = new int[arr.length - last4 - 1];
        for (int i = 0; i < finArr.length; i++) {
            finArr[i] = arr[last4 + i + 1];
        }
        return finArr;
    }


}
