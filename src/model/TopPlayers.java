//Gal Ashkenazi 315566752 - Final Test
package model;

/**
 * Holder for TOP 10 players
 * @author GalAs
 *
 */
public class TopPlayers {
	private int score;
	private String playerName;
	private String timeDate;
	
	/**
	 * c'tor
	 * @param _score
	 * @param _playerName
	 */
	public TopPlayers(int _score, String _playerName, String _timeDate) {
		this.score = _score;
		this.playerName = _playerName;
		this.timeDate = _timeDate;
	}
	
	// getters and setters
	
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

	public String getTimeDate() {
		return timeDate;
	}

	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
	
	
}
