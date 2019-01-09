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
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String teamName;

    private Integer teamPoints;
}
