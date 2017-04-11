import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * <pre class="doc_header">
 * <p>
 * </pre>
 *
 * @author kelmore5
 * @custom.date 3/18/17
 */
@SuppressWarnings({"StatementWithEmptyBody", "unused"})
public class Character {
    private BufferedImage characterUP, characterDOWN, characterLEFT, characterRIGHT, currentCharacter;
    private int x, dx, y, dy;
    public Main main;
    private Dimension futurePosition;
    private KeyEvent currentKeyEvent;
    private boolean keyPressed;

    Character(Main _main) {
        main = _main;

        x = -1710;
        y = -4760;

        try {
            characterUP = ImageIO.read(new File(Main.fileRoot + "Images/Pokemon/Sprite/Up.png"));
            characterDOWN = ImageIO.read(new File(Main.fileRoot + "Images/Pokemon/Sprite/Down.png"));
            characterLEFT = ImageIO.read(new File(Main.fileRoot + "Images/Pokemon/Sprite/Left.png"));
            characterRIGHT = ImageIO.read(new File(Main.fileRoot + "Images/Pokemon/Sprite/Right.png"));
        }
        catch(IOException ex) {
            characterUP = null;
            characterDOWN = null;
            characterLEFT = null;
            characterLEFT = null;
        }

        futurePosition = new Dimension();
        currentKeyEvent = null;
        keyPressed = false;

        currentCharacter = characterUP;
    }

    public Dimension getPosition()
    {
        return new Dimension(x, y);
    }

    public void setPosition(Dimension d) {
        x = (int) d.getWidth();
        y = (int) d.getHeight();
    }

    void move() {
        if(keyPressed)  {
            if(checkSpace()) {
                x += dx;
                y += dy;
            }
        }

    }

    private void manualMove() {
        x += dx;
        y += dy;
    }

    void keyPressed(KeyEvent e) {
        keyPressed = true;
        currentKeyEvent = e;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 27;
            if(dy != 0)
                dy = 0;
            currentCharacter = characterLEFT;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = -27;
            if(dy != 0)
                dy = 0;
            currentCharacter = characterRIGHT;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 27;
            if(dx != 0)
                dx = 0;
            currentCharacter = characterUP;
        }

        if(key == KeyEvent.VK_DOWN) {
            dy = -27;
            if(dx != 0)
                dx = 0;
            currentCharacter = characterDOWN;
        }

        //System.out.println("( " + x + ", " + y + " )");
    }

    void keyReleased(KeyEvent e) {
        keyPressed = false;
        currentKeyEvent = e;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
            dx = 0;

        if (key == KeyEvent.VK_RIGHT)
            dx = 0;

        if (key == KeyEvent.VK_UP)
            dy = 0;

        if(key == KeyEvent.VK_DOWN)
            dy = 0;
    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }

    public int getdx()
    {
        return dx;
    }

    public int getdy()
    {
        return dy;
    }

    Image getImage()
    {
        return currentCharacter;
    }

    private boolean checkSpace() {
        int tempx = x + dx;
        int tempy = y + dy;

        if(tempy == -5057) {
            return false;
        }
        else if(tempx == -2007 && tempy <= -3383 && tempy >= -5057) {
            return false;
        }
        else if(tempx <= -1602 && tempx >= -1683 && tempy >= -5030 && tempy <= -5003) {
            return false;
        }
        else if(tempx == -1440 && tempy >= -5030 && tempy <= -3329) {
            return false;
        }
        else if(tempx <= -1737 && tempx >= -1764 && tempy >= -4517 && tempy <= -4409 &&
                (tempy != futurePosition.height || tempx != futurePosition.width)) {
            futurePosition = new Dimension(tempx, tempy);
            Random i = new Random();
            if(i.nextDouble() < 0.2)
            {
                manualMove();
                keyReleased(currentKeyEvent);
                main.newBattle();
            }
            return false;
        }
        else if(tempx <= -1467 && tempx >= -1980 && tempy >= -4571 && tempy <= -4436) {
            return tempx <= -1737 && tempx >= -1764;
        }
        else if(tempy == -4841 && tempx <= -1548 && tempx >= -1656) {
            if(tempx == -1656 && tempy == -4841) {
                //TODO: Sign to left of Professor Oak's House
            }
            return false;
        }
        else if(tempy == -4976 && tempx <= -1764 && tempx >= -1899) {
            return false;
        }
        else if(tempx <= -1764 && tempy >= -4895 && tempx >= -1926 && tempy <= -4787) {
            return tempx == -1845 && tempy == -4895;
        }
        else if(tempx <= -1791 && tempy >= -4733 && tempx >= -1899 && tempy <= -4625) {
            return tempx == -1818 && tempy == -4733;
        }
        else if(tempx == -1764 && tempy == -4733) {
            //Sign next to Character's house
            return false;
        }
        else if(tempx <= -1548 && tempy >= -4733 && tempx >= -1656 && tempy <= -4652) {
            return tempx == -1575 && tempy == -4733;
        }
        else if(tempx == -1521 && tempy == -4733) {
            //Sign next to Rival's house
            return false;
        }
        else if(tempx == -1548 && tempy == -4922) {
            //Sign in Pallet town
            return false;
        }
        else if(tempy == -4301 && dy == 27 && (tempx <= -1656 || tempx >= -1548)) {
            return false;
        }
        else if(tempx <= -1467 && tempy >= -4166 && tempx >= -1710 && tempy <= -4139) {
            return false;
        }
        else if(tempx <= -1899 && tempy == -4166 && dy == 27 && tempx >= -1980) {
            return false;
        }
        else if(tempy == -4004 && dy == 27 && !(tempx == -1521 || (tempx <= -1656 && tempx >= -1710))) {
            return false;
        }
        else if(tempx <= -1467 && tempy >= -3896 && tempx >= -1521 && tempy <= -3869) {
            return false;
        }
        else if(tempy == -3869 && dy == 27 && tempx <= -1521 && tempx >= -1656) {
            return false;
        }
        else if(tempx <= -1683 && tempy >= -3896 && tempx >= -1818 && tempy <= -3869) {
            return false;
        }
        else if(tempy == -3734 && dy == 27 && tempx<= -1467 && tempx >= -1602) {
            return false;
        }
        else if(tempy >= -3761 && tempx <= -1629 && tempx >= -1656 && tempy <= -3572) {
            return false;
        }
        else if(tempy == -3599 && dy == 27 && tempx >= -1818) {
            return false;
        }
        else if(tempy >= -3518 && tempx <= -1278 && tempx >= -2358 && tempy <= -3383) {
            return tempx <= -1683 && tempx >= -1764;
        }
        else if(tempy == -3329) {
            //main.changeContent("Battle");
        }

        return true;
    }
}