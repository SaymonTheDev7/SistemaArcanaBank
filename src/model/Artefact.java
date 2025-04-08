package model;

public class Artefact {

    private long id;
    private String name;
    private String type;
    private String rarity;
    private String magicPower;
    private MagicAccount idOwnerAccount;

    public Artefact(long id, String name, String type, String rarity, String magicPower, MagicAccount idOwnerAccount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.magicPower = magicPower;
        this.idOwnerAccount = idOwnerAccount;
    }

    public Artefact(String name, String type, String rarity, String magicPower, MagicAccount idOwnerAccount) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.magicPower = magicPower;
        this.idOwnerAccount = idOwnerAccount;
    }

    public Artefact() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(String magicPower) {
        this.magicPower = magicPower;
    }

    public MagicAccount getIdOwnerAccount() {
        return idOwnerAccount;
    }

    public void setIdOwnerAccount(MagicAccount idOwnerAccount) {
        this.idOwnerAccount = idOwnerAccount;
    }

    @Override
    public String toString() {
        return "\n--- Artefato ---" +
                "\nID do artefato: " + getId() +
                "\nNome do artefato: " + getName() +
                "\nTipo de artefato: " + getType() +
                "\nRaridade do artefato: " + getRarity() +
                "\nPoder do artefato: " + getMagicPower() +
                "\nID da conta mágica dona: " +
                (getIdOwnerAccount() != null ? getIdOwnerAccount().getId() : "Nenhuma conta vinculada");
    }

}
