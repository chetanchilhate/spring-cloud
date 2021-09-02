package com.cj.universe.model;

import com.cj.universe.client.dto.Constellation;
import com.cj.universe.client.dto.Galaxy;
import lombok.Data;

@Data
public class Universe {

    private final Integer galaxyId;
    private final String galaxyName;
    private final Integer constellationId;
    private final String constellationName;

    public Universe(Galaxy galaxy, Constellation constellation) {
        this.galaxyId = galaxy.getId();
        this.galaxyName = galaxy.getName();
        this.constellationId = constellation.getId();
        this.constellationName = constellation.getName();
    }

}
