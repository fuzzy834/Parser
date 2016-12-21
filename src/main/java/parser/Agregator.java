package parser;

import parser.DAO.HHStrategy;
import parser.controller.AccessProviders;
import parser.controller.Controller;
import parser.service.ServiceProvider;
import parser.view.HtmlView;
import parser.view.View;

/**
 * Created by Vitalii on 12/20/2016.
 */
public class Agregator {

    public static void main(String[] args) {

        View view = new HtmlView();
        ServiceProvider HHprovider = new ServiceProvider(new HHStrategy());
        AccessProviders accessProviders = new AccessProviders(view, new ServiceProvider[]{HHprovider});
        Controller controller = new Controller(accessProviders);
        view.setController(controller);
        controller.onCitySelect("Kiev");
    }

}
