package HTC;

import java.util.Scanner;

public class Tictactoe {
    public static char[][] board;
    public static int n;
    public static char currentPlayer; 
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the board (n x n): ");
        
       
        n = sc.nextInt();
        board = new char[n][n];
        initializeBoard();
        
        currentPlayer = 'X'; 
        int moves = 0;
        boolean gameWon = false;
        
        while (moves < n * n && !gameWon) {
            printboard();
            System.out.printf("Player %c, enter your move (row and column):%n", currentPlayer);
            int row = sc.nextInt(); 
            int col = sc.nextInt();
            
            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                moves++;
                
                if (checkwin(row, col)) {
                    gameWon = true;
                    printboard();
                    System.out.printf("Player %c wins!%n", currentPlayer);
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        
        if (!gameWon) {
            printboard();
            System.out.println("The game is a draw.");
        }
        sc.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printboard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n && board[row][col] == '-';
    }

    private static boolean checkwin(int row, int col) {
        return checkRow(row) || checkColumn(col) || checkDiagonals();
    }

    private static boolean checkRow(int row) {
        for (int col = 0; col < n; col++) {
            if (board[row][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn(int col) {
        for (int row = 0; row < n; row++) {
            if (board[row][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonals() {
        boolean dia1 = true, dia2 = true;

        for (int i = 0; i < n; i++) {
            if (board[i][i] != currentPlayer) {
                dia1 = false;
            }
            if (board[i][n - i - 1] != currentPlayer) {
                dia2 = false;
            }
        }
        return dia1 || dia2;
    }
}
