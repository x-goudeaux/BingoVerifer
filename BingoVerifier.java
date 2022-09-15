/*
 * Xavier Jibril Goudeaux
 * Java Bingo Verifier
 * Due:9/11/2022
 * 
 */

import java.util.Scanner;

public class BingoVerifier {

	// Rotates player card 90 degrees to the right
	public void rotateCard90(String[][] card) {
		int n = card.length;
		for (int row = 0; row < n / 2; row++) {
			for (int column = row; column < n - row - 1; column++) {
				String temp = card[row][column];
				card[row][column] = card[n - 1 - column][row];
				card[n - 1 - column][row] = card[n - 1 - row][n - 1 - column];
				card[n - 1 - row][n - 1 - column] = card[column][n - 1 - row];
				card[column][n - 1 - row] = temp;
			}
		}
	}

	/*
	 * Indicates 'straight' pattern (return 1) or 'crazy' pattern (return 4)
	 * Return false if neither patterns are found
	 */
	public int patternType(String[][] pattern) {
		for (int row = 0; row < pattern.length; row++) {
			for (int column = 0; column < pattern[row].length; column++) {
				if (pattern[row][column].equals("1")) {
					return 1;
				}
				if (pattern[row][column].equals("4")) {
					return 4;
				}
			}
		}
		return -1;
	}

	// Marks positions of called numbers on player card. Includes free space '00'
	public void markCard(String[] calledNumbers, String[][] playerCard) {
		for (String num : calledNumbers) {
			for (int row = 0; row < playerCard.length; row++) {
				for (int column = 0; column < playerCard[row].length; column++) {
					if (num.equals(playerCard[row][column]) || playerCard[row][column] == "00") {
						playerCard[row][column] = "X" + playerCard[row][column];
					}
				}
			}
		}
	}

	/*
	 * Checks if marked positions are the same as straight or crazy pattern.
	 * Returns false if marked card positions don't match input pattern
	 */
	public boolean winPattern(String[][] pattern, String[][] playerCard) {
		for (int row = 0; row < playerCard.length; row++) {
			for (int column = 0; column < playerCard[row].length; column++) {
				if ((pattern[row][column].equals("1") || pattern[row][column].equals("4"))
						&& playerCard[row][column].charAt(0) != 'X') {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Checks to see if the last number called is a part of bingo pattern.
	 * Returns true if last number called is in the pattern
	 */
	public boolean lastCalled(String[] calledNumbers, String[][] playerCard, String pattern[][]) {
		String last = calledNumbers[calledNumbers.length - 1];
		for (int row = 0; row < pattern.length; row++) {
			for (int column = 0; column < pattern[row].length; column++) {
				if (playerCard[row][column].substring(0, 1).equals("X")
						&& playerCard[row][column].substring(1).equals(last)) {
					if (pattern[row][column].equals("1") || pattern[row][column].equals("4")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// Verify player card against called numbers and pattern card for bingo
	/*
	 * 1. Mark off player card based on numbers called out
	 * 2. Indicate 'Straight' Pattern or 'Crazy' Pattern
	 * 3a. check if winning pattern has been marked off AND that the last number
	 * called is in that pattern
	 * 3b. If 'Crazy', do step 3a then rotate card 90 degrees. Repeat until
	 * conditions are met or all of the rotations fail
	 * 4. Return true if 3a condition is met.
	 */
	public boolean verifyBingo(String[] calledNumbers, String[][] playerCard, String[][] pattern) {
		markCard(calledNumbers, playerCard);
		if (patternType(pattern) == 1) {
			if (lastCalled(calledNumbers, playerCard, pattern) && winPattern(pattern, playerCard)) {
				return true;
			}
		}
		if (patternType(pattern) == 4) {
			for (int i = 0; i < 4; i++) {
				if (lastCalled(calledNumbers, playerCard, pattern) && winPattern(pattern, playerCard)) {
					return true;
				}
				rotateCard90(playerCard);
			}
		}
		return false;
	}

	// displays pattern card or player card to terminal
	public void displayInput(String[][] card) {
		for (int row = 0; row < card.length; row++) {
			for (int column = 0; column < card[row].length; column++) {
				System.out.print(card[row][column] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// displays called numbers to terminal
	public void displayInput(String[] calledNumbers) {
		for (int index = 0; index < calledNumbers.length; index++) {
			System.out.print(calledNumbers[index] + " ");
		}
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		// Create scanner for input
		Scanner scan = new Scanner(System.in);

		// Create BingoVerifier Object to use functions
		BingoVerifier bingo = new BingoVerifier();

		String[][] pattern;
		String numbers;
		String[] calledNumbers;
		String[][] playerCard;

		System.out.println("------------------------------");
		System.out.println("INPUT:");
		// Pattern
		pattern = new String[5][5];
		for (int row = 0; row < pattern.length; row++) {
			for (int column = 0; column < pattern[row].length; column++) {
				pattern[row][column] = scan.next();
			}
		}

		/*
		 * Consume blank line
		 */
		scan.nextLine();
		scan.nextLine();

		// Called Numbers
		numbers = scan.nextLine();
		calledNumbers = numbers.split(" ");

		/*
		 * Consume blank line
		 */
		scan.nextLine();

		// Player Card
		playerCard = new String[5][5];
		for (int row = 0; row < playerCard.length; row++) {
			for (int column = 0; column < playerCard[row].length; column++) {
				playerCard[row][column] = scan.next();
			}
		}
		// close
		scan.close();

		/*
		 * Performs bingo verification using inputs pattern, called numbers, and player
		 * card.
		 * Outputs whether or not player has bingo.
		 */

		System.out.println("\nOUTPUT:");
		if (bingo.verifyBingo(calledNumbers, playerCard, pattern)) {
			System.out.println("VALID BINGO\n\r");
		} else {
			System.out.println("NO BINGO\n\r");
		}
	}
}