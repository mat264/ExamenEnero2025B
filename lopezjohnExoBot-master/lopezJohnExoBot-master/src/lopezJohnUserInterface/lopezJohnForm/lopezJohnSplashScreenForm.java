package lopezJohnUserInterface.lopezJohnForm;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import lopezJohnUserInterface.lopezJohnIAStyle;

public abstract class lopezJohnSplashScreenForm {
    
    private static JFrame         lopezJohnsCFrame;
    private static JProgressBar   lopezJohnsCProgressBar; 
    private static ImageIcon      lopezJohnsCImageIcon;
    private static JLabel         lopezJohnsCImageLabel;

    public static void lopezJohnshow(){
        
        lopezJohnsCImageIcon   = new ImageIcon(lopezJohnIAStyle.lopezJohnURL_SPLASH);
        lopezJohnsCImageLabel  = new JLabel(lopezJohnsCImageIcon);
        lopezJohnsCProgressBar = new JProgressBar(0, 100);
        
        lopezJohnsCProgressBar.setStringPainted(true);

        lopezJohnsCFrame = new JFrame();
        lopezJohnsCFrame.setUndecorated(true);
        lopezJohnsCFrame.getContentPane().add(lopezJohnsCImageLabel, BorderLayout.CENTER);
        lopezJohnsCFrame.add(lopezJohnsCProgressBar, BorderLayout.SOUTH);
        lopezJohnsCFrame.setSize(lopezJohnsCImageIcon.getIconWidth(), lopezJohnsCImageIcon.getIconHeight());
        lopezJohnsCFrame.setLocationRelativeTo(null); //Centrar en pantalla

        lopezJohnsCFrame.setVisible(true);
        for(int i=0; i<=100; i++){
            try{
                Thread.sleep(50); //50ms
;            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lopezJohnsCProgressBar.setValue(i);
        }
        lopezJohnsCFrame.setVisible(false);
    }
    
}
