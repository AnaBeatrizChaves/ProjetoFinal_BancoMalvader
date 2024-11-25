package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.FuncionarioController;
import model.Funcionario;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaCadastroFuncionario extends JFrame {
    private JLabel erroLabel;

    public TelaCadastroFuncionario() {
        setTitle("Cadastro de Funcionários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Fundo claro para estética
        add(panel);

        JLabel tituloLabel = new JLabel("Cadastro de Funcionários", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setBounds(0, 10, 800, 30);
        panel.add(tituloLabel);

        int startX = 120;
        int labelWidth = 100;
        int textWidth = 350;
        int height = 25;
        int gap = 10;
        int currentY = 60;

        // Campos de entrada
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(nomeLabel);

        JTextField nomeText = new JTextField(20);
        nomeText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(nomeText);

        currentY += height + gap;
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(cpfLabel);

        JTextField cpfText = new JTextField(20);
        cpfText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(cpfText);

        currentY += height + gap;
        JLabel dataNascimentoLabel = new JLabel("Nascimento:");
        dataNascimentoLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(dataNascimentoLabel);

        JTextField dataNascimentoText = new JTextField(10);
        dataNascimentoText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(dataNascimentoText);

        currentY += height + gap;
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(telefoneLabel);

        JTextField telefoneText = new JTextField(20);
        telefoneText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(telefoneText);

        currentY += height + gap;
        JLabel codigoFuncionarioLabel = new JLabel("Código:");
        codigoFuncionarioLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(codigoFuncionarioLabel);

        JTextField codigoFuncionarioText = new JTextField(20);
        codigoFuncionarioText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(codigoFuncionarioText);

        currentY += height + gap;
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(cargoLabel);

        JTextField cargoText = new JTextField(20);
        cargoText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(cargoText);

        currentY += height + gap;
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(senhaLabel);

        JPasswordField senhaText = new JPasswordField(20);
        senhaText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(senhaText);

        currentY += height + gap * 2;

        // Botões "Cadastrar" e "Voltar" lado a lado
        int buttonWidth = 150;
        int buttonHeight = 50;
        int buttonGap = 20;

        RoundedButton cadastrarButton = new RoundedButton("Cadastrar");
        cadastrarButton.setBounds((800 - buttonWidth * 2 - buttonGap) / 2, currentY, buttonWidth, buttonHeight);
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setBackground(new Color(166, 166, 166));
        panel.add(cadastrarButton);

        RoundedButton voltarButton = new RoundedButton("Voltar");
        voltarButton.setBounds((800 - buttonWidth * 2 - buttonGap) / 2 + buttonWidth + buttonGap, currentY, buttonWidth, buttonHeight);
        voltarButton.setFont(new Font("Arial", Font.BOLD, 16));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setBackground(new Color(150, 150, 150)); // Cinza para neutralidade
        panel.add(voltarButton);

        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setBounds(0, currentY + 60, 800, height);
        panel.add(erroLabel);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroLabel.setText("");
                try {
                    if (nomeText.getText().isEmpty() || cpfText.getText().isEmpty() || dataNascimentoText.getText().isEmpty() ||
                            telefoneText.getText().isEmpty() || codigoFuncionarioText.getText().isEmpty() ||
                            cargoText.getText().isEmpty() || new String(senhaText.getPassword()).isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    String nome = nomeText.getText();
                    String cpf = cpfText.getText();
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoText.getText());
                    String telefone = telefoneText.getText();
                    String codigoFuncionario = codigoFuncionarioText.getText();
                    String cargo = cargoText.getText();
                    String senha = new String(senhaText.getPassword());

                    FuncionarioController funcionarioController = new FuncionarioController();
                    funcionarioController.criarFuncionario(nome, cpf, dataNascimento, telefone, codigoFuncionario, cargo, senha, null, null, 0, null, null, null);

                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Número inválido em campo(s) numérico(s).");
                } catch (DateTimeParseException ex) {
                    erroLabel.setText("Data de nascimento em formato inválido. Use AAAA-MM-DD.");
                }
            }
        });

        voltarButton.addActionListener(e -> {
            dispose(); // Fecha a tela atual
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastroFuncionario frame = new TelaCadastroFuncionario();
            frame.setVisible(true);
        });
    }

    // Classe para botões arredondados
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
}