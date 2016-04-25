package clarke.sw.mcbookie;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import clarke.sw.scraper.MakeMatches;
import clarke.sw.scraper.Matches;
import clarke.sw.scraper.Player;
import clarke.sw.scraper.RetrieveOdds;
import clarke.sw.scraper.RoundDecimal;
import clarke.sw.scraper.Sports;

public class McbookieTennis extends MakeMatches implements RetrieveOdds, Callable<LinkedList<Matches>> {

	private Document document;
	private Elements playersNames;
	private Elements playersOdds;
	private String temp;
	private String[] tmp;
	private String website = "Mcbookie";
	private String page = "http://www.mcbookie.com/sports/en/tennis/coupons/600/0/0/35836/0/PE/0/0/0/0/1";
	private LinkedList<String> playersList = new LinkedList<>();
	private LinkedList<Double> oddsList = new LinkedList<>();
	private LinkedList<Player> mcbookiePlayers = new LinkedList<>();
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

		for(Element e : doc.select(".bet_boost_coupon")){
			e.remove();
		}
		playersNames = doc.select(".event_description").select("a");
		playersOdds = doc.select(".price");

		for (int i = 0; i < playersNames.size(); i++) {
			temp = playersNames.get(i).text().replaceAll("\\u00A0", "");
			tmp = temp.split(" v ");
			playersList.add(tmp[0].toLowerCase().trim());
			playersList.add(tmp[1].toLowerCase().trim());
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
		mcbookiePlayers.clear();
		for (int i = 0; i < playersList.size(); i++) {
			mcbookiePlayers.add(new Player(playersList.get(i), oddsList.get(i), website));
		}
		playersList.clear();
		oddsList.clear();
		return mcbookiePlayers;
	}

	// Call method needed to start the thread
	public LinkedList<Matches> call() {
		try {
			Document doc = getConnection(page);
			players = getOdds(doc);
			matches = getMatches(players, Sports.TENNIS);
			return matches;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Test main method to see if it was finding the right names and odds

//	public static void main(String[] args) throws Exception {
//		McbookieTennis cs = new McbookieTennis();
//		Document doc = cs.getConnection("http://www.mcbookie.com/sports/en/tennis/coupons/600/0/0/35836/0/PE/0/0/0/0/1");
//		LinkedList<Player> players = cs.getOdds(doc);
//		LinkedList<Matches> matches = cs.getMatches(players, Sports.TENNIS);
//		for (int i = 0; i < matches.size(); i++) {
//			System.out.printf("%30s%8.2f\t\tV\t%s%8.2f\t\t\t%s\n", matches.get(i).getPlayer1(),
//					matches.get(i).getP1Odds(), matches.get(i).getPlayer2(), matches.get(i).getP2Odds(),
//					matches.get(i).getPlayer1Website());
//		}
//
//		System.out.println(matches.size());
//	}
}
