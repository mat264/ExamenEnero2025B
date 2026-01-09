package lopezJohnUserInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;

import javax.swing.SwingConstants;

public abstract class lopezJohnIAStyle {

    public static final Color lopezJohnCOLOR_FRONT       = new Color(200,10,50);
    public static final Color lopezJohnCOLOR_FRONT_LIGHT = new Color(100,100,100);
    public static final Color lopezJohnCOLOR_CURSOS      = Color.CYAN;
    public static final Color lopezJohnCOLOR_BORDER      = Color.lightGray;
    
    public static final Font lopezJohnFONT       = new Font("Arial", Font.PLAIN, 15);
    public static final Font lopezJohnFONT_BOLD  = new Font("Arial", Font.BOLD, 15);
    public static final Font lopezJohnFONT_SMALL = new Font("Arial", Font.PLAIN, 12);

    public static final int lopezJohnALIGHNMENT_LEFT  = SwingConstants.LEFT;
    public static final int lopezJohnALIGHMENT_RIGHT  = SwingConstants.RIGHT;
    public static final int lopezJohnALIGHMENT_CENTER = SwingConstants.CENTER;

    public static final Cursor lopezJohnCURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
    public static final Cursor lopezJohnCURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);

    // public static final URL URL_MAIN   = lopezJohnIAStyle.class.getResource("/lopezJohnUserInterface/lopezJohnResource/lopezJohnImg/Robot.png");
    // public static final URL URL_LOGO   = lopezJohnIAStyle.class.getResource("/lopezJohnUserInterface/lopezJohnResource/lopezJohnImg/RobotLogo.png");
    public static final URL lopezJohnURL_SPLASH = lopezJohnIAStyle.class.getResource("/lopezJohnUserInterface/lopezJohnResource/lopezJohnImg/exobotejemplo.jpg");

    public static void lopezJohnshowMsgError(String message) {
        javax.swing.JOptionPane.showMessageDialog(null, message, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

}
