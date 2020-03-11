import com.csvparser.CSVBuilderException;
import com.google.gson.Gson;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {
    Map<SortField, Comparator<IPLDTO>> sortMap;

    public enum BatsOrBall {BATTING, BALLING}

    public IPLAnalyser() {
        this.sortMap = new HashMap<>();

        this.sortMap.put(SortField.AVG, Comparator.comparing(iplData -> iplData.average));

        this.sortMap.put(SortField.STRIKINGRATES, Comparator.comparing(iplData -> iplData.strikingRates));

        this.sortMap.put(SortField.SIXFOURS, Comparator.comparing(iplData -> iplData.sixs + iplData.fours));

        Comparator<IPLDTO> sixFourWithAvg = Comparator.comparing(iplData -> iplData.sixs + iplData.fours);
        this.sortMap.put(SortField.MAXSTRIKERATE_WITH_SIXFOUR, sixFourWithAvg.thenComparing(iplData -> iplData.strikingRates));

        Comparator<IPLDTO> bestAverageWithStrikeRate = Comparator.comparing(iplData -> iplData.average);
        this.sortMap.put(SortField.BESTAVERAGE_WITH_STRIKERATE, bestAverageWithStrikeRate.thenComparing(iplData -> iplData.strikingRates));

        Comparator<IPLDTO> maxRunsWithBestAverages = Comparator.comparing(iplData -> iplData.runs);
        this.sortMap.put(SortField.MAXRUNS_WITH_BESTAVERAGES, maxRunsWithBestAverages.thenComparing(iplData -> iplData.average));

        this.sortMap.put(SortField.ECONOMYRATE, Comparator.comparing(iplData -> iplData.economyRate));

        Comparator<IPLDTO> maxWicketsWithStrikeRate = Comparator.comparing(iplData -> iplData.wicket4 + iplData.wicket5);
        this.sortMap.put(SortField.MAXWICKETS_WITH_STRIKERATE, maxWicketsWithStrikeRate.thenComparing(iplData -> iplData.strikingRates));

    }

    public List analyseIPLData(IPLAnalyser.BatsOrBall gameFact, String csvFilePath) {
        try {
         return new IPLLoaderAdapter().loadCensusData(gameFact, csvFilePath);
        } catch (CSVBuilderException e) {
            throw new CSVBuilderException("Unable To Load Data", CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    public String sortListAndConvertJson(SortField sortField, List iplCricketersList) {
       return new Gson().toJson(iplCricketersList.stream()
                .sorted(this.sortMap.get(sortField).reversed())
                .collect(Collectors.toList()));
    }
}
