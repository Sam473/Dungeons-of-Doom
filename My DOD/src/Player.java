/**
 * Class player which is the superclass that bot and human players inherit from for their common functions/variables.
 * It is abstact as it does not need to be instansiated.
*/
public abstract class Player {
	//For the bot and human to inherit
	//Pass player and map as parameters

	protected int xCoord = 0;
	protected int yCoord = 0;
	
	/**
     * Checks if movement is legal and updates player's location on the map.
     * If the player is an instance of HumanPlayer then it outputs whether the move was successful or not.
     * 
     * @param direction : The direction of the movement.
     * @param map : The map that the game runs with.
     * @param player : The human or bot player of the game.
     */
    public void move(char direction, Map map, Player player) { 
    	
    	switch (direction) {
    	case 'N':
    		if (map.getMapLocation(player.getXCoord(), player.getYCoord() - 1) != '#') { //only move if there is not a wall in the way
    			player.addYCoord(-1); //move player coordinates
    			if (player instanceof HumanPlayer) { //only output SUCCESS OR FAIL if the human is the one moving as this function is also used by the bot
	    			System.out.println("SUCCESS");
    			}
    			return;
    		}
    		break;
    	case 'S':
    		if (map.getMapLocation(player.getXCoord(),player.getYCoord() + 1) != '#') {
    			player.addYCoord(1);
    			if (player instanceof HumanPlayer) {
	    			System.out.println("SUCCESS");
    			}
    			return;
       		}
    		break;
    	case 'E':
    		if (map.getMapLocation(player.getXCoord() + 1,player.getYCoord()) != '#') {
    			player.addXCoord(1);
    			if (player instanceof HumanPlayer) {
	    			System.out.println("SUCCESS");
    			}
    			return;
    		}
    		break;
    	case 'W':
    		if (map.getMapLocation(player.getXCoord() - 1,player.getYCoord()) != '#') {
    			player.addXCoord(-1);	
    			if (player instanceof HumanPlayer) {
	    			System.out.println("SUCCESS");
    			}
    			return;
    		}
    		break;
    	default:    		
    		System.out.println("To move you must type a command in the form 'MOVE <direction>' Directions are N,S,E,W");
    		return;
    	}
    	
    	if (player instanceof HumanPlayer) {
			System.out.println("FAIL");
		}
    }
	
	/**
	 * Method to get the x coordinate of the player.
	 * @return x coordinate of player
	 */
	public int getXCoord() {  
	    return xCoord;
	}
	
	/**
	 * Method to get the y coordinate of the player.
	 * @return y coordinate of player
	 */
	public int getYCoord() {
	    return yCoord;
	}
	    
	/**
	 * Method to set the x coordinate of the player.
	 * @param  x :  integer to set the x coord to
	 */
	public void setXCoord(int x) {
	    xCoord = x;
	}
	    
	/**
	 * Method to set the y coordinate of the player.
	 * @param y : integer to set the y coord to
	 */
	public void setYCoord(int y) {
	    yCoord = y;
	}
	   
	/**
	 * Method to add a number to the y coordinate of the player.
	 * @param y : number to add to the y coord
	 */
	public void addYCoord(int y) {
		yCoord = yCoord + y;
	}
	 
	/**
	 * Method to add a number to the y coordinate of the player.
	 * @param x : number to add to the x coord
	 */
	public void addXCoord(int x) {
	    xCoord = xCoord + x;
	}
}
