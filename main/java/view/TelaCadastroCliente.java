package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ClienteController;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaCadastroCliente extends JFrame {
    private JLabel erroLabel;

    public TelaCadastroCliente() {
        setTitle("Cadastro de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Fundo claro
        add(panel);

        JLabel tituloLabel = new JLabel("Cadastro de Clientes", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setBounds(0, 10, 800, 30);
        panel.add(tituloLabel);

        int startX = 120; // Ponto inicial X para alinhar
        int labelWidth = 100;
        int textWidth = 350;
        int height = 25;
        int gap = 10;
        int currentY = 60;

        // Campos de formulário
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
        JLabel cepLabel = new JLabel("CEP:");
        cepLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(cepLabel);

        JTextField cepText = new JTextField(20);
        cepText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(cepText);

        currentY += height + gap;
        JLabel localLabel = new JLabel("Local:");
        localLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(localLabel);

        JTextField localText = new JTextField(20);
        localText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(localText);

        currentY += height + gap;
        JLabel numeroCasaLabel = new JLabel("Número:");
        numeroCasaLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(numeroCasaLabel);

        JTextField numeroCasaText = new JTextField(5);
        numeroCasaText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(numeroCasaText);

        currentY += height + gap;
        JLabel bairroLabel = new JLabel("Bairro:");
        bairroLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(bairroLabel);

        JTextField bairroText = new JTextField(20);
        bairroText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(bairroText);

        currentY += height + gap;
        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(cidadeLabel);

        JTextField cidadeText = new JTextField(20);
        cidadeText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(cidadeText);

        currentY += height + gap;
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(estadoLabel);

        JTextField estadoText = new JTextField(20);
        estadoText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(estadoText);

        currentY += height + gap;
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(startX, currentY, labelWidth, height);
        panel.add(senhaLabel);

        JPasswordField senhaText = new JPasswordField(20);
        senhaText.setBounds(startX + labelWidth, currentY, textWidth, height);
        panel.add(senhaText);

        currentY += height + gap * 2;

        // Botão "Cadastrar"
        RoundedButton cadastrarButton = new RoundedButton("Cadastrar");
        cadastrarButton.setBounds(400 - 170, currentY, 150, 50);
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        cadastrarButton.setForeground(Color.WHITE);
        cadastrarButton.setBackground(new Color(100, 150, 200)); // Azul para destaque
        panel.add(cadastrarButton);

        // Botão "Voltar"
        RoundedButton voltarButton = new RoundedButton("Voltar");
        voltarButton.setBounds(400 + 20, currentY, 150, 50);
        voltarButton.setFont(new Font("Arial", Font.BOLD, 16));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setBackground(new Color(150, 150, 150)); // Cinza para neutralidade
        panel.add(voltarButton);

        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);
        erroLabel.setBounds(0, currentY + 70, 800, height);
        panel.add(erroLabel);

        // Ação do botão "Cadastrar"
        cadastrarButton.addActionListener(e -> {
            erroLabel.setText("");
            try {
                if (nomeText.getText().isEmpty() || cpfText.getText().isEmpty() || dataNascimentoText.getText().isEmpty() ||
                        telefoneText.getText().isEmpty() || cepText.getText().isEmpty() || localText.getText().isEmpty() ||
                        numeroCasaText.getText().isEmpty() || bairroText.getText().isEmpty() || cidadeText.getText().isEmpty() ||
                        estadoText.getText().isEmpty() || new String(senhaText.getPassword()).isEmpty()) {
                    erroLabel.setText("Preencha todos os campos corretamente.");
                    return;
                }

                String nome = nomeText.getText();
                String cpf = cpfText.getText();
                LocalDate dataNascimento = LocalDate.parse(dataNascimentoText.getText());
                String telefone = telefoneText.getText();
                String cep = cepText.getText();
                String local = localText.getText();
                int numero = Integer.parseInt(numeroCasaText.getText());
                String bairro = bairroText.getText();
                String cidade = cidadeText.getText();
                String estado = estadoText.getText();
                String senha = new String(senhaText.getPassword());

                if (estado.length() > 2) {
                    erroLabel.setText("Preencha o estado com sua UF");
                    return;
                }

                ClienteController clienteNovo = new ClienteController();

                clienteNovo.criarCliente(nome, cpf, dataNascimento, telefone, senha,
                        cep, local, numero, bairro, cidade, estado);

                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                dispose();
            } catch (NumberFormatException ex) {
                erroLabel.setText("Número inválido em campo(s) numérico(s).");
            } catch (DateTimeParseException ex) {
                erroLabel.setText("Data de nascimento em formato inválido. Use AAAA-MM-DD.");
            }
        });

        voltarButton.addActionListener(e -> {
            dispose(); // Fecha a tela atual
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastroCliente frame = new TelaCadastroCliente();
            frame.setVisible(true);
        });
    }

    // Botão arredondado
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
