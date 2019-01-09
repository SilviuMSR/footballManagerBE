package com.ssd.ssdapp.controller;

import com.ssd.ssdapp.repositories.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ssd.ssdapp.repositories.GameRepository;
import com.ssd.ssdapp.repositories.TeamRepository;
import com.ssd.ssdapp.model.Game;
import com.ssd.ssdapp.model.Team;

@RestController
@RequestMapping("/api")
@Slf4j
public class GameController {

    private GameRepository gameRepository;
    private TeamRepository teamRepository;

    @GetMapping("/games")
    public Iterable<Game> getAllGames()
    {
        return gameRepository.findAll();
    }

    @PostMapping("/addGame")
    public boolean saveGame(@RequestBody GameDTO gameDTO)
    {

        if(teamRepository.findByTeamName(gameDTO.getHomeTeam()) == null)
        {
            return false;
        }
        if(teamRepository.findByTeamName(gameDTO.getGuestTeam()) == null)
        {
            return false;
        }

        Game game = Game.builder()
                .homeTeam(gameDTO.getHomeTeam())
                .guestTeam(gameDTO.getGuestTeam())
                .build();

        gameRepository.save(game);
        return true;
    }


    @PostMapping("/setScore")
    public boolean setScore(@RequestBody GameDTO gameDTO)
    {
        if(gameRepository.findByHomeTeamAndGuestTeam(gameDTO.getHomeTeam(), gameDTO.getGuestTeam()) == null)
        {
            return false;
        }

        Game updatedGame = gameRepository.findByHomeTeamAndGuestTeam(gameDTO.getHomeTeam(), gameDTO.getGuestTeam());
        updatedGame.setHomeScore(gameDTO.getHomeScore());
        updatedGame.setGuestScore(gameDTO.getGuestScore());
        gameRepository.save(updatedGame);

        Team homeTeam = teamRepository.findByTeamName(gameDTO.getHomeTeam());
        Team guestTeam = teamRepository.findByTeamName(gameDTO.getGuestTeam());

        if(updatedGame.getHomeScore() > updatedGame.getGuestScore())
        {
            homeTeam.setTeamPoints(homeTeam.getTeamPoints() + 3);
            teamRepository.save(homeTeam);
            return true;
        }
        else if(updatedGame.getHomeScore() < updatedGame.getGuestScore())
        {
            guestTeam.setTeamPoints(guestTeam.getTeamPoints() + 3);
            teamRepository.save(guestTeam);
            return true;
        }
        else if(updatedGame.getHomeScore() == updatedGame.getGuestScore())
        {
            homeTeam.setTeamPoints(homeTeam.getTeamPoints() + 1);
            guestTeam.setTeamPoints(guestTeam.getTeamPoints() + 1);
            teamRepository.save(homeTeam);
            teamRepository.save(guestTeam);
            return true;
        }

        return true;
    }

    @DeleteMapping("/deleteGame")
    public boolean deleteGameWithId(@RequestParam("gameId") Long gameId)
    {
        if(gameRepository.deleteByGameId(gameId) == 0)
        {
            return false;
        }

        return true;
    }

    @Autowired
    public void setGameRepository(GameRepository gameRepository, TeamRepository teamRepository)
    {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }
}

@Data
@NoArgsConstructor
class GameDTO
{
    private String homeTeam;
    private String guestTeam;
    private Integer homeScore;
    private Integer guestScore;

}
