package view;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class TelaVisualizarConta extends JFrame {

    public TelaVisualizarConta(ArrayList<Conta> contas) {
        setTitle("Banco Malvader - Visualizar Conta");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criar painel de abas
        JTabbedPane tabbedPane = new JTabbedPane();

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            // Painel de cada conta
            JPanel contaPanel = new JPanel();
            contaPanel.setLayout(null); // Layout absoluto para melhor posicionamento dos elementos

            JLabel clienteLabel = new JLabel("Cliente: " + conta.getCliente().getNome(), SwingConstants.CENTER);
            clienteLabel.setFont(new Font("Arial", Font.BOLD, 20));
            clienteLabel.setBounds(20, 20, 740, 30); // Centralizado no painel
            contaPanel.add(clienteLabel);

            JLabel agenciaLabel = new JLabel("Agência:");
            agenciaLabel.setBounds(50, 80, 200, 30);
            contaPanel.add(agenciaLabel);

            JLabel agenciaValor = new JLabel(conta.getAgencia());
            agenciaValor.setBounds(200, 80, 200, 30);
            contaPanel.add(agenciaValor);

            JLabel saldoLabel = new JLabel("Saldo:");
            saldoLabel.setBounds(50, 120, 200, 30);
            contaPanel.add(saldoLabel);

            JLabel saldoValor = new JLabel(String.valueOf(conta.getSaldo()));
            saldoValor.setBounds(200, 120, 200, 30);
            contaPanel.add(saldoValor);

            JLabel numeroContaLabel = new JLabel("Número da Conta:");
            numeroContaLabel.setBounds(50, 160, 200, 30);
            contaPanel.add(numeroContaLabel);

            JLabel numeroContaValor = new JLabel(conta.getNumeroConta());
            numeroContaValor.setBounds(200, 160, 200, 30);
            contaPanel.add(numeroContaValor);

            JLabel senhaLabel = new JLabel("Senha:");
            senhaLabel.setBounds(50, 200, 200, 30);
            contaPanel.add(senhaLabel);

            JLabel senhaValor = new JLabel(conta.getCliente().getSenha().replaceAll(".", "*"));
            senhaValor.setBounds(200, 200, 200, 30);
            contaPanel.add(senhaValor);

            // Painel para os botões "Visualizar Informações" e "Voltar"
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Layout horizontal
            buttonPanel.setBounds(50, 300, 700, 40); // Posicionando no painel

            // Botão para visualizar detalhes adicionais
            JButton visualizarDetalhesButton = new JButton("Visualizar Informações");
            visualizarDetalhesButton.setBackground(new Color(120, 120, 120)); // Cor de fundo
            visualizarDetalhesButton.setForeground(Color.WHITE); // Cor do texto
            visualizarDetalhesButton.setFocusPainted(false); // Remove a borda quando o botão é clicado
            visualizarDetalhesButton.addActionListener(e -> mostrarDetalhesConta(conta));

            // Botão "Voltar"
            JButton voltarButton = new JButton("Voltar");
            voltarButton.setBackground(new Color(120, 120, 120)); // Cor de fundo
            voltarButton.setForeground(Color.WHITE); // Cor do texto
            voltarButton.setFocusPainted(false); // Remove a borda quando o botão é clicado
            voltarButton.addActionListener(e -> dispose()); // Fecha a janela atual

            buttonPanel.add(visualizarDetalhesButton);
            buttonPanel.add(voltarButton);
            contaPanel.add(buttonPanel);
            tabbedPane.addTab("Conta " + (i + 1), contaPanel);
        }

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void mostrarDetalhesConta(Conta conta) {
        String tipo;
        String detalhesAdicionais;

        if (conta instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) conta;
            tipo = "Poupança";
            detalhesAdicionais = "Taxa de rendimento: " + contaPoupanca.getTaxaDeRendimento();
        } else {
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            tipo = "Corrente";
            detalhesAdicionais = "Vencimento: " + contaCorrente.getDataVencimento();
        }

        JOptionPane.showMessageDialog(
                this,
                "Agência: " + conta.getAgencia() + "\n" +
                        "Número da Conta: " + conta.getNumeroConta() + "\n" +
                        "Saldo: " + conta.getSaldo() + "\n" +
                        "Tipo: " + tipo + "\n" +
                        detalhesAdicionais + "\n" +
                        "Senha: " + conta.getCliente().getSenha().replaceAll(".", "*"),
                "Informações da Conta",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
