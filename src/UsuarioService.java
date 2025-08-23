
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import DAO.Conexao;

public class UsuarioService extends JFrame implements ActionListener {
    JPanel painelFundo, painelLinhaLog, painelLinhaPas, painelBotoes;
    JTextField nomeUsuario;
    JPasswordField senhaUsuario;
    JLabel usuarioLabel, senhaLabel;
    JButton botaoEntrar, botaoVoltar;
    private static boolean usuarioLogado = false;

    public UsuarioService(){
        setTitle("Sistema de Controle Adminitrativo de Objetos Sobrenaturais");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        painelFundo = new JPanel();
        painelFundo.setLayout(new BoxLayout(painelFundo, BoxLayout.Y_AXIS));
        painelFundo.setBackground(new Color(0,0,0));
        add(painelFundo);

        painelLinhaLog = new JPanel();
        painelLinhaLog.setLayout(new BoxLayout(painelLinhaLog, BoxLayout.X_AXIS));
        painelLinhaLog.setBackground(Color.black);
        painelLinhaLog.setBorder(BorderFactory.createEmptyBorder(75, 25,0,0));
        painelLinhaLog.setAlignmentX(CENTER_ALIGNMENT);
        painelFundo.add(painelLinhaLog);

        painelLinhaPas = new JPanel();
        painelLinhaPas.setLayout(new BoxLayout(painelLinhaPas, BoxLayout.X_AXIS));
        painelLinhaPas.setBackground(Color.black);
        painelLinhaPas.setBorder(BorderFactory.createEmptyBorder(25, 25,75,0));
        painelLinhaPas.setAlignmentX(CENTER_ALIGNMENT);
        painelFundo.add(painelLinhaPas);

        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));
        painelBotoes.setBackground(Color.BLACK);

        painelFundo.add(painelBotoes);

        usuarioLabel = new JLabel("Usuário: ");
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,25));
        painelLinhaLog.add(usuarioLabel);

        senhaLabel = new JLabel("Senha: ");
        senhaLabel.setForeground(Color.WHITE);
        senhaLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,27));
        painelLinhaPas.add(senhaLabel);

        nomeUsuario = new JTextField();
        nomeUsuario.setMaximumSize(new Dimension(250, 25));
        painelLinhaLog.add(nomeUsuario);

        senhaUsuario = new JPasswordField();
        senhaUsuario.setMaximumSize(new Dimension(250, 25));
        painelLinhaPas.add(senhaUsuario);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setPreferredSize(new Dimension(120, 40));
        botaoEntrar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        botaoEntrar.addActionListener(this);
        botaoEntrar.setBackground(new Color(34, 139, 34));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setFocusable(false);
        painelBotoes.add(botaoEntrar);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(120, 40));
        botaoVoltar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        botaoVoltar.addActionListener(this);
        botaoVoltar.setBackground(Color.RED);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusable(false);
        painelBotoes.add(botaoVoltar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == botaoEntrar) {
             try {
            // Obtendo os valores inseridos pelo usuário no formulário
            String nome = nomeUsuario.getText();
            String senha = new String(senhaUsuario.getPassword());  // Convertendo a senha de JPasswordField

            // Chamando o método logarUser para autenticação
            boolean loginSucesso = logarUser(nome, senha);
            
            if (loginSucesso) {
                // Sucesso no login, redireciona para a tela principal ou outra ação
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Aqui você pode fazer qualquer ação após o login bem-sucedido
                dispose(); // Fecha a tela de login
                new CadastroService(); // Abre a tela principal ou outra tela de sua aplicação
            } else {
                // Se o login falhar
                JOptionPane.showMessageDialog(this, "Nome de usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao realizar login: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        } else if (e.getSource() == botaoVoltar){
            dispose();
            new Main();
        } 

    }

    public boolean logarUser(String nome, String senha) {
    try (Connection conn = Conexao.getConexao()) {
        // Consulta SQL para verificar o nome e a senha do usuário
        String sql = "SELECT * FROM  TB_CAOS_USUARIO WHERE nom_nome = ? AND cod_senha = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome); // Configura o nome do usuário na consulta
            stmt.setString(2, senha); // Configura a senha na consulta

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se encontrar um usuário com essas credenciais, retorna true
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar o ResultSet: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao preparar a consulta: " + e.getMessage());
        }
    } catch (Exception e) {
        System.out.println("Problema com a conexão: " + e.getMessage());
    }

    // Se não encontrar o usuário ou tiver algum erro, retorna false
    return false;
    }
}
