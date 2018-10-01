package dev.kyuelin.accounting.etl;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BeanioFileReader implements Reader {

    private BeanReader reader = null;
    private StreamFactory streamFactory = null;
    private InputStream inputStream = null;

    private String streamName = "transactions";
    private String transBIOConfig = "transactions.xml";
    private String inputFile = System.getProperty("FileReaderInputFile");

    public boolean init() {
        if (null == streamFactory) {
            streamFactory = StreamFactory.newInstance();
            streamFactory.loadResource(transBIOConfig);
        }

        try {
            inputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        reader = streamFactory.createReader(streamName, new InputStreamReader(inputStream));
        return (reader != null);
    }

    public Object read() {
        return null;
    }
}
