package dev.kyuelin.accounting.etl;

import dev.kyuelin.accounting.model.Transaction;
import dev.kyuelin.accounting.utils.Constants;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TransAnalyst {

    public static void main (String[] args) {

        BeanioFileReaderConfig config = new BeanioFileReaderConfig("transactions", "transactions.xml", "/Users/kennethlin/Downloads/transactions.csv");

        ReaderInterface reader = new BeanioFileReader(config);

        reader.init();

        int count =0;

        Optional<Object> objectOptional = ((BeanioFileReader) reader).read();

        Map<String, BigDecimal> summary = new HashMap<>();

        while (objectOptional.isPresent()) {
            if ("detail".equals(((BeanioFileReader) reader).getRecordName())) {
                Transaction transaction = (Transaction) objectOptional.get();

                int year  = transaction.getTxnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
                int month = transaction.getTxnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();

                String cat = !Constants.catMap.containsKey(transaction.getTxnCat()) ? transaction.getTxnCat() : Constants.catMap.get(transaction.getTxnCat());
                String seg = !Constants.segMap.containsKey(transaction.getTxnCat()) ? "" : Constants.segMap.get(transaction.getTxnCat());

                if (year == 2018 && month == 9) {
                    //System.out.println(transaction);
                    //System.out.println(String.valueOf(year) + " " + String.valueOf(month) + " " + cat + " " + seg);
                    String key = seg + "_" + cat;
                    if (summary.get(key) == null)
                        summary.put(key, BigDecimal.valueOf(0));
                    else
                        summary.put(key, summary.get(key).add(transaction.getTxnAmt()));
                }
            }
            objectOptional = ((BeanioFileReader) reader).read();
            count++;
        }

        System.out.println(Arrays.toString(summary.entrySet().toArray()));

    }
}
