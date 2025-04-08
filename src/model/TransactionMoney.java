package model;

public class TransactionMoney {

    private long id;
    private Double quantityMoney;
    private String date;
    private String transactionType;

    private MagicAccount originAccount;
    private MagicAccount destinationAccount;

    public TransactionMoney(long id, Double quantityMoney, String date, String transactionType, MagicAccount originAccount, MagicAccount destinationAccount) {
        this.id = id;
        this.quantityMoney = quantityMoney;
        this.date = date;
        this.transactionType = transactionType;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }

    public TransactionMoney(Double quantityMoney, String date, String transactionType, MagicAccount originAccount, MagicAccount destinationAccount) {
        this.quantityMoney = quantityMoney;
        this.date = date;
        this.transactionType = transactionType;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }

    public TransactionMoney() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getQuantityMoney() {
        return quantityMoney;
    }

    public void setQuantityMoney(Double quantityMoney) {
        this.quantityMoney = quantityMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public MagicAccount getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(MagicAccount originAccount) {
        this.originAccount = originAccount;
    }

    public MagicAccount getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(MagicAccount destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    @Override
    public String toString() {
        return "\n--- Transação Mágica ---" +
                "\nID da transação: " + getId() +
                "\nQuantidade de dinheiro: " + getQuantityMoney() + " R$" +
                "\nData da transação: " + getDate() +
                "\nTipo de transação: " + getTransactionType() +
                "\nID da conta de origem: " +
                (getOriginAccount() != null ? getOriginAccount().getId() : "Conta não informada") +
                "\nID da conta de destino: " +
                (getDestinationAccount() != null ? getDestinationAccount().getId() : "Conta não informada");
    }

}
