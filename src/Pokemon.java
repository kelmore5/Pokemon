import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
public abstract class Pokemon {
    public abstract int getHealth();
    public abstract JLabel getLabel();
    public abstract Icon getIcon();
    public abstract String[] getMoves();
    public abstract String getName();
    public abstract int[] getAttack();
}