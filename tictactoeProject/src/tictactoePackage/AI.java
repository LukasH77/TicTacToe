//AI movement class
package tictactoePackage;

import java.util.Random;

class AI {  //parent class AI, holds properties and methods common to all difficulties
	final Random random = new Random();
    static final char[][] game = Play.game;
	
	void respond() {  //subclasses will override this; only exists so you can call it on an AI object, you don't have to specifically instantiate Beginner/Advanced
		
	}
}

class Beginner extends AI {  //beginner difficulty AI, only moves randomly
	static final String name = "beginner";
	
	@Override
	void respond () {  //beginner AI makes a random move
    	if(!Check.checkEnd()) {
    		return;
    	}
    	
    	int x = random.nextInt(3);
    	int y = random.nextInt(3);
   	
    	while(game[y][x] != ' ') {
			x = random.nextInt(3);
		 	y = random.nextInt(3);
    	}  	
    	System.out.println("Making move level \"" + name + "\"");
    	if(Check.countXO() == 0) {
    		game[y][x] = 'O';
    	} else if (Check.countXO() == 1) {
    		game[y][x] = 'X';
    	}
		Create.printGame();	    		
    }
}

class Advanced extends AI {  //advanced difficulty AI, moves accordingly if it or its opponent can win in one move, outside of that it moves randomly
	static final String name = "advanced";
	private char aiSymbol;
	private char opponentSymbol;
	
	Advanced(char aiSymbol, char opponentSymbol) {
		this.aiSymbol = aiSymbol;
		this.opponentSymbol = opponentSymbol;
	}	
	
