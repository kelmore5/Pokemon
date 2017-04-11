import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
public class Charmander extends Pokemon
{
    private String[] moveSet = { "Tackle", "Fire Spin", "--", "--"};
    private int[] attack = { 10, 20, 0, 0 };
    private int health;
    private JLabel picture;
    private Icon icon;
    private String name;

    Charmander() {
        name = "Charmander";
        health = 150;
        icon = new ImageIcon(Main.fileRoot + "Images/Pokemon/Pokemon/Charmander.png");
        picture = new JLabel(icon);
    }

    public int getHealth()
    {
        return health;
    }

    public String[] getMoves()
    {
        return moveSet;
    }

    public JLabel getLabel()
    {
        return picture;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public String getName()
    {
        return name;
    }

    public int[] getAttack()
    {
        return attack;
    }
}