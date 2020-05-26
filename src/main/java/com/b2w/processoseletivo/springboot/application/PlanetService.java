package com.b2w.processoseletivo.springboot.application;

import com.b2w.processoseletivo.springboot.Presentation.Models.Planets;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Arrays;

public class PlanetService {

    public List<Planets> getAll(){
        String url = "https://swapi.dev/api/planets/";
        RestTemplate restTemplate = new RestTemplate();

        Planets[] planets = restTemplate.getForObject("https://swapi.dev/api/planets/",Planets[].class);

        return Arrays.asList(planets);
    }

}
