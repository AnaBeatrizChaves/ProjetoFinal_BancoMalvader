package view;

import controller.ContaCorrenteController;
import dao.ClienteDAO;
import dao.ContaDAO;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class TelaEditarConta extends JFrame {

    public TelaEditarConta(ArrayList<Conta> contas) {
        setTitle("Banco Malvader - Editar Conta");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();

        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);

            // Painel de edição da conta
            JPanel contaPanel = criarPainelEdicaoConta(conta);
            String tituloAba = "Cliente: " + conta.getCliente().getNome() + " (Conta " + (i + 1) + ")";
            tabbedPane.addTab(tituloAba, contaPanel);
        }

        add(tabbedPane);

        setVisible(true);
    }

    /**
     * Cria um painel de edição para a conta especificada.
     *
     * @param conta A conta a ser editada.
     * @return O painel de edição.
     */
    private JPanel criarPainelEdicaoConta(Conta conta) {
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout absoluto para melhor controle dos componentes

        JLabel numeroContaLabel = new JLabel("Número da Conta:");
        numeroContaLabel.setBounds(10, 20, 150, 25);
        panel.add(numeroContaLabel);

        JTextField numeroContaText = new JTextField(conta.getNumeroConta());
        numeroContaText.setBounds(180, 20, 200, 25);
        panel.add(numeroContaText);

        JLabel tipoContaLabel = new JLabel("Tipo da Conta:");
        tipoContaLabel.setBounds(10, 60, 150, 25);
        panel.add(tipoContaLabel);

        JTextField tipoContaText = new JTextField(conta instanceof ContaPoupanca ? "Poupança" : "Corrente");
        tipoContaText.setBounds(180, 60, 200, 25);
        panel.add(tipoContaText);

        JTextField vencimentoText = null;
        if (conta instanceof ContaCorrente) {
            JLabel vencimentoLabel = new JLabel("Vencimento:");
            vencimentoLabel.setBounds(10, 100, 150, 25);
            panel.add(vencimentoLabel);

            vencimentoText = new JTextField(((ContaCorrente) conta).getDataVencimento().toString());
            vencimentoText.setBounds(180, 100, 200, 25);
            panel.add(vencimentoText);
        }

        JButton salvarButton = new JButton("Salvar");
        salvarButton.setBounds(180, 150, 120, 25);
        panel.add(salvarButton);

        JTextField finalVencimentoText = vencimentoText;
        salvarButton.addActionListener(e -> {
            String numeroOriginal = conta.getNumeroConta();
            conta.setNumeroConta(numeroContaText.getText());

            if (conta instanceof ContaCorrente && finalVencimentoText != null) {
                ContaCorrente contaCorrente = (ContaCorrente) conta;
                contaCorrente.setDataVencimento(LocalDate.parse(finalVencimentoText.getText()));
                ContaCorrenteController controller = new ContaCorrenteController();
                controller.editarContaController(contaCorrente, tipoContaText.getText().toUpperCase(), numeroOriginal);
            }

            if (conta instanceof ContaPoupanca && tipoContaText.getText().equalsIgnoreCase("CORRENTE")) {
                TelaMudarTipoConta telaMudarTipoConta = new TelaMudarTipoConta("CORRENTE", conta.getNumeroConta());
            } else if (conta instanceof ContaCorrente && tipoContaText.getText().equalsIgnoreCase("POUPANCA")) {
                TelaMudarTipoConta telaMudarTipoConta = new TelaMudarTipoConta("POUPANCA", conta.getNumeroConta());
            } else {
                JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        ArrayList<Conta> contas = contaDAO.getClasConta(new ClienteDAO().getClasseCliente("hugo12"));

        SwingUtilities.invokeLater(() -> new TelaEditarConta(contas));
    }
}
