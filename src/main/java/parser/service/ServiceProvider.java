package parser.service;

import parser.DAO.Strategy;
import parser.model.Vacancy;

import java.util.List;

/**
 * Created by Vitalii on 12/20/2016.
 */
public class ServiceProvider {
    private Strategy strategy;

    public ServiceProvider()
    {
    }

    public ServiceProvider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString){
        return strategy.getVacancies(searchString);
    }
}
