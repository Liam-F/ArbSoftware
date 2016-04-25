package clarke.sw.calculateArbs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import clarke.sw.betfair.BetfairSnooker;
import clarke.sw.betfair.BetfairTennis;
import clarke.sw.mcbookie.McbookieSnooker;
import clarke.sw.mcbookie.McbookieTennis;
import clarke.sw.paddyPower.PaddyPowerBasketball;
import clarke.sw.paddyPower.PaddyPowerSnooker;
import clarke.sw.paddyPower.PaddyPowerTennis;
import clarke.sw.scraper.Matches;
import clarke.sw.scraper.RoundDecimal;
import clarke.sw.scraper.Sports;
import clarke.sw.skybet.SkyBetSnooker;
import clarke.sw.skybet.SkyBetTennis;
import clarke.sw.williamHill.WilliamHillBasketball;
import clarke.sw.williamHill.WilliamHillSnooker;
import clarke.sw.williamHill.WilliamHillTennis;

public class CalculateArbs extends CompareLists {

//	private double startTime;
//	private double endTime;
//	private double duration;

	private LinkedList<Matches> williamHillMatches;
	private LinkedList<Matches> betfairMatches;
	private LinkedList<Matches> paddyPowerMatches;
	private LinkedList<Matches> mcbookieMatches;
//	private LinkedList<Matches> coralSportsMatches;
	private LinkedList<Matches> skyBetMatches;
	private LinkedList<Matches> matches;

	private LinkedList<Matches> bestOddsMatches;
	private LinkedList<ArbData> arbData;

	private LinkedList<Double> arbPercentage;
	private LinkedList<Double> singleArbPercentage;

	private LinkedList<Double> profitList;
	private LinkedList<Double> singleBetList;

	private Set<ArbData> arbDataNoDups;

	private LinkedList<ArbData> profitableArbs;

	ExecutorService executor;

	private RoundDecimal rd = new RoundDecimal();

	private double tempArb;
	private double arb;

	private double tmpProfit;
	private double profit;
	private double tmpSingleBet;
	private double singleBet;

	private int z = 0;

	public CalculateArbs() {
		bestOddsMatches = new LinkedList<Matches>();
		arbData = new LinkedList<ArbData>();
		arbPercentage = new LinkedList<Double>();
		singleArbPercentage = new LinkedList<Double>();
		profitList = new LinkedList<Double>();
		singleBetList = new LinkedList<Double>();
		arbDataNoDups = new HashSet<ArbData>();
		profitableArbs = new LinkedList<>();
	}

