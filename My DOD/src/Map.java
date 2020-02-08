import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads and contains in memory the map of the game.
 */
public class Map {

	private char[][] map;		//Representation of the map
	private String mapName;		
	private int goldRequired;	//Gold required for the human player to win
	

	/**
	 * Constructor that accepts a map to read in from.
	 *
	 * @param fileName : The filename of the map file. 
	 */
	public Map(String fileName) {
		try {
			readMap(fileName);
		} catch (IOException e) { //handles an exception with the IO of the map
			System.out.print("There is a problem with the map file");
			System.exit(0);
		} //catch the exception
	}

    /**
     * @return : Gold required to exit the current map.
     */
    public int getGoldRequired() {
        return goldRequired;
    }

    /**
     * @return : The map as stored in memory.
     */
    public char[][] getMap() {
        return map;
    }
    
    /**
     * @return : The value of a particular coordinate in the map.
     * @param x : coord of the location
     * @param y : coord of the location
     */
    public char getMapLocation(int x,int y) {
        return map[y][x];
    }
 
    /**
     * Removes gold at a certain location in the map.
     * @param x : coord of the location
     * @param y : coord of the location
     */
    public void removeCollectedGold(int x, int y) {
    	map[y][x] = '.';
    }
    
    /**
     * Gets the width of the map.
     * @return width of the map
     */
    public int getMapWidth() {
    	return map[0].length;
    }
    
    /**
     * Gets the height of the map.
     * @return height of the map
     */
    public int getMapHeight() {
    	return map.length;
    }

    /**
     * Reads the map from file using a buffered reader.
     *
     * @param fileName : Name of the map's file.
     * @throws IOException if there is an issue with the file
     */
    public void readMap(String fileName) throws IOException {
    	
    	File mapFile = new File(fileName); 
    	BufferedReader mapRead = new BufferedReader(new FileReader(mapFile)); //reader used to read the map file
    	String st; 
    	int i = 0;
    	int mapNumRows = numOfLines(fileName) - 2;
    	
    	  while ((st = mapRead.readLine()) != null) { //read the map from file and input specs to program
    	    if (st.substring(0,4).equals("name")) {
    	    	mapName = st.substring(4);
    	    	continue;
    	    }
    	    if (st.substring(0,3).equals("win")) {
    	    	goldRequired = Integer.parseInt(st.substring(4));
    	    	continue;
    	    }
    	    if (map == null) { //to define the size of the two dimensional array to store the map
    	    	map = new char[mapNumRows][st.length()];
    	    }
    	    
	    	for(int j=0; j<st.length(); j++){ //loop through to store map in an array
	    	   map[i][j] = st.charAt(j);
	    	}
    	    i++;
    	  }
    	  mapRead.close();
    }
    
    /**
     * Reads the map from file using a buffered reader.
     * @param the name of the map file
     * @return number of lines in the file
     */
    private int numOfLines(String fileName) {
    	int numOfLines = 0;
    	File mapFile = new File(fileName); 
    	
    	try {
	    	BufferedReader br = new BufferedReader(new FileReader(mapFile)); //file reader to find the number of lines in the file
	        while ((br.readLine()) != null){ //reads until there are no more lines and counts lines
	        	numOfLines++;
	        }
	            br.close();
	    	}
    	catch (Exception e) { //catches an exception with reading the map
    		System.out.println("MAP FILE ISSUE");
    		System.exit(0);
    	}
    	return numOfLines;
    }
}
