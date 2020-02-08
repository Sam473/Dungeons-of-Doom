/**
 * Runs the bot's turn in the game and contains logic/commands that the bot may use.
 */
public class BotPlayer extends Player{
	
	private int humanPlayerX;
	private int humanPlayerY;
	
	/**
	 * Controls the flow of the bots turn when called from GameLogic.
	 * @param player
	 * 		the human player of the game
	 * @param bot
	 * 		the bot player of the game
	 * @param map
	 * 		the map that the bot and player use to play
	 */
    public void botTurn(HumanPlayer player, BotPlayer bot, Map map) {
    	if (foundPlayer() != 0) { //check if player has been found
    		switch(foundPlayer()) { //finds the direction to the player
    		case 'N':
    			move('N', map, bot);
    			break;
    		case 'S':
    			move('S', map, bot);
    			break;
    		case 'E':
    			move('E', map, bot);
    			break;
    		case 'W':
    			move('W', map, bot);
    			break;
    		}
    		isPlayerCaught(player);
    	}
    	else { 
    		look(player); //look for the player if it has not been found yet
    	}
    }
    
    /**
	 * Method to check if the player has been caught at the end of the bot's turn.
	 * @param player
	 * 		the human player of the game
	 */
    private void isPlayerCaught(Player player) { //check if player has been caught and if it has then the player loses
    	if (player.getXCoord() == xCoord && player.getYCoord() == yCoord) {
    		System.out.println("LOSE");
    		System.exit(0);
    	}
    }
    
    /**
	 * Method to check if the player is within the bots field of view.
	 * @return The direction to the player if it has been found or 0 to indicate player if not found.
	 */
    private char foundPlayer() { //return direction to player if it is found, otherwise return zero to show that player has not been found
    	if ((humanPlayerY -yCoord) < 3 && (humanPlayerY -yCoord) > 0) {
    		return 'S';
    	} else if ((yCoord - humanPlayerY) < 3 && (yCoord - humanPlayerY) > 0) {
    		return 'N';
    	} else if ((xCoord - humanPlayerX) < 3 && (xCoord - humanPlayerX) > 0) {
    		return 'W';
    	} else if ((humanPlayerX -xCoord) < 3 && (humanPlayerX -xCoord) > 0) {
    		return 'E';
    	}
    	return 0; //if player is not found 
    }
    
    /**
	 * Method to get the players location and store where the player is at the point when the player looks.
	 * This is then used to check if the player is in the bot's field of view so that the bot can give chase.
	 * @param player
	 * 		The human player of the game.
	 */
    private void look(HumanPlayer player) { 
    	humanPlayerX = player.getXCoord();
    	humanPlayerY = player.getYCoord();
    }
}

