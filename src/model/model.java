//Gal Ashkenazi 315566752 - Final Test
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * The model define and manage all the data for this program.
 * @author GalAs
 *
 */
public class Model {
	private Square[][] arrSquares;
	private ArrayList<RectangleSelected> selectList;
	private ArrayList<TopPlayers> topPlayers;
	private GameDetails gameDetails;
	
	/**
	 * c'tor
	 */
	public Model() {
		selectList = new ArrayList<>();
		topPlayers = new ArrayList<>();
		gameDetails = new GameDetails();
	}
	
	/**
	 * Get game details
	 * @return
	 */
	public GameDetails getGameDetails() {
		return gameDetails;
	}

	/**
	 * Set game details
	 * @param gameDetails
	 */
	public void setGameDetails(GameDetails gameDetails) {
		this.gameDetails = gameDetails;
	}


	/**
	 * Get square selected list
	 * @return
	 */
	public ArrayList<RectangleSelected> getSelectList() {
		return selectList;
	}

	/**
	 * Set square selected list
	 * @return
	 */
	public void setSelectList(ArrayList<RectangleSelected> selectList) {
		this.selectList = selectList;
	}
	
	/**
	 * 
	 */
	public int getSelectedSize() {
		return this.selectList.size();
	}

	/**
	 * Update the tools game details
	 * 
	 * @param colorNumber
	 * @param hardLevel
	 * @param event
	 * @return
	 */
	public Square updateGameToolsArray(Color _color, boolean hardLevel, EventHandler<MouseEvent> event) {
		return (new Square(_color, hardLevel, event)); 
	}
	
	/**
	 * Make new squares array
	 * 
	 * @param size_x
	 * @param size_y
	 */
	public void initSquareArray(int size_x, int size_y) {
		this.arrSquares = new Square[size_x][size_y];
	}
	
	/**
	 * Get squares array
	 * 
	 * @return Square array
	 */
	public Square[][] getSquareArray() {
		return this.arrSquares;
	}
	
	/**
	 * Get the TOP 10 players list.
	 * 
	 * @return
	 */
	public ArrayList<TopPlayers> getTopPlayersList() {
		return topPlayers;
	}
	
	/**
	 * Update the TOP 10 players list
	 * 
	 * @param newTopPlayers
	 */
	public void updateTopPlayersList(ArrayList<TopPlayers> newTopPlayers) {
		this.topPlayers = newTopPlayers;
	}
	
	/**
	 * Sort and add the player to the TOP 10 list
	 * 
	 * @param score
	 * @param playerName
	 */
	public void addTopPlyaersList(int score, String playerName, String timeDate) {
		// add the player to the list
		this.topPlayers.add(new TopPlayers(score, playerName, timeDate));
		
		// sort the list by the score
		Collections.sort(this.topPlayers, new Comparator<TopPlayers>() {

			@Override
			public int compare(TopPlayers o1, TopPlayers o2) {
				if(o1.getScore() < o2.getScore())
					return 0;
				
				return -1;
			}
		});
		
		// remove the last player
		if(this.topPlayers.size() > 10)
			this.topPlayers.remove(this.topPlayers.size() -1);
	}
}

