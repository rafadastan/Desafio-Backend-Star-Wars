package com.b2w.processoseletivo.springboot.Repositories.Contracts;

import com.b2w.processoseletivo.springboot.Presentation.Models.Planets;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetsRepository extends MongoRepository<Planets, Integer> {

   Planets findByName(String name);
   Planets deleteById(int id);
}
