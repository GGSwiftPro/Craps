package com.example.craps.controller;

import com.example.craps.dto.PlayCrapsRequest;
import com.example.craps.dto.PlayCrapsResponse;
import com.example.craps.service.CrapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CrapsController {

    private final List<String> SUPPORTED_GAME_TYPES = List.of("craps");
    private final CrapsService crapsService;

    @Autowired
    public CrapsController(CrapsService crapsService) {
        this.crapsService = crapsService;
    }

    @PostMapping("/playMultipleRounds")
    public ResponseEntity<?> playCraps(@RequestBody PlayCrapsRequest request) {
        if (!SUPPORTED_GAME_TYPES.contains(request.getGameType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported game type");
        }

        if (request.getStake() <= 0 || request.getRounds() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid stake or rounds");
        }

        CrapsService.CrapsSummary summary = crapsService.playMultipleRounds(request.getStake(), request.getRounds(), request.getGameType());

        PlayCrapsResponse response = new PlayCrapsResponse(
                summary.getTotalStake(),
                summary.getTotalWins(),
                summary.getWinPercentage()/2,
                summary.getGameDetails(),
                summary.getGameType()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/play")
    public ResponseEntity<?> playCrapsMultipleRounds(@RequestBody PlayCrapsRequest request) {
        if (!SUPPORTED_GAME_TYPES.contains(request.getGameType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported game type");
        }

        if (request.getStake() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid stake or rounds");
        }


        CrapsService.CrapsSummary summary = crapsService.playMultipleRounds(request.getStake(), 1,request.getGameType());

        PlayCrapsResponse response = new PlayCrapsResponse(
                summary.getTotalStake(),
                summary.getTotalWins(),
                summary.getWinPercentage()/2,
                summary.getGameDetails(),
                summary.getGameType()
        );

        return ResponseEntity.ok(response);
    }
}
