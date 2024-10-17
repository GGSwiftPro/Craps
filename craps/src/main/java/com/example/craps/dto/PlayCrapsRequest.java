package com.example.craps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayCrapsRequest {
    private double stake;
    private String gameType;
    private int rounds;
}
