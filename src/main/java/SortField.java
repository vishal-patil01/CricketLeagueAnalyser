import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortField {
    STRIKINGRATES, ALLROUNDER, ALLROUNDERAVG, AVG, SIXFOURS, MAXSTRIKERATE_WITH_SIXFOUR, BESTAVERAGE_WITH_STRIKERATE, MAXRUNS_WITH_BESTAVERAGES, ECONOMYRATE, WICKETS4AND6_WITH_STRIKERATE, WICKETSAND_WITH_STRIKERATE, WICKETS_WITH_STRIKERATE;
    static Map<SortField, Comparator<IPLDTO>> sortMap = new HashMap<>();

    static void initializeSortField() {
        sortMap.put(SortField.AVG, Comparator.comparing(iplData -> iplData.average));
        sortMap.put(SortField.STRIKINGRATES, Comparator.comparing(iplData -> iplData.strikingRates));
        sortMap.put(SortField.SIXFOURS, Comparator.comparing(iplData -> iplData.sixs + iplData.fours));
        Comparator<IPLDTO> sixFourWithAvg = Comparator.comparing(iplData -> iplData.sixs + iplData.fours);
        sortMap.put(SortField.MAXSTRIKERATE_WITH_SIXFOUR, sixFourWithAvg.thenComparing(iplData -> iplData.strikingRates));
        Comparator<IPLDTO> bestAverageWithStrikeRate = Comparator.comparing(iplData -> iplData.average);
        sortMap.put(SortField.BESTAVERAGE_WITH_STRIKERATE, bestAverageWithStrikeRate.thenComparing(iplData -> iplData.strikingRates));
        Comparator<IPLDTO> maxRunsWithBestAverages = Comparator.comparing(iplData -> iplData.runs);
        sortMap.put(SortField.MAXRUNS_WITH_BESTAVERAGES, maxRunsWithBestAverages.thenComparing(iplData -> iplData.average));
        sortMap.put(SortField.ECONOMYRATE, Comparator.comparing(iplData -> iplData.economyRate));
        Comparator<IPLDTO> maxWicket4And6sWithStrikeRate = Comparator.comparing(iplData -> iplData.wicket4 + iplData.wicket5);
        sortMap.put(SortField.WICKETS4AND6_WITH_STRIKERATE, maxWicket4And6sWithStrikeRate.thenComparing(iplData -> iplData.strikingRates));
        Comparator<IPLDTO> maxWicketsWithStrikeRate = Comparator.comparing(iplData -> iplData.wicket);
        sortMap.put(SortField.WICKETS_WITH_STRIKERATE, maxWicketsWithStrikeRate.thenComparing(iplData -> iplData.strikingRates));

    }
}
