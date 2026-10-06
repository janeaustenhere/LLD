package com.example.splitwise.factory;


import com.example.splitwise.strategy.EqualSplitStrategy;
import com.example.splitwise.strategy.PercentageSplitStrategy;
import com.example.splitwise.strategy.SplitStrategy;
import com.example.splitwise.strategy.SplitStrategyType;
import org.springframework.stereotype.Component;

@Component
public class SplitTypeObjectFactory {

    private final EqualSplitStrategy equalSplitStrategy;
    private final PercentageSplitStrategy percentageSplitStrategy;

    public SplitTypeObjectFactory(EqualSplitStrategy equalSplitStrategy, PercentageSplitStrategy percentageSplitStrategy) {
        this.equalSplitStrategy = equalSplitStrategy;
        this.percentageSplitStrategy = percentageSplitStrategy;
    }


    public SplitStrategy getSplitStrategy(SplitStrategyType splitStrategyType){

        if (splitStrategyType.equals(SplitStrategyType.EQUAL)){
            return this.equalSplitStrategy;
        } else if(splitStrategyType.equals(SplitStrategyType.PERCENTAGE)){
            return this.percentageSplitStrategy;
        }
        return null;
    }
}
