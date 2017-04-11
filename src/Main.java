import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
public class Main extends JFrame implements Runnable, KeyListener {
    final static String fileRoot = "../The_KeLonichles/";
    final static int width = 580;
    final static int height = 750;

    private BattleFrame currentBattle;
    private Background background;
    private CardLayout layout;
    private JPanel mainPanel;
    private Dimension currentPosition;

    private Main() {
        super("Welcome to the World of Pokemon2!");

        layout = new CardLayout();
        mainPanel = new JPanel(layout);

        background = new Background(this);
        currentPosition = background.getPosition();
        currentBattle = new BattleFrame(this);

        mainPanel.add(background, "Background");
        add(mainPanel);
        addKeyListener(this);
    }

    void changeContent(String panelName) {
        if(background.isVisible()) { currentPosition = background.getPosition(); }
        else if(currentBattle.isVisible()) { background.setPosition(currentPosition); }
        layout.show(mainPanel, panelName);
        repaint();
    }

    public void setPosition(Dimension d)
    {
        currentPosition = d;
    }

    void newBattle() {
        currentBattle = new BattleFrame(this);
        mainPanel.add(currentBattle, "Battle");
        changeContent("Battle");
    }

    public void run() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
        javax.swing.SwingUtilities.invokeLater(main);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(background.isVisible()) { background.keyPressed(e); }
        else if(currentBattle.isVisible()) { currentBattle.keyPressed(e); }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(background.isVisible()) { background.keyReleased(e); }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

}
