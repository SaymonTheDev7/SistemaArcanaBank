package service;

import connection.BankConnection;
import model.MagicAccount;
import model.ProtectionContract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProtectionContractService {

    public static ProtectionContract saveProtectionContract (ProtectionContract protectionContract) throws SQLException {
        String sql = "INSERT INTO protection_contract (description, security_level, id_magic_account) VALUES (?, ?, ?)";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, protectionContract.getDescription());
            ps.setString(2, protectionContract.getSecurityLevel());
            ps.setLong(3, protectionContract.getIdMagicAccount().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    protectionContract.setId(rs.getLong(1));
                }
            }
        }
        return protectionContract;
    }

    public static ProtectionContract updateProtectionContract(ProtectionContract protectionContract) throws SQLException {
        String sql = "UPDATE protection_contract SET description = ?, security_level = ?, id_magic_account = ? WHERE id = ?";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, protectionContract.getDescription());
            ps.setString(2, protectionContract.getSecurityLevel());
            ps.setLong(3, protectionContract.getIdMagicAccount().getId());
            ps.setLong(4, protectionContract.getId());
            ps.executeUpdate();
        }
        return protectionContract;
    }

    public static void deleteProtectionContract (long id) throws SQLException {
        String sql = "DELETE FROM protection_contract WHERE id = ? ";
        try (Connection connection = BankConnection.getConnections()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public static ProtectionContract searchProtectionContractPerId(long id) throws SQLException {
        String sql = "SELECT * FROM protection_contract WHERE id = ?";
        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProtectionContract protectionContract = new ProtectionContract();
                    protectionContract.setId(rs.getLong("id"));
                    protectionContract.setDescription(rs.getString("description"));
                    protectionContract.setSecurityLevel(rs.getString("security_level"));
                    MagicAccount magicAccount = new MagicAccount();
                    magicAccount.setId(rs.getLong("id_magic_account"));
                    protectionContract.setIdMagicAccount(magicAccount);
                    return protectionContract;
                }
            }
        }
        return null;
    }

    public static List<ProtectionContract> getAllProtectionContracts() throws SQLException {
        String sql = "SELECT * FROM protection_contract";
        List<ProtectionContract> protectionContracts = new ArrayList<>();

        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProtectionContract protectionContract = new ProtectionContract();
                protectionContract.setId(rs.getLong("id"));
                protectionContract.setDescription(rs.getString("description"));
                protectionContract.setSecurityLevel(rs.getString("security_level"));
                MagicAccount magicAccount = new MagicAccount();
                magicAccount.setId(rs.getLong("id_magic_account"));
                protectionContract.setIdMagicAccount(magicAccount);
                protectionContracts.add(protectionContract);
            }
        }

        return protectionContracts;
    }
}
