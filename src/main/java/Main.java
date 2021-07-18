import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static int BOARD = 0;
    public static int MINES = 0;

    public static void main(String[] args) {
        selectMode();
        char[][] board = new char[BOARD][BOARD];
        initBoard(board);
        int[][] solution = new int[BOARD][BOARD];
        int[][] playboard = new int[BOARD][BOARD];
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                solution[i][j] = 0;
                playboard[i][j] = 0;
            }
        }
        int mine = MINES;
        int countplay = 0;
        fillBoard(board, mine);
        countAdjacentMines(board, solution);
        printBoard(board);
        while (countplay <= (board.length * board[0].length) - MINES) {
            System.out.println("Enter your move,(row,column) -> ");
            int[] coordinates = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int prow = coordinates[0];
            int pcol = coordinates[1];


            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    if (board[prow][pcol] == '*') {
                        System.out.println("You lost!\n");
                        gameOverPrint(board, solution);
                        return;
                    } else {
                        playboard[prow][pcol] = solution[prow][pcol];
                        i = board.length;
                        j = board[0].length;
                        break;
                    }
                }
            }

           printBoard(playboard);

            countplay++;
        }

        System.out.println("You won");
    }

    private static void countAdjacentMines(char[][] board, int[][] solution) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                int r = i;
                int c = j;

                if (board[i][c] == '*') {
                    if (r > 0 && c > 0) {
                        if (board[r - 1][c - 1] == '-') {
                            solution[r - 1][c - 1] += 1;
                        }
                    }
                    if (r > 0) {
                        if (board[r - 1][c] == '-') {
                            solution[r - 1][c] += 1;
                        }
                    }
                    if (r > 0 && c + 1 < board[0].length) {
                        if (board[r - 1][c + 1] == '-') {
                            solution[r - 1][c + 1] += 1;
                        }
                    }

                    if (c > 0) {
                        if (board[r][c - 1] == '-') {
                            solution[r][c - 1] += 1;
                        }
                    }
                    if (c + 1 < board[0].length) {
                        if (board[r][c + 1] == '-') {
                            solution[r][c + 1] += 1;
                        }
                    }
                    if (r + 1 < board.length && c > 0) {
                        if (board[r + 1][c - 1] == '-') {
                            solution[r + 1][c - 1] += 1;
                        }
                    }
                    if (r + 1 < board.length) {
                        if (board[r + 1][c] == '-') {
                            solution[r + 1][c] += 1;
                        }
                    }
                    if (r + 1 < board.length && c + 1 < board[0].length) {
                        if (board[r + 1][c + 1] == '-') {
                            solution[r + 1][c + 1] += 1;
                        }
                    }
                }

            }
        }
    }

    private static void gameOverPrint(char[][] board, int[][] solution) {
        System.out.print("    ");
        for (int i = 0; i < BOARD; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int k = 0; k < solution.length; k++) {
            System.out.printf("%d   ", k);
            for (int x = 0; x < solution[0].length; x++) {
                if (board[k][x] == '*')
                    System.out.print("*" + " ");
                else
                    System.out.print(solution[k][x] + " ");
            }

            System.out.println();
        }
    }


    private static void fillBoard(char[][] board, int mines) {
        for (int m = 0; m < mines; m++) {
            //Loops until a mine is placed.
            while (true) {
                int x, y = 0;
                x = (int) (board.length * Math.random());
                y = (int) (board[0].length * Math.random());

                //So that a mine is placed in a tile visible to the player.
                if (x >= 1 && x < board.length) {
                    if (y >= 1 && y < board[0].length) {
                        //Checks if a mine is present in a spot.
                        if (board[x][y] != '*') {
                            board[x][y] = '*';
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void printBoard(char[][] board) {
        System.out.print("    ");
        for (int i = 0; i < BOARD; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int i = 0; i < BOARD; i++) {
            System.out.printf("%d   ", i);
            for (int j = 0; j < BOARD; j++) {
                if (board[i][j] == '*')
                    System.out.print("-" + " ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printBoard(int[][] board) {
        System.out.print("    ");
        for (int i = 0; i < BOARD; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        for (int i = 0; i < BOARD; i++) {
            System.out.printf("%d   ", i);
            for (int j = 0; j < BOARD; j++) {
                if (board[i][j] == '*')
                    System.out.print("-" + " ");
                else if (board[i][j] == 0)
                    System.out.print("-" + " ");
                else
                    System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void selectMode() {
        System.out.println(String.format("Enter the Difficulty Level%n" +
                "Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)%n" +
                "Press 1 for INTERMEDIATE(16 * 16 Cells and 40 Mines)%n" +
                "Press 2 for ADVANCED(24 * 24 Cells and 99 Mines)"));
        int level = Integer.parseInt(scan.nextLine());
        switch (level) {
            case 0:
                BOARD = 9;
                MINES = 10;
                break;
            case 1:
                BOARD = 16;
                MINES = 40;
                break;
            case 2:
                BOARD = 24;
                MINES = 99;
                break;
        }

    }
}
