package clarke.sw.calculateArbs;

import java.util.LinkedList;

import clarke.sw.scraper.Matches;
import clarke.sw.scraper.Sports;

public class CompareLists extends CompareNames {

	public LinkedList<Matches> compareLists(LinkedList<Matches> bestOddsMatches, LinkedList<Matches> matchesList, Sports s) {
		try {
			for (int i = 0; i < bestOddsMatches.size(); i++) {
					for (int j = 0; j < matchesList.size(); j++) {
						if (bestOddsMatches.get(i).getPlayer1()
								.matches(matchesList.get(j).getPlayer1())
								&& bestOddsMatches
										.get(i)
										.getPlayer2()
										.matches(
												matchesList.get(j).getPlayer2())
								&& matchesList.get(j).getSport()
										.equals(s)) {
							if (bestOddsMatches.get(i).getP1Odds() < matchesList
									.get(j).getP1Odds()
									&& matchesList.get(j).getSport()
											.equals(s)) {
								bestOddsMatches.get(i).setP1Odds(
										matchesList.get(j).getP1Odds());
								bestOddsMatches.get(i).setPlayer1Website(
										matchesList.get(j).getPlayer1Website());
							}
							if (bestOddsMatches.get(i).getP2Odds() < matchesList
									.get(j).getP2Odds()
									&& matchesList.get(j).getSport()
											.equals(s)) {
								bestOddsMatches.get(i).setP2Odds(
										matchesList.get(j).getP2Odds());
								bestOddsMatches.get(i).setPlayer2Website(
										matchesList.get(j).getPlayer2Website());
							}
						}
					}
				} 
			return bestOddsMatches;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
