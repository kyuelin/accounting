package dev.kyuelin.accounting.etl;

public interface ReaderInterface {
    boolean init();
    Object read();
}
