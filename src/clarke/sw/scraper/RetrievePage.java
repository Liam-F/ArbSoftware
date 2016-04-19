package clarke.sw.scraper;

import org.jsoup.nodes.Document;

public interface RetrievePage {
	Document getConnection(String url) throws Exception;
}
