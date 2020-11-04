import java.util.Scanner;

//making a change here

public class ConnectFour {

    public static void printBoard(char[][]board) { //prints board
        for(int h = board[0].length - 1; h >= 0; h--) { // runs through the columns
            for(int l = 0; l < board.length; l++) { //runs through the rows
                System.out.print(board[l][h] + " "); //prints the element
            }
            System.out.print("\n");
        }
    }

    public static void initializeBoard(char[][]board) { //initialize board to have '-' as each element
        for(int h = 0; h < board[0].length; h++) { //columns
            for(int l = board.length-1; l >= 0; l--) { //rows
                board[l][h] = '-'; //array
            }
        }
    }

    public static int insertChip(char[][]board, int col, char chipType) { //Places the token in the column that the user has chosen. Will find the next available spot in that column if there are already tokens there. The row that the token is placed in is returned.
        int row = 0;

        for(int r = 0; r < board[0].length; r++) { //runs through each row
            if(board[col][r] == '-') { //checks for empty '-'
                board[col][r] = chipType; //if empty, adds the chip
                row = board[0].length - 1 - r; //the height of the board, minus 1, minus the rows that were run through
                break;
            }
        }
        return row;
    }

    public static boolean checkIfWinner(char[][] board, int col, int row, char chipType) { //After a token is added, checks whether the token in this location, of the specified chip type, creates four in a row.Will return true if someone won, and falseotherwise.
        boolean win = false;

        //runs through each row and checks for four chips in a row, if true breaks and changes win to true
        int fourInRow = 0;
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(board[r][c] == chipType) {
                    fourInRow++;
                }
                if(fourInRow >= 4) {
                    win = true;
                    break;
                }
            }
            fourInRow = 0;
        }

        //runs through each col and checks for four chips in a col, if true breaks and changes win to true
        int fourInCol = 0;
        for(int c = 0; c < board[0].length; c++) {
            for(int r = 0; r < board.length; r++) {
                if(board[r][c] == chipType) {
                    fourInCol++;
                }
                if(fourInCol >= 4) {
                    win = true;
                    break;
                }
            }
            fourInCol = 0;
        }

        return win;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in); //DO NOT ADD IN LOOP!!!!!!!!!!!!

        //inputs height and length of board
        System.out.print("What would you like the height of the board to be? ");
        int height = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = scnr.nextInt();

        //creates an empty array
        char[][] board = new char[length][height];

        //initializes the board and displays it
        initializeBoard(board);
        printBoard(board);

        //prints the players tokens
        System.out.print("\nPlayer 1: x");
        System.out.print("\nPlayer 2: o\n");

        int numOfRuns = 0;

        while(true) {

            //checks for tie
            if(numOfRuns < (height * length)) {
                numOfRuns++;
            } else {
                System.out.println("\nDraw. Nobody wins.");
                break;
            }

            //ask player one for input
            System.out.print("\nPlayer 1: Which column would you like to choose? ");
            int columnP1 = scnr.nextInt();
            //inserts chip, displays board, then checks fow win
            int rowP1 = insertChip(board, columnP1, 'x');
            printBoard(board);
            if(checkIfWinner(board, columnP1, rowP1, 'x')) {
                System.out.println("\nPlayer 1 won the game!");
                break;
            }

            //checks for tie
            if(numOfRuns < (height * length)) {
                numOfRuns++;
            } else {
                System.out.println("\nDraw. Nobody wins.");
                break;
            }

            //ask player two for input
            System.out.print("\nPlayer 2: Which column would you like to choose? ");
            int columnP2 = scnr.nextInt();
            //inserts chip, displays board, then checks fow win
            int rowP2 = insertChip(board, columnP2, 'o');
            printBoard(board);
            if(checkIfWinner(board, columnP2, rowP2, 'o')) {
                System.out.println("\nPlayer 2 won the game!");
                break;
            }

        }

    }
}
