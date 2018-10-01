package dev.kyuelin.accounting.etl;

public interface Reader {
    boolean init();
    Object read();
}
