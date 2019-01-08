package com.ssd.ssdapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.ssd.ssdapp.model.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Iterable<Player> findAllByTeamName(String teamName);

}
