package com.ssd.ssdapp.repositories;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.ssd.ssdapp.model.Game;
import org.springframework.transaction.annotation.Transactional;

public interface GameRepository extends CrudRepository<Game, Long> {

    @Transactional
    Long deleteByGameId(Long gameId);

    Game findByHomeTeamAndGuestTeam(String hometeam, String guestteam);
    Iterable<Game> findAllByGuestTeamOrHomeTeam(String hometeam, String guestteam);

}
