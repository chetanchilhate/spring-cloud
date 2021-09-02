package com.cj.galaxy.controller;

import com.cj.galaxy.entity.Galaxy;
import com.cj.galaxy.service.GalaxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/galaxies")
public class GalaxyController {

    private GalaxyService galaxyService;

    @Autowired
    public GalaxyController(GalaxyService galaxyService) {
        this.galaxyService = galaxyService;
    }

    @GetMapping("")
    public List<Galaxy> getAllGalaxy() {
        return galaxyService.getAllGalaxy();
    }

    @GetMapping("/{id}")
    public Galaxy getGalaxyById(@PathVariable("id") Integer id) {
        return galaxyService.getGalaxyByid(id);
    }

}
