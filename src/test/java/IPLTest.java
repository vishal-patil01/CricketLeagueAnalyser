import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;


public class IPLTest {
    private static IPLAnalyser iplAnalyser;

    private static final String IPL_2019_FACTSHEET_MOST_RUNS_CSV = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @BeforeClass
    public static void beforeClass() {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIplCSVFile_ShouldReturnTopBattingAverages() throws IOException {
        String numOfRecords = iplAnalyser.analyseIPLData(SortField.AVG, IPL_2019_FACTSHEET_MOST_RUNS_CSV);
        IPLMostRunsCSV[] censusCSV = new Gson().fromJson(numOfRecords, IPLMostRunsCSV[].class);
        Assert.assertEquals("MS Dhoni", censusCSV[0].player);
    }


}