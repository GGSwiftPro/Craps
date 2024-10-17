package com.example.craps.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CrapsService {

    private static final ArrayList<Integer> WIN_FIRST_THROW_1 = new ArrayList<Integer>(Arrays.asList(7, 11));
    private static final ArrayList<Integer> LOSE_FIRST_THROW_1 = new ArrayList<Integer>(Arrays.asList(2, 3, 12));

    private final Random random = new Random();

    // Simulates a throw of 2 dice
    private int rollDice() {
        return random.nextInt(6) + 1 + random.nextInt(6) + 1;
    }

    private boolean playSingleRound(List<String> gameDetails) {
        int firstThrow = rollDice();
        gameDetails.add("First throw: " + firstThrow);

        if (WIN_FIRST_THROW_1.contains(firstThrow)) {
            gameDetails.add("Player wins by rolling a 7 or 11.");
            return true;
        } else if (LOSE_FIRST_THROW_1.contains(firstThrow)) {
            gameDetails.add("Player loses by rolling a 2, 3, or 12.");
            return false;
        }

        int point = firstThrow;
        gameDetails.add("Player's point is: " + point);

        while (true) {
            int nextThrow = rollDice();
            gameDetails.add("Next throw: " + nextThrow);

            if (nextThrow == point) {
                gameDetails.add("Player wins by hitting the point.");
                return true;
            } else if (nextThrow == 7) {
                gameDetails.add("Player loses by rolling a 7.");
                return false;
            }
        }
    }

    public CrapsSummary playMultipleRounds(double stake, int rounds,String gameType) {
        double totalWins = 0;
        List<String> gameDetails = new ArrayList<>();

        for (int i = 0; i < rounds; i++) {
            gameDetails.add("Round " + (i + 1));
            boolean win = playSingleRound(gameDetails);
            if (win) {
                totalWins += 2 * stake;
            }
        }

        double totalStake = stake * rounds;
        double winPercentage = (totalStake > 0) ? totalWins * 100 / totalStake : 0;

        return new CrapsSummary(totalStake, totalWins, winPercentage, gameDetails, gameType);
    }

    public static class CrapsSummary {
        private final double totalStake;
        private final double totalWins;
        private final double winPercentage;
        private final List<String> gameDetails;
        private final String gameType;

        public CrapsSummary(double totalStake, double totalWins, double winPercentage, List<String> gameDetails, String gameType) {
            this.totalStake = totalStake;
            this.totalWins = totalWins;
            this.winPercentage = winPercentage;
            this.gameDetails = gameDetails;
            this.gameType = gameType;
        }

        public double getTotalStake() {
            return totalStake;
        }

        public double getTotalWins() {
            return totalWins;
        }

        public double getWinPercentage() {
            return winPercentage;
        }

        public List<String> getGameDetails() {
            return gameDetails;
        }

        public String getGameType(){return gameType;}
    }
}
