//Main class, runs the game and handles allows player moves
package tictactoePackage;

import java.util.Scanner;

class Play {
	static final Scanner input = new Scanner(System.in);
	static final char[][] game = new char[3][3];  //matchfield
    static int gameCount = 0;
	
	public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tact-Toe!");
        System.out.println("How would you like to play?");
        System.out.println("Make your choice by entering the corrseponding number.\n");
		runGame();
	}
  
  	static void runGame() {  //runs the game
 		AI[] array = {new Beginner(), new Advanced('X', 'O'), new Advanced('O', 'X')};  // the symbols of the AI objects are for advanced AI to check for win conditions, so it knows what its symbol is
		Create.createGame();
		int setup = Create.setup();
		gameCount++;
		switch(setup) {
			case 1:  //no need to flipCoin as X starts anyway
		    	Create.createGame();
		    	Create.printGame();
		        while(Check.checkEnd()) {
		        	makeMove();
		        }
		        break;    
	        case 2: 			
		  		Create.createGame();
		  		Create.printGame();
		  		switch(Create.flipCoin()) {
		  			case 0:
				        while(Check.checkEnd()) {
				        	array[0].respond();	 
				        	makeMove();				        	
				        }
				        break;
		  			case 1:
				        while(Check.checkEnd()) {
				        	makeMove();
				        	array[0].respond();				        	
				        }
				        break;
		  		}
	        break;	
	        case 3:	
	  		Create.createGame();
	  		Create.printGame();
	  		switch(Create.flipCoin()) {
	  			case 0:
			        while(Check.checkEnd()) {
			        	array[1].respond();	 
			        	makeMove();				        	
			        }
			        break;
	  			case 1:
			        while(Check.checkEnd()) {			        	
			        	makeMove();
			        	array[2].respond();
			        }	        	
		        }
	        break;
			case 4:  //no need to flipCoin as X starts anyway
		  		Create.createGame();
		  		Create.printGame();
		        while(Check.checkEnd()) {
		        	array[0].respond();
		        }
		        break;
	        case 5:		
	        	Create.createGame();
	        	Create.printGame();
		  		switch(Create.flipCoin()) {
		  			case 0:
				        while(Check.checkEnd()) {
				        	array[0].respond();	 
				        	array[2].respond();		        	
				        }
				        break;
		  			case 1:
				        while(Check.checkEnd()) {			        	
				        	array[1].respond();
				        	array[0].respond();
				        }	        	
		        }
	        break;
	        case 6:  //no need to flipCoin as X starts anyway
		  		Create.createGame();
		  		Create.printGame();
		        while(Check.checkEnd()) {
		        	array[1].respond();
		        	array[2].respond();
		        }
		        break;  
			case 7:
				return;
			default: 
				System.out.println("Bad parameters");
		}
		System.out.println(Check.checkState());
		System.out.println("Wanna play again?\n1.Yes, restart!\n2.No, stop it.");
		if(!Create.restart()) {
			return;
		}
		runGame();
  	}
  
  	static void makeMove() {  //player move
  		if(!Check.checkEnd()) {
	  		return;
	  	}
		System.out.println("Enter the coordinates: x y");
	  	String move = input.nextLine();
	  	String[] coordinates = move.split(" ");
		if(!Check.checkLength(coordinates)) {
			System.out.println("Enter exactly two coordinates!"); 
			makeMove();
		} else if(!Check.checkInt(coordinates)) {
			makeMove();
	  	} else {			
			int xAxis = Integer.parseInt(coordinates[0]);
			int yAxis = Integer.parseInt(coordinates[1]);			
	
			if(xAxis > game[0].length || yAxis > game.length || yAxis < 0 || xAxis < 0) {
	    		System.out.println("Coordinates should be from 1 to 3!");	    		
	    		makeMove();
	    	} else if(game[yAxis - 1][xAxis - 1] != ' ') {
	    		System.out.println("This cell is occupied! Choose another one!");	    		
	    		makeMove();
	        } else if (Check.countXO() == 1) {
	    		game[yAxis - 1][xAxis - 1] = 'X';
	    		Create.printGame();	    		
	        } else if(Check.countXO() == 0) {
		        game[yAxis - 1][xAxis - 1] = 'O';
		        Create.printGame();   
	    	} else {
	        	System.out.println("Move error");
	        }
		}
  	}
}
