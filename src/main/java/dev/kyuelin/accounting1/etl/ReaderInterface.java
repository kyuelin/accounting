package dev.kyuelin.accounting1.etl;

public interface ReaderInterface {
    boolean init();
    Object read();
}
