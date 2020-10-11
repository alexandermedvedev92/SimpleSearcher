package service.impl;

import service.RankingService;
import service.Searcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SearcherImpl implements Searcher
{
    private String searchPath;
    private RankingService rankingService;
    private Map<String, String> searchResults;
    private final String EXIT = "exit";

    public SearcherImpl(RankingService rankingService, String searchPath)
    {
        this.searchPath = searchPath;
        this.rankingService = rankingService;
        searchResults = new HashMap<>();
    }

    @Override
    public void search() throws IOException
    {
        Scanner scan = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("Write words to search: ");
            input = scan.nextLine();
            if (input.equals(EXIT)) break;
            searchWordsByEachFile(input);
            showSearchInfo();
        }
    }

    /**
     * Shows file name and rank sorted by rank
     */
    private void showSearchInfo()
    {
        searchResults.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(map -> System.out.println(map.getKey() + "  :  " + map.getValue()));
    }

    /**
     * Search words in each file
     * @param searchWords is string which contains words to search
     * @throws IOException when exception is thrown
     */
    private void searchWordsByEachFile(String searchWords) throws IOException
    {
        List<String> words = Arrays.stream(searchWords.split(" ")).map(String::toLowerCase).collect(Collectors.toList());
        Map<String, List<String>> wordsInFile = readAndGroupWordsByFile();
        wordsInFile.forEach((fileName, wordsList) -> {
            List<String> wordsListCopy = new ArrayList<>(words);
            wordsList.forEach(wordsListCopy::remove);
            searchResults.put(fileName, String.format("%s", rankingService.calculateRank(wordsListCopy.size(), words.size()) + " %"));
        });
    }

    /**
     * Grouping words by file
     * @return Map with file name as a key and list of words in this file as a value
     * @throws IOException when exception is thrown
     */
    private Map<String, List<String>> readAndGroupWordsByFile() throws IOException
    {
        Map<String, List<String>> wordsInFile = new HashMap<>();
        List<Path> paths = Files.list(Paths.get(searchPath)).collect(Collectors.toList());
        for (Path filePath : paths) {
            if (!Files.isHidden(filePath))
                wordsInFile.put(filePath.getFileName().toString(), readAllWordsInFile(filePath));
        }
        return wordsInFile;
    }

    /**
     * Read words in file
     * @param path is path to file
     * @return List of words in current file
     * @throws IOException when exception is thrown
     */
    private List<String> readAllWordsInFile(Path path) throws IOException
    {
        return Files.lines(path).flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase).collect(Collectors.toList());
    }
}