	// Get arbs method starts an executor service and get the odds from all the webpages. 
	// Then calls the methods that does the calculations to find arb opportunities
	public LinkedList<ArbData> getArbs() {
		executor = Executors.newFixedThreadPool(10);

//		startTime = System.currentTimeMillis();

		Future<LinkedList<Matches>> futureWillHillTennis = executor.submit(new WilliamHillTennis());
		Future<LinkedList<Matches>> futureBetfairTennis = executor.submit(new BetfairTennis());
		Future<LinkedList<Matches>> futurePaddyPowerTennis = executor.submit(new PaddyPowerTennis());
		Future<LinkedList<Matches>> futureMcbookieTennis = executor.submit(new McbookieTennis());
		// Future<LinkedList<Matches>> futureCoralSportsTennis =
		// executor.submit(new CoralSportsTennis());
		Future<LinkedList<Matches>> futureSkyBetTennis = executor.submit(new SkyBetTennis());

		Future<LinkedList<Matches>> futureWillHillSnooker = executor.submit(new WilliamHillSnooker());
		Future<LinkedList<Matches>> futureBetfairSnooker = executor.submit(new BetfairSnooker());
		Future<LinkedList<Matches>> futurePaddyPowerSnooker = executor.submit(new PaddyPowerSnooker());
		Future<LinkedList<Matches>> futureMcbookieSnooker = executor.submit(new McbookieSnooker());
//		Future<LinkedList<Matches>> futureCoralSportsSnooker = executor.submit(new CoralSportsSnooker());
		Future<LinkedList<Matches>> futureSkyBetSnooker = executor.submit(new SkyBetSnooker());

		Future<LinkedList<Matches>> futureWilliamHillBasketball = executor.submit(new WilliamHillBasketball());
		Future<LinkedList<Matches>> futurePaddyPowerBasketball = executor.submit(new PaddyPowerBasketball());

		try {
			executor.awaitTermination(15, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		// Had to catch each executor seperatly so if one failed the rest would still run 
		
		// System.out.println("Getting matches...");
		try {
			williamHillMatches = futureWillHillTennis.get();
		} catch (Exception e) {

		}
		try {
			matches = futureWillHillSnooker.get();
			williamHillMatches.addAll(matches);
		} catch (Exception e) {

		}
		try {
			matches = futureWilliamHillBasketball.get();
			williamHillMatches.addAll(matches);
		} catch (Exception e) {

		}
		try {
			betfairMatches = futureBetfairTennis.get();
		} catch (Exception e) {

		}
		try {
			matches = futureBetfairSnooker.get();
			betfairMatches.addAll(matches);
		} catch (Exception e) {

		}

		try {
			paddyPowerMatches = futurePaddyPowerTennis.get();
		} catch (Exception e) {

		}
		try {
			matches = futurePaddyPowerSnooker.get();
			paddyPowerMatches.addAll(matches);
		} catch (Exception e) {

		}
		try {
			matches = futurePaddyPowerBasketball.get();
			paddyPowerMatches.addAll(matches);
		} catch (Exception e) {

		}

		try {
			mcbookieMatches = futureMcbookieTennis.get();
		} catch (Exception e) {

		}
		try {
			matches = futureMcbookieSnooker.get();
			mcbookieMatches.addAll(matches);
		} catch (Exception e) {

		}

//		try {
//			coralSportsMatches = futureCoralSportsSnooker.get();
//		} catch (Exception e) {
//
//		}

		try {
			skyBetMatches = futureSkyBetTennis.get();
		} catch (Exception e) {

		}
		try {
			matches = futureSkyBetSnooker.get();
			skyBetMatches.addAll(matches);
		} catch (Exception e) {

		}

		// System.out.println("Finished getting matches");

//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime) / 1000;
//		System.out.print("connection time: ");
//		System.out.println(duration);

//		startTime = System.currentTimeMillis();

		// Compare the names in each list to the best list. 
		// William hill in my opinion had the names done the best so each other website has to compare to that.
		compareNames(williamHillMatches, betfairMatches);
		compareNames(williamHillMatches, paddyPowerMatches);
		compareNames(williamHillMatches, mcbookieMatches);
//		compareNames(williamHillMatches, coralSportsMatches);
		compareNames(williamHillMatches, skyBetMatches);

//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime) / 1000;
//		System.out.print("compare names time: ");
//		System.out.println(duration);

		// Add all the matches found to a new list of all matches
		addMatchesToList(williamHillMatches);
		addMatchesToList(betfairMatches);
		addMatchesToList(paddyPowerMatches);
		addMatchesToList(mcbookieMatches);
//		addMatchesToList(coralSportsMatches);
		addMatchesToList(skyBetMatches);

//		startTime = System.currentTimeMillis();
		
		// If the matches lists are not null compare them to the list with the best odds and replace any contender and odd that on the best list with the new best value.
		if (williamHillMatches != null) {
			bestOddsMatches = compareLists(bestOddsMatches, williamHillMatches, Sports.TENNIS);
			bestOddsMatches = compareLists(bestOddsMatches, williamHillMatches, Sports.SNOOKER);
			bestOddsMatches = compareLists(bestOddsMatches, williamHillMatches, Sports.BASKETBALL);
		}
		if (betfairMatches != null) {
			bestOddsMatches = compareLists(bestOddsMatches, betfairMatches, Sports.TENNIS);
			bestOddsMatches = compareLists(bestOddsMatches, betfairMatches, Sports.SNOOKER);

		}
		if (paddyPowerMatches != null) {
			bestOddsMatches = compareLists(bestOddsMatches, paddyPowerMatches, Sports.TENNIS);
			bestOddsMatches = compareLists(bestOddsMatches, paddyPowerMatches, Sports.SNOOKER);
			bestOddsMatches = compareLists(bestOddsMatches, paddyPowerMatches, Sports.BASKETBALL);
		}
		if (mcbookieMatches != null) {
			bestOddsMatches = compareLists(bestOddsMatches, mcbookieMatches, Sports.TENNIS);
			bestOddsMatches = compareLists(bestOddsMatches, mcbookieMatches, Sports.SNOOKER);
		}
//		if (coralSportsMatches != null) {
//			bestOddsMatches = compareLists(bestOddsMatches, coralSportsMatches, Sports.SNOOKER);
//		}
		if (skyBetMatches != null) {
			bestOddsMatches = compareLists(bestOddsMatches, skyBetMatches, Sports.TENNIS);
			bestOddsMatches = compareLists(bestOddsMatches, skyBetMatches, Sports.SNOOKER);
		}

		// Some timing stuff to ensure my program ran within a time limit
//		endTime = System.currentTimeMillis();
//		duration = (endTime - startTime) / 1000;
//		System.out.print("comparing time: ");
//		System.out.println(duration);

		
		// Calculations for finding the arbitrage opportunities.
		getArbPercentages();

		getSingleArbs();

		calculateProfits();

		calcSingleBets();

		
		// Create objects to populate the table of data to add to the database.
		populateArbTableData();

		// Remove any duplicates in the data to go to the database.
		removeDuplicates();

		// If the arb percentage is below %100 then it is an arb opportunity. So get rid of any data that are not arbs.
		getProfitableArbs(arbData);

//		test();

		return profitableArbs;
	}

	// Adds all the mathces to the best odds list
	private void addMatchesToList(LinkedList<Matches> match) {
		try {
			for (Matches m : match) {
				bestOddsMatches.add(m);
			}
		} catch (Exception e) {
//			System.out.println(match + " not included");
		}
	}

	// Get the percentage of the matches, if it is below %100 it is an arbitrage oportunity.
	private void getArbPercentages() {
		for (int i = 0; i < bestOddsMatches.size(); i++) {
			arbPercentage.add(calcFullArb(bestOddsMatches.get(i).getP1Odds(), bestOddsMatches.get(i).getP2Odds()));
		}
	}

	private double calcFullArb(double odd1, double odd2) {
		tempArb = ((1 / (odd1) * 100) + (1 / (odd2)) * 100);
		arb = rd.round(tempArb, 3);
		return arb;
	}

	private void getSingleArbs() {
		for (int i = 0; i < bestOddsMatches.size(); i++) {
			singleArbPercentage.add(calcIndividualArb(bestOddsMatches.get(i).getP1Odds()));
			singleArbPercentage.add(calcIndividualArb(bestOddsMatches.get(i).getP2Odds()));
		}
	}

	private double calcIndividualArb(double odd) {
		tempArb = ((1 / (odd)) * 100);
		arb = rd.round(tempArb, 2);
		return arb;
	}

	// Calculate the profit that will be earned from €100. €100 is the default amount, it can be change by each user on the website.
	private void calculateProfits() {
		for (int i = 0; i < arbPercentage.size(); i++) {
			tmpProfit = ((100 / (arbPercentage.get(i) / 100)) - 100);
			profit = rd.round(tmpProfit, 2);
			profitList.add(profit);
		}
	}

	/*
	 * looping using single arb percentage might not work properly if not change
	 * to best odds matches and duplicate the loc inside
	 */
	private void calcSingleBets() {
		for (int i = 0; i < singleArbPercentage.size(); i++) {
			tmpSingleBet = calcSingleBet(singleArbPercentage.get(i), i);
			singleBet = rd.round(tmpSingleBet, 2);
			singleBetList.add(singleBet);
		}
		z = 0;
	}

	// Calculate the individual bet that needs to be placed.
	private double calcSingleBet(double individualArb, int i) {
		double b;
		if (i % 2 == 0) {
			b = arbPercentage.get(z);
		} else {
			b = arbPercentage.get(z++);
		}
		double individualBet = (100 * individualArb) / b;
		return individualBet;
	}

	private void populateArbTableData() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < bestOddsMatches.size(); i++) {
			// Had sound in an earlier version to alert us when testing if an arbitrage opportunity was found.
			// if (rd.round(arbPercentage.get(x), 2) < 99) {
			// one = new Thread() {
			// public void run() {
			// playSoundInternal(audio);
			// }
			// };
			// one.start();
			//
			// }
			// if (round(arbPercentage.get(x), 2) < 102) {

			arbData.add(new ArbData(rd.round(arbPercentage.get(x), 2), profitList.get(x++),
					bestOddsMatches.get(i).getPlayer1(), rd.round(bestOddsMatches.get(i).getP1Odds(), 2),
					bestOddsMatches.get(i).getPlayer1Website(), singleBetList.get(y++),
					bestOddsMatches.get(i).getPlayer2(), rd.round(bestOddsMatches.get(i).getP2Odds(), 2),
					bestOddsMatches.get(i).getPlayer2Website(), singleBetList.get(y++),
					bestOddsMatches.get(i).getSport()));

			// }
		}
	}

	// Remove duplicate arbs from the list
	private void removeDuplicates() {
		arbDataNoDups.addAll(arbData);
		arbData.clear();
		arbData.addAll(arbDataNoDups);
	}

	// Get the bets that are arbitrages (meaning bets where the arb percentage is below %100).
	private void getProfitableArbs(LinkedList<ArbData> allArbs) {
		for (ArbData arbData : allArbs) {
			if (arbData.getArbPercentage() < 100) {
				profitableArbs.add(arbData);
			}
		}
	}

	// Test the class
//	private void test() {
//		System.out.println("\n\n");
//		for (ArbData ad : arbData) {
//			System.out.println("€" + ad.getProfit() + " --- %" + ad.getArbPercentage() + " --- " + ad.getPlayer1()
//					+ "   €" + ad.getPlayer1BetAmount() + "   " + ad.getPlayer1Website() + "   " + ad.getPlayer1Odds()
//					+ " --- " + ad.getPlayer2() + "   €" + ad.getPlayer2BetAmount() + "   " + ad.getPlayer2Website()
//					+ "   " + ad.getPlayer2Odds() + "   " + ad.getSport() + "\n");
//		}
//		System.out.println("\n\n");
//	}
}
