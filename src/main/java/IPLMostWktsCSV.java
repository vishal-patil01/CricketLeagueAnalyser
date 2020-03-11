import com.opencsv.bean.CsvBindByName;

public class IPLMostWktsCSV {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikingRates;

    @CsvBindByName(column = "Econ", required = true)
    public double economyRate;
}
