package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import dao.*;
import model.Cliente;
import model.Conta;
import model.Funcionario;

public class TelaMenuFuncionario extends JFrame {

    public TelaMenuFuncionario(Funcionario funcionario) {
            setTitle("Banco Malvader - Sistema");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Painel principal
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);
            add(mainPanel);

            // Título e informações do usuário
            JLabel tituloLabel = new JLabel("ESPAÇO DO FUNCIONÁRIO", SwingConstants.CENTER);
            tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
            tituloLabel.setForeground(Color.BLACK);
            tituloLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // 20 pixels de margem superior

        JLabel subtituloLabel = new JLabel("Escolha uma opção", SwingConstants.CENTER);
            subtituloLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            subtituloLabel.setForeground(Color.BLACK);

            JLabel usuarioLabel = new JLabel("Usuário: " + funcionario.getNome() + " | Cargo: " + funcionario.getCargo(), SwingConstants.RIGHT);
            usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            usuarioLabel.setForeground(Color.BLACK);
            usuarioLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // 20 pixels de margem inferior

        ImageIcon logoIcon = new ImageIcon(new ImageIcon ("C:\\Users\\anabe\\OneDrive\\Área de Trabalho\\ProjetoFinal_BancoMalvader\\logo.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)); // Substitua pelo caminho da sua logo
        JLabel logoLabel = new JLabel(logoIcon);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

// Painel para organizar logo e informações do usuário
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(logoLabel, BorderLayout.WEST); // Adiciona a logo no canto superior esquerdo
        topPanel.add(usuarioLabel, BorderLayout.CENTER); // Adiciona as informações do usuário ao lado da logo

        headerPanel.add(topPanel, BorderLayout.NORTH); // Adiciona o painel superior ao headerPanel

// título e subtítulo
        headerPanel.add(tituloLabel, BorderLayout.CENTER);
        headerPanel.add(subtituloLabel, BorderLayout.SOUTH);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

            // Painel central com botões
            JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 20, 20));
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Criação e estilização dos botões
            buttonPanel.add(criarBotao("Abrir Conta", e -> abrirConta()));
            buttonPanel.add(criarBotao("Encerrar Conta", e -> encerrarConta()));
            buttonPanel.add(criarBotao("Consultar Dados da Conta", e -> consultarDadosConta()));
            buttonPanel.add(criarBotao("Consultar Dados do Cliente", e -> consultarDadosCliente()));
            buttonPanel.add(criarBotao("Alterar Dados da Conta", e -> alterarDadosConta()));
            buttonPanel.add(criarBotao("Alterar Dados do Cliente", e -> alterarDadosCliente()));
            buttonPanel.add(criarBotao("Cadastrar Funcionário", e -> cadastrarFuncionario()));
            buttonPanel.add(criarBotao("Gerar Relatório", e -> gerarRelatorio()));
            buttonPanel.add(criarBotao("Sair", e -> sair()));

