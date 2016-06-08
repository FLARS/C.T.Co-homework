
public class Transaction {
    String transactionMadeByName;
    String transactionReceivedByName;
    int transactionSum;


    public Transaction(String transactionMadeByName, int transactionSum, String transactionReceivedByName) {
        this.transactionMadeByName = transactionMadeByName;
        this.transactionSum = transactionSum;
        this.transactionReceivedByName = transactionReceivedByName;
    }

    public String getTransactionMadeByName() {
        return transactionMadeByName;
    }

    public void setTransactionMadeByName(String transactionMadeByName) {
        this.transactionMadeByName = transactionMadeByName;
    }

    public String getTransactionReceivedByName() {
        return transactionReceivedByName;
    }

    public void setTransactionReceivedByName(String transactionReceivedByName) {
        this.transactionReceivedByName = transactionReceivedByName;
    }

    public int getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(int transactionSum) {
        this.transactionSum = transactionSum;
    }

}
