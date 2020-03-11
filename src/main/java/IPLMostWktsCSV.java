import com.opencsv.bean.CsvBindByName;

public class IPLMostWktsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;
}
