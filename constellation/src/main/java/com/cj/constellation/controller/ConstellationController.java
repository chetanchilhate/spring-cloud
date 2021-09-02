package com.cj.constellation.controller;

import com.cj.constellation.entity.Constellation;
import com.cj.constellation.service.ConstellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/constellations")
public class ConstellationController {

    private ConstellationService constellationService;

    @Autowired
    public ConstellationController(ConstellationService constellationService) {
        this.constellationService = constellationService;
    }

    @GetMapping("")
    public List<Constellation> getAllConstellations() {
        return constellationService.getAllConstellations();
    }

    @GetMapping("/{id}")
    public Constellation getConstellationById(@PathVariable("id") Integer id) {
        return constellationService.getConstellationById(id);
    }

}
