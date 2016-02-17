package clarke.sw.scraper;

public class Matches {

	private String player1;
	private double p1Odds;
	private String player1Website;

	private String player2;
	private double p2Odds;
	private String player2Website;
	
	private Sports sport;
	

	public Matches() {
		super();
		this.player1 = "";
		this.p1Odds = 0;
		this.player1Website = "";
		this.player2 = "";
		this.p2Odds = 0;
		this.player2Website = "";
		this.sport = null;
	}
	
	public Matches(String player1, double p1Odds, String player1Website,
			String player2, double p2Odds, String player2Website, Sports sport) {
		super();
		this.player1 = player1;
		this.p1Odds = p1Odds;
		this.player1Website = player1Website;
		this.player2 = player2;
		this.p2Odds = p2Odds;
		this.player2Website = player2Website;
		this.sport = sport;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public double getP1Odds() {
		return p1Odds;
	}

	public void setP1Odds(double p1Odds) {
		this.p1Odds = p1Odds;
	}

	public String getPlayer1Website() {
		return player1Website;
	}

	public void setPlayer1Website(String player1Website) {
		this.player1Website = player1Website;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public double getP2Odds() {
		return p2Odds;
	}

	public void setP2Odds(double p2Odds) {
		this.p2Odds = p2Odds;
	}

	public String getPlayer2Website() {
		return player2Website;
	}

	public void setPlayer2Website(String player2Website) {
		this.player2Website = player2Website;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}	
	
	
}
