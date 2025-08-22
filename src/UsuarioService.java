
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UsuarioService extends JFrame implements ActionListener {
    JPanel painelFundo, painelLinhaLog, painelLinhaPas, painelBotoes;
    JTextField nomeUsuario;
    JPasswordField senhaUsuario;
    JLabel usuarioLabel, senhaLabel;
    JButton botaoEntrar, botaoVoltar;

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
            
        } else if (e.getSource() == botaoVoltar){
            dispose();
            new Main();
        } 

    }
}
