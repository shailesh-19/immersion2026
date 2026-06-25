package projects;

import java.util.Scanner;

/**
 * Class 1: number_puzzle
 * [span_2](start_span)Contains the main() method to launch the application[span_2](end_span).
 */
public class number_puzzle {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

/**
 * Class 3: Card
 * [span_3](start_span)Stores and displays individual card data[span_3](end_span).
 */
class Card {
    private final int id;
    private final int bitValue;
    private final int[] numbers;

    public Card(int id, int bitValue, int[] numbers) {
        this.id = id;
        this.bitValue = bitValue;
        this.numbers = numbers;
    }

    public int getBitValue() {
        return bitValue;
    }

    public int getId() {
        return id;
    }

    [span_4](start_span)[span_5](start_span)// Displays the card in a clean grid format[span_4](end_span)[span_5](end_span)
    public void display() {
        System.out.println("\n=======================");
        System.out.println("        CARD " + id);
        System.out.println("=======================");
        int count = 0;
        for (int num : numbers) {
            System.out.printf("%4d", num);
            count++;
            if (count % 4 == 0) {
                System.out.println();
            }
        }
        if (count % 4 != 0) {
            System.out.println();
        }
        System.out.println("=======================");
    }
}

/**
 * Class 2: Game
 * [span_6](start_span)[span_7](start_span)Controls the core game loops, menu UI, and scoring logic[span_6](end_span)[span_7](end_span).
 */
class Game {
    private final Scanner scanner;
    private Card[] cards;

    public Game() {
        this.scanner = new Scanner(System.in);
        initializeCards();
    }

    [span_8](start_span)[span_9](start_span)// Generates cards based on binary bit manipulation for numbers 1 to 20[span_8](end_span)[span_9](end_span)
    private void initializeCards() {
        // 5 cards are needed to represent numbers up to 20 since 2^4 = 16 (16 + 4 = 20)
        cards = new Card[5];
        int[] bitValues = {1, 2, 4, 8, 16};

        for (int i = 0; i < 5; i++) {
            int bit = bitValues[i];
            
            [span_10](start_span)// Count how many numbers between 1 and 20 have this bit set[span_10](end_span)
            int size = 0;
            for (int n = 1; n <= 20; n++) {
                if ((n & bit) != 0) {
                    size++;
                }
            }

            [span_11](start_span)// Populate the card array[span_11](end_span)
            int[] cardNumbers = new int[size];
            int idx = 0;
            for (int n = 1; n <= 20; n++) {
                if ((n & bit) != 0) {
                    cardNumbers[idx++] = n;
                }
            }
            cards[i] = new Card(i + 1, bit, cardNumbers);
        }
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readIntegerInput("Enter Choice: ");
            
            switch (choice) {
                case 1:
                    displayRules();
                    break;
                case 2:
                    displayCards();
                    break;
                case 3:
                    playGame();
                    break;
                case 4:
                    System.out.println("\nThank you for playing Secret Numbers Puzzle Game! Goodbye.");
                    running = false; [span_12](start_span)// Graceful termination[span_12](end_span)
                    break;
                default:
                    [span_13](start_span)System.out.println("\n[Error] Invalid choice! Please select a number between 1 and 4.[span_13](end_span)");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n*** MENU ***");
        [span_14](start_span)System.out.println("1. View Rules[span_14](end_span)");
        [span_15](start_span)System.out.println("2. Display Cards[span_15](end_span)");
        [span_16](start_span)System.out.println("3. Play Game[span_16](end_span)");
        [span_17](start_span)System.out.println("4. Exit[span_17](end_span)");
    }

    private void displayRules() {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("                    GAME RULES");
        System.out.println("-----------------------------------------------------");
        [span_18](start_span)[span_19](start_span)System.out.println("1. Think of a secret number between 1 and 20[span_18](end_span)[span_19](end_span).");
        [span_20](start_span)System.out.println("2. The system will present 5 different number cards[span_20](end_span).");
        [span_21](start_span)[span_22](start_span)System.out.println("3. Look closely at each card and answer 'Y' (Yes) if[span_21](end_span)[span_22](end_span)");
        [span_23](start_span)System.out.println("   your number is on it, or 'N' (No) if it is not[span_23](end_span).");
        [span_24](start_span)System.out.println("4. The system will automatically calculate and reveal[span_24](end_span)");
        [span_25](start_span)System.out.println("   your secret number[span_25](end_span)!");
        System.out.println("-----------------------------------------------------");
    }

    private void displayCards() {
        for (Card card : cards) {
            card.display();
        }
    }

    private void playGame() {
        [span_26](start_span)[span_27](start_span)System.out.println("\nThink of a number between 1 and 20[span_26](end_span)[span_27](end_span).");
        int secretLetterSum = 0;

        for (Card card : cards) {
            boolean validResponse = false;
            char response = ' ';

            while (!validResponse) {
                [span_28](start_span)[span_29](start_span)System.out.print("Is your number present in Card " + card.getId() + "? (Y/N):[span_28](end_span)[span_29](end_span)");
                String input = scanner.next().trim().toUpperCase();

                if (input.equals("Y") || input.equals("YES")) {
                    response = 'Y';
                    validResponse = true;
                } else if (input.equals("N") || input.equals("NO")) {
                    response = 'N';
                    validResponse = true;
                } else {
                    [span_30](start_span)[span_31](start_span)System.out.println("[Error] Invalid input. Please type Y/Yes or N/No.[span_30](end_span)[span_31](end_span)");
                }
            }

            [span_32](start_span)[span_33](start_span)// If the number is present, add the first number (the positional bit value) of the card[span_32](end_span)[span_33](end_span)
            if (response == 'Y') {
                secretLetterSum += card.getBitValue();
            }
        }

        System.out.println("\n------------------------------------");
        if (secretLetterSum >= 1 && secretLetterSum <= 20) {
            [span_34](start_span)[span_35](start_span)System.out.println("  Your Secret Number is: " + secretLetterSum + "[span_34](end_span)[span_35](end_span)");
        } else if (secretLetterSum == 0) {
            [span_36](start_span)System.out.println("  Hey! You answered 'No' to everything.\n  (0 is out of the 1-20 range!)[span_36](end_span)");
        } else {
            [span_37](start_span)System.out.println("  Oops! The calculated number (" + secretLetterSum + ") is out of range.[span_37](end_span)");
            System.out.println("  Did you make a mistake while answering?");
        }
        System.out.println("------------------------------------");
        
        askPlayAgain();
    }

    private void askPlayAgain() {
        while (true) {
            System.out.print("\nDo you want to play again? (Y/N): ");
            String input = scanner.next().trim().toUpperCase();
            if (input.equals("Y") || input.equals("YES")) {
                [span_38](start_span)playGame(); // Restart game loop without restarting the app[span_38](end_span)
                break;
            } else if (input.equals("N") || input.equals("NO")) {
                break; // Return back to the main menu
            } else {
                [span_39](start_span)[span_40](start_span)System.out.println("[Error] Invalid input. Please type Y or N.[span_39](end_span)[span_40](end_span)");
            }
        }
    }

    private int readIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                [span_41](start_span)[span_42](start_span)System.out.println("[Error] Please enter a valid number.[span_41](end_span)[span_42](end_span)");
                scanner.next(); // Clear the invalid input buffer
            }
        }
    }
}
