package clarke.sw.runner;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import clarke.sw.calculateArbs.ArbData;
import clarke.sw.calculateArbs.CalculateArbs;
import clarke.sw.database.Database;

public class Runner extends TimerTask {

	private LinkedList<ArbData> arbData;

	public Runner() {
//		arbData = new CalculateArbs().getArbs();
//		displayResults();
	}

	// When ran display arbs to the terminal or cmd prompt.
	private void displayResults() {
		System.out.println();

		for (ArbData ad : arbData)
			if (ad.getArbPercentage() < 100)
				System.out.println("€" + ad.getProfit() + " --- %" + ad.getArbPercentage() + " --- " + ad.getPlayer1()
						+ "   €" + ad.getPlayer1BetAmount() + "   " + ad.getPlayer1Website() + "   "
						+ ad.getPlayer1Odds() + " --- " + ad.getPlayer2() + "   €" + ad.getPlayer2BetAmount() + "   "
						+ ad.getPlayer2Website() + "   " + ad.getPlayer2Odds() + "   " + ad.getSport() + "\n");

		System.out.println(arbData.size());
	}

	// Run method to be invoked by the main to get the program running.
	public void run() {
		arbData = new CalculateArbs().getArbs();
		try {
			new Database(arbData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		displayResults();
	}

	public static void main(String[] args) {
		// Rerun every 15 seconds to keep finding new arbitrage opportunities.
		Timer timer = new Timer();
		timer.schedule(new Runner(), 0, 1000*15);
		
	}

}
