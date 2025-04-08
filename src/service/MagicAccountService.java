package service;

import connection.BankConnection;
import model.MagicAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagicAccountService {

    public static MagicAccount saveMagicAccount (MagicAccount magicAccount) throws SQLException {
        String sql = "INSERT INTO magic_account (titular_name, race, status_account, magic_level, money) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, magicAccount.getTitularName());
            ps.setString(2, magicAccount.getRace());
            ps.setString(3, magicAccount.getStatusAccount());
            ps.setInt(4, magicAccount.getMagicLevel());
            ps.setDouble(5, magicAccount.getMoney());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    magicAccount.setId(rs.getLong(1));
                }
            }
        }
        return magicAccount;
    }

    public static MagicAccount updateMagicAccount(MagicAccount magicAccount) throws SQLException {
        String sql = "UPDATE magic_account SET titular_name = ?, race = ?, status_account = ?, magic_level = ?, money = ? WHERE id = ?";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, magicAccount.getTitularName());
            ps.setString(2, magicAccount.getRace());
            ps.setString(3, magicAccount.getStatusAccount());
            ps.setInt(4, magicAccount.getMagicLevel());
            ps.setDouble(5, magicAccount.getMoney());
            ps.setLong(6, magicAccount.getId());
            ps.executeUpdate();
        }
        return magicAccount;
    }

    public static void deleteMagicAccount (long id) throws SQLException {
        String sql = "DELETE FROM magic_account WHERE id = ? ";
        try (Connection connection = BankConnection.getConnections()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public static MagicAccount searchMagicAccountPerId(long id) throws SQLException {
        String sql = "SELECT * FROM magic_account WHERE id = ?";
        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MagicAccount magicAccount = new MagicAccount();
                    magicAccount.setId(rs.getLong("id"));
                    magicAccount.setTitularName(rs.getString("titular_name"));
                    magicAccount.setRace(rs.getString("race"));
                    magicAccount.setStatusAccount(rs.getString("status_account"));
                    magicAccount.setMagicLevel(rs.getInt("magic_level"));
                    magicAccount.setMoney(rs.getDouble("money"));
                    return magicAccount;
                }
            }
        }
        return null;
    }

    public static List<MagicAccount> getAllMagicAccounts() throws SQLException {
        String sql = "SELECT * FROM magic_account";
        List<MagicAccount> magicAccounts = new ArrayList<>();

        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MagicAccount magicAccount = new MagicAccount();
                magicAccount.setId(rs.getLong("id"));
                magicAccount.setTitularName(rs.getString("titular_name"));
                magicAccount.setRace(rs.getString("race"));
                magicAccount.setStatusAccount(rs.getString("status_account"));
                magicAccount.setMagicLevel(rs.getInt("magic_level"));
                magicAccount.setMoney(rs.getDouble("money"));
                magicAccounts.add(magicAccount);
            }
        }

        return magicAccounts;
    }
}
