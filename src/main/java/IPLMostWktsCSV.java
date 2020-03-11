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

    @CsvBindByName(column = "4W", required = true)
    public double wicket4;

    @CsvBindByName(column = "5W", required = true)
    public double wicket5;
}
