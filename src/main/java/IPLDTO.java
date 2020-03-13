public class IPLDTO {

    public String player;
    public double average;
    public double sixs;
    public double fours;
    public double strikingRates;
    public double runs;
    public double economyRate;
    public double wicket4;
    public double wicket5;
    public double wicket;

    public IPLDTO(IPLMostRunsCSV iplFactsCSV) {
        player = iplFactsCSV.player;
        average = iplFactsCSV.average;
        sixs=iplFactsCSV.sixs;
        fours=iplFactsCSV.fours;
        strikingRates=iplFactsCSV.strikingRates;
        runs=iplFactsCSV.runs;
    }
    public IPLDTO(IPLMostWktsCSV iplFactsCSV) {
        player = iplFactsCSV.player;
        average = iplFactsCSV.average;
        strikingRates=iplFactsCSV.strikingRates;
        economyRate=iplFactsCSV.economyRate;
        wicket4=iplFactsCSV.wicket4;
        wicket5=iplFactsCSV.wicket5;
        wicket=iplFactsCSV.wicket;
    }
}
