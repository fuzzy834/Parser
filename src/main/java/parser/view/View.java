package parser.view;

import parser.controller.Controller;
import parser.model.Vacancy;

import java.util.List;

/**
 * Created by Vitalii on 12/20/2016.
 */
public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
