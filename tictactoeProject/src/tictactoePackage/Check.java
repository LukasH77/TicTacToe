//Input and state validation class
package tictactoePackage;

import java.util.Scanner;

class Check {
    static final Scanner input = Play.input;
    static final char[][] game = Play.game;
    
	static int countXO() {  //returns 0 if O moves next, 1 if X moves next, 2 if neither apply, this usually results in an error
    	int xNext;
    	int countX = 0;
    	int countO = 0;
    	for(int i = 0; i < game.length; i++) {
        	for(int j = 0; j < game[i].length; j++) {
        		if(game[i][j] == 'X') {
        			countX++;
        		} else if(game[i][j] == 'O') {
        			countO++;
        		}
        	}
    	}
    	if(countX == countO + 1) {
    		xNext = 0;
    		return xNext;
    	} else if(countX == countO) {
    		xNext = 1;
    		return xNext;
    	} else {
    		xNext = 2;
    		System.out.println("Something went wrong");
    		return xNext;
    	}
    }

    
    static String checkState() {  //checks the state of the game(win, draw, who moves)
    	String xWins = "\n     X wins!";
    	String oWins = "\n     O wins!";
    	String draw = "\n   It's a draw!";
    	String continueGamePlayer = "Player moves"; 
		String continueGameAI = "AI moves";   	    	
    	//check if there's a horizontal win
    	for(int i = 0; i < game.length; i++) {
			char comp = game[i][0];
    			if(comp == 'X' && comp == game[i][1] && comp == game[i][2]) {
    				return xWins;
    			} else if(comp == 'O' && comp == game[i][1] && comp == game[i][2]) {
    				return oWins;
    			}
    	}    	
    	//check if there's a vertical win
    	for(int i = 0; i < game.length; i++) {
			char comp = game[0][i];
    			if(comp == 'X' && comp == game[1][i] && comp == game[2][i]) {
    				return xWins;
    			} else if(comp == 'O' && comp == game[1][i] && comp == game[2][i]) {
    				return oWins;
    			}
    	}    	
    	//check if there's a diagonal win
    	char comp = game[1][1];
    	if((comp == 'X' && comp == game[0][0] && comp == game[2][2]) || (comp == 'X' && comp == game[0][2] && comp == game[2][0])) {
			return xWins;
    	} else if((comp == 'O' && comp == game[0][0] && comp == game[2][2]) || (comp == 'O' && comp == game[0][2] && comp == game[2][0])) {
			return oWins;
    	}    	
    	//check if there's a draw
    	boolean emptySpace = false;
    	for(int i = 0; i < game.length; i++) {
    		for(int j = 0; j < game[i].length; j++) {
    			if(game[i][j] == ' ') {
    				emptySpace = true;
    			}
    		}
    	}
    	if(!emptySpace) {
    		return draw;
    	}		
    	//game is still ongoing, check who's next
		if(countXO() == 1) {
			return continueGamePlayer;
		} else if(countXO() == 0) {
			return continueGameAI;
		} else {
			return "Move error checkState";
		}
    }
    
    static boolean checkEnd() {  //checks if the game is still ongoing
    	boolean checkEnd = (Check.checkState() == "AI moves" || Check.checkState() == "Player moves");
    	return checkEnd;
    }
    
    static boolean checkLength(String[] coordinates) {  //checks if a player entered exactly two 'coordinates'
    	boolean valid = false;
    	if(coordinates.length != 2) {
    		return valid;
    	} else {
    		valid = true;
    		return valid;
    	}
    }
    
    static boolean checkInt(String[] coordinates) {  //checks if the entered 'coordinates' really are integers
    	String x = coordinates[0];
    	String y = coordinates[1];
    	try {
    		Integer.parseInt(x);
    		Integer.parseInt(y);
    		return true;
    	} catch(Exception e) {
			System.out.println("Enter numbers only!");
			return false;
    	}
    }
}