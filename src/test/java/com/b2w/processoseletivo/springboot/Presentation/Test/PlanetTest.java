package com.b2w.processoseletivo.springboot.Presentation.Test;

import com.b2w.processoseletivo.springboot.Presentation.Models.Planets;
import com.b2w.processoseletivo.springboot.Repositories.Contracts.PlanetsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetTest {

    RestTemplate template = new RestTemplate();

    @Value("${local.server.port}")
    protected int port;

    @Autowired
    @MockBean
    private PlanetsRepository repository;

    @Test
    public void consumerApi(){
        //https://swapi.dev/api/planets/?search=Tatooine

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("swapi.dev")
                .path("api/planets")
                .queryParam("search", "Tatooine")
                .build();

        ResponseEntity<Planets> entity = template.getForEntity(uri.toUriString(), Planets.class);

        System.out.println(entity.getBody().getName());
    }

    @Test
    public void getPlanetsTest(){
        when(repository.findAll())
                .thenReturn(Stream.of(new Planets(1,"saturno", "sum","very sum"))
                        .collect(Collectors.toList()));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void getPlanetNameTest(){
        Planets planets = new Planets(21,"Terra", "Sum", "Very sum");
        repository.findByName("Terra");
        verify(repository, times(1)).findByName("Terra");
    }

    @Test
    public void savePlanetTest(){
        Planets planets = new Planets(21,"Terra", "Sum", "Very sum");
        when(repository.save(planets)).thenReturn(planets);
        assertEquals(planets, repository.save(planets));
    }

    @Test
    public void deletePlanetTest(){
        Planets planets = new Planets(21,"Terra", "Sum", "Very sum");
        repository.deleteById(21);
        verify(repository, times(1)).deleteById(21);
    }

    @Test
    public void getPlanetIdTest(){
        Planets planets = new Planets(21,"Terra", "Sum", "Very sum");
        repository.findById(21);
        verify(repository, times(1)).findById(21);
    }
}
