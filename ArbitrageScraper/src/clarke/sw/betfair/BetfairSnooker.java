package clarke.sw.betfair;

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

public class BetfairSnooker extends MakeMatches implements RetrieveOdds, Callable<LinkedList<Matches>> {

	private Document document;
	private Elements playersNames;
	private Elements players2Names;
	private Elements playersOdds;
	private LinkedList<String> playersList = new LinkedList<String>();
	private LinkedList<Double> oddsList = new LinkedList<Double>();
	private LinkedList<Player> betfairPlayers = new LinkedList<Player>();
	private LinkedList<Player> players;
	private LinkedList<Matches> matches;
	private String temp;
	private String[] tmp;
	private String website = "Betfair";
	private String page = "https://www.betfair.com/sport/snooker";
	private RoundDecimal rd = new RoundDecimal();
	
	public Document getConnection(String url) throws Exception {
		Connection connection = Jsoup.connect(url);
		connection.timeout(15000);
		document = connection.get();
		return document;
	}
	
	public LinkedList<Player> getOdds(Document doc) {
		playersNames = doc.getElementsByClass("home-team-name");
		players2Names = doc.getElementsByClass("away-team-name");
		playersOdds = doc.getElementsByClass("ui-runner-price");

		for (int i = 0; i < playersNames.size(); i++) { // loop through all the
														// players
			if (playersNames.get(i).text().contains(",") && players2Names.get(i).text().contains(",")) {
				tmp = playersNames.get(i).text().split(",");
				temp = tmp[1] + " " + tmp[0];
				playersList.add(temp.toLowerCase().trim());
				tmp = players2Names.get(i).text().split(",");
				temp = tmp[1] + " " + tmp[0];
				playersList.add(temp.toLowerCase().trim());
			} else {
				playersList.add(playersNames.get(i).text().toLowerCase().trim());
				playersList.add(players2Names.get(i).text().toLowerCase().trim());
			}
		}
		for (int i = 0; i < playersOdds.size(); i++) {
			oddsList.add(rd.convertToDecimal(playersOdds.get(i).text()) + 1);
		}
		for (int i = playersList.size() - 1; i >= 0; i--) {
			if (playersList.get(i).contains("/")) {
				playersList.remove(i);
				oddsList.remove(i);
			}
		}
		betfairPlayers.clear();
		for (int i = 0; i < playersList.size(); i++) {
			betfairPlayers.add(new Player(playersList.get(i), oddsList.get(i), website));
		}
		playersList.clear();
		oddsList.clear();
		return betfairPlayers;
	}
	
	public LinkedList<Matches> call() throws Exception {
		try {
			Document doc = getConnection(page);
			players = getOdds(doc);
			matches = getMatches(players, Sports.SNOOKER);
			return matches;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	
	// public static void main(String[] args) throws Exception {
	// BetfairSnooker bf = new BetfairSnooker();
	// Document doc = bf.getConnection("https://www.betfair.com/sport/snooker");
	// LinkedList<Player> players = bf.getOdds(doc);
	// LinkedList<Matches> matches = bf.getMatches(players, Sports.SNOOKER);
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
