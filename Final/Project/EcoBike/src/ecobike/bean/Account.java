package ecobike.bean;


public class Account {

    private int AID;
    private int UID;
    private String cardNumber;
    private int balance;
    private String issuingBank;
    private String expirationDate;

    public Account(int AID, int UID, String cardNumber, int balance, String issuingBank, String expirationDate) {
        this.AID = AID;
        this.UID = UID;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.issuingBank = issuingBank;
        this.expirationDate = expirationDate;
    }

    public Account() {

    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
