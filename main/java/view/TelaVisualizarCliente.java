package view;

import model.Cliente;
import model.Endereco;

import javax.swing.*;
import java.awt.*;

public class TelaVisualizarCliente extends JFrame {

    public TelaVisualizarCliente(Cliente cliente) {

        setTitle("Banco Malvader - Visualizar Cliente");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout em coluna
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel clienteLabel = new JLabel("Cliente: " + cliente.getNome());
        clienteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        clienteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clienteLabel);
        panel.add(Box.createVerticalStrut(15));

        // Painel de informações do cliente
        JPanel clientePanel = new JPanel(new GridLayout(0, 2, 10, 5));
        clientePanel.setBorder(BorderFactory.createTitledBorder("Informações do Cliente"));

        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel telefoneLabel = new JLabel("Telefone:");

        JLabel nomeValor = new JLabel(cliente.getNome());
        JLabel cpfValor = new JLabel(cliente.getCpf());
        JLabel telefoneValor = new JLabel(cliente.getTelefone());

        clientePanel.add(nomeLabel);
        clientePanel.add(nomeValor);
        clientePanel.add(cpfLabel);
        clientePanel.add(cpfValor);
        clientePanel.add(telefoneLabel);
        clientePanel.add(telefoneValor);

        Endereco endereco = cliente.getEndereco();
        JLabel cepLabel = new JLabel("CEP:");
        JLabel localLabel = new JLabel("Local:");
        JLabel numeroCasaLabel = new JLabel("Número da Casa:");
        JLabel bairroLabel = new JLabel("Bairro:");
        JLabel cidadeLabel = new JLabel("Cidade:");
        JLabel estadoLabel = new JLabel("Estado:");

        JLabel cepValor = new JLabel(endereco.getCep());
        JLabel localValor = new JLabel(endereco.getLocal());
        JLabel numeroCasaValor = new JLabel(String.valueOf(endereco.getNumeroCasa()));
        JLabel bairroValor = new JLabel(endereco.getBairro());
        JLabel cidadeValor = new JLabel(endereco.getCidade());
        JLabel estadoValor = new JLabel(endereco.getEstado());

        clientePanel.add(cepLabel);
        clientePanel.add(cepValor);
        clientePanel.add(localLabel);
        clientePanel.add(localValor);
        clientePanel.add(numeroCasaLabel);
        clientePanel.add(numeroCasaValor);
        clientePanel.add(bairroLabel);
        clientePanel.add(bairroValor);
        clientePanel.add(cidadeLabel);
        clientePanel.add(cidadeValor);
        clientePanel.add(estadoLabel);
        clientePanel.add(estadoValor);

        panel.add(clientePanel);
        panel.add(Box.createVerticalStrut(10));

        // Painel para o botão "Voltar"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Layout horizontal

        // Botão "Voltar"
        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(new Color(120, 120, 120)); // Cor de fundo
        voltarButton.setForeground(Color.WHITE); // Cor do texto
        voltarButton.setFocusPainted(false); // Remove a borda quando o botão é clicado
        voltarButton.addActionListener(e -> dispose()); // Fecha a janela atual

        buttonPanel.add(voltarButton); // Adicionar o botão no painel de botões

        // Adicionando o painel de botões abaixo das informações
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }
}