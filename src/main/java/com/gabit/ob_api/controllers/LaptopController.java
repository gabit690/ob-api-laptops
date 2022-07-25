package com.gabit.ob_api.controllers;

import com.gabit.ob_api.entities.LaptopEntity;
import com.gabit.ob_api.repositories.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    @Autowired
    private LaptopRepository repository;

    @PostMapping
    public ResponseEntity<LaptopEntity> create(@RequestBody LaptopEntity laptop) {

        if(laptop.getId() != null) {

            log.warn("Try to CREATE a laptop with an Id");

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this.repository.save(laptop));
    }

    @GetMapping()
    public List<LaptopEntity> findAll() {

        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaptopEntity> findOneById(@PathVariable Long id) {

        Optional<LaptopEntity> laptopOpt = this.repository.findById(id);

        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping()
    public ResponseEntity<LaptopEntity> update(@RequestBody LaptopEntity laptop) {

        if(!this.repository.existsById(laptop.getId())) {

            log.warn("Try to UPDATE a laptop with a non existent Id");

            return ResponseEntity.notFound().build();
        }

        if(laptop.getId() == null) {

            log.warn("Try to UPDATE a laptop with a non existent Id");

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this.repository.save(laptop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LaptopEntity> deleteById(@PathVariable Long id) {

        if(!this.repository.existsById(id)) {

            log.warn("Try to DELETE a laptop with a non existent Id");

            return ResponseEntity.notFound().build();
        }

        this.repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<LaptopEntity> deleteAll() {

        log.info("REST Request for DELETE all Laptops");

        this.repository.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
