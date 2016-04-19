package clarke.sw.calculateArbs;

import clarke.sw.scraper.Sports;;

public class ArbData {
	private double arbPercentage;
	private double profit;
	
	private String player1;
	private double player1Odds;
	private String player1Website;
	private double player1BetAmount;
	
	private String player2;
	private double player2Odds;
	private String player2Website;
	private double player2BetAmount;
	
	private Sports sport;
	
	public ArbData() {
		super();
		this.arbPercentage = 0;
		this.profit = 0;
		this.player1 = "";
		this.player1Odds = 0;
		this.player1Website = "";
		this.player1BetAmount = 0;
		this.player2 = "";
		this.player2Odds = 0;
		this.player2Website = "";
		this.player2BetAmount = 0;
		this.sport = null;
	}
	
	public ArbData(double arbPercentage, double profit, String player1,
			double player1Odds, String player1Website, double player1BetAmount,
			String player2, double player2Odds, String player2Website,
			double player2BetAmount, Sports sport) {
		super();
		this.arbPercentage = arbPercentage;
		this.profit = profit;
		this.player1 = player1;
		this.player1Odds = player1Odds;
		this.player1Website = player1Website;
		this.player1BetAmount = player1BetAmount;
		this.player2 = player2;
		this.player2Odds = player2Odds;
		this.player2Website = player2Website;
		this.player2BetAmount = player2BetAmount;
		this.sport = sport;
	}

	public double getArbPercentage() {
		return arbPercentage;
	}

	public void setArbPercentage(double arbPercentage) {
		this.arbPercentage = arbPercentage;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public double getPlayer1Odds() {
		return player1Odds;
	}

	public void setPlayer1Odds(double player1Odds) {
		this.player1Odds = player1Odds;
	}

	public String getPlayer1Website() {
		return player1Website;
	}

	public void setPlayer1Website(String player1Website) {
		this.player1Website = player1Website;
	}

	public double getPlayer1BetAmount() {
		return player1BetAmount;
	}

	public void setPlayer1BetAmount(double player1BetAmount) {
		this.player1BetAmount = player1BetAmount;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public double getPlayer2Odds() {
		return player2Odds;
	}

	public void setPlayer2Odds(double player2Odds) {
		this.player2Odds = player2Odds;
	}

	public String getPlayer2Website() {
		return player2Website;
	}

	public void setPlayer2Website(String player2Website) {
		this.player2Website = player2Website;
	}

	public double getPlayer2BetAmount() {
		return player2BetAmount;
	}

	public void setPlayer2BetAmount(double player2BetAmount) {
		this.player2BetAmount = player2BetAmount;
	}

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(arbPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((player1 == null) ? 0 : player1.hashCode());
		temp = Double.doubleToLongBits(player1BetAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(player1Odds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((player1Website == null) ? 0 : player1Website.hashCode());
		result = prime * result + ((player2 == null) ? 0 : player2.hashCode());
		temp = Double.doubleToLongBits(player2BetAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(player2Odds);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((player2Website == null) ? 0 : player2Website.hashCode());
		temp = Double.doubleToLongBits(profit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sport == null) ? 0 : sport.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArbData other = (ArbData) obj;
		if (Double.doubleToLongBits(arbPercentage) != Double.doubleToLongBits(other.arbPercentage))
			return false;
		if (player1 == null) {
			if (other.player1 != null)
				return false;
		} else if (!player1.equals(other.player1))
			return false;
		if (Double.doubleToLongBits(player1BetAmount) != Double.doubleToLongBits(other.player1BetAmount))
			return false;
		if (Double.doubleToLongBits(player1Odds) != Double.doubleToLongBits(other.player1Odds))
			return false;
		if (player1Website == null) {
			if (other.player1Website != null)
				return false;
		} else if (!player1Website.equals(other.player1Website))
			return false;
		if (player2 == null) {
			if (other.player2 != null)
				return false;
		} else if (!player2.equals(other.player2))
			return false;
		if (Double.doubleToLongBits(player2BetAmount) != Double.doubleToLongBits(other.player2BetAmount))
			return false;
		if (Double.doubleToLongBits(player2Odds) != Double.doubleToLongBits(other.player2Odds))
			return false;
		if (player2Website == null) {
			if (other.player2Website != null)
				return false;
		} else if (!player2Website.equals(other.player2Website))
			return false;
		if (Double.doubleToLongBits(profit) != Double.doubleToLongBits(other.profit))
			return false;
		if (sport != other.sport)
			return false;
		return true;
	}
	
	
}
