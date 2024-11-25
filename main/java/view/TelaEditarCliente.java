package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ClienteController;
import dao.ClienteDAO;
import model.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TelaEditarCliente extends JFrame {
    private JLabel erroLabel;

    public TelaEditarCliente(Cliente cliente) {
        setTitle("Banco Malvader - Edição de Cliente");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());
        add(panel);

        // Painel de formulário
        JPanel formPanel = new JPanel();
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeText = new JTextField(cliente.getNome());

        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfText = new JTextField(cliente.getCpf());

        JLabel dataNascimentoLabel = new JLabel("Nascimento (AAAA-MM-DD):");
        JTextField dataNascimentoText = new JTextField(cliente.getDataDeNascimento().toString());

        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneText = new JTextField(cliente.getTelefone());

        JLabel cepLabel = new JLabel("CEP:");
        JTextField cepText = new JTextField(cliente.getEndereco().getCep());

        JLabel localLabel = new JLabel("Local:");
        JTextField localText = new JTextField(cliente.getEndereco().getLocal());

        JLabel numeroCasaLabel = new JLabel("Número da Casa:");
        JTextField numeroCasaText = new JTextField(String.valueOf(cliente.getEndereco().getNumeroCasa()));

        JLabel bairroLabel = new JLabel("Bairro:");
        JTextField bairroText = new JTextField(cliente.getEndereco().getBairro());

        JLabel cidadeLabel = new JLabel("Cidade:");
        JTextField cidadeText = new JTextField(cliente.getEndereco().getCidade());

        JLabel estadoLabel = new JLabel("Estado (UF):");
        JTextField estadoText = new JTextField(cliente.getEndereco().getEstado());

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaText = new JPasswordField(cliente.getSenha());

        // Botão de Atualizar
        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.setBackground(new Color(0, 123, 255));
        atualizarButton.setForeground(Color.WHITE);
        atualizarButton.setFocusPainted(false);

        // Label de erro
        erroLabel = new JLabel("", SwingConstants.CENTER);
        erroLabel.setForeground(Color.RED);

        // Layout vertical e horizontal
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nomeLabel)
                        .addComponent(cpfLabel)
                        .addComponent(dataNascimentoLabel)
                        .addComponent(telefoneLabel)
                        .addComponent(cepLabel)
                        .addComponent(localLabel)
                        .addComponent(numeroCasaLabel)
                        .addComponent(bairroLabel)
                        .addComponent(cidadeLabel)
                        .addComponent(estadoLabel)
                        .addComponent(senhaLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nomeText)
                        .addComponent(cpfText)
                        .addComponent(dataNascimentoText)
                        .addComponent(telefoneText)
                        .addComponent(cepText)
                        .addComponent(localText)
                        .addComponent(numeroCasaText)
                        .addComponent(bairroText)
                        .addComponent(cidadeText)
                        .addComponent(estadoText)
                        .addComponent(senhaText))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nomeLabel)
                        .addComponent(nomeText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cpfLabel)
                        .addComponent(cpfText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dataNascimentoLabel)
                        .addComponent(dataNascimentoText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(telefoneLabel)
                        .addComponent(telefoneText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cepLabel)
                        .addComponent(cepText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(localLabel)
                        .addComponent(localText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroCasaLabel)
                        .addComponent(numeroCasaText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(bairroLabel)
                        .addComponent(bairroText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cidadeLabel)
                        .addComponent(cidadeText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(estadoLabel)
                        .addComponent(estadoText))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(senhaLabel)
                        .addComponent(senhaText))
        );

        panel.add(formPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(atualizarButton, BorderLayout.CENTER);
        buttonPanel.add(erroLabel, BorderLayout.SOUTH);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Ação do botão atualizar
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                erroLabel.setText("");
                try {
                    if (nomeText.getText().isEmpty() || cpfText.getText().isEmpty() || dataNascimentoText.getText().isEmpty() ||
                            telefoneText.getText().isEmpty() || cepText.getText().isEmpty() || localText.getText().isEmpty() ||
                            numeroCasaText.getText().isEmpty() || bairroText.getText().isEmpty() || cidadeText.getText().isEmpty() ||
                            estadoText.getText().isEmpty() || new String(senhaText.getPassword()).isEmpty()) {
                        erroLabel.setText("Preencha todos os campos corretamente.");
                        return;
                    }

                    if (estadoText.getText().length() > 2) {
                        erroLabel.setText("Preencha o estado com sua UF.");
                        return;
                    }

                    cliente.setNome(nomeText.getText());
                    cliente.setCpf(cpfText.getText());
                    cliente.setDataDeNascimento(LocalDate.parse(dataNascimentoText.getText()));
                    cliente.setTelefone(telefoneText.getText());
                    cliente.getEndereco().setCep(cepText.getText());
                    cliente.getEndereco().setLocal(localText.getText());
                    cliente.getEndereco().setNumeroCasa(Integer.parseInt(numeroCasaText.getText()));
                    cliente.getEndereco().setBairro(bairroText.getText());
                    cliente.getEndereco().setCidade(cidadeText.getText());
                    cliente.getEndereco().setEstado(estadoText.getText());
                    cliente.setSenha(new String(senhaText.getPassword()));

                    ClienteController clienteController = new ClienteController();
                    clienteController.editarClienteController(cliente);

                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    erroLabel.setText("Número inválido em campo(s) numérico(s).");
                } catch (DateTimeParseException ex) {
                    erroLabel.setText("Data de nascimento em formato inválido. Use AAAA-MM-DD.");
                }
            }
        });
    }

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente1 = clienteDAO.getClasseCliente("hugo12");

        TelaEditarCliente telaEditarCliente = new TelaEditarCliente(cliente1);
        telaEditarCliente.setVisible(true);
    }
}
