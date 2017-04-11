import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
@SuppressWarnings("unused")
public class BattleFrame extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int currentPanel, previousPanel;
    private JLayeredPane mainPane;
    private JPanel pokemon;
    private ArrayList<JLabel> trainerMoves, pokemonMoves;
    public Main main;
    private Pokemon user, enemy;
    private boolean fight, fainted;
    private String[] trainerMoves2 = {"Fight", "Bag", Main.fileRoot + "Images/Pokemon/Pokemon/", "Run"};
    private HealthBar userHealth, enemyHealth;

    BattleFrame(Main _main) {
        super();

        main = _main;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        ArrayList<Pokemon> enemies = new ArrayList<>();
        enemies.add(new Pikachu());
        enemies.add(new Squirtle());
        enemies.add(new Charmander());
        enemies.add(new Rattata());
        enemies.add(new Pidgey());

        mainPane = new JLayeredPane();
        user = enemies.get((int) (Math.random()*5));
        enemy = enemies.get((int) (Math.random()*5));

        pokemon = new JPanel(new GridLayout(2,2,0,0));
        pokemon.setBackground(Color.white);
        pokemon.setBounds(0, Main.height - 200, Main.width - 2, 170);
        mainPane.add(pokemon, new Integer(1));

        currentPanel = 1;
        previousPanel = 0;
        trainerMoves = new ArrayList<>();
        pokemonMoves = new ArrayList<>();
        fight = false;
        fainted = false;

        userHealth = new HealthBar(user.getHealth());
        enemyHealth = new HealthBar(enemy.getHealth());
        addMenuItems(trainerMoves, trainerMoves2);

        makePokemonPanels();

        add(Box.createRigidArea(new Dimension(0,0)));
        add(mainPane);
    }

    private void makePokemonPanels() {
        JPanel enemyPanel = new JPanel(new GridLayout(2,1));
        enemyPanel.add(new JLabel("     " + enemy.getName()));
        enemyPanel.add(enemyHealth);

        Icon icon = enemy.getIcon();
        JLabel pokemonLabel = enemy.getLabel();
        pokemonLabel.setBounds((int)(Main.width*((double)1/2)), 75, icon.getIconWidth(), icon.getIconHeight());

        JPanel userPanel = new JPanel(new GridLayout(2,1));
        userPanel.add(new JLabel("     " + user.getName()));
        userPanel.add(userHealth);

        icon = user.getIcon();
        JLabel userLabel = user.getLabel();
        userLabel.setBounds(50, Main.height-(icon.getIconHeight()+225), icon.getIconWidth(), icon.getIconHeight());

        enemyPanel.setBounds(50, 50, 200, 40);
        enemyPanel.setBackground(Color.white);
        userPanel.setBounds(Main.width - 250, Main.height-(icon.getIconHeight()+50), 200, 40);
        userPanel.setBackground(Color.white);
        mainPane.add(enemyPanel, new Integer(1));
        mainPane.add(pokemonLabel, new Integer(1));
        mainPane.add(userPanel, new Integer(1));
        try {
            mainPane.add(userLabel, new Integer(1));
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
    }

    private void addMenuItems(ArrayList<JLabel> menu, String[] items) {
        if(menu.isEmpty()) {
            for(String s: items) {
                menu.add(new JLabel(s));
                menu.add(new JLabel(">> " + s));
            }
            for(JLabel l: menu) {
                l.setVerticalAlignment(SwingConstants.CENTER);
                l.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

        pokemon.removeAll();

        for(int k = 0; k < trainerMoves.size(); k++) {
            if((k % 2 == 0 && k != 0) || k == 1) {
                pokemon.add(menu.get(k));
            }
        }

        pokemon.revalidate();
        repaint();
    }

    private void swapComponents(ArrayList<JLabel> menuItems) {
        pokemon.remove(menuItems.get(previousPanel+1));
        pokemon.add(menuItems.get(previousPanel), previousPanel/2);
        pokemon.remove(menuItems.get(currentPanel - 1));
        pokemon.add(menuItems.get(currentPanel), currentPanel/2);

        pokemon.revalidate();
        repaint();
    }

    private void victory() {
        pokemon.removeAll();
        pokemon.setLayout(new BorderLayout());
        pokemon.add(new JLabel("Enemy " + enemy.getName() + " has fainted!"));
        fainted = true;
        pokemon.revalidate();
        repaint();
    }

    private void defeat() {
        pokemon.removeAll();
        pokemon.setLayout(new BorderLayout());
        pokemon.add(new JLabel("Your " + user.getName() + " has fainted!"));
        fainted = true;
        main.setPosition(new Dimension(-1818, -4760));
        pokemon.revalidate();
        repaint();
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(fainted)
        {
            if(key == KeyEvent.VK_ENTER) {
                main.changeContent("Background");
            }
        }
        else
        {
            if(key == KeyEvent.VK_ENTER)
            {
                if(fight)
                {
                    int currentAttack = currentPanel / 2;
                    if(user.getMoves()[currentAttack].equals("--")) {
                        return;
                    }
                    else {
                        if(enemyHealth.damageHealth(user.getAttack()[currentAttack])) {
                            victory();
                            return;
                        }

                        int enemyNulls = 0;
                        for(String s: enemy.getMoves())
                        {
                            if(s.equals("--")) {
                                enemyNulls++;
                            }
                        }

                        if(userHealth.damageHealth(enemy.getAttack()[(int)(Math.random()*(4 - enemyNulls))])) {
                            defeat();
                            return;
                        }
                    }
                }
                else
                {
                    if(currentPanel == 1) {
                        addMenuItems(pokemonMoves, user.getMoves());
                        fight = true;
                    }
                    if(currentPanel == 7) {
                        main.changeContent("Background");
                    }
                }
            }

            if(key == KeyEvent.VK_ESCAPE) {
                if(fight) {
                    fight = false;
                    addMenuItems(trainerMoves, trainerMoves2);
                }
            }

            if (key == KeyEvent.VK_LEFT) {
                if(currentPanel == 1 || currentPanel == 5) {
                    return;
                }
                else {
                    previousPanel = currentPanel - 1;
                    currentPanel -= 2;
                }
            }

            if (key == KeyEvent.VK_RIGHT) {
                if(currentPanel == 3 || currentPanel == 7) { return; }
                else {
                    previousPanel = currentPanel - 1;
                    currentPanel += 2;
                }
            }

            if (key == KeyEvent.VK_UP) {
                if(currentPanel == 1 || currentPanel == 3) { return; }
                else if(currentPanel == 5) {
                    previousPanel = currentPanel - 1;
                    currentPanel = 1;
                }
                else {
                    previousPanel = currentPanel - 1;
                    currentPanel = 3;
                }
            }

            if(key == KeyEvent.VK_DOWN) {
                if(currentPanel == 5 || currentPanel == 7) {
                    return;
                }
                else if(currentPanel == 1) {
                    previousPanel = currentPanel - 1;
                    currentPanel = 5;
                }
                else {
                    previousPanel = currentPanel - 1;
                    currentPanel = 7;
                }
            }

            if(fight) {
                swapComponents(pokemonMoves);
            }
            else {
                swapComponents(trainerMoves);
            }
        }
    }

    private class HealthBar extends JPanel {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private double mod, totalHealth, currentHealth;

        HealthBar(int _totalHealth) {
            setBackground(Color.white);
            totalHealth = _totalHealth;
            currentHealth = totalHealth;
            mod = 200/totalHealth;
        }

        boolean damageHealth(int damage) {
            currentHealth -= damage;
            if(currentHealth <= 0) {
                currentHealth = 0;
                return true;
            }
            return false;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.red);
            g.fillRect(0,0,(int) (currentHealth*mod),20);
            g.setColor(Color.black);
            g.fillRect((int) (currentHealth*mod), 0, (int) ((totalHealth - currentHealth)*mod), 20);
        }
    }
}
