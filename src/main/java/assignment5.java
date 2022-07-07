
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adam Botens 
 * CSCI 4270 
 * Assignment 5
 *
 */
public class assignment5 {

    public static void main(String args[]) {
        Scanner stringIn = new Scanner(System.in);
        String first = "Z";
        String second = "Z";

        while (!isValid(first)) {
            System.out.println("Please enter first sequence (A, C, G, T): ");
            first = stringIn.nextLine();
        }

        while (!isValid(second)) {
            System.out.println("Please enter second sequence (A, C, G, T): ");
            second = stringIn.nextLine();
        }

        int[][] matrix = createMatrix(first.length() + 1, second.length() + 1);

        System.out.println(findCost(matrix, first, second));
        
        
        /*
        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[j].length; i++) {
                System.out.println(matrix[i][j]);
            }

        }
         */
    }

    private static final Pattern p = Pattern.compile("^[ACGT]+");

    public static boolean isValid(String sequence) {
        return p.matcher(sequence).matches();
    }

    //creates initial (n+1) x (m+1) matrix
    public static int[][] createMatrix(int n, int m) {
        int[][] matrix = new int[n][m];
        matrix[n - 1][m - 1] = 0;
        int count = 0;
        int count2 = 0;

        for (int j = m - 1; j >= 0; j--) {
            for (int i = n - 1; i >= 0; i--) {
                matrix[i][j] = count;
                count += 2;
            }
            count2 += 2;
            count = count2;
        }
        return matrix;
    }

    //returns cost of optimal sequence alignment
    public static int findCost(int[][] matrix, String one, String two) {

        int element1 = 0;
        int element2 = 0;
        int element3 = 0;

        for (int j = two.length() - 1; j >= 0; j--) {
            for (int i = one.length() - 1; i >= 0; i--) {
                if (one.charAt(i) == two.charAt(j)) {
                    matrix[i][j] = matrix[i + 1][j + 1];
                } else {
                    element1 = matrix[i + 1][j] + 2;
                    element2 = matrix[i][j + 1] + 2;
                    element3 = matrix[i + 1][j + 1] + 1;
                    matrix[i][j] = Math.min(Math.min(element1, element2), element3);
                }
            }
        }
        return matrix[0][0];
    }

}
