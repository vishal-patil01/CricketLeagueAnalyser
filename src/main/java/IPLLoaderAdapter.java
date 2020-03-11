import com.csvparser.CSVBuilderException;
import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IPLLoaderAdapter {

    public <E> List loadCensusData(IPLAnalyser.BatsOrBall gameFact,String csvFilePath) {
        List csvFileList = new ArrayList();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IPLFactory.getIPLCSVClass(gameFact));
            Iterable<E> csvIterable = () -> censusCSVIterator;
            if (gameFact.equals(IPLAnalyser.BatsOrBall.BATTING)) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLMostRunsCSV.class::cast)
                        .forEach(censusCSV ->csvFileList.add(new IPLDTO(censusCSV)));
            } else if (gameFact.equals(IPLAnalyser.BatsOrBall.BALLING)) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLMostWktsCSV.class::cast)
                        .forEach(censusCSV -> csvFileList.add( new IPLDTO(censusCSV)));
            }
            return csvFileList;
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
