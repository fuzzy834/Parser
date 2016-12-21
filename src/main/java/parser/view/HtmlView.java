package parser.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.controller.Controller;
import parser.model.Vacancy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Vitalii on 12/20/2016.
 */
public class HtmlView implements View{

    private Controller controller;
    private final String filePath = "./src/main/java/" + this.getClass()
            .getPackage().getName().replace('.', '/') + "/Vacancies.html";


    @Override
    public void update(List<Vacancy> vacancies)
    {
        updateFile(getUpdatedFileContent(vacancies));
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        String result = null;
        try
        {
            Document document = getDocument();
            Element template = document.select(".template").first();
            Element pattern = template.clone();
            pattern.removeClass("template").removeAttr("style");
            document.getElementsByAttributeValue("class", "vacancy").remove();

            vacancies.forEach(vacancy -> {
                Element temp = pattern.clone();
                temp.addClass("vacancy");
                temp.getElementsByClass("city").first().text(vacancy.getCity());
                temp.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                temp.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = temp.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());
                template.before(temp.outerHtml());
            });

            result = document.html();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private void updateFile(String content) {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(content);
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

}
