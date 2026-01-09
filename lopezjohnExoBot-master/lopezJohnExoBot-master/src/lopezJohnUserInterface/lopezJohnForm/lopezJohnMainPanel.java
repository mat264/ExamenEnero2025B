package lopezJohnUserInterface.lopezJohnForm;

import javax.swing.JButton;
import javax.swing.JPanel;

public class lopezJohnMainPanel extends JPanel{

    public JButton lopezJohnbtnHome;
    public JButton lopezJohnbtnLogin;
    public JButton lopezJohnbtnSexo;
    public JButton lopezJohnbtnLocalidad;
    public JButton lopezJohnbtnTest;

    public lopezJohnMainPanel() {
        lopezJohnbtnHome = new JButton("Home");
        lopezJohnbtnLogin = new JButton("Login");
        lopezJohnbtnSexo = new JButton("Sexo");
        lopezJohnbtnLocalidad = new JButton("Localidad");
        lopezJohnbtnTest = new JButton("Test");

        // Add buttons to the panel
        add(lopezJohnbtnHome);
        add(lopezJohnbtnLogin);
        add(lopezJohnbtnSexo);
        add(lopezJohnbtnLocalidad);
        add(lopezJohnbtnTest);
    }

}
