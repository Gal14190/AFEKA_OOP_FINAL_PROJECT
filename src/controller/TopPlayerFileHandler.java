//Gal Ashkenazi 315566752 - Final Test
package controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.TopPlayers;

/**
 * handle TOP 10 players file.
 * @author GalAs
 *
 */
public class TopPlayerFileHandler {
	final String pathFile = "topPlayers.txt";
	
	private File file;
	PrintWriter wirteFile;
	Scanner reader;
	
	/**
	 * c'tor
	 */
	public TopPlayerFileHandler() throws Exception {
		file = new File(pathFile);
		file.createNewFile(); // create new file if not exist
	}
	
	/**
	 * Create and update file
	 * 
	 * @param topPlayers
	 * @throws Exception
	 */
	public void update(ArrayList<model.TopPlayers> topPlayers) throws Exception {
		PrintWriter writer = new PrintWriter(file); // start write into the file

		for (model.TopPlayers player : topPlayers) {
			writer.println(player.getPlayerName() + "\n" + player.getScore() + "\n" + player.getTimeDate());
		}
			
		writer.close(); // close file
	}
	
	/**
	 * Read and parse file
	 * @return
	 * @throws Exception
	 */
	public ArrayList<model.TopPlayers> read() throws Exception {
		ArrayList<model.TopPlayers> topPlayers;
		topPlayers = new ArrayList<>();
		
		Scanner fileReader = new Scanner(file); // start read the file
			
		while (fileReader.hasNextLine()) {
			String playerName = fileReader.nextLine(); // read player name
		    int playerScore = Integer.parseInt(fileReader.nextLine()); // read the score
		    String timeDate = fileReader.nextLine();
		        
		    topPlayers.add(new TopPlayers(playerScore, playerName, timeDate));
		}
			
		fileReader.close(); // close file
		return topPlayers;
	}
}
