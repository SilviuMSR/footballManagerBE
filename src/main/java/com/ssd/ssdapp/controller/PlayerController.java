package com.ssd.ssdapp.controller;

import com.ssd.ssdapp.model.Player;
import com.ssd.ssdapp.repositories.PlayerRepository;
import com.ssd.ssdapp.repositories.TeamRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@Slf4j
public class PlayerController {

    PlayerRepository playerRepository;

    private String loggedTeamName;

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


    @GetMapping("/players")
    public Iterable<Player> getPlayers()
    {
        return playerRepository.findAllByTeamName(loggedTeamName);
    }

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

}

@Data
@NoArgsConstructor
class PlayerDTO {

    private String playerName;
    private String teamName;
    private Integer playerNumber;
}
