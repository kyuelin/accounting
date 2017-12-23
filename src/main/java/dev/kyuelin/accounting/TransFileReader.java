package dev.kyuelin.accounting;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransFileReader {

    private BeanReader reader = null;
    private StreamFactory streamFactory = null;
    private InputStream inputStream = null;
    private String transBIOConfig = "transactions.xml";
    Connection con = null;
    PreparedStatement pst = null;

    String url = "jdbc:mysql://localhost:3306/mysql";
    String user = "kennethlin";
    String password = "k123";

    public void initReader(final String transFile) throws FileNotFoundException {
        if (null == streamFactory) {
            streamFactory = StreamFactory.newInstance();
            streamFactory.loadResource(transBIOConfig);
        }

        inputStream = new FileInputStream(transFile);
        reader = streamFactory.createReader("transactions", new InputStreamReader(inputStream));
    }

    public void initWriter() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
        pst = con.prepareStatement("INSERT INTO tbl_transactions(trans_date, description, original_description, amount, " +
                "transtype, segment, category, account_name,labels, notes ) VALUES(?,?,?,?, ?,?,?,?,?,?)");
//        pst.setString(1, author);
//        pst.executeUpdate();
    }

    public List<Transaction> readTransList() throws SQLException {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        Object record = null;
        int recordRead = 0;
        if (null != reader) {
            while ((record = reader.read()) != null)
            {
                if ("detail".equals(reader.getRecordName()))
                //if (reader.getLineNumber() > 1)
                {
                    Transaction transaction = (Transaction) record;
                    //transactionList.add(transaction);
                    if (null != pst) {
                        pst.setDate(1, new Date(transaction.getTxnDate().getTime()));
                        pst.setString(2, transaction.getTxnDesc());
                        pst.setString(3, transaction.getTxnOrgDesc());
                        pst.setBigDecimal(4, transaction.getTxnAmt());
                        pst.setString(5, transaction.getTxnType());
                        pst.setString(6, null == Constants.segMap.get(transaction.getTxnCat()) ? "" : Constants.segMap.get(transaction.getTxnCat()));
                        pst.setString(7, null == Constants.catMap.get(transaction.getTxnCat()) ? transaction.getTxnCat() : Constants.catMap.get(transaction.getTxnCat()));
                        pst.setString(8, transaction.getAcctName());
                        pst.setString(9, transaction.getLables());
                        pst.setString(10, transaction.getNotes());
                        pst.executeUpdate();
                    }
                    ++recordRead;
                }
            }
            //System.out.println(transactionList);
            System.out.println("record read = " + recordRead);
        }
        return transactionList;
    }

/*
    public Transaction readNext() {
        if (null != reader) {
            Transaction t = (Transaction) reader.read();
            if ("detail".equals(reader.getRecordName()))
                return t;
            else
                return null;
        }
        else {
            return null;
        }
    }
*/

    //"Date","Description","Original Description","Amount","Transaction Type","Category","Account Name","Labels","Notes"

    public static void main(String[] args)
    {
        TransFileReader reader = new TransFileReader();
        try {
            reader.initReader("/Users/kennethlin/Google Drive/19204/archive/transactions_1712.csv");
            reader.initWriter();
            reader.readTransList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
