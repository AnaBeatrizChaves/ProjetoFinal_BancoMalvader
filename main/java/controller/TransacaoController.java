package controller;

import dao.ContaDAO;
import dao.TransacaoDAO;
import model.Conta;
import model.Transacao;

import java.util.ArrayList;

public class TransacaoController {
    public void depositoController(String numeroConta, double valor){
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numeroConta);

        TransacaoDAO transacaoDAO = new TransacaoDAO();
        transacaoDAO.depositoDAO(valor, idConta);
    }

    public boolean saque(String numeroContaDestino, double valor){
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(numeroContaDestino);

        TransacaoDAO transacaoDAO = new TransacaoDAO();
        return  transacaoDAO.saqueDAO(valor, idConta);

    }

    public ArrayList<Transacao> transacao(Conta conta){
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(conta.getNumeroConta());

        return transacaoDAO.extratoDAO(idConta);
    }

    public static void main(String[] args) {
        TransacaoController transacaoController = new TransacaoController();
        transacaoController.depositoController("numero legal", 1000);
    }
}
