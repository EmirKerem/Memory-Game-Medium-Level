import java.util.Scanner;

public class Main {
    private static Card[][] cards = new Card[4][4];

    public static void main(String[] args) {
        initializeCards();
        gameBoard();
        while (!isGameOver()) {
            predict();
            gameBoard();
        }
        System.out.println("Congratulations! You have matched all pairs.");
    }

    private static void initializeCards() {
        cards[0][0] = new Card('E');
        cards[0][1] = new Card('A');
        cards[0][2] = new Card('B');
        cards[0][3] = new Card('F');
        cards[1][0] = new Card('G');
        cards[1][1] = new Card('A');
        cards[1][2] = new Card('D');
        cards[1][3] = new Card('H');
        cards[2][0] = new Card('F');
        cards[2][1] = new Card('C');
        cards[2][2] = new Card('D');
        cards[2][3] = new Card('H');
        cards[3][0] = new Card('E');
        cards[3][1] = new Card('G');
        cards[3][2] = new Card('B');
        cards[3][3] = new Card('C');
    }

    public static void predict() {
        Scanner scanner = new Scanner(System.in);

        int i1, j1, i2, j2;

        System.out.println("Enter the coordinates (x and y) for the first guess (1-4 for both):");
        i1 = getValidCoordinate("Row");
        j1 = getValidCoordinate("Column");

        cards[i1][j1].setRevealed(true);
        gameBoard();

        System.out.println("Enter the coordinates (x and y) for the second guess (1-4 for both):");
        i2 = getValidCoordinate("Row");
        j2 = getValidCoordinate("Column");

        if (i1 == i2 && j1 == j2) {
            System.out.println("The coordinates for the second guess are the same as the first guess. Please try again.");
            cards[i1][j1].setRevealed(false);
            return;
        }

        if (cards[i1][j1].getValue() == cards[i2][j2].getValue()) {
            System.out.println("Correct guess!");
            cards[i2][j2].setRevealed(true);
        } 
        else {
            cards[i1][j1].setRevealed(false);
            System.out.println("Incorrect guess. Try again!");
        }
    }

    public static int getValidCoordinate(String type) {
        Scanner scanner = new Scanner(System.in);
        int coord;

        while (true) {
            System.out.print(type + " (1-4): ");
            coord = scanner.nextInt();
            if (coord >= 1 && coord <= 4) {
                return coord - 1;
            } 
            else {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }
    }

    public static boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!cards[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void gameBoard() {
        for (int i = 0; i < 4; i++) {
            System.out.println("__________________________");
            for (int j = 0; j < 4; j++) {
                if (cards[i][j].isRevealed()) {
                    System.out.print("| " + cards[i][j].getValue() + " |");
                } 
                else {
                    System.out.print(" |   |");
                }
            }
            System.out.println();
        }
        System.out.println("__________________________");
    }
}
