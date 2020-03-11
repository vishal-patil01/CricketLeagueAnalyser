import com.google.gson.Gson;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class IPLAnalyser {
    Map<SortField, Comparator<IPLMostRunsCSV>> sortMap;


    public IPLAnalyser() {
        this.sortMap = new HashMap<>();

        this.sortMap.put(SortField.AVG, Comparator.comparing(iplData -> iplData.average));

        this.sortMap.put(SortField.STRIKINGRATES, Comparator.comparing(iplData -> iplData.strikingRates));

        this.sortMap.put(SortField.SIXFOURS, Comparator.comparing(iplData -> iplData.sixs + iplData.fours));

        Comparator<IPLMostRunsCSV> sixFourWithAvg = Comparator.comparing(iplData -> iplData.sixs + iplData.fours);
        this.sortMap.put(SortField.SIXFOUR_WITHAVG, sixFourWithAvg.thenComparing(iplData -> iplData.strikingRates));

        Comparator<IPLMostRunsCSV> bestAverageWithStrikeRate = Comparator.comparing(iplData -> iplData.average);
        this.sortMap.put(SortField.BESTAVERAGE_WITH_STRIKERATE, bestAverageWithStrikeRate.thenComparing(iplData -> iplData.strikingRates));

        Comparator<IPLMostRunsCSV> maxRunsWithBestAverages = Comparator.comparing(iplData -> iplData.runs);
        this.sortMap.put(SortField.MAXRUNS_WITH_BESTAVERAGES, maxRunsWithBestAverages.thenComparing(iplData -> iplData.average));

    }

    public String analyseIPLData(SortField sortField, String csvFilePath) throws IOException {
        List iplCricketersList = new IPLCSVLoader().loadIPLData(csvFilePath);
        List sortedList = sortList(sortField, iplCricketersList);
        String sortedStateCensus = new Gson().toJson(sortedList);
        return sortedStateCensus;
    }

    public List sortList(SortField sortField, List iplCricketersList) {
        return (List) iplCricketersList.stream()
                .sorted(this.sortMap.get(sortField).reversed())
                .collect(Collectors.toList());
    }
}
