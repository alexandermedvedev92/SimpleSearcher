package service;

import java.io.IOException;

public interface Searcher
{
    /**
     * Search words in files of necessary directory and show result
     * @throws IOException when exception is thrown
     */
    void search() throws IOException;
}
