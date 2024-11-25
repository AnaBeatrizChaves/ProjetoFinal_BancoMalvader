package view;

import dao.TransacaoDAO;
import model.Transacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaExtrato extends JFrame {

    public TelaExtrato(ArrayList<Transacao> transacoes, double saldo) {
        setTitle("Banco Malvader - Extrato de Transações");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();

        for (int i = 0; i < transacoes.size(); i++) {
            Transacao transacao = transacoes.get(i);

            JPanel transacaoPanel = new JPanel();
            transacaoPanel.setLayout(new BorderLayout());
            transacaoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Painel para os detalhes da transação alinhados à esquerda
            JPanel detalhesPanel = new JPanel();
            detalhesPanel.setLayout(new BoxLayout(detalhesPanel, BoxLayout.Y_AXIS));

            JLabel idTransacaoLabel = new JLabel("ID: " + transacao.getIdTransacao());
            JLabel tipoTransacaoLabel = new JLabel("Tipo: " + transacao.getTipoTransacao());
            JLabel valorLabel = new JLabel("Valor: R$ " + String.format("%.2f", transacao.getValor()));
            JLabel dataLabel = new JLabel("Data: " + transacao.getData().toString());

            idTransacaoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            tipoTransacaoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            valorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            dataLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            detalhesPanel.add(idTransacaoLabel);
            detalhesPanel.add(Box.createVerticalStrut(10));
            detalhesPanel.add(tipoTransacaoLabel);
            detalhesPanel.add(Box.createVerticalStrut(10));
            detalhesPanel.add(valorLabel);
            detalhesPanel.add(Box.createVerticalStrut(10));
            detalhesPanel.add(dataLabel);

            JButton visualizarButton = new JButton("Visualizar Detalhes");
            visualizarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            visualizarButton.addActionListener(e -> JOptionPane.showMessageDialog(
                    TelaExtrato.this,
                    "ID: " + transacao.getIdTransacao() + "\n" +
                            "Tipo: " + transacao.getTipoTransacao() + "\n" +
                            "Valor: R$ " + String.format("%.2f", transacao.getValor()) + "\n" +
                            "Data: " + transacao.getData(),
                    "Detalhes da Transação",
                    JOptionPane.INFORMATION_MESSAGE
            ));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.add(Box.createVerticalGlue());
            buttonPanel.add(visualizarButton);
            buttonPanel.add(Box.createVerticalGlue());
            transacaoPanel.add(detalhesPanel, BorderLayout.CENTER);
            transacaoPanel.add(buttonPanel, BorderLayout.SOUTH);
            tabbedPane.addTab("Transação " + (i + 1), transacaoPanel);
        }

        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Exemplo de inicialização
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ArrayList<Transacao> transacoes = transacaoDAO.extratoDAO(19);

        SwingUtilities.invokeLater(() -> {
            TelaExtrato telaExtrato = new TelaExtrato(transacoes, 3700);
            telaExtrato.setVisible(true);
        });
    }
}