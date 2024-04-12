import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Font;


public class Options extends JPanel {
    private BufferedImage backgroundImage;
    private Font Fonte;

    public Options() {
        Fonte = DefinirFonte.fonte();
        editar();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Colocar a imagem como plano de fundo
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public void editar() {
        definirBackground();
        setLayout(new GridLayout(2, 1));

        botao("FIGHT");
        botao("BAG");
        botao("POKEMON");
        botao("RUN");
    }

    private void definirBackground() {
        // Carregar background
        try {
            backgroundImage = ImageIO.read(new File("assents/inBattleMenu/rightMenu.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Erro se o arquivo não estiver lá
        }
    }

    public void botao(String nome) {
        JButton botao = new JButton(nome);
        // Parte do background
        botao.setOpaque(false); // Faz que o botão fique transparante
        botao.setContentAreaFilled(false); // Tirar qualquer coisa que faça os botões ficarem coloridos
        botao.setBorderPainted(false); // Tirar as bordas
        botao.setFont(Fonte.deriveFont(Font.PLAIN, 35)); // Definir fonte

        botao.addActionListener(e -> {
            if (nome.equals("FIGHT")) {
                System.out.println(nome + " apertado!");
                TelasRef.interfaceCaixa.mudarInterfaceBattleLayout("poderes");
            } else if (nome.equals("BAG")) {
                TelasRef.battle.mudarTela("inventario");
                System.out.println(nome + " apertado!");
            } else if (nome.equals("POKEMON")) {
                TelasRef.battle.mudarTela("pokemonsSelection");
                System.out.println(nome + " apertado!");
            } else if (nome.equals("RUN")) {
                TelasRef.game.mudarTela("home");
                System.out.println(nome + " apertado!");
            }
        });
        add(botao);
    }
}