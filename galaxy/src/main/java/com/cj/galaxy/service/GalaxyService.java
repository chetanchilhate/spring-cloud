package com.cj.galaxy.service;

import com.cj.galaxy.entity.Galaxy;
import com.cj.galaxy.repositroy.GalaxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GalaxyService {

    private GalaxyRepository galaxyRepository;

    @Autowired
    public GalaxyService(GalaxyRepository galaxyRepository) {
        this.galaxyRepository = galaxyRepository;
    }

    public List<Galaxy> getAllGalaxy(){
        return  galaxyRepository.findAll();
    }

    public Galaxy getGalaxyByid(Integer id){
        try {
            Thread.sleep(id*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  galaxyRepository.findById(id).orElseThrow();
    }

}
