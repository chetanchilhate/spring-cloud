package com.cj.constellation.service;

import com.cj.constellation.entity.Constellation;
import com.cj.constellation.repository.ConstellationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ConstellationService {

    private ConstellationRepository  constellationRepository;

    @Autowired
    public ConstellationService(ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    public List<Constellation> getAllConstellations() {
        return  constellationRepository.findAll();
    }

    public Constellation getConstellationById(Integer id) {
        try {
            Thread.sleep(id*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  constellationRepository.findById(id).orElseThrow();
    }

}
