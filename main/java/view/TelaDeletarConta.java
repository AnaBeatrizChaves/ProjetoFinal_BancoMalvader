package view;

import controller.RemoverContaController;
import model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaDeletarConta extends JFrame {

    public TelaDeletarConta(ArrayList<Conta> contas) {

        setTitle("Banco Malvader - Deletar Conta");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();

        // Cria uma aba para cada conta
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            // Painel da aba da conta
            JPanel contaPanel = new JPanel();
            contaPanel.setLayout(new BorderLayout());
            contaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JPanel centralPanel = new JPanel();
            centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
            centralPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Título com o nome do cliente
            JLabel clienteLabel = new JLabel("Cliente: " + conta.getCliente().getNome());
            clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
            clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Adicionar o título ao painel central
            centralPanel.add(clienteLabel);
            centralPanel.add(Box.createVerticalStrut(15));  // Espaço entre o título e as informações

            // Exibir informações da conta
            JLabel agenciaLabel = new JLabel("Agência: " + conta.getAgencia());
            JLabel numeroContaLabel = new JLabel("Número da Conta: " + conta.getNumeroConta());
            JLabel senhaLabel = new JLabel("Senha: " + conta.getCliente().getSenha().replaceAll(".", "*"));

            agenciaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            numeroContaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            senhaLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            agenciaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            numeroContaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            centralPanel.add(agenciaLabel);
            centralPanel.add(Box.createVerticalStrut(10));
            centralPanel.add(numeroContaLabel);
            centralPanel.add(Box.createVerticalStrut(10));
            centralPanel.add(senhaLabel);
            centralPanel.add(Box.createVerticalStrut(10));

            // Botão de deletar
            JButton deletarButton = new JButton("Deletar Conta");
            deletarButton.setBackground(new Color(166, 166, 166));
            deletarButton.setForeground(Color.WHITE);
            deletarButton.setFocusPainted(false);
            deletarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            deletarButton.addActionListener(e -> {
                RemoverContaController removerContaController = new RemoverContaController();
                removerContaController.removerContaController(conta);

                JOptionPane.showMessageDialog(
                        TelaDeletarConta.this,
                        "Conta deletada com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
            });

            centralPanel.add(deletarButton);
            contaPanel.add(centralPanel, BorderLayout.CENTER);
            tabbedPane.addTab("Conta " + (i + 1), contaPanel);
        }

        add(tabbedPane, BorderLayout.CENTER);

        // Botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(new Color(120, 120, 120));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFocusPainted(false);
        voltarButton.addActionListener(e -> dispose()); // Fecha a janela atual

        // Painel para o botão "Voltar"
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(voltarButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
