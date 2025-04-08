package model;

public class MagicAccount {

    private long id;
    private String titularName;
    private String race;
    private String statusAccount;
    private int magicLevel;
    private double money;

    public MagicAccount(long id, String titularName, String race, String statusAccount, int magicLevel, double money) {
        this.id = id;
        this.titularName = titularName;
        this.race = race;
        this.statusAccount = statusAccount;
        this.magicLevel = magicLevel;
        this.money = money;
    }

    public MagicAccount(String titularName, String race, String statusAccount, int magicLevel, double money) {
        this.titularName = titularName;
        this.race = race;
        this.statusAccount = statusAccount;
        this.magicLevel = magicLevel;
        this.money = money;
    }

    public MagicAccount() {
    }


    public long getId() {
        return id;
    }

    public String getTitularName() {
        return titularName;
    }

    public String getRace() {
        return race;
    }

    public String getStatusAccount() {
        return statusAccount;
    }

    public int getMagicLevel() {
        return magicLevel;
    }

    public double getMoney() {
        return money;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitularName(String titularName) {
        this.titularName = titularName;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setStatusAccount(String statusAccount) {
        this.statusAccount = statusAccount;
    }

    public void setMagicLevel(int magicLevel) {
        this.magicLevel = magicLevel;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String toString() {
        return "\nID da conta mágica: " + getId() +
               "\nNome do titular: " + getTitularName() +
               "\nRaça do titular: " + getRace() +
               "\nStatus da conta de " + getTitularName() + " : " + getStatusAccount() +
               "\nNivel da conta mágica: " + getMagicLevel() +
               "\nSaldo da conta: " + getMoney() + "R$";
    }
}
