package service;

import connection.BankConnection;
import model.Artefact;
import model.MagicAccount;
import model.TransactionMoney;

import java.lang.management.ManagementFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionMoneyService {

    public static TransactionMoney saveTransactionMoney(TransactionMoney transactionMoney) throws SQLException {
        String sql = "INSERT INTO transaction_money (quantity_money, date, transaction_type, id_magic_account_origin, id_magic_account_destination) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, transactionMoney.getQuantityMoney());
            ps.setString(2, transactionMoney.getDate());
            ps.setString(3, transactionMoney.getTransactionType());
            ps.setLong(4, transactionMoney.getOriginAccount().getId());
            ps.setLong(5, transactionMoney.getDestinationAccount().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    transactionMoney.setId(rs.getLong(1));
                }
            }
        }
        return transactionMoney;
    }

    public static TransactionMoney updateTransactionMoney(TransactionMoney transactionMoney) throws SQLException {
        String sql = "UPDATE transaction_money SET quantity_money = ?, date = ?, transaction_type = ?, id_magic_account_origin = ?, id_magic_account_destination = ? WHERE id = ?";
        try (Connection connection = BankConnection.getConnections()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, transactionMoney.getQuantityMoney());
            ps.setString(2, transactionMoney.getDate());
            ps.setString(3, transactionMoney.getTransactionType());
            ps.setLong(4, transactionMoney.getOriginAccount().getId());
            ps.setLong(5, transactionMoney.getDestinationAccount().getId());
            ps.setLong(6, transactionMoney.getId());
            ps.executeUpdate();
        }
        return transactionMoney;
    }

    public static void deleteTransactionMoney (long id) throws SQLException {
        String sql = "DELETE FROM transaction_money WHERE id = ? ";
        try (Connection connection = BankConnection.getConnections()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public static TransactionMoney searchTransactionMoneyPerId(long id) throws SQLException {
        String sql = "SELECT * FROM transaction_money WHERE id = ?";
        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TransactionMoney transactionMoney = new TransactionMoney();
                    transactionMoney.setId(rs.getLong("id"));
                    transactionMoney.setQuantityMoney(rs.getDouble("quantity_money"));
                    transactionMoney.setDate(rs.getString("date"));
                    transactionMoney.setTransactionType(rs.getString("transaction_type"));
                    MagicAccount magicAccountOrigin = new MagicAccount();
                    magicAccountOrigin.setId(rs.getLong("id_magic_account_origin"));
                    transactionMoney.setOriginAccount(magicAccountOrigin);

                    MagicAccount magicAccountDestination = new MagicAccount();
                    magicAccountDestination.setId(rs.getLong("id_magic_account_destination"));
                    transactionMoney.setDestinationAccount(magicAccountDestination);
                    return transactionMoney;
                }
            }
        }
        return null;
    }

    public static List<TransactionMoney> getAllTransactionMoneys() throws SQLException {
        String sql = "SELECT * FROM transaction_money";
        List<TransactionMoney> transactionMoneys = new ArrayList<>();

        try (Connection connection = BankConnection.getConnections();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TransactionMoney transactionMoney = new TransactionMoney();
                transactionMoney.setId(rs.getLong("id"));
                transactionMoney.setQuantityMoney(rs.getDouble("quantity_money"));
                transactionMoney.setDate(rs.getString("date"));
                transactionMoney.setTransactionType(rs.getString("transaction_type"));
                MagicAccount magicAccountOrigin = new MagicAccount();
                magicAccountOrigin.setId(rs.getLong("id_magic_account_origin"));
                transactionMoney.setOriginAccount(magicAccountOrigin);

                MagicAccount magicAccountDestination = new MagicAccount();
                magicAccountDestination.setId(rs.getLong("id_magic_account_destination"));
                transactionMoney.setDestinationAccount(magicAccountDestination);
                transactionMoneys.add(transactionMoney);
            }
        }

        return transactionMoneys;
    }
    public static boolean transferMoney(TransactionMoney transaction) throws SQLException {
        MagicAccount origin = MagicAccountService.searchMagicAccountPerId(transaction.getOriginAccount().getId());
        MagicAccount destination = MagicAccountService.searchMagicAccountPerId(transaction.getDestinationAccount().getId());

        if (origin == null || destination == null) {
            System.out.println("Conta(s) não encontrada(s).");
            return false;
        }

        if (origin.getMoney() < transaction.getQuantityMoney()) {
            System.out.println("Saldo insuficiente na conta de origem.");
            return false;
        }


        origin.setMoney(origin.getMoney() - transaction.getQuantityMoney());
        destination.setMoney(destination.getMoney() + transaction.getQuantityMoney());


        MagicAccountService.updateMagicAccount(origin);
        MagicAccountService.updateMagicAccount(destination);
        saveTransactionMoney(transaction);

        System.out.println("Transferência realizada com sucesso!");
        return true;
    }


}
