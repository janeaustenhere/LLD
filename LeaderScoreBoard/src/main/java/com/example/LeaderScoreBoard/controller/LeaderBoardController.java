package com.example.LeaderScoreBoard.controller;


import com.example.LeaderScoreBoard.model.LeaderBoardResponse;
import com.example.LeaderScoreBoard.service.ILeaderBoardService;
import com.example.LeaderScoreBoard.service.LeaderBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Response;

@RestController
@RequestMapping("/leaderBoard")
public class LeaderBoardController {

    private final ILeaderBoardService leaderBoardService;

    public LeaderBoardController(ILeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }


    @PostMapping("/updateScore")
    public ResponseEntity<String> updateScore(@RequestParam String userId, @RequestParam double score){
        leaderBoardService.updateScore(userId,score);

       return  ResponseEntity.status(HttpStatus.OK).body("Score Updated");

    }

    @PostMapping("/getLeaderScoreBoardView")

    public ResponseEntity<LeaderBoardResponse> getLeaderBordView(String userId){
        LeaderBoardResponse leaderBoardResponse = leaderBoardService.getLeaderBoardView(userId);

        return ResponseEntity.status(HttpStatus.OK).body(leaderBoardResponse);

    }
}
