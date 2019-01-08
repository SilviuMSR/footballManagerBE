package com.ssd.ssdapp.controller;

import com.ssd.ssdapp.model.*;
import com.ssd.ssdapp.repositories.TeamRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssd.ssdapp.repositories.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TeamController {

    private TeamRepository teamRepository;

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams()
    {
        Iterable<Team> teams = teamRepository.findAllByOrderByTeamPointsDesc() ;

        return teams;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }

}