	int[] checkWinCondition() {  //AI checks if it or its opponent has an immediate win condition
		int[] coordinates = new int[2];
		int[] temp = new int[2];
		int countEmpty;
		int countAi;
		int countOpponent;
		boolean blockChance = false;		
    	//check if there's a horizontal win condition AI or OPPONENT
    	for(int i = 0; i < game.length; i++) { 
    		countEmpty = 0;
    		countAi = 0;
    		countOpponent = 0;
    		for(int j = 0; j < game[i].length; j++) {
    			if(game[i][j] == ' ') {
    				countEmpty++;
    				coordinates[0] = j;
    				coordinates[1] = i;    			
    			} else if(game[i][j] == this.aiSymbol) {
    				countAi++;
    			} else if(game[i][j] == this.opponentSymbol){
    				countOpponent++;
    			}
    		}
    		if(countEmpty == 1) {
    			if(countAi == 2) {
	    			System.out.println("Horizontal win");
	    			return coordinates;
    			} else if(countOpponent == 2) {
    				temp[0] = coordinates[0];
    				temp[1] = coordinates[1];  
	    			blockChance = true;
    			}
    		}
    	}   	
    	//check if there's a vertical win condition AI or OPPONENT
    	for(int i = 0; i < game.length; i++) { 
    		countEmpty = 0;
    		countAi = 0;
    		countOpponent = 0;
    		for(int j = 0; j < game[i].length; j++) {
    			if(game[j][i] == ' ') {
    				countEmpty++;
    				coordinates[0] = i;
    				coordinates[1] = j;    			
    			}
    			if(game[j][i] == this.aiSymbol) {
    				countAi++;
    			} else if(game[j][i] == this.opponentSymbol) {
    				countOpponent++;
    			}
    		}
    		if(countEmpty == 1) {
    			if(countAi == 2) {
	    			System.out.println("Vertical win");
	    			return coordinates;
    			} else if(countOpponent == 2) {
    				temp[0] = coordinates[0];
    				temp[1] = coordinates[1];  
	    			blockChance = true;
    			}
    		}
    	}   	    	
    	//the checks for diagonal win conditions are a bit messy when compared to others. Somewhat of a brute force solution 
    	//check if there's a diagonal win condition AI
    	do {
			char comp = game[1][1];  	
			boolean aiWinDiagonallyOuterLD = (comp == this.aiSymbol && comp == game[0][2] && game[2][0] == ' ');
			boolean aiWinDiagonallyOuterRU = (comp == this.aiSymbol && comp == game[2][0] && game[0][2] == ' ');
			boolean aiWinDiagonallyOuterLU = (comp == this.aiSymbol && comp == game[2][2] && game[0][0] == ' ');
			boolean aiWinDiagonallyOuterRD = (comp == this.aiSymbol && comp == game[0][0] && game[2][2] == ' ');
			boolean aiWinDiagonallyCenter = (comp == ' ' && ((game[2][2] == this.aiSymbol && game[2][2] == game[0][0]) || (game[2][0] == this.aiSymbol && game[2][0] == game[0][2])));
	
			if(aiWinDiagonallyOuterLD) {
				coordinates[0] = 0;
				coordinates[1] = 2;
    			System.out.println("Diagonal win");
				return coordinates;
			} else if(aiWinDiagonallyOuterRU) {
				coordinates[0] = 2;
				coordinates[1] = 0;
    			System.out.println("Diagonal win");
				return coordinates;
			} else if(aiWinDiagonallyOuterLU) {
				coordinates[0] = 0;
				coordinates[1] = 0;
    			System.out.println("Diagonal win");
				return coordinates;
			} else if(aiWinDiagonallyOuterRD) {
				coordinates[0] = 2;
				coordinates[1] = 2;
    			System.out.println("Diagonal win");
				return coordinates;
			} else if(aiWinDiagonallyCenter) {
				coordinates[0] = 1;
				coordinates[1] = 1;
    			System.out.println("Diagonal win");
				return coordinates;
			}
    	} while(false);   	
    	//check if there's a diagonal win condition OPPONENT
    	do {
			char comp = game[1][1];  	
			boolean opponentWinDiagonallyOuterLD = (comp == this.opponentSymbol && comp == game[0][2] && game[2][0] == ' ');
			boolean opponentWinDiagonallyOuterRU = (comp == this.opponentSymbol && comp == game[2][0] && game[0][2] == ' ');
			boolean opponentWinDiagonallyOuterLU = (comp == this.opponentSymbol && comp == game[2][2] && game[0][0] == ' ');
			boolean opponentWinDiagonallyOuterRD = (comp == this.opponentSymbol && comp == game[0][0] && game[2][2] == ' ');
			boolean opponentWinDiagonallyCenter = (comp == ' ' && ((game[2][2] == this.opponentSymbol && game[2][2] == game[0][0]) || (game[2][0] == this.opponentSymbol && game[2][0] == game[0][2])));
	
			if(opponentWinDiagonallyOuterLD) {
				temp[0] = 0;
				temp[1] = 2;
    			blockChance = true;
			} else if(opponentWinDiagonallyOuterRU) {
				temp[0] = 2;
				temp[1] = 0;
    			blockChance = true;
			} else if(opponentWinDiagonallyOuterLU) {
				temp[0] = 0;
				temp[1] = 0;
    			blockChance = true;
			} else if(opponentWinDiagonallyOuterRD) {
				temp[0] = 2;
				temp[1] = 2;
    			blockChance = true;
			} else if(opponentWinDiagonallyCenter) {
				temp[0] = 1;
				temp[1] = 1;
    			blockChance = true;
			}
    	} while(false);
		
    	if(blockChance) {
    		coordinates[0] = temp[0];
    		coordinates[1] = temp[1];
    		System.out.println("Blocking");    
    		return coordinates;
    	}
    	
		coordinates[0] = random.nextInt(3);
		coordinates[1] = random.nextInt(3);			
		return coordinates;
	}
		
	@Override
	void respond() {  //advanced AI makes its move
    	if(!Check.checkEnd()) {
    		return;
    	}
    	System.out.println("Making move level \"" + name + "\"");    
		int [] moveAdvantage = checkWinCondition();
		int x;
    	int y;
		x = moveAdvantage[0];
    	y = moveAdvantage[1];
    	
    	while(game[y][x] != ' ') {
			x = random.nextInt(3);
		 	y = random.nextInt(3);
    	}
    	
    	game[y][x] = this.aiSymbol;
		Create.printGame();	   
	}
}