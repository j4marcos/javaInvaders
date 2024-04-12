import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Bag extends JPanel {
    JLabel background = new JLabel();
    Font Fonte = DefinirFonte.fonte();
    private JButton useButton = editarBotao("USE", 663, 450);
    private JButton cancelButton = editarBotao("CANCEL", 663, 510);
    JLabel item;
    JLabel imgItem;

    public Bag() {
        editar();
    }

    public void editar() {
        background.setFocusable(true);
        background.setIcon(new ImageIcon("assents/backgroundImages/base-itens.png"));
        JScrollPane scrollPane = listaDeItens();
        background.add(scrollPane);
        background.revalidate();

        add(background);
    }

    public JLabel itemAtual() {
        JLabel itemAtual = new JLabel();
         // link do sprite: https://github.com/msikma/pokesprite/blob/master/items-outline/medicine/super-potion.png
        itemAtual.setBounds(15, 460, 98, 98);

        return itemAtual;
    }

    public JButton editarBotao(String nome, int x, int y) {
        JButton botao = new JButton(nome);

        botao.setContentAreaFilled(false); 
        botao.setBorderPainted(false); 
        botao.setFont(Fonte.deriveFont(Font.PLAIN,45f));
        botao.setBounds(x, y, 215, 50);

        botao.addActionListener(e -> {
            if (nome.equals("USE")) {
                System.out.println(nome + " apertado!");
            } else if (nome.equals("CANCEL")) {
                System.out.println(nome + " apertado!");
                useButton.setVisible(false);
                cancelButton.setVisible(false);
                background.setIcon(new ImageIcon("assents/backgroundImages/base-itens.png"));
                item.setVisible(false);
                imgItem.setVisible(false);
            }
        });

        return botao;   
    }

    public JScrollPane listaDeItens() {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (int i = 0; i < 20; i++) {
            modeloLista.addElement(String.format("ITEM  %02d", (i + 1)));
        }

        JList<String> listaDeItens = new JList<>(modeloLista);
        listaDeItens.setFont(Fonte.deriveFont(Font.PLAIN,60f));
        listaDeItens.setBackground(new Color(0, 0, 0, 0)); // Tornando a JList transparente
        
        JScrollPane scrollPane = new JScrollPane(listaDeItens);
        scrollPane.setBounds(340, 30, 520, 350);
        scrollPane.getViewport().addChangeListener((e) -> scrollPane.getParent().repaint());

        scrollPane.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                repaint(); 
            }
        });

        item = new JLabel();
        item.setBounds(180, 460, 400, 100);
        item.setFont(Fonte.deriveFont(Font.PLAIN,50f));
        imgItem = itemAtual();
        background.add(imgItem);
        background.add(item);
                        

        listaDeItens.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (listaDeItens.getSelectedIndex() != -1) {
                        item.setText(String.format("%s  is  selected.", listaDeItens.getSelectedValue()));
                        background.setIcon(new ImageIcon("assents/backgroundImages/nova-base.png"));
                        imgItem.setIcon(new ImageIcon("assents/backgroundImages/super-potion.png"));
                        background.revalidate();
                        scrollPane.getParent().repaint();
                        
                        background.add(useButton);
                        background.add(cancelButton);
                        useButton.setVisible(true);
                        cancelButton.setVisible(true);
                        item.setVisible(true);
                        imgItem.setVisible(true);
                    }
                }
            }
        });
        

        scrollPane.setBackground(new Color(0, 0, 0, 0)); // Tornando o JScrollPane transparente
        scrollPane.revalidate();
        return scrollPane;
    }
}