package clarke.sw.skybet;

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

public class SkyBetTennis extends MakeMatches implements RetrieveOdds, Callable<LinkedList<Matches>> {

	private Document document;
	private Elements playersNames;
	private Elements playersOdds;
	private String temp;
	private String website = "Sky Bet";
	private String page = "https://www.skybet.com/tennis/coupon/match-winner";
	private LinkedList<String> playersList = new LinkedList<>();
	private LinkedList<Double> oddsList = new LinkedList<>();
	private LinkedList<Player> skyBetPlayers = new LinkedList<>();
	private LinkedList<Player> players;
	private LinkedList<Matches> matches;
	private RoundDecimal rd = new RoundDecimal();
	
	public Document getConnection(String url) throws Exception {
		Connection connection = Jsoup.connect(url);
		connection.timeout(15000);
		document = connection.get();
		return document;
	}
	
	public LinkedList<Player> getOdds(Document doc) {
		playersNames = doc.select(".oc-desc");
		playersOdds = doc.select(".odds");

		for (int i = 0; i < playersNames.size(); i++) {
			temp = playersNames.get(i).text().replaceAll("\\u00A0", "");		
			playersList.add(temp.toLowerCase().trim());
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
		skyBetPlayers.clear();
		for (int i = 0; i < playersList.size(); i++) {
			skyBetPlayers.add(new Player(playersList.get(i), oddsList.get(i), website));
		}
		playersList.clear();
		oddsList.clear();
		return skyBetPlayers;
	}
	
	public LinkedList<Matches> call() {
		try{
			Document doc = getConnection(page);
			players = getOdds(doc);
			matches = getMatches(players, Sports.TENNIS);
			return matches;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	// public static void main(String[] args) throws Exception {
	// SkyBetTennis sb = new SkyBetTennis();
	//
	// Document doc =
	// sb.getConnection("https://www.skybet.com/tennis/coupon/match-winner");
	// LinkedList<Player> players = sb.getOdds(doc);
	// LinkedList<Matches> matches = sb.getMatches(players, Sports.TENNIS);
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
