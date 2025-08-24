
import DAO.Conexao;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.ResultSet;

public class CadastroUsuario extends JFrame implements ActionListener {

    private JLabel labelUsuario, labelSexo, labelSenha, labelIdade, labelConfirmaSenha, labelTelefone;
    private JTextField nomeUser, idade, telefone;
    private JPasswordField senha, confirmaSenha;
    private JComboBox<String> sexo;
    private JButton botaoCadastrar, botaoCancelar, botaoAlterar;
    private boolean usuarioCadastrado = false;
    private String nomeOriginal; // Para alterar um usuário existente
    

    public CadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // margem entre os elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo Usuário
        labelUsuario = new JLabel("Usuário");
        labelUsuario.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFundo.add(labelUsuario, gbc);

        nomeUser = new JTextField();
        gbc.gridy = 1;
        painelFundo.add(nomeUser, gbc);

        // Campo Sexo
        labelSexo = new JLabel("Sexo");
        labelSexo.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelFundo.add(labelSexo, gbc);

        sexo = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        gbc.gridy = 1;
        painelFundo.add(sexo, gbc);

        // Campo Senha
        labelSenha = new JLabel("Senha");
        labelSenha.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFundo.add(labelSenha, gbc);

        senha = new JPasswordField();
        gbc.gridy = 3;
        painelFundo.add(senha, gbc);

        // Campo Idade
        labelIdade = new JLabel("Idade");
        labelIdade.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelFundo.add(labelIdade, gbc);

        idade = new JTextField();
        gbc.gridy = 3;
        painelFundo.add(idade, gbc);

        // Campo Confirmar Senha
        labelConfirmaSenha = new JLabel("Confirmar Senha");
        labelConfirmaSenha.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelFundo.add(labelConfirmaSenha, gbc);

        confirmaSenha = new JPasswordField();
        gbc.gridy = 5;
        painelFundo.add(confirmaSenha, gbc);

        //Campo telefone
        labelTelefone = new JLabel("Telefone");
        labelTelefone.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 4;
        painelFundo.add(labelTelefone, gbc);

        telefone = new JTextField();
        gbc.gridy = 5;
        painelFundo.add(telefone, gbc);

        // Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.BLACK);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this);
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setBackground(Color.green);

        botaoAlterar = new JButton("Alterar Cadastro");
        botaoAlterar.addActionListener(this);
        botaoAlterar.setForeground(Color.WHITE);
        botaoAlterar.setBackground(Color.blue);

        botaoCancelar = new JButton("Voltar");
        botaoCancelar.addActionListener(this);
        botaoCancelar.setForeground(Color.WHITE);
        botaoCancelar.setBackground(Color.red);

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoAlterar);
        painelBotoes.add(botaoCancelar);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // ocupar as duas colunas
        painelFundo.add(painelBotoes, gbc);

        add(painelFundo);

        setVisible(true);
    }

    public void preencherCampos(String nomeEntidade) {
        String consultaSql = "SELECT * FROM TB_CAOS_USUARIO WHERE nom_nome = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(consultaSql)) {

            stmt.setString(1, nomeEntidade);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nomeUser.setText(rs.getString("nom_nome"));
                    idade.setText(String.valueOf(rs.getInt("nr_idade")));
                    telefone.setText(rs.getString("nr_telefone"));
                    sexo.setSelectedItem(rs.getString("tp_sexo"));
                    senha.setText(rs.getString("cod_senha"));  // Apenas para preenchimento, não é recomendado em uma UI
                    confirmaSenha.setText(rs.getString("cod_senha"));  // Apenas para preenchimento
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
public void actionPerformed(ActionEvent e){
    if (e.getSource() == botaoCadastrar) {
        // Verifica se as senhas coincidem
        String senhaStr = new String(senha.getPassword());
        String confirmaStr = new String(confirmaSenha.getPassword());

        if (!senhaStr.equals(confirmaStr)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Usuario usuario = new Usuario();
            usuario.setNome(nomeUser.getText());
            usuario.setIdade(Integer.parseInt(idade.getText()));
            usuario.setTelefone(telefone.getText());
            usuario.setSexo(sexo.getSelectedItem().toString());
            usuario.setSenha(senhaStr);

             cadastrarUser(usuario);

            if(usuarioCadastrado == true){
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                dispose();
                new CadastroService();
            } else {
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else if (e.getSource() == botaoCancelar) {
        dispose();
        new Main();
    
    } else if(e.getSource() == botaoAlterar){
         // Ação de alterar os dados
            try {
                // Valida se as senhas coincidem
                String senhaStr = new String(senha.getPassword());
                String confirmaStr = new String(confirmaSenha.getPassword());

                if (!senhaStr.equals(confirmaStr)) {
                    JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario usuario = new Usuario();
                usuario.setNome(nomeUser.getText());
                usuario.setIdade(Integer.parseInt(idade.getText()));
                usuario.setTelefone(telefone.getText());
                usuario.setSexo(sexo.getSelectedItem().toString());
                usuario.setSenha(senhaStr);

                // Chama o método de alterar no banco
                alterarUser(usuario);

                if (usuarioCadastrado) {
                    JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!");
                    dispose();  // Fecha a janela de edição
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

  public void cadastrarUser(Usuario usuario) {
    String verificarSql = "SELECT COUNT(*) FROM TB_CAOS_USUARIO WHERE nom_nome = ?";
    String inserirSql = "INSERT INTO TB_CAOS_USUARIO (nom_nome, nr_idade, nr_telefone, tp_sexo, cod_senha) VALUES (?, ?, ?, ?, ?)";

    try (
        Connection conn = Conexao.getConexao();
        PreparedStatement verificarStmt = conn.prepareStatement(verificarSql);
    ) {
        verificarStmt.setString(1, usuario.getNome());
        try (ResultSet rs = verificarStmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Usuário com esse nome já existe. Cadastro não permitido." + JOptionPane.ERROR_MESSAGE);
                usuarioCadastrado = false;
                return;
            }
        }

        try (PreparedStatement inserirStmt = conn.prepareStatement(inserirSql)) {
            inserirStmt.setString(1, usuario.getNome());
            inserirStmt.setInt(2, usuario.getIdade());
            inserirStmt.setString(3, usuario.getTelefone());
            inserirStmt.setString(4, usuario.getSexo());
            inserirStmt.setString(5, usuario.getSenha());

            inserirStmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso.");
            usuarioCadastrado = true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }   

    public void alterarUser(Usuario usuario) {
        String alterarSql = "UPDATE TB_CAOS_USUARIO SET nr_idade = ?, nr_telefone = ?, tp_sexo = ?, cod_senha = ? WHERE nom_nome = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(alterarSql)) {

            stmt.setInt(1, usuario.getIdade());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getSexo());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, nomeOriginal); // Nome original serve para identificar o registro

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                usuarioCadastrado = true;
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar o usuário", "Erro", JOptionPane.ERROR_MESSAGE);
                usuarioCadastrado = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            usuarioCadastrado = false;
        }
    }
}

