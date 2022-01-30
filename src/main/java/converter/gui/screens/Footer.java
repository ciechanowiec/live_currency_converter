package converter.gui.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import converter.Currency;
import converter.gui.MainFrame;
import converter.gui.utils.DatePickerHolder;
import converter.gui.utils.MessagesFactory;
import converter.gui.utils.MessagesFactory.MessageType;

public class Footer extends JPanel {

    private MainFrame mainFrame;
    
    private JLabel dataProvidedText;
    private JLabel bankLogo;    

    public Footer(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBounds(0, 600, 650, 150);
        this.setBackground(new Color(194, 194, 194));   
        initComponents();
    }

    private void initComponents() {
        initBankLogo();        
        initDataProvidedText();        
        this.dataProvidedText.setBounds(257, 9, 150, 40);
        this.bankLogo.setBounds(175, 10, 300, 130);
        // this.add(this.dataProvidedText);
        this.add(this.bankLogo);
    }

    private void initBankLogo() {
        BufferedImage bankLogoBuffered = null;
        try {
            bankLogoBuffered = ImageIO.read(new File("src/main/resources/nbp_logo.png"));
        } catch (IOException e) {            
            e.printStackTrace();
        }
        this.bankLogo = new JLabel(new ImageIcon(bankLogoBuffered.getScaledInstance(300, 103, Image.SCALE_SMOOTH)));
        this.bankLogo.setMinimumSize(new Dimension(300, 130));
        this.bankLogo.setPreferredSize(new Dimension(300, 130));
        this.bankLogo.setMaximumSize(new Dimension(300, 130));        
    }    

    private void initDataProvidedText() {
        this.dataProvidedText = new JLabel("data provided by:");
        // this.dataProvidedText.setFont(this.dataProvidedText.getFont().deriveFont(14f));        
        this.dataProvidedText.setFont(new Font("Times New Roman", Font.PLAIN, 13));        
        this.dataProvidedText.setForeground(new Color(29, 45, 79));        
        
        this.dataProvidedText.setHorizontalAlignment(JLabel.CENTER);                             
    }
    
}
