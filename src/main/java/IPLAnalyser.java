import com.google.gson.Gson;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {
    Map<SortField, Comparator<IPLMostRunsCSV>> sortMap;

    public IPLAnalyser() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(iplData -> iplData.average));
        this.sortMap.put(SortField.STRIKINGRATES, Comparator.comparing(iplData -> iplData.strikingRates));
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
