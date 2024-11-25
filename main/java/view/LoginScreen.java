package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.LoginDAO;
import model.Cliente;
import model.Funcionario;
import model.Usuario;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("Banco Malvader - Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal dividido
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // Divisão em duas colunas
        add(mainPanel);

        // Painel imagem
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(166, 166, 166)); // Fundo cinza
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Layout em coluna
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        mainPanel.add(leftPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        //imagem no lado esquerdo
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\anabe\\OneDrive\\Área de Trabalho\\ProjetoFinal_BancoMalvader\\logo2.png"));
            Image scaledImg = img.getScaledInstance(300, 250, Image.SCALE_SMOOTH); // Redimensiona para 300x250
            ImageIcon icon = new ImageIcon(scaledImg);
            JLabel imageLabel = new JLabel(icon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinha a imagem ao centro
            leftPanel.add(imageLabel);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Painel formulário
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout());
        mainPanel.add(rightPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginTitle = new JLabel("LOGIN");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        loginTitle.setForeground(new Color(58, 58, 58));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(loginTitle, gbc);
        gbc.gridwidth = 1;

        // Campos do formulário
        JLabel userLabel = new JLabel("Usuário:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        userLabel.setForeground(new Color(58, 58, 58));
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        userText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        rightPanel.add(userText, gbc);

        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordLabel.setForeground(new Color(58, 58, 58));
        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(passwordLabel, gbc);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        rightPanel.add(passwordText, gbc);

        JLabel profileLabel = new JLabel("Perfil:");
        profileLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        profileLabel.setForeground(new Color(58, 58, 58));
        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(profileLabel, gbc);

        // Dropdown para selecionar o perfil
        String[] profiles = {"Selecione", "Cliente", "Funcionário"};
        JComboBox<String> profileDropdown = new JComboBox<>(profiles);
        profileDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        rightPanel.add(profileDropdown, gbc);

        // Botão Login
        JButton loginButton = new JButton("Entrar");
        loginButton.setBackground(new Color(58, 58, 58));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
            String usuario = userText.getText();
            String senha = new String(passwordText.getPassword());
            String selectedProfile = (String) profileDropdown.getSelectedItem();

            LoginDAO loginDAO = new LoginDAO();
            boolean verify = loginDAO.realizarLogin(usuario, senha, selectedProfile.equals("Funcionário"));

            if (verify) {
                JOptionPane.showMessageDialog(this, "<html>Login bem-sucedido!<br>Bem-vindo(a) " + usuario + "!</html>", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                if ("Funcionário".equals(selectedProfile)) {
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    Funcionario funcionario = funcionarioDAO.getClassFuncionario(usuario);

                    TelaMenuFuncionario telaFuncionario = new TelaMenuFuncionario(funcionario);
                    telaFuncionario.setVisible(true);
                } else {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    TelaMenuCliente telaMenuCliente = new TelaMenuCliente(cliente);
                    telaMenuCliente.setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos... Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen frame = new LoginScreen();
            frame.setVisible(true);
        });
    }
}