            mainPanel.add(buttonPanel, BorderLayout.CENTER);
        }

    public class RoundedButton extends JButton {

        public RoundedButton(String texto) {
            super(texto);
            setContentAreaFilled(false); // Remove o preenchimento padrão do botão
            setFocusPainted(false); // Remove o foco padrão
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Define a cor de fundo do botão
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Arredondamento com raio 30

            // Desenha o texto do botão
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

            // Define a cor da borda
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30); // Arredondamento com raio 30

            g2.dispose();
        }
    }

    private JButton criarBotao(String texto, ActionListener acao) {
        RoundedButton botao = new RoundedButton(texto);
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setForeground(Color.WHITE); // Cor do texto
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Cor padrão do botão
        botao.setBackground(new Color(166, 166, 166));

        // Efeito hover - mesma cor, mas um tom mais escuro para dar destaque
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(140, 140, 140)); // Tom mais escuro no hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(166, 166, 166)); // Volta para a cor original
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(120, 120, 120)); // Tom mais escuro ao pressionar
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                botao.setBackground(new Color(140, 140, 140)); // Cor ao soltar o botão
            }
        });

        // Adiciona o evento de clique
        botao.addActionListener(acao);

        return botao;
    }

        private void abrirConta() {
        JTextField nomeUsuarioField = new JTextField();

        JLabel cpfLabel = new JLabel("Nome completo do Usuário:");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.add(cpfLabel, BorderLayout.NORTH);
        panel.add(nomeUsuarioField, BorderLayout.CENTER);

        Object[] options = {"Voltar", "Confirmar", "Novo cliente"};

        int escolha = JOptionPane.showOptionDialog(
                null,
                panel,
                "Abrir Conta",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]);

        String nomeUsuario = nomeUsuarioField.getText();

        switch (escolha) {
            case JOptionPane.YES_OPTION:
                System.out.println("Usuário clicou em Voltar.");
                break;
            case JOptionPane.NO_OPTION:

                nomeUsuario = nomeUsuarioField.getText();
                if (!nomeUsuario.isEmpty()) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    boolean verificar = clienteDAO.verificarCliente(nomeUsuario);

                    if (verificar) {

                        Object[] opcoesConta = {"Poupança", "Corrente"};
                        int escolhaConta = JOptionPane.showOptionDialog(
                                null,
                                "Escolha o tipo de conta:",
                                "Seleção de Tipo de Conta",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opcoesConta,
                                opcoesConta[0]);

                        if (escolhaConta == 0) {
                            TelaCriarContaPoupanca telaCriarContaPoupanca = new TelaCriarContaPoupanca(nomeUsuario);
                            telaCriarContaPoupanca.setVisible(true);

                            System.out.println("Usuário escolheu Poupança.");
                        } else if (escolhaConta == 1) {
                            TelaCriarContaCorrente telaCriarContaCorrente = new TelaCriarContaCorrente(nomeUsuario);
                            telaCriarContaCorrente.setVisible(true);

                            System.out.println("Usuário escolheu Corrente.");
                        }

                    }

                } else {
                    System.out.println("O campo CPF está vazio.");
                }
                break;
            case JOptionPane.CANCEL_OPTION:
                TelaCadastroCliente telaCadastroCliente = new TelaCadastroCliente();
                telaCadastroCliente.setVisible(true);
                break;
            default:
                System.out.println("Diálogo fechado.");
        }
    }

    private void encerrarConta() {
        String admin = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if (admin != null && admin.equals("admin")) {
            String usuario = JOptionPane.showInputDialog(this, "Digite o nome do usuário:");

            if (usuario != null && !usuario.isEmpty()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                if (usuarioDAO.hasUsuario(usuario)) {
                    ContaDAO contaDAO = new ContaDAO();
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                    TelaDeletarConta telaDeletarConta = new TelaDeletarConta(contas);
                    telaDeletarConta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome do usuário não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha de administrador incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarDadosConta() {
        String admin = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if (admin != null && admin.equals("admin")) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            String usuario = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

            if (usuario != null && !usuario.isEmpty()) {
                if (usuarioDAO.hasUsuario(usuario)) {
                    ClienteDAO clienteDAO = new ClienteDAO();
                    Cliente cliente = clienteDAO.getClasseCliente(usuario);

                    ContaDAO contaDAO = new ContaDAO();
                    ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                    if (contas != null && !contas.isEmpty()) {
                        TelaVisualizarConta telaVisualizarConta = new TelaVisualizarConta(contas);
                        telaVisualizarConta.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Cliente não possui contas.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome do cliente não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha de administrador incorreta ou operação cancelada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarDadosCliente() {
        String usuario = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

        if (usuario != null && !usuario.isEmpty()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            if (usuarioDAO.hasUsuario(usuario)) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getClasseCliente(usuario);

                TelaVisualizarCliente telaVisualizarCliente = new TelaVisualizarCliente(cliente);
                telaVisualizarCliente.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nome do cliente não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void alterarDadosConta() {

        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if ("admin".equals(senha)) {
            String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getClasseCliente(nome);

            if(cliente != null){
                ContaDAO contaDAO = new ContaDAO();
                ArrayList<Conta> contas = contaDAO.getClasConta(cliente);

                if(!contas.isEmpty()){
                    TelaEditarConta telaEditarConta = new TelaEditarConta(contas);
                    telaEditarConta.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(this, "Usuario nao tem contas cadastradas.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(this, "Cliente não cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarDadosCliente() {
        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if (senha != null && senha.equals("admin")) {
            String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

            if (nome != null && !nome.trim().isEmpty()) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.getClasseCliente(nome);

                if (cliente != null) {
                    TelaEditarCliente telaEditarCliente = new TelaEditarCliente(cliente);
                    telaEditarCliente.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nome do cliente não pode estar vazio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cadastrarFuncionario() {
        String senha = JOptionPane.showInputDialog(this, "Digite a senha de administrador:");

        if ("admin".equals(senha)) {
            JOptionPane.showMessageDialog(this, "Acesso concedido. Cadastro de Funcionário permitido.");

            TelaCadastroFuncionario cadastroFuncionario = new TelaCadastroFuncionario();
            cadastroFuncionario.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarRelatorio() {
        String nomeCliente = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");

        ContaDAO contaDAO = new ContaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente cliente = clienteDAO.getClasseCliente(nomeCliente);

        if(cliente != null){
            ArrayList<Conta> contas = contaDAO.getClasConta(cliente);
            TelaGerarRelatorio telaGerarRelatorio = new TelaGerarRelatorio(contas, cliente.getNome());
            telaGerarRelatorio.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sair() {
        this.dispose();
    }

    public static void main(String[] args) {
        FuncionarioDAO b = new FuncionarioDAO();

        Funcionario a = b.getClassFuncionario("bia chaves");

        SwingUtilities.invokeLater(() -> {
            TelaMenuFuncionario frame = new TelaMenuFuncionario(a);
            frame.setVisible(true);
        });
    }
}
