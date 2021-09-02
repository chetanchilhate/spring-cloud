package com.cj.galaxy.repositroy;

import com.cj.galaxy.entity.Galaxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyRepository extends JpaRepository<Galaxy, Integer> {
}