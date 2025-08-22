
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

public class CadastroUsuario extends JFrame implements ActionListener {

    private JLabel labelUsuario, labelSexo, labelSenha, labelIdade, labelConfirmaSenha, labelTelefone;
    private JTextField nomeUser, idade, telefone;
    private JPasswordField senha, confirmaSenha;
    private JComboBox<String> sexo;
    private JButton botaoCadastrar, botaoCancelar;

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

        botaoCancelar = new JButton("Voltar");
        botaoCancelar.addActionListener(this);
        botaoCancelar.setForeground(Color.WHITE);
        botaoCancelar.setBackground(Color.red);

        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoCancelar);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // ocupar as duas colunas
        painelFundo.add(painelBotoes, gbc);

        add(painelFundo);

        setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            dispose();
            new Main();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } else if (e.getSource() == botaoCancelar) {
        dispose();
        new Main();
    }
}


    /*@Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == botaoCadastrar) {
            cadastrarUser();
        } else if (e.getSource() == botaoCancelar) {
            dispose();
            new Main();
        }

    }
*/
    public void cadastrarUser(Usuario usuario){
        String sql = "INSERT INTO TB_CAOS_USUARIO ( nom_nome, nr_idade, nr_telefone, tp_sexo, cod_senha) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setInt(2, usuario.getIdade());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getSexo());
            ps.setString(5, usuario.getSenha());    

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}