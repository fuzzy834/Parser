package parser.controller;

import parser.model.Vacancy;
import parser.service.ServiceProvider;
import parser.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 12/20/2016.
 */
public class AccessProviders {
    private View view;
    private ServiceProvider[] serviceProviders;

    public AccessProviders(View view, ServiceProvider[] serviceProviders)
    {
        this.view = view;
        this.serviceProviders = serviceProviders;
        if(view == null || serviceProviders == null || serviceProviders.length == 0)
            throw new IllegalArgumentException();
    }

    public void selectCity(String city){
        List<Vacancy> vacancies = new ArrayList<>();
        for (ServiceProvider provider : serviceProviders) {
            try {
                vacancies.addAll(provider.getJavaVacancies(city));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        view.update(vacancies);
    }
}
