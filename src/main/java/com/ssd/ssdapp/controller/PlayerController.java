package com.ssd.ssdapp.controller;

import com.ssd.ssdapp.model.Player;
import com.ssd.ssdapp.model.PlayerAndTeam;
import com.ssd.ssdapp.repositories.PlayerAndTeamRepository;
import com.ssd.ssdapp.repositories.PlayerRepository;
import com.ssd.ssdapp.repositories.TeamRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://ssdproject-4a4a4.firebaseapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class PlayerController {

    PlayerRepository playerRepository;
    PlayerAndTeamRepository playerAndTeamRepository;


    private String loggedTeamName = null;

    @PostMapping("/addPlayer")
    public boolean insertToTeam(@RequestBody PlayerDTO playerDTO)
    {

        Player player = Player.builder()
                .playerName(playerDTO.getPlayerName())
                .teamName(playerDTO.getTeamName())
                .playerNumber(playerDTO.getPlayerNumber())
                .build();

        loggedTeamName = player.getTeamName();
        playerRepository.save(player);
        return true;
    }


    @PostMapping("/playerToGame")
    public boolean addPlayerToGame(@RequestBody PlayerDTO playerDTO, @RequestBody TeamDTO teamDTO)
    {
        PlayerAndTeam pAndT = PlayerAndTeam.builder()
                .playerName(playerDTO.getPlayerName())
                .guestTeam(teamDTO.getGuestTeam())
                .homeTeam(teamDTO.getHomeTeam())
                .build();

        this.playerAndTeamRepository.save(pAndT);
        return true;
    }

    @PostMapping("/teamPlayers")
    public Iterable<Player> getPlayers(@RequestBody PlayerDTO playerDTO)
    {
        return playerRepository.findAllByTeamName(playerDTO.getTeamName());
    }

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository, PlayerAndTeamRepository playerAndTeamRepository)
    {
        this.playerRepository = playerRepository;
        this.playerAndTeamRepository = playerAndTeamRepository;
    }

}

@Data
@NoArgsConstructor
class PlayerDTO {

    private String playerName;
    private String teamName;
    private Integer playerNumber;
}
