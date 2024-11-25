package view;

import controller.TransacaoController;
import dao.ClienteDAO;
import dao.ContaDAO;
import dao.TransacaoDAO;
import model.Cliente;
import model.Conta;
import model.ContaPoupanca;
import model.Transacao;
import view.TelaExtrato;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TelaMenuCliente extends JFrame {
    private Map<Conta, JLabel> saldoLabelsMap = new HashMap<>();

    public TelaMenuCliente(Cliente cliente) {
        setTitle("Banco Malvader - Visualizar Conta");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            JPanel contaPanel = new JPanel();
            contaPanel.setLayout(new BorderLayout(20, 20));
            contaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Painel das informações da conta
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(5, 2, 10, 10));
            infoPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    "Informações da Conta",
                    TitledBorder.CENTER,
                    TitledBorder.TOP,
                    new Font("Arial", Font.BOLD, 14)));

            infoPanel.add(new JLabel("Cliente:"));
            infoPanel.add(new JLabel(conta.getCliente().getNome()));
            infoPanel.add(new JLabel("Agência:"));
            infoPanel.add(new JLabel(conta.getAgencia()));
            infoPanel.add(new JLabel("Saldo:"));
            JLabel saldoLabel = new JLabel(String.valueOf(conta.getSaldo()));
            saldoLabelsMap.put(conta, saldoLabel);
            infoPanel.add(saldoLabel);
            infoPanel.add(new JLabel("Número da Conta:"));
            infoPanel.add(new JLabel(conta.getNumeroConta()));
            infoPanel.add(new JLabel("Senha:"));
            infoPanel.add(new JLabel(conta.getCliente().getSenha().replaceAll(".", "*")));
            contaPanel.add(infoPanel, BorderLayout.CENTER);

            // Painel lateral com botões
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 linhas, 1 coluna, espaçamento de 10px
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

// Adicionando botões ao painel
            buttonsPanel.add(criarBotao("Extrato", e -> extrato(conta)));
            buttonsPanel.add(criarBotao("Depósito", e -> depositar(conta)));
            buttonsPanel.add(criarBotao("Saque", e -> sacar(conta)));
            buttonsPanel.add(criarBotao("Sair", e -> dispose()));

            contaPanel.add(buttonsPanel, BorderLayout.EAST);
            tabbedPane.addTab("Conta " + (i + 1), contaPanel);
        }

        add(tabbedPane);
        setVisible(true);
    }

    public class RoundedButton extends JButton {
        public RoundedButton(String texto) {
            super(texto);
            setContentAreaFilled(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
            g2.drawString(getText(), x, y);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            g2.dispose();
        }
    }

    private JButton criarBotao(String texto, ActionListener acao) {
        RoundedButton botao = new RoundedButton(texto);
        botao.setPreferredSize(new Dimension(200, 40));
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(166, 166, 166));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addActionListener(acao);
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(140, 140, 140));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(166, 166, 166));
            }
        });

        return botao;
    }

    private void atualizarSaldoLabel(Conta conta) {
        JLabel saldoLabel = saldoLabelsMap.get(conta);
        if (saldoLabel != null) {
            saldoLabel.setText(String.valueOf(conta.getSaldo()));
        }
    }

    private void depositar(Conta conta) {
        String input = JOptionPane.showInputDialog(this, "Insira o valor do depósito:", "Depósito", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                double valor = Double.parseDouble(input);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Erro: O valor deve ser maior que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    TransacaoController transacaoController = new TransacaoController();
                    transacaoController.depositoController(conta.getNumeroConta(), valor);
                    conta.setSaldo(conta.getSaldo() + valor);
                    atualizarSaldoLabel(conta);
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sacar(Conta conta) {
        String input = JOptionPane.showInputDialog(this, "Insira o valor do saque:", "Saque", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                double valor = Double.parseDouble(input);
                if (valor <= 0 || valor > conta.getSaldo()) {
                    JOptionPane.showMessageDialog(this, "Erro: Valor inválido para saque.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    TransacaoController transacaoController = new TransacaoController();
                    transacaoController.saque(conta.getNumeroConta(), valor);
                    conta.setSaldo(conta.getSaldo() - valor);
                    atualizarSaldoLabel(conta);
                    JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro: O valor inserido não é válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void extrato(Conta conta) {
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        ContaDAO contaDAO = new ContaDAO();
        int idConta = contaDAO.getIDConta(conta.getNumeroConta());
        ArrayList<Transacao> transacaos = transacaoDAO.extratoDAO(idConta);
        TelaExtrato telaExtrato = new TelaExtrato(transacaos, conta.getSaldo());
        telaExtrato.setVisible(true);
    }

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.getClasseCliente("hugo12");
        TelaMenuCliente telaMenuCliente = new TelaMenuCliente(cliente);
    }
}
