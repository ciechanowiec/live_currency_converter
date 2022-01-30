package converter.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.BoxLayout;

import converter.Controller;
import converter.gui.screens.Footer;
import converter.gui.screens.MainScreen;

public class MainFrame extends JFrame {
    
    private Controller controller;    
    private MainScreen mainScreen;
    private Footer footer;

    public MainFrame(Controller controller) {    
        this.controller = controller;
        this.mainScreen = new MainScreen(this);
        this.footer = new Footer(this);
        
        setMainFrameParams();
        
        this.add(this.mainScreen);
        this.add(this.footer);
        
        this.setVisible(true);
    }

    private void setMainFrameParams() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Live Currency Converter");
        this.setResizable(false);        
        this.setSize(650, 789);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        // this.getContentPane().setBackground(Color.GRAY);
        setMainFrameIcon();
    }

    private void setMainFrameIcon() {
        String iconURI = "src/main/resources/icon.png";
        ImageIcon icon = new ImageIcon(iconURI);
        this.setIconImage(icon.getImage());
    }
}
