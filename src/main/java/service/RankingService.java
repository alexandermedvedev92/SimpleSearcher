package service;

public interface RankingService
{
    /**
     * Calculating rank for each file
     * @param currentSize is size of words which we have not found
     * @param initialSize is count of words which we need to search
     * @return rank
     */
    String calculateRank(int currentSize, int initialSize);
}
