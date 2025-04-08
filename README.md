# 🧙‍♂️ Magic Bank System

Bem-vindo ao sistema bancário mágico desenvolvido em **Java 23** com JDBC!  
Esse sistema simula um banco em um universo de magia, permitindo a criação de contas mágicas, artefatos encantados, contratos de proteção e transações mágicas.

---

## 🚀 Funcionalidades

- Criar e listar contas mágicas
- Criar e listar artefatos mágicos
- Criar e listar contratos de proteção
- Realizar e listar transações financeiras mágicas
- Validações de entrada com repetição em caso de erro

---

## 📋 Menu do Sistema

```text
======= MAGIC BANK SYSTEM =======
1 - Criar Conta Mágica
2 - Listar Contas Mágicas
3 - Criar Artefato
4 - Listar Artefatos
5 - Criar Contrato de Proteção
6 - Listar Contratos de Proteção
7 - Transferência de Dinheiro
8 - Listar Transações
0 - Sair
```

---

## 🧾 Estrutura de Classes

### 🔮 MagicAccount
Representa uma conta mágica vinculada a um titular.

**Atributos:**
- `id` (Long)
- `titularName` (String)
- `race` (String)
- `statusAccount` (String)
- `magicLevel` (int)
- `money` (double)

---

### 🗡️ Artefact
Representa um artefato mágico associado a uma conta.

**Atributos:**
- `id` (Long)
- `name` (String)
- `type` (String)
- `rarity` (String)
- `magicPower` (String)
- `idOwnerAccount` (MagicAccount)

---

### 🛡️ ProtectionContract
Contrato que protege uma conta mágica.

**Atributos:**
- `id` (Long)
- `description` (String)
- `securityLevel` (String)
- `idMagicAccount` (MagicAccount)

---

### 💸 TransactionMoney
Representa uma transferência de dinheiro entre duas contas.

**Atributos:**
- `id` (Long)
- `quantityMoney` (double)
- `date` (String)
- `transactionType` (String)
- `originAccount` (MagicAccount)
- `destinationAccount` (MagicAccount)

---

## ✅ Validações

Todas as entradas do usuário são validadas:
- Se for esperado um número (`int`, `double`, `long`), o sistema só aceita se o usuário digitar corretamente.
- Em caso de erro, o usuário é orientado a digitar novamente.
- Nenhum dado inválido é salvo ou utilizado.

---

## 💡 Exemplo de Fluxo

```text
1 - Criar Conta Mágica
Nome do Titular: Gandalf
Raça: Maia
Status da Conta: Ativa
Nível Mágico: 100
Saldo Inicial: 5000.00
-> Conta criada com sucesso!
```

---

## 📦 Tecnologias Utilizadas

- Java 23
- JDBC
- MySQL (estrutura de banco não incluída aqui)
- IntelliJ IDEA / Eclipse (qualquer IDE compatível)

---

## 👨‍💻 Autor

Desenvolvido por Saymon — projeto criativo de simulação bancária mágica usando Java puro e JDBC.

---
