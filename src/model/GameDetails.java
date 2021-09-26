package model;

/**
 * Holder for game details - current level, score and player name.
 * @author GalAs
 *
 */
public class GameDetails {
	private int level;
	private int score;
	private String playerName;
	private boolean hardLevel;
	
	/**
	 * c'tor
	 */
	public GameDetails() {
		this.level = 1; // default level
		this.hardLevel = false;
		this.score = 0; // default score
		this.playerName = "guest"; // default player name
	}
	
	// getters and setters
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
		
		if(level == 1 || level == 2) this.hardLevel = false;
		else this.hardLevel = true;
	}
	
	public boolean isHardLevel() {
		return hardLevel;
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
}
