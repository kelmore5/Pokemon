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
public class Pikachu extends Pokemon
{
    private int health;
    private String[] moveSet = {"Tackle", "Thunderbolt", "--", "--"};
    private JLabel picture;
    private Icon icon;
    private String name;
    private int[] attack = { 10, 15, 0, 0};

    Pikachu()
    {
        name = "Pikachu";
        health = 100;
        icon = new ImageIcon(Main.fileRoot + "Images/Pokemon/Pokemon/Pikachu.png");
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
