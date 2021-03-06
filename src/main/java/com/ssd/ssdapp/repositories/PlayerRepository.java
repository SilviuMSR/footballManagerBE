package com.ssd.ssdapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ssd.ssdapp.model.Player;
import org.springframework.transaction.annotation.Transactional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Iterable<Player> findAllByTeamName(String teamName);

    @Transactional
    Long deleteByPlayerId(Long playerId);

}
