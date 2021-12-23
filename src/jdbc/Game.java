package jdbc;

public class Game {
	
	private int gameId;
	private String gameTitle;
	
	public Game(String gameTitle) {
		
		this.gameTitle = gameTitle;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	@Override
	public String toString() {
		return getGameTitle() ;
	}
	
	

}
