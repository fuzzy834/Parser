package parser.DAO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.model.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vitalii on 12/20/2016.
 */
public abstract class Strategy {

    public abstract List<Vacancy> getVacancies(String searchString);

    public abstract String getUrl(Element element);

    public abstract String getTitle(Element element);

    public abstract String getSalary(Element element);

    public abstract String getCity(Element element);

    public abstract String getCompanyName(Element element);


    Document getDocument(String referrer, String urlFormat,  String searchString, int page)
            throws IOException {
        Document html;
        html = Jsoup.connect(String.format(urlFormat, searchString, page))
                .userAgent("Mozilla/5.0 (jsoup)")
                .referrer(referrer)
                .get();

        return html;
    }
}
