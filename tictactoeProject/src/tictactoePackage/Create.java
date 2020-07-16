//Game creation class
package tictactoePackage;

import java.util.Scanner;
import java.util.Random;

class Create {
    static final Scanner input = Play.input;
    static final char[][] game = Play.game;
    
    static int setup() {  //choose the way you want to play
        System.out.println("1.Player vs. Player\n2.Player vs. beginner AI\n3.Player vs. advanced AI\n");
        System.out.println("4.Beginner AI vs. beginner AI\n5.Beginner AI vs. advanced AI\n6.Advanced AI vs. advanced AI\n");
        if(Play.gameCount == 0) {
        	System.out.println("\n7.I don't.");
        }
        int choice = 0;
        try {
        	String line = input.nextLine();
            choice = Integer.parseInt(line);
        } catch(Exception e) {
        	System.out.println("Enter only a single number, try again.");
        	return setup();
        }
        if(choice < 1 || choice > 7) {
            System.out.println("Invalid choice, try again.");
            return setup();  
        } else if (choice == 7) {
            System.out.println("Alright, shutting down.");
        } else {
            System.out.println("Starting game");
        }
        return choice;
    }
    
    static void createGame() {  //fills the game array with blank characters
    	for(int i = 0; i < game.length; i++) {
        	for(int j = 0; j < game[i].length; j++) {
        			game[i][j] = ' ';
        	}
    	}
    }
    
    static void printGame() {  //prints the game array as a tictactoe field
    	System.out.println("   1    2    3");
    	System.out.println("  -------------");
        for(int i = 0; i < game.length; i++) {
            System.out.print((i + 1) + " | ");
        	for(int j = 0; j < game[i].length; j++) {
        		System.out.print(game[i][j] + " | ");
        	}
        	System.out.println();
        	System.out.println("  -------------");
        }
    }
    
    static int flipCoin() {  //determines who goes first
    	final Random random = new Random();
    	int flip = random.nextInt(2);
    	System.out.println(flip);
    	return flip;
    }
    
    static boolean restart() {  //restart or end program
		String choice = input.nextLine();
		if("1".equals(choice)) {  //literals should be on the left side to avoid NPE - the left side mustn't be null - the literal cannot be null. That's why it should be on the left side
			System.out.println("Here we go again! Choose how you want to play.");
		} else if("2".equals(choice)) {
			System.out.println("Shutting down, goodbye.");
			return false;
		} else {
			System.out.println("Invalid input, try again.");
			return restart();
		}
		return true;
    }
}