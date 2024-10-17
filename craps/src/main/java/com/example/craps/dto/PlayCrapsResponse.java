package com.example.craps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlayCrapsResponse {
    private final double totalStake;
    private final double totalWins;
    private final double winPercentage;
    private final List<String> gameDetails;
    private final String gameType;
}
