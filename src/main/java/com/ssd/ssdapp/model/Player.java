package com.ssd.ssdapp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Player {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long playerId;

    private String playerName;

    private String teamName;

    private Integer playerNumber;

}
