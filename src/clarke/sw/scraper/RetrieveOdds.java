package clarke.sw.scraper;

import java.util.LinkedList;

import org.jsoup.nodes.Document;

public interface RetrieveOdds extends RetrievePage {
	LinkedList<Player> getOdds(Document doc);
}
