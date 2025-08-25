import DAO.Conexao;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class CadastroService extends JFrame implements ActionListener {

    private JTextField nomeField, apelidoField;
    private JComboBox<String> sexoComboBox, classeComboBox;
    private JTextArea descricaoArea;
    private JRadioButton contencaoButton, foraContencaoButton;
    private JButton cadastrarButton, voltarButton, alterarButton;
    private boolean scpCadastrado;
    private String nomeOriginal;
    
    public CadastroService() {
        JFrame frame = new JFrame("Cadastro de Entidades SCP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setSize(700, 500);  // Ajustei o tamanho para caber todos os componentes
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(Color.BLACK);

        // Configuração do layout GridBagLayout
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Adicionando o campo "Nome"
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(nomeLabel, gbc);

        nomeField = new JTextField(20);
        gbc.gridx = 1;
        frame.add(nomeField, gbc);

        // Adicionando o campo "Apelido"
        JLabel apelidoLabel = new JLabel("Apelido");
        apelidoLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(apelidoLabel, gbc);

        apelidoField = new JTextField(20);
        gbc.gridx = 1;
        frame.add(apelidoField, gbc);

        // Adicionando o campo "Sexo"
        JLabel sexoLabel = new JLabel("Sexo");
        sexoLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(sexoLabel, gbc);

        String[] sexos = {"Masculino", "Feminino", "ND"};
        sexoComboBox = new JComboBox<>(sexos);
        gbc.gridx = 1;
        frame.add(sexoComboBox, gbc);

        // Adicionando o campo "Classe"
        JLabel classeLabel = new JLabel("Classe");
        classeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(classeLabel, gbc);

        String[] classes = {"Safe", "Euclid", "Ketter"};
        classeComboBox = new JComboBox<>(classes);
        gbc.gridx = 1;
        frame.add(classeComboBox, gbc);

        // Adicionando o campo "Descrição"
        JLabel descricaoLabel = new JLabel("Descrição");
        descricaoLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(descricaoLabel, gbc);

        descricaoArea = new JTextArea(5, 20);
        descricaoArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane descricaoScroll = new JScrollPane(descricaoArea);
        gbc.gridx = 1;
        frame.add(descricaoScroll, gbc);

        // Adicionando os radio buttons "Contenção" e "Fora de Contenção"
        JLabel contencaoLabel = new JLabel("Tipo de Contenção:");
        contencaoLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(contencaoLabel, gbc);

        contencaoButton = new JRadioButton("Contenção");
        contencaoButton.setBackground(Color.BLACK);
        contencaoButton.setForeground(Color.WHITE);
        foraContencaoButton = new JRadioButton("Fora de Contenção");
        foraContencaoButton.setBackground(Color.BLACK);
        foraContencaoButton.setForeground(Color.WHITE);

        // Agrupar os botões para que apenas um possa ser selecionado
        ButtonGroup grupoContencao = new ButtonGroup();
        grupoContencao.add(contencaoButton);
        grupoContencao.add(foraContencaoButton);

        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(Color.BLACK);
        radioPanel.add(contencaoButton);
        radioPanel.add(foraContencaoButton);
        gbc.gridx = 1;
        frame.add(radioPanel, gbc);

        // Adicionando os botões
        JPanel botaoPanel = new JPanel();
        botaoPanel.setBackground(Color.BLACK);
        botaoPanel.setLayout(new FlowLayout());

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(this);
        cadastrarButton.setBackground(Color.GREEN);
        cadastrarButton.setForeground(Color.WHITE);
        botaoPanel.add(cadastrarButton);

        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(this);
        voltarButton.setBackground(Color.RED);
        voltarButton.setForeground(Color.WHITE);
        botaoPanel.add(voltarButton);

        alterarButton = new JButton("Alterar");
        alterarButton.addActionListener(this);
        alterarButton.setBackground(Color.BLUE);
        alterarButton.setForeground(Color.WHITE);
        botaoPanel.add(alterarButton);

        gbc.gridx = 1;
        gbc.gridy = 6;  // Ajustei o índice de linha
        frame.add(botaoPanel, gbc);

        // Exibindo a janela
        frame.setVisible(true);
        //Ajusta os tamanhos dos itens da tela
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrarButton) {
            try {
                CadastroSCP scp = new CadastroSCP(
                    nomeField.getText(),
                    sexoComboBox.getSelectedItem().toString(),
                    apelidoField.getText(),
                    classeComboBox.getSelectedItem().toString(),
                    descricaoArea.getText(),
                    contencaoButton.isSelected()
                );

                // Chame o método para cadastrar no banco de dados
                cadastrarEntidade(scp);

                if (scpCadastrado) {
                    JOptionPane.showMessageDialog(this, "Entidade cadastrada com sucesso!");
                    dispose();  // Fecha a janela atual
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == voltarButton) {
            // Exemplo de como voltar para outra tela
            dispose();
            new UsuarioService();  // Supondo que seja uma tela de usuários
        } else if (e.getSource() == alterarButton) {
            // Ação para o botão alterar
             try {
                CadastroSCP scp = new CadastroSCP(
                    nomeField.getText(),
                    sexoComboBox.getSelectedItem().toString(),
                    apelidoField.getText(),
                    classeComboBox.getSelectedItem().toString(),
                    descricaoArea.getText(),
                    contencaoButton.isSelected()
                );

                // Atualiza no banco de dados
                alterarEntidade(scp);

                if (scpCadastrado) {
                    JOptionPane.showMessageDialog(this, "Entidade alterada com sucesso!");
                    dispose();  // Fecha a janela
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cadastrarEntidade(CadastroSCP scp) {
        String verificarSql = "SELECT COUNT(*) FROM TB_CAOS_SCP WHERE id_usuario = ?";
        String inserirSql = "INSERT INTO TB_CAOS_SCP (id_usuario, nm_nomenclatura, tp_scp_sexo, nm_apelido, tp_classe, ds_descricao, tp_contencao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement verificarStmt = conn.prepareStatement(verificarSql)) {

            // Verifica se o nome já existe
            verificarStmt.setString(1, scp.getNomenclatura());
            try (ResultSet rs = verificarStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Entidade com esse nome já existe. Cadastro não permitido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    scpCadastrado = false;
                    return;
                }
            }

            // Inserir a nova entidade
            try (PreparedStatement inserirStmt = conn.prepareStatement(inserirSql)) {
                inserirStmt.setString(1, scp.getNomenclatura());
                inserirStmt.setString(2, scp.getSexo());
                inserirStmt.setString(3, scp.getApelido());
                inserirStmt.setString(4, scp.getClasse());
                inserirStmt.setString(5, scp.getDescricao());
                inserirStmt.setBoolean(6, scp.isContencao());

                int rowsAffected = inserirStmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Entidade cadastrada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    scpCadastrado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao cadastrar a entidade", "Erro", JOptionPane.ERROR_MESSAGE);
                    scpCadastrado = false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar entidade: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            scpCadastrado = false;
        }
    }

    public void alterarEntidade(CadastroSCP scp){
        String alterarSql = "UPDATE TB_CAOS_SCP SET id_usuario, nm_nomenclatura = ?, tp_scp_sexo = ?, nm_apelido = ?, tp_classe = ?, ds_descricao = ?, tp_contencao = ? WHERE nm_nomenclatura = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(alterarSql)) {

            stmt.setString(1, scp.getNomenclatura());
            stmt.setString(2, scp.getSexo());
            stmt.setString(3, scp.getApelido());
            stmt.setString(4, scp.getClasse());
            stmt.setString(5, scp.getDescricao());
            stmt.setBoolean(6, scp.isContencao());
            stmt.setString(7, nomeOriginal);  // A nomenclatura original serve como chave para a atualização

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Entidade alterada com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                scpCadastrado = true;
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar a entidade", "Erro", JOptionPane.ERROR_MESSAGE);
                scpCadastrado = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao alterar entidade: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            scpCadastrado = false;
        }
    }

}