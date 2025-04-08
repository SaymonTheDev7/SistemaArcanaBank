package model;

public class ProtectionContract {

    private long id;
    private String description;
    private String securityLevel;
    private MagicAccount idMagicAccount;

    public ProtectionContract(long id, String description, String securityLevel, MagicAccount idMagicAccount) {
        this.id = id;
        this.description = description;
        this.securityLevel = securityLevel;
        this.idMagicAccount = idMagicAccount;
    }

    public ProtectionContract(String description, String securityLevel, MagicAccount idMagicAccount) {
        this.description = description;
        this.securityLevel = securityLevel;
        this.idMagicAccount = idMagicAccount;
    }

    public ProtectionContract() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public MagicAccount getIdMagicAccount() {
        return idMagicAccount;
    }

    public void setIdMagicAccount(MagicAccount idMagicAccount) {
        this.idMagicAccount = idMagicAccount;
    }

    @Override
    public String toString() {
        return "\n--- Contrato de Proteção ---" +
                "\nID do contrato: " + getId() +
                "\nDescrição do contrato: " + getDescription() +
                "\nNível de segurança: " + getSecurityLevel() +
                "\nID da conta mágica vinculada: " +
                (getIdMagicAccount() != null ? getIdMagicAccount().getId() : "Nenhuma conta vinculada");
    }

}
