import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NQueens {

    /*Input: Array
     * Output: returns string
     */
    public static String minimumOneVariable(ArrayList<Integer> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            str += " " + String.valueOf(arr.get(i));
        }
        str += " 0\n";
        return str;
    }

    /*Input: Array
     * Output: returns string
     */
    public static String maximumOneVariable(ArrayList<Integer> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            for (int j = arr.indexOf(arr.get(i)) + 1; j < arr.size(); j++) {
                str += " -" + String.valueOf(arr.get(i)) + " -" + String.valueOf(arr.get(j)) + " 0\n";
            }
        }

        return str;
    }

    /*Input: Array
     * Output: returns string
     */
    public static String exactlyOneVariable(ArrayList<Integer> arr) {
        String str = "";
        str += minimumOneVariable(arr);
        str += maximumOneVariable(arr);
        return str;
    }

    /*Input: Integer-row, Integer-column, Integer-InputValue
     * Output: returns int(Position)
     */
    public static int mappingPosition(int row, int column, int inputValue) {
        return row * inputValue + column + 1;
    }

    public static void main(String[] args) {
        
        /*To get the number of queens from the user as a input
         * The input value is in N
         * Chess Board size is calculated by N * N
        */
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter N (number of Queens): ");
        int N = userInput.nextInt();
        userInput.close();

        /* Check if there is atleast one queen present in the chessboard
         * if not present display error message.
        */
        if(N<1){
            System.err.println("There should exist atleast one queen in the chess board");
            return;
        }
        int boardSize = N * N;
        String str = "";
        /*
        When we have one queen per row
        Calculate mapping postiton and add it to the string
        */
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < N; j++) {
                int mapposition = mappingPosition(i, j, N);
                arr.add(mapposition);
            }
            str += exactlyOneVariable(arr);
        }
        /*
        When we have one queen per column
        Calculate mapping postiton and add it to the string
        */
        for (int j = 0; j < N; j++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < N; i++) {
                int mapposition = mappingPosition(i, j, N);
                arr.add(mapposition);
            }
            str += exactlyOneVariable(arr);
        }

        /*
        When we have one queen in left negative diagonal
        Calculate mapping postiton and add it to the string
         */
        for (int i = N - 1; i > -1; i--) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < N - i; j++) {
                arr.add(mappingPosition(i + j, j, N));
            }
            str += maximumOneVariable(arr);
        }
        /*
        When we have one queen from top of negative diagonal
        Calculate mapping postiton and add it to the string
         */
        for (int j = 1; j < N; j++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < N - j; i++) {
                arr.add(mappingPosition(i, j + i, N));
            }
            str += maximumOneVariable(arr);
        }
        /*
        When we have one queen in right positive diagonal
        Calculate mapping postiton and add it to the string
         */
        for (int i = N - 1; i > -1; i--) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < N - i; j++) {
                arr.add(mappingPosition(i + j, N - 1 - j, N));
            }
            str += maximumOneVariable(arr);
        }
        /*
        When we have queen from top of positive diagonal
        Calculate mapping postiton and add it to the string
         */
        for (int j = N - 2; j > -1; j--) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < j + 1; i++) {
                arr.add(mappingPosition(i, j - i, N));
            }
            str += maximumOneVariable(arr);
        }
        int count = str.length() - str.replace("\n", "").length();
        try {
            /*Write the results in input.cnf file using fileWriter*/
            String fileName = "input.cnf";
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.printf("c cnf expression for N= "+ N +"\n");
            pw.printf("c Board has "+boardSize+" positions\n");
            pw.printf("p cnf "+boardSize+" "+ count +"\n\n");
            pw.printf(str);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}