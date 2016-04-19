package clarke.sw.scraper;

import java.util.LinkedList;

public abstract class MakeMatches {
	private LinkedList<Matches> matches = new LinkedList<>();

	public LinkedList<Matches> getMatches(LinkedList<Player> list, Sports sport) {
		for (int i = 0; i < list.size(); i++) {
			matches.add(new Matches(list.get(i).getPlayerName(), list.get(i).getOdds(), list.get(i).getPlayerWebsite(),
					list.get(++i).getPlayerName(), list.get(i).getOdds(), list.get(i).getPlayerWebsite(), sport));
		}
		return matches;
	}
}
