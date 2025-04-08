package service;

import connection.BankConnection;
import model.Artefact;
import model.MagicAccount;
import model.ProtectionContract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtefactService {

    public static Artefact saveArtefact (Artefact artefact) throws SQLException {
        String sql = "INSERT INTO artefact (name, type, rarity, magic_power, id_magic_account) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, artefact.getName());
            ps.setString(2, artefact.getType());
            ps.setString(3, artefact.getRarity());
            ps.setString(4, artefact.getMagicPower());
            ps.setLong(5, artefact.getIdOwnerAccount().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    artefact.setId(rs.getLong(1));
                }
            }
        }
        return artefact;
    }

    public static Artefact updateArtefact(Artefact artefact) throws SQLException {
        String sql = "UPDATE artefact SET name = ?, type = ?, rarity = ?, magic_power = ?, id_magic_account = ? WHERE id = ?";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, artefact.getName());
            ps.setString(2, artefact.getType());
            ps.setString(3, artefact.getRarity());
            ps.setString(4, artefact.getMagicPower());
            ps.setLong(5, artefact.getIdOwnerAccount().getId());
            ps.setLong(6, artefact.getId());
            ps.executeUpdate();
        }
        return artefact;
    }

    public static void deleteArtefact (long id) throws SQLException {
        String sql = "DELETE FROM artefact WHERE id = ? ";
        try (Connection connection = BankConnection.getConnections()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public static Artefact searchArtefactPerId(long id) throws SQLException {
        String sql = "SELECT * FROM artefact WHERE id = ?";
        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Artefact artefact = new Artefact();
                    artefact.setId(rs.getLong("id"));
                    artefact.setName(rs.getString("name"));
                    artefact.setType(rs.getString("type"));
                    artefact.setRarity(rs.getString("rarity"));
                    artefact.setMagicPower(rs.getString("magic_power"));
                    MagicAccount magicAccount = new MagicAccount();
                    magicAccount.setId(rs.getLong("id_magic_account"));
                    artefact.setIdOwnerAccount(magicAccount);
                    return artefact;
                }
            }
        }
        return null;
    }

    public static List<Artefact> getAllArtefacts() throws SQLException {
        String sql = "SELECT * FROM artefact";
        List<Artefact> artefacts = new ArrayList<>();

        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Artefact artefact = new Artefact();
                artefact.setId(rs.getLong("id"));
                artefact.setName(rs.getString("name"));
                artefact.setType(rs.getString("type"));
                artefact.setRarity(rs.getString("rarity"));
                artefact.setMagicPower(rs.getString("magic_power"));
                MagicAccount magicAccount = new MagicAccount();
                magicAccount.setId(rs.getLong("id_magic_account"));
                artefact.setIdOwnerAccount(magicAccount);
                artefacts.add(artefact);
            }
        }

        return artefacts;
    }
}
