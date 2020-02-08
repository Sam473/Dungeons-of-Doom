/**
 * Runs the game with a human player and contains code needed to execute inputs.
 *
 */
public class HumanPlayer extends Player {
	
	private int goldOwned = 0;
	
	/**
     * Outputs part of the map that the player can see to the console char by char.
     * @param player
	 * 		the human player of the game
	 * @param bot
	 * 		the bot player of the game
	 * @param map
	 * 		the map that the bot and player use to play
     */
    public void look(Map map, Player player, BotPlayer bot) { 
    	
    	for(int i=player.getYCoord()-2; i<player.getYCoord()+3; i++) {
    		for(int j=player.getXCoord()-2; j<player.getXCoord()+3; j++) {
    			if (j == player.getXCoord() && i == player.getYCoord()) { //player and bot are both shown in look as they are not stored directly on the map 
    	    		  System.out.print("P");
  	        	} else if (j == bot.getXCoord() && i == bot.getYCoord()) {
    	    		  System.out.print("B");
  	        	} else if(j < 0 || j > map.getMapWidth() -1 || i < 0 || i > map.getMapHeight() -1) { //outputs walls if there is no tile as required
    	    		  System.out.print("#");
  	        	} else {
    	    		  System.out.print(map.getMapLocation(j,i));
    	    	}
    		}
    	    System.out.println(""); //for new line on the map
    	}
    }
   
    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     * Prints whether the command was successful or not.
     * @param map : map that the game is played on
     */
    public void pickup(Map map) {
    	if (map.getMapLocation(getXCoord(), getYCoord()) == 'G') {
    		System.out.println("SUCCESS"); //remove the gold from the map and increment gold owned
    		map.removeCollectedGold(getXCoord(), getYCoord());
    		goldOwned++;
    		System.out.println("Gold owned: " + getGoldOwned());
    	}
    	else {
    		System.out.println("FAIL");
    		System.out.println("Gold owned: " + getGoldOwned());
    	}
    }

    /**
     * Decides if the player has won or lost and prints to console when a quit command is called.
     * @param map that the player is playing on
     */
    public void checkPlayerWin(Map map) {
    	//checks if win conditions have been met and the player is on an exit tile to print if the user has won or lost
    	if (map.getMapLocation(getXCoord(), getYCoord()) == 'E' && getGoldOwned() >= map.getGoldRequired()) {
    		System.out.println("WIN");
    	}
    	else {
    		System.out.println("LOSE");
    	}
    }
    
    /**
	 * Get gold owned by the player.
	 * @return gold owned by the player
	 */
    public int getGoldOwned() {
    	return goldOwned;
    }
}