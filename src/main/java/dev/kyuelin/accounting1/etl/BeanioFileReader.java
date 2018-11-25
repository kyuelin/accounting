package dev.kyuelin.accounting1.etl;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class BeanioFileReader implements ReaderInterface {

    private BeanReader reader = null;
    private StreamFactory streamFactory = null;
    private InputStream inputStream = null;

    private BeanioFileReaderConfig config;

    public BeanioFileReader(BeanioFileReaderConfig config) {
        this.config=config;
    }

    public boolean init() {
        if (null == streamFactory) {
            streamFactory = StreamFactory.newInstance();
            streamFactory.loadResource(config.getBioConfig());
        }

        try {
            inputStream = new FileInputStream(config.getInputFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        reader = streamFactory.createReader(config.getStreamName(), new InputStreamReader(inputStream));
        return (reader != null);
    }

    public Optional<Object> read() {
        Optional<Object> objectOptional = Optional.empty();

        if (null != reader) {
            objectOptional= Optional.ofNullable(reader.read());
        }
        return objectOptional;
    }

    public String getRecordName() {
        return reader.getRecordName();
    }
}
