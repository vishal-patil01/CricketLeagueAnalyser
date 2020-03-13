public class IPLFactory {
    public static Class getIPLCSVClass(IPLAnalyser.BatsOrBall gameFact) {
        if (gameFact.equals(IPLAnalyser.BatsOrBall.BATTING))
            return IPLMostRunsCSV.class;
        else if (gameFact.equals(IPLAnalyser.BatsOrBall.BALLING))
            return IPLMostWktsCSV.class;
        return null;
    }
}
