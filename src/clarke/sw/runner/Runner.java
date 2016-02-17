package clarke.sw.runner;

import java.util.LinkedList;

import clarke.sw.calculateArbs.ArbData;
import clarke.sw.calculateArbs.CalculateArbs;

public class Runner {

	private LinkedList<ArbData> arbData;
	
	public Runner(){
		arbData = new CalculateArbs().getArbs();
	}
	
	private void displayResults(){
		System.out.println();
		
		for (ArbData ad : arbData)
			if(ad.getArbPercentage() < 105)
				System.out.println(ad.getArbPercentage() + " --- " + ad.getPlayer1() + "   " + ad.getPlayer1BetAmount()
						+ "   " + ad.getPlayer1Website() + "   " + ad.getPlayer1Odds() + " --- " + ad.getPlayer2() + "   " + ad.getPlayer2BetAmount()
						+ "   " + ad.getPlayer2Website() + "   " + ad.getPlayer2Odds() + "   " + ad.getSport() + "\n");

		System.out.println(arbData.size());
	}
	
	public static void main(String[] args) {

		new Runner().displayResults();
		
	}

}
