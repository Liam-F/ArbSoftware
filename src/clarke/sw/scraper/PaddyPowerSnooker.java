package clarke.sw.scraper;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PaddyPowerSnooker extends MakeMatches implements RetrieveOdds, Callable<LinkedList<Matches>> {

	private Document document;
	private Elements playersNames;
	private Elements playersOdds;
	private String temp;
	private String[] tmp;
	private String website = "Paddy Power";
	private String page = "http://www.paddypower.com/bet/snooker/snooker-coupon";
	private LinkedList<String> playersList = new LinkedList<>();
	private LinkedList<Double> oddsList = new LinkedList<>();
	private LinkedList<Player> paddyPowerPlayers = new LinkedList<>();
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
		playersNames = doc.select(".mkt-td-label");
		playersOdds = doc.select(".odds");

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
		paddyPowerPlayers.clear();
		for (int i = 0; i < playersList.size(); i++) {
			paddyPowerPlayers.add(new Player(playersList.get(i), oddsList.get(i), website));
		}
		playersList.clear();
		oddsList.clear();
		return paddyPowerPlayers;
	}

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

	// public static void main(String[] args) throws Exception {
	// PaddyPowerSnooker pp = new PaddyPowerSnooker();
	//
	// Document docpp =
	// pp.getConnection("http://www.paddypower.com/bet/snooker/snooker-coupon");
	// LinkedList<Player> players = pp.getOdds(docpp);
	// LinkedList<Matches> matches = pp.getMatches(players, Sports.SNOOKER);
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
