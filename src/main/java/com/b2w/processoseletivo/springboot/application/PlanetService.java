package com.b2w.processoseletivo.springboot.application;

import com.b2w.processoseletivo.springboot.Presentation.Models.Planets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Arrays;

public class PlanetService {

    @Autowired
    RestTemplate template = new RestTemplate();

    public List<Planets> getAll(){
        String url = "https://swapi.dev/api/planets/";
        RestTemplate restTemplate = new RestTemplate();

        Planets[] planets = restTemplate.getForObject("https://swapi.dev/api/planets/",Planets[].class);

        return Arrays.asList(planets);
    }

    public void consumerApi(){
        //https://swapi.dev/api/planets/?search=Tatooine

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("swapi.dev")
                .path("api/planets")
                .queryParam("search", "Tatooine")
                .build();

        ResponseEntity<Planets> entity = template.getForEntity(uri.toUriString(), Planets.class);

        entity.getBody().getName();
    }

}
