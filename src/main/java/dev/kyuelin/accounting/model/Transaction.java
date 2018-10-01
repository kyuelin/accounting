package dev.kyuelin.accounting.model;

import java.math.BigDecimal;
import java.util.Date;

//"Date","Description","Original Description","Amount","Transaction Type","Category","Account Name","Labels","Notes"
public class Transaction {
    private Date txnDate;
    private String txnDesc;
    private String txnOrgDesc;
    private BigDecimal txnAmt;
    private String txnType;
    private String txnCat;
    private String acctName;
    private String lables;
    private String notes;

    public Date getTxnDate() {
        return txnDate;
    }

    public String getTxnDesc() {
        return txnDesc;
    }

    public String getTxnOrgDesc() {
        return txnOrgDesc;
    }

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public String getTxnType() {
        return txnType;
    }

    public String getTxnCat() {
        return txnCat;
    }

    public String getAcctName() {
        return acctName;
    }

    public String getLables() {
        return lables;
    }

    public String getNotes() {
        return notes;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public void setTxnDesc(String txnDesc) {
        this.txnDesc = txnDesc;
    }

    public void setTxnOrgDesc(String txnOrgDesc) {
        this.txnOrgDesc = txnOrgDesc;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public void setTxnCat(String txnCat) {
        this.txnCat = txnCat;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public void setLables(String lables) {
        this.lables = lables;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnDate=" + txnDate +
                ", txnDesc='" + txnDesc + '\'' +
                ", txnOrgDesc='" + txnOrgDesc + '\'' +
                ", txnAmt=" + txnAmt +
                ", txnType='" + txnType + '\'' +
                ", txnCat='" + txnCat + '\'' +
                ", acctName='" + acctName + '\'' +
                ", lables='" + lables + '\'' +
                ", notes='" + notes + '\'' +
                "}\n";
    }
}
