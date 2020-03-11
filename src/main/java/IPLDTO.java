public class IPLDTO {

    public String player;
    public double average;
    public double sixs;
    public double fours;
    public double strikingRates;
    public double runs;

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
    }
}
