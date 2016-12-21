package parser.controller;

/**
 * Created by Vitalii on 12/20/2016.
 */
public class Controller {
    private AccessProviders providers;

    public Controller(AccessProviders providers)
    {
        this.providers = providers;
        if(providers == null)
            throw new IllegalArgumentException();
    }

    public void onCitySelect(String cityName){
        providers.selectCity(cityName);
    }
}
