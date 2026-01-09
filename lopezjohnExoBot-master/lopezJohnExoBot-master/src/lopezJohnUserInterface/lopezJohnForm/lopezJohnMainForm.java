package lopezJohnUserInterface.lopezJohnForm;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JPanel;

import lopezJohnUserInterface.lopezJohnIAStyle;

import javax.swing.JFrame;

public class lopezJohnMainForm extends JFrame{

    JPanel panelMain = new lopezJohnMainPanel();

    public lopezJohnMainForm (String tittleApp){
        lopezJohncustomizeComponent(tittleApp);
        ((lopezJohnMainPanel)panelMain).lopezJohnbtnHome.addActionListener(         e -> lopezJohnSetPanel(new JPanel()));
        ((lopezJohnMainPanel)panelMain).lopezJohnbtnLogin.addActionListener(        e -> lopezJohnSetPanel(new JPanel()));
        ((lopezJohnMainPanel)panelMain).lopezJohnbtnSexo.addActionListener(         e -> lopezJohnSetPanel(new JPanel()));
        ((lopezJohnMainPanel)panelMain).lopezJohnbtnLocalidad.addActionListener(    e -> lopezJohnSetPanel(new JPanel()));

        ((lopezJohnMainPanel)panelMain).lopezJohnbtnTest.addActionListener(         e -> { lopezJohnIAStyle.lopezJohnshowMsgError("Mensaje de error");});

    }

    private void lopezJohnSetPanel(JPanel formularioPanel){
        Container container = getContentPane();
        container.remove(panelMain);
        panelMain = formularioPanel;
        container.add(panelMain, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    private void lopezJohncustomizeComponent(String tittleApp){
        setTitle(tittleApp);
        setSize(800,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(panelMain, BorderLayout.WEST);
        setVisible(true);
    }
}
