package kom.securities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.logging.Logger;

@ApplicationScoped
public class SecurityService {

    private static final Logger LOGGER = Logger.getLogger(SecurityService.class.getName());

    public String SearchPrice(String ticker) {
        LOGGER.info("Start Searching Price.");
        LOGGER.info("DATA = " + ticker);
        Document doc;
        String price = "";
        String url = String.format("http://www.nasdaq.com/symbol/%s/real-time", ticker);
        try {

            doc = Jsoup.connect(url).get();
            Element element = doc.getElementsByClass("qwidget-dollar").first();
            price = element.text();

        } catch (IOException exception) {
            throw new RuntimeException("Couldn't parse.", exception);
        }
        LOGGER.info("End Searching Price.");
        return price;
    }
}
