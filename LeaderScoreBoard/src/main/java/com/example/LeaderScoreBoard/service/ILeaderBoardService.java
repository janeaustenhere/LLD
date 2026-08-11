package com.example.LeaderScoreBoard.service;

import com.example.LeaderScoreBoard.model.LeaderBoardResponse;public interface ILeaderBoardService {

    void updateScore(String userId, double score);

    LeaderBoardResponse getLeaderBoardView(String userId);

}
