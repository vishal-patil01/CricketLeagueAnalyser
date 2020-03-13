import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {
    List<IPLDTO> allRounder;

    public enum BatsOrBall {BATTING, BALLING}

    public IPLAnalyser() {
        allRounder = new ArrayList<>();
        SortField.initializeSortField();
    }

    public List<IPLDTO> analyseIPLData(SortField sortType, String... csvFilePath) {
        List<IPLDTO> batsmenList = new IPLLoaderAdapter().loadIPLData(BatsOrBall.BATTING, csvFilePath[0]);
        List<IPLDTO> bowlersList = new IPLLoaderAdapter().loadIPLData(BatsOrBall.BALLING, csvFilePath[1]);
        for (int i = 0; i < batsmenList.size(); i++) {
            for (int j = 0; j < bowlersList.size(); j++) {
                if (batsmenList.get(i).player.equals(bowlersList.get(j).player)) {
                    if (sortType.equals(SortField.ALLROUNDERAVG))
                        allRounder.add(new IPLDTO(batsmenList.get(i).player, batsmenList.get(i).average + bowlersList.get(j).average));
                    if (sortType.equals(SortField.ALLROUNDER))
                        allRounder.add(new IPLDTO(batsmenList.get(i).player, batsmenList.get(i).runs * bowlersList.get(j).wicket));
                }
            }
        }
        return allRounder;
    }

    public List analyseIPLData(IPLAnalyser.BatsOrBall batsOrBall, String csvFilePath) {
        return new IPLLoaderAdapter().loadIPLData(batsOrBall, csvFilePath);
    }

    public String sortListAndConvertJson(SortField sortField, List iplCricketersList) {
        return new Gson().toJson(iplCricketersList.stream()
                .sorted(SortField.sortMap.get(sortField).reversed())
                .collect(Collectors.toList()));
    }
}
