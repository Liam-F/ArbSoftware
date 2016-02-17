package clarke.sw.scraper;

public class Player {

	private String playerName;
	private double odds;
	private String playerWebsite;
	
	public Player() {
		super();
		this.playerName = "";
		this.odds = 0;
		this.playerWebsite = "";
	}
	public Player(String playerName, double odds, String playerWebsite) {
		super();
		this.playerName = playerName;
		this.odds = odds;
		this.playerWebsite = playerWebsite;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public double getOdds() {
		return odds;
	}
	
	public void setOddsDecimal(double oddsDecimal) {
		this.odds = oddsDecimal;
	}
	
	public String getPlayerWebsite() {
		return playerWebsite;
	}
	
	public void setPlayerWebsite(String playerWebsite) {
		this.playerWebsite = playerWebsite;
	}
}
