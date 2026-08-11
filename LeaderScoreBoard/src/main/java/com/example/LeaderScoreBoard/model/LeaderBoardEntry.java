package com.example.LeaderScoreBoard.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LeaderBoardEntry {

    final String userId;
    final double score;
    final long rank;

    @Override
    public String toString(){
        return String.format("Rank= %d , score= %f, userName= %s", rank,score,userId);

    }



}
