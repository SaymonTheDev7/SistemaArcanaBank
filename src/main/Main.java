package main;

import model.*;
import service.*;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n======= MAGIC BANK SYSTEM =======");
            System.out.println("1 - Criar Conta Mágica");
            System.out.println("2 - Listar Contas Mágicas");
            System.out.println("3 - Criar Artefato");
            System.out.println("4 - Listar Artefatos");
            System.out.println("5 - Criar Contrato de Proteção");
            System.out.println("6 - Listar Contratos de Proteção");
            System.out.println("7 - Transferência de Dinheiro");
            System.out.println("8 - Listar Transações");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            option = readInt(sc);

            switch (option) {
                case 1 -> {
                    MagicAccount account = new MagicAccount();
                    System.out.print("Nome do Titular: ");
                    account.setTitularName(sc.nextLine());

                    System.out.print("Raça: ");
                    account.setRace(sc.nextLine());

                    System.out.print("Status da Conta: ");
                    account.setStatusAccount(sc.nextLine());

                    System.out.print("Nível Mágico: ");
                    account.setMagicLevel(readInt(sc));

                    System.out.print("Saldo Inicial: ");
                    account.setMoney(readDouble(sc));

                    try {
                        MagicAccountService.saveMagicAccount(account);
                        System.out.println("Conta criada com sucesso!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 2 -> {
                    try {
                        List<MagicAccount> accounts = MagicAccountService.getAllMagicAccounts();
                        for (MagicAccount acc : accounts) {
                            System.out.println(acc);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    Artefact artefact = new Artefact();
                    System.out.print("Nome do Artefato: ");
                    artefact.setName(sc.nextLine());

                    System.out.print("Tipo: ");
                    artefact.setType(sc.nextLine());

                    System.out.print("Raridade: ");
                    artefact.setRarity(sc.nextLine());

                    System.out.print("Poder Mágico: ");
                    artefact.setMagicPower(sc.nextLine());

                    System.out.print("ID da Conta Mágica Dono: ");
                    MagicAccount owner = new MagicAccount();
                    owner.setId(readLong(sc));
                    artefact.setIdOwnerAccount(owner);

                    try {
                        ArtefactService.saveArtefact(artefact);
                        System.out.println("Artefato salvo com sucesso!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 4 -> {
                    try {
                        List<Artefact> artefacts = ArtefactService.getAllArtefacts();
                        for (Artefact a : artefacts) {
                            System.out.println(a);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 5 -> {
                    ProtectionContract contract = new ProtectionContract();
                    System.out.print("Descrição do Contrato: ");
                    contract.setDescription(sc.nextLine());

                    System.out.print("Nível de Segurança: ");
                    contract.setSecurityLevel(sc.nextLine());

                    System.out.print("ID da Conta Mágica: ");
                    MagicAccount acc = new MagicAccount();
                    acc.setId(readLong(sc));
                    contract.setIdMagicAccount(acc);

                    try {
                        ProtectionContractService.saveProtectionContract(contract);
                        System.out.println("Contrato criado!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 6 -> {
                    try {
                        List<ProtectionContract> contracts = ProtectionContractService.getAllProtectionContracts();
                        for (ProtectionContract c : contracts) {
                            System.out.println(c);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 7 -> {
                    TransactionMoney tx = new TransactionMoney();
                    System.out.print("Valor da transferência: ");
                    tx.setQuantityMoney(readDouble(sc));

                    System.out.print("Data (YYYY-MM-DD): ");
                    tx.setDate(sc.nextLine());

                    System.out.print("Tipo de transação (PIX, TED, MAGIA): ");
                    tx.setTransactionType(sc.nextLine());

                    System.out.print("ID da conta de origem: ");
                    MagicAccount origin = new MagicAccount();
                    origin.setId(readLong(sc));
                    tx.setOriginAccount(origin);

                    System.out.print("ID da conta de destino: ");
                    MagicAccount dest = new MagicAccount();
                    dest.setId(readLong(sc));
                    tx.setDestinationAccount(dest);

                    try {
                        TransactionMoneyService.saveTransactionMoney(tx);
                        System.out.println("Transferência registrada!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 8 -> {
                    try {
                        List<TransactionMoney> transactions = TransactionMoneyService.getAllTransactionMoneys();
                        for (TransactionMoney tx : transactions) {
                            System.out.println(tx);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 0);

        sc.close();
    }

    private static int readInt(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número inteiro: ");
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número decimal (use ponto ou vírgula): ");
            }
        }
    }

    private static long readLong(Scanner sc) {
        while (true) {
            try {
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número inteiro longo: ");
            }
        }
    }
}
