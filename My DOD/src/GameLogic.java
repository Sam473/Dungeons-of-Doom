import java.util.Scanner;
import java.util.Random;

/**
 * Contains the main logic part of the game, as it processes.
 */
public class GameLogic {
	
	private Map map;
	private HumanPlayer player;
	private BotPlayer bot;
	Scanner userIn = new Scanner(System.in);
	
	/**
	 *  Default constructor to initialise the basic components of the game.
	 */
	private GameLogic() {
		map = new Map(chooseMap()); //initialise map with parameter being the map chosen by the user in the choosemap() function below
		player = new HumanPlayer();
		bot = new BotPlayer();
		playersStartLocation(); //calls function to give player and bot a random location on the map
	}

	/**
	 * Allows the user to choose a map to start the game on.
	 * @return The map name as a string
	 */
    private String chooseMap() {
		System.out.println("Please choose a map, enter '1', '2', or '3'");
		System.out.println("1. Small");
		System.out.println("2. Medium");
		System.out.println("3. Large");
		
		int userChoice = Integer.parseInt(userIn.nextLine());
		
		switch(userChoice) {
		case 1:
			return "small_example_map.txt";
		case 2:
			return "medium_example_map.txt";
		case 3:
			return "large_example_map.txt";
		default:
			return chooseMap(); //if the input is not 1,2, or 3 then the user must try again to select a map
		}
    }
	
    /**
	 * Function handles the main running of the game and controls player turns.
	 */
    private void playGame() {

    	String action = "";
    	while (isGameRunning(action)) { //calls is game running function to decide whether to quit the game
    		System.out.println("Please enter a command");
    		
            action = userIn.nextLine().toUpperCase(); //to upper case so that command is understood
            
            if (action.length() < 4) { //ensure command is long enough so that substring() doesn't fail
            	System.out.println("Please enter a valid command");
            	continue;
            }

        	if (action.equals("HELLO")) {
        		System.out.println("Gold to win: " + map.getGoldRequired());
        	} else if (action.equals("GOLD")) {
        		System.out.println("Gold owned: " + player.getGoldOwned());
        	} else if (action.substring(0, 4).equals("MOVE")) {
        		try {
        			player.move(action.charAt(5), map, player);
        		}
        		catch(StringIndexOutOfBoundsException e) { // catches in case the command does not contain a direction
        			System.out.println("To move you must type a command in the form 'MOVE <direction>' Directions are N,S,E,W");
        		}
        	} else if (action.equals("LOOK")) {
        		player.look(map, player, bot);
        	} else if (action.equals("PICKUP")) {
        		player.pickup(map);
        	} else if (action.equals("QUIT")) {
        		player.checkPlayerWin(map);
        	} else {
        		System.out.println("Please enter a valid command");
        	}
        	bot.botTurn(player, bot, map); //Once player has had a turn, the bot's turn is started
    	}
    	userIn.close(); //close userin when it is no longer required.
    }
    
    /**
	 * Checks if the game is running.
	 * @param action
	 * 		The action entered by the user
     * @return if the game should still be running or not.
     */
	public boolean isGameRunning(String action) {
		if (action.equals("QUIT")) {
    		return false;
    	}
    	return true;
	}
    
	/**
	 * Defines the start locations for both player and bot with coordinates.
	 * 
	 */
    private void playersStartLocation() {
    	Random random = new Random();
    	
    	while(player.getXCoord() == 0) { //While loop to ensure that the player is not on a gold tile or wall to begin with
    		int x = random.nextInt(map.getMapWidth());
        	int y = random.nextInt(map.getMapHeight());
        	
        	if (map.getMapLocation(x,y) != 'G' && map.getMapLocation(x,y) != '#') {
        		player.setXCoord(x);
        		player.setYCoord(y);
        	}
    	} 
    	while(bot.getXCoord() == 0) {
    		int x = random.nextInt(map.getMapWidth());
        	int y = random.nextInt(map.getMapHeight());
        	
        	if (map.getMapLocation(x,y) != 'G' && map.getMapLocation(x,y) != '#'&& x != player.getXCoord() && y != player.getYCoord()) {
        		bot.setXCoord(x);
        		bot.setYCoord(y);
        	}
    	} 
    }
	
    /**
	 * Main function controls the flow of the program.
	 */
	public static void main(String[] args) {
		GameLogic logic = new GameLogic();
		logic.playGame(); //to start the game
    }
}