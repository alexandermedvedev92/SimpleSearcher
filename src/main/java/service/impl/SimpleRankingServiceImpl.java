package service.impl;

import service.RankingService;

public class SimpleRankingServiceImpl implements RankingService
{
    @Override
    public String calculateRank(int currentSize, int initialSize)
    {
        return String.valueOf((initialSize - currentSize) * 100 / initialSize);
    }
}
