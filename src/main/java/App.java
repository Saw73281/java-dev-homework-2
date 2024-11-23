import java.util.Scanner;

public class App {
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';

    private static char[] board = new char[BOARD_SIZE];
    private static boolean isGameOver = false;

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            displayBoard();
            playerMove(scanner);
            if (checkWinner('X')) {
                System.out.println("You won the game!");
                isGameOver = true;
                continue;
            }

            if (isBoardFull()) {
                System.out.println("It's a draw!");
                isGameOver = true;
                continue;
            }

            computerMove();
            if (checkWinner('O')) {
                System.out.println("You lost the game!");
                isGameOver = true;
            }
        }
        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    private static void displayBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void playerMove(Scanner scanner) {
        System.out.println("Enter a box number (1-9):");
        int input;
        while (true) {
            input = scanner.nextInt();
            if (input >= 1 && input <= 9 && board[input - 1] == EMPTY_CELL) {
                board[input - 1] = 'X';
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void computerMove() {
        while (true) {
            int move = (int) (Math.random() * BOARD_SIZE);
            if (board[move] == EMPTY_CELL) {
                board[move] = 'O';
                break;
            }
        }
    }

    private static boolean checkWinner(char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
               (board[3] == player && board[4] == player && board[5] == player) ||
               (board[6] == player && board[7] == player && board[8] == player) ||
               (board[0] == player && board[3] == player && board[6] == player) ||
               (board[1] == player && board[4] == player && board[7] == player) ||
               (board[2] == player && board[5] == player && board[8] == player) ||
               (board[0] == player && board[4] == player && board[8] == player) ||
               (board[2] == player && board[4] == player && board[6] == player);
    }

    private static boolean isBoardFull() {
        for (char cell : board) {
            if (cell == EMPTY_CELL) {
                return false;
            }
        }
        return true;
    }
}
