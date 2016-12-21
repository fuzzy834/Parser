package parser.DAO;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.model.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 12/20/2016.
 */
public class HHStrategy extends Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String REFERRER = "https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";
    private static final String SITE_NAME = "http://hh.ua/";

    @Override
    public String getUrl(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href");
    }

    @Override
    public String getTitle(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first().text();
    }

    @Override
    public String getSalary(Element element) {
        Elements temp = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation");
        return temp.size() != 0 ? temp.first().text() : "";
    }

    @Override
    public String getCity(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").first().text();
    }

    @Override
    public String getCompanyName(Element element) {
        return element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").first().text();
    }

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        int page = 0;
        try
        {
            for(Document html = getDocument(REFERRER, URL_FORMAT, searchString, page);
                html!=null;
                html = getDocument(REFERRER, URL_FORMAT, searchString, ++page)){

                Elements elements = html.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break;

                elements.forEach(element -> result.add(
                        new Vacancy(
                                getTitle(element),
                                getSalary(element),
                                getCity(element),
                                getCompanyName(element),
                                SITE_NAME,
                                getUrl(element))
                        )
                );
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
