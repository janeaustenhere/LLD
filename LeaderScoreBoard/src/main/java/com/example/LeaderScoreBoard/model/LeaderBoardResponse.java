package com.example.LeaderScoreBoard.model;


import lombok.Getter;

import java.util.List;

@Getter
public class LeaderBoardResponse {

    private final List<LeaderBoardEntry> topTen;
    private final List<LeaderBoardEntry> userContextWindow;

    public LeaderBoardResponse(List<LeaderBoardEntry> topTen, List<LeaderBoardEntry> userContextWindow) {
        this.topTen = topTen;
        this.userContextWindow = userContextWindow;
    }
}

