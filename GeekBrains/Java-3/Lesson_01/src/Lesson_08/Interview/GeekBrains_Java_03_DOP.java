package DZ;

import java.util.Arrays;
import java.util.Scanner;

public class GeekBrains_Java_03_DOP {

static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Данная программа заполнит матрицу по спирали");
        System.out.println("Задайте количество столбцов матрицы");
        int numCol = inputRange(5,10);
        System.out.println("Задайте количество строк матрицы");
        int numRow = inputRange(5,10);
        int[][] matrix = new int[numRow][numCol];
        matrix = spiral(matrix);
        for (int i =0; i < numRow; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }


    }

    public static int inputRange (int min, int max) {
        int range = 0;
        do {
            System.out.printf("Введите число от %d до %d \n", min, max);
            range = scanner.nextInt();
        } while (range < min || range > max);
        return range;
    }


    public static int[][] spiral (int mat[][]) {
        String[] moves = {"Right", "Down", "Left", "Up"};
        String move = moves[0];
        int colT = 0;
        int rowT = 0;
        for (int i = 1; i < mat.length * mat[0].length; i++){
            mat[rowT][colT] = i;
            boolean step = true;
            do {
                switch (move) {
                    case "Right":
                        if (colT < mat[0].length - 1){
                            if (mat[rowT][colT + 1] == 0) {
                                colT++;
                                step = false;
                            } else {
                                move = moves[1];
                            }
                        } else {
                            move = moves[1];
                        }
                        break;
                    case "Down":
                        if (rowT < mat.length - 1){
                            if (mat[rowT + 1][colT] == 0) {
                                rowT++;
                                step = false;
                            } else {
                                move = moves[2];
                            }
                        } else {
                            move = moves[2];
                        }
                        break;
                    case "Left":
                        if (colT > 0){
                            if (mat[rowT][colT - 1] == 0) {
                                colT--;
                                step = false;
                            } else {
                                move = moves[3];
                            }
                        } else {
                            move = moves[3];
                        }
                        break;
                    case "Up":
                        if (rowT > 0){
                            if (mat[rowT - 1][colT] == 0) {
                                rowT--;
                                step = false;
                            } else {
                                move = moves[0];
                            }
                        } else {
                            move = moves[0];
                        }
                        break;

                }
            } while (step);
        }
        mat[rowT][colT] =  mat.length * mat[0].length;
        return mat;
    }


}
