package clarke.sw.williamHill;

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

public class WilliamHillTennis extends MakeMatches implements RetrieveOdds, Callable<LinkedList<Matches>> {

	private Document document;
	private Elements playersNames;
	private Elements playersOdds;
	private String temp;
	private String[] tmp;
	private String website = "William Hill";
	private String page = "http://sports.williamhill.com/bet/en-ie/betting/y/17/mh/Tennis.html";
	private LinkedList<String> playersList = new LinkedList<>();
	private LinkedList<Double> oddsList = new LinkedList<>();
	private LinkedList<Player> williamHillPlayers = new LinkedList<>();
	private LinkedList<Player> players;
	private LinkedList<Matches> matches;
	private RoundDecimal rd = new RoundDecimal();

	public Document getConnection(String url) throws Exception {
		// try {
		Connection connection = Jsoup.connect(url);
		connection.timeout(15000);
		document = connection.get();
		return document;
		// } catch (Exception e) {
		// System.out.println("Error in william hill connection: " +
		// e.getMessage());
		// return null;
		// }
	}

	public LinkedList<Player> getOdds(Document doc) {
		Elements rem = doc.select("table");
		for (int i = 0; i < rem.size(); i++) {
			if (rem.get(i).text().contains("Correct Score"))
				rem.remove(i);
		}
		playersNames = rem.select("table[class=tableData]").select("tbody").select("tr[class=rowOdd]").select("tr")
				.select("td").select(".CentrePad");
		playersOdds = rem.select("table[class=tableData]").select("tbody").select("tr[class=rowOdd]").select("tr")
				.select("td").select(".eventprice");
		Elements e = rem.select("table[class=tableData]").select("tbody").select("tr[class=rowLive]").select("tr")
				.select("td").select(".CentrePad");
		Elements f = rem.select("table[class=tableData]").select("tbody").select("tr[class=rowLive]").select("tr")
				.select("td").select(".eventprice");

		playersNames.addAll(e);
		playersOdds.addAll(f);

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
		williamHillPlayers.clear();
		for (int i = 0; i < playersList.size(); i++) {
			williamHillPlayers.add(new Player(playersList.get(i), oddsList.get(i), website));
		}
		playersList.clear();
		oddsList.clear();
		return williamHillPlayers;
	}

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

	// public static void main(String[] args) {
	// WilliamHill wh = new WilliamHill();
	// Document doc =
	// wh.getConnection("http://sports.williamhill.com/bet/en-ie/betting/y/17/mh/Tennis.html");
	// LinkedList<Player> players = wh.getTennisOdds(doc);
	// LinkedList<Matches> matches = wh.getMatches(players, Sports.TENNIS);
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
