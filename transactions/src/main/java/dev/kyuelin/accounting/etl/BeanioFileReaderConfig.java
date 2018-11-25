package dev.kyuelin.accounting.etl;

public class BeanioFileReaderConfig {

    public BeanioFileReaderConfig(String streamName, String bioConfig, String inputFile) {
        this.streamName = streamName;
        this.bioConfig = bioConfig;
        this.inputFile = inputFile;
    }

    public String getStreamName() {
        return streamName;
    }

    public String getBioConfig() {
        return bioConfig;
    }

    public String getInputFile() {
        return inputFile;
    }

    private String streamName;
    private String bioConfig;
    private String inputFile;

    @Override
    public String toString() {
        return "BeanioFileReaderConfig{" +
                "streamName='" + streamName + '\'' +
                ", bioConfig='" + bioConfig + '\'' +
                ", inputFile='" + inputFile + '\'' +
                '}';
    }
}
