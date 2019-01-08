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
public class Game {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long gameId;

    private String homeTeam;

    private String guestTeam;

    private Integer homeScore;
    private Integer guestScore;
}
