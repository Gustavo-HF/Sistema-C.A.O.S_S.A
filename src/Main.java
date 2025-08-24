import DAO.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener {

    private JPanel painelFundo, painelLinhaCima, painelLinhaBaixo;
    private JButton botao, botaoCadastrar, botaoSair;
    private ImageIcon icon;

    public Main() {

        setTitle("Sistema de Controle Adminitrativo de Objetos Sobrenaturais");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação do painel de fundo
        painelFundo = new JPanel();
        painelFundo.setLayout(new BoxLayout(painelFundo, BoxLayout.Y_AXIS));
        painelFundo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelFundo.setBackground(new Color(0, 0, 0));
        add(painelFundo);

        // Painel da linha de cima (Entrar e Cadastrar)
        painelLinhaCima = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        painelLinhaCima.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // espaçamento entre as linhas
        painelLinhaCima.setBackground(new Color(0, 0, 0));

        botao = new JButton("Entrar");
        botao.addActionListener(this);
        botao.setFont(new Font("Times New Roman", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(120, 40));
        botao.setBackground(new Color(34, 139, 34));
        botao.setForeground(Color.WHITE);
        botao.setFocusable(false);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this);
        botaoCadastrar.setFont(new Font("Times New Roman", Font.BOLD, 16));
        botaoCadastrar.setPreferredSize(new Dimension(120, 40));
        botaoCadastrar.setBackground(new Color(34, 139, 34));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFocusable(false);

        painelLinhaCima.add(botao);
        painelLinhaCima.add(botaoCadastrar);

        // Painel da linha de baixo (Sair)
        painelLinhaBaixo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelLinhaBaixo.setBackground(new Color(0, 0, 0));
        painelLinhaBaixo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); 

        botaoSair = new JButton("Sair");
        botaoSair.addActionListener(this);
        botaoSair.setFont(new Font("Times New Roman", Font.BOLD, 16));
        botaoSair.setPreferredSize(new Dimension(120, 40));
        botaoSair.setBackground(new Color(148, 16, 4));
        botaoSair.setForeground(Color.WHITE);
        botaoSair.setFocusable(false);

        painelLinhaBaixo.add(botaoSair);

        // Criação do JLabel com imagem (imagem na pasta do projeto ou dentro de resources)
        ImageIcon originalIcon = new ImageIcon("imagens/Logo.png");
        Image image = originalIcon.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        //icon = new ImageIcon("imagens/logo.png"); // Caminho relativo ou absoluto

        JLabel imagemLabel = new JLabel(icon);
        imagemLabel.setAlignmentX(CENTER_ALIGNMENT); // Centraliza no BoxLayout
        

        // Adiciona ao topo do painel
        painelFundo.add(imagemLabel, 0); // Adiciona na posição 0 (topo)
        painelFundo.add(Box.createVerticalStrut(20)); // Espaço abaixo da imagem

        // Adiciona os dois painéis ao painel principal
        painelFundo.add(painelLinhaCima);
        painelFundo.add(painelLinhaBaixo);

        //Ajuste dos paineis, botões e labels para o tamanho correspondente ao tamanho máximo da tela 
        pack();

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == botao){
            UsuarioService usuarioServico = new UsuarioService();
        } else if (e.getSource() == botaoCadastrar) {
            CadastroUsuario cadastroUsuario = new CadastroUsuario();
        } else {
            System.exit(0); // Fecha o aplicativo
        }

        
    }
         

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.setVisible(true);
    }
}
