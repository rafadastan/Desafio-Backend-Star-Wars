package com.b2w.processoseletivo.springboot.Presentation.Resource;

import com.b2w.processoseletivo.springboot.Presentation.Models.Planets;
import com.b2w.processoseletivo.springboot.Repositories.Contracts.PlanetsRepository;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "Api Rest StarWars")
@RestController
@RequestMapping("/planets")
public class PlanetsController {

    @Autowired
    private PlanetsRepository repository;

    @ApiOperation(value = "Save Planets")
    @PostMapping("/addPlanet")
    public ResponseEntity<Planets> savePlanet(@RequestBody Planets planet) {
        Planets result = repository.save(planet);
        try {
            return ResponseEntity.ok().body(planet);
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Get All Planets")
    @GetMapping("/findAllPlanets")
    public ResponseEntity<List<Planets>> getPlanets() {
        try {
            List<Planets> result = repository.findAll();
            if (result == null || result.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (ExceptionHasMessage e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Get Planets with id")
    @GetMapping("/findById/{id}")
    public ResponseEntity getPlanetId(@PathVariable int id) {
        try {
            Optional<Planets> result = repository.findById(id);
            if (!result.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.fillInStackTrace());
        }
    }

    @ApiOperation(value = "Get Planets with name")
    @GetMapping("/findByName/{name}")
    public ResponseEntity<Optional<Planets>> getPlanetName(@PathVariable String name) {
        Optional<Planets> result = Optional.ofNullable(repository.findByName(name));
        try {
            if (!result.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Delete Planets with id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlanet(@PathVariable int id) {
        Planets result = repository.deleteById(id);
        try {
            if (!StringUtils.isEmpty(result)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}