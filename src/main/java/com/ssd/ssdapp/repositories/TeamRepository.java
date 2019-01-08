package com.ssd.ssdapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ssd.ssdapp.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamname);
    Iterable<Team> findAllByOrderByTeamPointsDesc();
}
