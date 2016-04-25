package clarke.sw.coralSports;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import clarke.sw.scraper.MakeMatches;
import clarke.sw.scraper.Matches;
import clarke.sw.scraper.Player;
import clarke.sw.scraper.RetrieveOdds;
import clarke.sw.scraper.RoundDecimal;
import clarke.sw.scraper.Sports;

public class CoralSportsSnooker extends MakeMatches implements RetrieveOdds, Callable<LinkedList<Matches>> {

	private Document document;
	private Elements playersNames;
	private Elements playersOdds;
	private String temp;
	private String[] tmp;
	private String website = "Coral Sports";
	private String page = "http://sports.coral.co.uk/snooker";
	private LinkedList<String> playersList = new LinkedList<>();
	private LinkedList<Double> oddsList = new LinkedList<>();
	private LinkedList<Player> coralSportsPlayers = new LinkedList<>();
	private LinkedList<Player> players;
	private LinkedList<Matches> matches;
	private RoundDecimal rd = new RoundDecimal();
	
	
	// Get connection to webpage to scrape
	public Document getConnection(String url) throws Exception {
		Connection connection = Jsoup.connect(url);
		connection.timeout(15000);
		document = connection.get();
		return document;
	} 
	
	// Parse the page that contains the odds to get the name of the contenders and their odds
	public LinkedList<Player> getOdds(Document doc) {
		
		Elements e = doc.select("gp-widget-coral-widget-news");
		e.remove();
		
		e = doc.select("bet-highlights");
		e.remove();
		
		playersNames = doc.select(".block-content").select(".match")
				.select(".bet-title");
		playersOdds = doc.select(".block-content").select(".match")
				.select(".bip-odds, .home-odds");

		for (int i = 0; i < playersNames.size(); i++) {
			temp = playersNames.get(i).text().replaceAll("\\u00A0", "");
			tmp = temp.split(" v ");
			playersList.add(tmp[0].toLowerCase().trim());
			playersList.add(tmp[1].toLowerCase().trim());
		}
		for (int i = 0; i < playersOdds.size(); i++) {
			tmp = playersOdds.get(i).text().split(" ");
			oddsList.add(rd.convertToDecimal(tmp[0]) + 1);
		}
		for (int i = playersList.size() - 1; i >= 0; i--) {
			if (playersList.get(i).contains("/")) {
				playersList.remove(i);
				oddsList.remove(i);
			}
		}
		coralSportsPlayers.clear();
		for (int i = 0; i < playersList.size(); i++) {
			coralSportsPlayers.add(new Player(playersList.get(i), oddsList.get(i), website));
		}
		playersList.clear();
		oddsList.clear();
		return coralSportsPlayers;
	}
	
	// Call method needed to start the thread
	public LinkedList<Matches> call() throws Exception {
		try {
			Document doc = getConnection(page);
			players = getOdds(doc);
			matches = getMatches(players, Sports.SNOOKER);
			return matches;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	// Test main method to see if it was finding the right names and odds

	// public static void main(String[] args) throws Exception {
	// CoralSportsSnooker cs = new CoralSportsSnooker();
	//
	// Document docpp = cs.getConnection("http://sports.coral.co.uk/snooker");
	// LinkedList<Player> players = cs.getOdds(docpp);
	// LinkedList<Matches> matches = cs.getMatches(players, Sports.SNOOKER);
	// for (int i = 0; i < matches.size(); i++) {
	// System.out.printf("%30s%8.2f\t\tV\t%s%8.2f\t\t\t%s\n",
	// matches.get(i).getPlayer1(),
	// matches.get(i).getP1Odds(), matches.get(i).getPlayer2(),
	// matches.get(i).getP2Odds(),
	// matches.get(i).getPlayer1Website());
	// }
	//
	// System.out.println(matches.size());
	// }
}
