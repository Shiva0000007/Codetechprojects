package ticgame;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tic-Tac-Toe Game");
        System.out.println("Select Game Mode:");
        System.out.println("1. System vs Man");
        System.out.println("2. Man vs Man");

        int gameMode = scanner.nextInt();

        if (gameMode == 1) {
            playSystemVsManGame();
        } else if (gameMode == 2) {
            playManVsManGame();
        } else {
            System.out.println("Invalid game mode selected. Exiting.");
        }

        scanner.close();
    }

    private static void playSystemVsManGame() {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        boolean isPlayer1Turn = true;
        boolean gameOver = false;

        while (!gameOver) {
            displayBoard(board);
            char currentPlayerMark = (isPlayer1Turn) ? 'X' : 'O';

            if (isPlayer1Turn) {
                makeMove(board, currentPlayerMark);
            } else {
                makeSystemMove(board, currentPlayerMark);
            }

            if (checkForWin(board, currentPlayerMark)) {
                displayBoard(board);
                System.out.println((isPlayer1Turn ? "Player" : "System") + " wins!");
                gameOver = true;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("It's a draw!");
                gameOver = true;
            }

            isPlayer1Turn = !isPlayer1Turn; // Switch turns
        }
    }

    private static void playManVsManGame() {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        boolean isPlayer1Turn = true;
        boolean gameOver = false;

        while (!gameOver) {
            displayBoard(board);
            char currentPlayerMark = (isPlayer1Turn) ? 'X' : 'O';
            makeMove(board, currentPlayerMark);

            if (checkForWin(board, currentPlayerMark)) {
                displayBoard(board);
                System.out.println("Player " + currentPlayerMark + " wins!");
                gameOver = true;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("It's a draw!");
                gameOver = true;
            }

            isPlayer1Turn = !isPlayer1Turn; // Switch turns
        }
    }

    private static void displayBoard(char[][] board) {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void makeMove(char[][] board, char currentPlayerMark) {
        int row, col;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter row (1-3) for player " + currentPlayerMark + ": ");
            row = scanner.nextInt() - 1;
            System.out.print("Enter column (1-3) for player " + currentPlayerMark + ": ");
            col = scanner.nextInt() - 1;
        } while (!isValidMove(board, row, col));

        board[row][col] = currentPlayerMark;
    }

    private static void makeSystemMove(char[][] board, char currentPlayerMark) {
        // You can implement a simple algorithm for the system's move here.
        // For simplicity, let's choose a random empty cell.
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (!isValidMove(board, row, col));

        System.out.println("System chooses row " + (row + 1) + " and column " + (col + 1));
        board[row][col] = currentPlayerMark;
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        return true;
    }

    private static boolean checkForWin(char[][] board, char currentPlayerMark) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayerMark && board[i][1] == currentPlayerMark && board[i][2] == currentPlayerMark) ||
                    (board[0][i] == currentPlayerMark && board[1][i] == currentPlayerMark && board[2][i] == currentPlayerMark)) {
                return true;
            }
        }

        return (board[0][0] == currentPlayerMark && board[1][1] == currentPlayerMark && board[2][2] == currentPlayerMark) ||
                (board[0][2] == currentPlayerMark && board[1][1] == currentPlayerMark && board[2][0] == currentPlayerMark);
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
