import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


public class IPLTest {
    private static IPLAnalyser iplAnalyser;

    private static final String IPL_2019_FACTSHEET_MOST_RUNS_CSV = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_2019_FACTSHEET_MOST_WKTS_CSV = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @BeforeClass
    public static void beforeClass() {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_BatsmanHavingTopBattingAverages() {
        List cricketersList = iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BATTING, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.AVG,cricketersList);
        IPLDTO[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLDTO[].class);
        Assert.assertEquals("MS Dhoni", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_BatsmenHavingTopStrikingRates() {
        List cricketersList = iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BATTING, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.STRIKINGRATES,cricketersList);
        IPLDTO[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLDTO[].class);
        Assert.assertEquals("Ishant Sharma", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_BatsmenWhoHitsMaximumSixFour() {
        List cricketersList =  iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BATTING, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.SIXFOURS,cricketersList);
        IPLDTO[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLDTO[].class);
        Assert.assertEquals("Andre Russell", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_BatsmenWhoHaveMaximumStrikingRateWithSixFour() {
        List cricketersList =   iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BATTING, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.MAXSTRIKERATE_WITH_SIXFOUR,cricketersList);
        IPLDTO[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLDTO[].class);
        Assert.assertEquals("Andre Russell", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_BatsmenWhoHaveBestAvgAndStrikeRates() {
        List cricketersList =  iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BATTING, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.BESTAVERAGE_WITH_STRIKERATE,cricketersList);
        IPLDTO[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLDTO[].class);
        Assert.assertEquals("MS Dhoni", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_BatsmenWhoHitsMaxRunsWithBestAverage() {
        List cricketersList =   iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BATTING, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.MAXRUNS_WITH_BESTAVERAGES,cricketersList);
        IPLDTO[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLDTO[].class);
        Assert.assertEquals("David Warner ", censusCSV[0].player);
    }

    @Test
    public void givenIplCSVFile_ShouldReturn_PlayerHavingTopBowlingAverage() {
        List cricketersList =  iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BALLING, IPL_2019_FACTSHEET_MOST_WKTS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.AVG,cricketersList);
        IPLMostWktsCSV[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLMostWktsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }
    @Test
    public void givenIplCSVFile_ShouldReturn_PlayerHavingTopStrikingRate() {
        List cricketersList =  iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BALLING, IPL_2019_FACTSHEET_MOST_WKTS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.STRIKINGRATES,cricketersList);
        IPLMostWktsCSV[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLMostWktsCSV[].class);
        Assert.assertEquals("Krishnappa Gowtham", censusCSV[0].player);
    }
    @Test
    public void givenIplCSVFile_ShouldReturn_PlayerHavingBestEconomyRate() {
        List cricketersList =  iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BALLING, IPL_2019_FACTSHEET_MOST_WKTS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.ECONOMYRATE,cricketersList);
        IPLMostWktsCSV[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLMostWktsCSV[].class);
        Assert.assertEquals("Ben Cutting", censusCSV[0].player);
    }
    @Test
    public void givenIplCSVFile_ShouldReturn_PlayerHavingBestStrikingRateWithWickets() {
        List cricketersList =  iplAnalyser.analyseIPLData(IPLAnalyser.BatsOrBall.BALLING, IPL_2019_FACTSHEET_MOST_WKTS_CSV);
        String cricketersDataInJson = iplAnalyser.sortListAndConvertJson(SortField.MAXWICKETS_WITH_STRIKERATE,cricketersList);
        IPLMostWktsCSV[] censusCSV = new Gson().fromJson(cricketersDataInJson, IPLMostWktsCSV[].class);
        Assert.assertEquals("Lasith Malinga", censusCSV[0].player);
    }
}