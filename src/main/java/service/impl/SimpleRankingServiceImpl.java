package service.impl;

import service.RankingService;

public class SimpleRankingServiceImpl implements RankingService
{
    @Override
    public float calculateRank(int currentSize, int initialSize)
    {
        return (float)(initialSize - currentSize) * 100 / initialSize;
    }
}
