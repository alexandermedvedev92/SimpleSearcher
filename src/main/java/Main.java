import service.Searcher;
import service.impl.SearcherImpl;
import service.impl.SimpleRankingServiceImpl;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        if (args != null && args.length != 0)
            searchWord(new SearcherImpl(new SimpleRankingServiceImpl(), args[0]));
        else System.out.println("We didn't find a path. Please try again.");
    }

    private static void searchWord(Searcher searcher) throws IOException
    {
        searcher.search();
    }
}
