package com.example.LeaderScoreBoard.service;

import com.example.LeaderScoreBoard.model.LeaderBoardEntry;
import com.example.LeaderScoreBoard.model.LeaderBoardResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.resps.Tuple;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderBoardService implements ILeaderBoardService{

    private final JedisPool jedisPool;


    private final String leaderBoardKey;

    public LeaderBoardService(JedisPool jedisPool,
                              @Value("${leaderboard.key}")String leaderBoardKey) {
        this.jedisPool = jedisPool;
        this.leaderBoardKey = leaderBoardKey;
    }

    @Override
    public void updateScore(String userId, double score) {
        try (Jedis jedis = jedisPool.getResource()){
            jedis.zadd(leaderBoardKey,score,userId);
        }

    }

    @Override
    public LeaderBoardResponse getLeaderBoardView(String userId) {
        List<LeaderBoardEntry> topTenList = new ArrayList<>();
        List<LeaderBoardEntry> userWindowList = new ArrayList<>();

        try (Jedis jedis = jedisPool.getResource()){

            Pipeline pipeline = jedis.pipelined();

            Response<List<Tuple>> listResponse = pipeline.zrangeWithScores(leaderBoardKey,0,9);

            Response<Long> userRankResponse = pipeline.zrevrank(leaderBoardKey,userId);

            List<Tuple> tupleList = listResponse.get();

            Long userRank = userRankResponse.get();
            int baseRank = 0;
            for(Tuple tuple : tupleList){

                topTenList.add(new LeaderBoardEntry(tuple.getElement(), tuple.getScore(),baseRank));
            }

            long startWindow = userRank - 5;
            long endWindow  = userRank + 5;

            Response<List<Tuple>> userWindowTupleResponse = pipeline.zrangeWithScores(leaderBoardKey, startWindow, endWindow);

            List<Tuple> userWindowTupleList = userWindowTupleResponse.get();

            long userBaseRank = startWindow;
            for(Tuple tuple : userWindowTupleList){

                userWindowList.add(new LeaderBoardEntry(tuple.getElement(), tuple.getScore(), userBaseRank++));
            }

        }

        return new LeaderBoardResponse(topTenList, userWindowList);

    }
}
