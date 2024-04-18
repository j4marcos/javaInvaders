import javax.swing.*;
import java.awt.event.*;

public class Player {

    static Pokemon pokemonSelecionado;
    static String nome;
    static InterfaceCaixa painel;

    public Player() {
        pokemonSelecionado = new Pokemon("Charmander", 60, "back");
    }

    public static void setInterfaceCaixa(InterfaceCaixa painel) {
        Player.painel = painel; // Adicione este método
    }

    public static void atacar() {
        Enemy.inimigoAtual.vida -= 10;
        System.out.println("O pokemon do player atacou o pokemon inimigo e causou 10 de dano");
        if (Enemy.inimigoAtual.vida <= 0) {
            System.out.println("O pokemon inimigo foi derrotado");
            painel.mostrarDerrotaInimigo();
            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Enemy.trocarInimigo();
                    PokemonsBatle.instance.atualizarInimigo();
                    PokemonsBatle.instance.atualizarVidaInimigo(); 
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        PokemonsBatle.instance.atualizarVidaInimigo(); 
    }

    public void curar() {
        pokemonSelecionado.vida += 10;
        System.out.println("O pokemon do player foi curado e recuperou 10 de vida");
    }
}