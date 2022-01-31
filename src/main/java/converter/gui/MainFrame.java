package converter.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.time.LocalDate;

import converter.controller.Controller;
import converter.controller.Currency;
import converter.gui.components.Footer;
import converter.gui.components.MainScreen;

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
        setMainFrameIcon();
    }

    private void setMainFrameIcon() {
        String iconURI = "src/main/resources/icon.png";
        ImageIcon icon = new ImageIcon(iconURI);
        this.setIconImage(icon.getImage());
    }

    public void processConvertActionInLogic(Currency currencyCodeFROM, Currency currencyCodeTO, 
                                            double amount, LocalDate inquiredDate) {
        this.controller.processConvertActionInLogic(currencyCodeFROM, currencyCodeTO,
                                                    amount, inquiredDate);
    }

    public void showUsualResultInGUI(Currency currencyCodeFROM, Currency currencyCodeTO,
                                     double amount, LocalDate responseDate, double result) {
        this.mainScreen.showUsualResultInGUI(currencyCodeFROM, currencyCodeTO,
                                             amount, responseDate, result);
    }

    public void showBackwardResultInGUI(Currency currencyCodeFROM, Currency currencyCodeTO,
                                        double amount, LocalDate responseDate, double result) {        
        this.mainScreen.showBackwardResultInGUI(currencyCodeFROM, currencyCodeTO,
                                                amount, responseDate, result);
    }

    public void showFailedResultInGUI() {
        this.mainScreen.showFailedResultInGUI();
    }
}
