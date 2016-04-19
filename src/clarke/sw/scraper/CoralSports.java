package clarke.sw.scraper;

import java.util.ArrayList;
import java.util.LinkedList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CoralSports extends MakeMatches implements RetrieveOdds {

	private Document document;
	private Elements playersNames;
	private Elements playersOdds;
	private String temp;
	private String temp2;
	private String[] tmp;
	private String[] tmp2;
	private String website = "Coral Sports";
	private ArrayList<String> playersList = new ArrayList<String>();
	private ArrayList<Double> oddsList = new ArrayList<Double>();
	private LinkedList<Player> coralSportsPlayers = new LinkedList<Player>();
	private RoundDecimal rd = new RoundDecimal();
	
	public Document getConnection(String url) {
		try {
			Connection connection = Jsoup.connect(url);
			connection.timeout(15000);
			document = connection.get();
			return document;
		} catch (Exception e) {
			System.out.println("Error in coral sports connection: " + e.getMessage());
			return null;
		}
	}

	public LinkedList<Player> getOdds(Document doc) {

//		@SuppressWarnings("unused")
//		Elements els = document.select(".bet-highlights").remove();
//		
//		playersNames = doc.select("div[class=block-content]").select("div[class=match]")
//				.select("div[class=bet-title]");
//		playersOdds = doc.select("div[class=block-content]").select("div[class=match]")
//				.select(".bip-odds, .home-odds");
		
		playersNames = doc.select(".block-content");
		playersOdds = doc.select("tr");
		
				
		System.out.println(playersNames.text());
//		System.out.println(playersOdds);

		for (int i = 0; i < playersNames.size(); i++) {
			temp = playersNames.get(i).text().replaceAll("\\u00A0", "");
			tmp = temp.split(" v ");
			if (tmp[0].contains(",") && tmp[1].contains(",")) {
				tmp2 = tmp[0].split(",");
				temp2 = tmp2[1] + " " + tmp2[0];
				playersList.add(temp2.toLowerCase().trim());
				tmp2 = tmp[1].split(",");
				temp2 = tmp2[1] + " " + tmp2[0];
				playersList.add(temp2.toLowerCase().trim());
			} else {
				playersList.add(tmp[0].toLowerCase().trim());
				playersList.add(tmp[1].toLowerCase().trim());
			}
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
		for (int i = 0; i < playersList.size(); i++) {
			coralSportsPlayers.add(new Player(playersList.get(i), oddsList
					.get(i), website));
		}
		return coralSportsPlayers;	
	}

	public static void main(String[] args) {
		CoralSports cs = new CoralSports();
		Document doc = cs.getConnection("http://sports.coral.co.uk/tennis");
		LinkedList<Player> players = cs.getOdds(doc);
		LinkedList<Matches> matches = cs.getMatches(players, Sports.TENNIS);
		for (int i = 0; i < matches.size(); i++) {
			System.out.printf("%30s%8.2f\t\tV\t%s%8.2f\t\t\t%s\n", matches.get(i).getPlayer1(),
					matches.get(i).getP1Odds(), matches.get(i).getPlayer2(), matches.get(i).getP2Odds(),
					matches.get(i).getPlayer1Website());
		}

		System.out.println(matches.size());
	}
}
