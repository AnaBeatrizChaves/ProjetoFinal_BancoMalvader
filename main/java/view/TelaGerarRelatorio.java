package view;

import controller.RelatorioController;
import dao.RelatorioDAO;
import model.Conta;
import dao.ClienteDAO;
import dao.ContaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TelaGerarRelatorio extends JFrame {

    public TelaGerarRelatorio(ArrayList<Conta> contas, String nomeCliente) {
        setTitle("Banco Malvader - Gerar Relatório");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            JPanel contaPanel = new JPanel();
            contaPanel.setLayout(new BoxLayout(contaPanel, BoxLayout.Y_AXIS));
            contaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Detalhes da conta
            JLabel agenciaLabel = new JLabel("Agência: " + conta.getAgencia());
            JLabel numeroContaLabel = new JLabel("Número da Conta: " + conta.getNumeroConta());
            JLabel saldoLabel = new JLabel("Saldo: R$ " + String.format("%.2f", conta.getSaldo()));

            agenciaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            numeroContaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            saldoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            agenciaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            numeroContaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            saldoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            contaPanel.add(agenciaLabel);
            contaPanel.add(Box.createVerticalStrut(10));
            contaPanel.add(numeroContaLabel);
            contaPanel.add(Box.createVerticalStrut(10));
            contaPanel.add(saldoLabel);
            contaPanel.add(Box.createVerticalStrut(20));

            // Botão para gerar relatório
            JButton gerarRelatorioButton = new JButton("Gerar Relatório");
            gerarRelatorioButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            gerarRelatorioButton.addActionListener(e -> new TelaEscolherDiretorio(conta, nomeCliente));

            // Painel para centralizar o botão
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.add(Box.createVerticalGlue());
            buttonPanel.add(gerarRelatorioButton);
            buttonPanel.add(Box.createVerticalGlue());

            contaPanel.add(buttonPanel);
            tabbedPane.addTab("Conta " + (i + 1), contaPanel);
        }

        add(tabbedPane);
        setVisible(true);
    }

    // Classe interna para escolher diretório e gerar relatório
    private class TelaEscolherDiretorio extends JFrame {
        public TelaEscolherDiretorio(Conta conta, String nomeCliente) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                String caminho = selectedDirectory.getAbsolutePath();

                RelatorioController relatorioController = new RelatorioController();
                relatorioController.relatorioController(caminho, conta.getNumeroConta(), conta.getSaldo(), nomeCliente);

                JOptionPane.showMessageDialog(null, "Relatório criado em: " + caminho);
            }
        }
    }

    public static void main(String[] args) {
        // Exemplo de inicialização
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(new ClienteDAO().getClasseCliente("hugo12"));

        SwingUtilities.invokeLater(() -> {
            TelaGerarRelatorio tela = new TelaGerarRelatorio(contas, "hugo12");
            tela.setVisible(true);
        });
    }
}