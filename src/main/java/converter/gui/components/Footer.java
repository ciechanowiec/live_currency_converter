package converter.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import converter.gui.MainFrame;

public class Footer extends JPanel {

    private MainFrame mainFrame;
        
    private JLabel bankLogo;    

    public Footer(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBounds(0, 600, 650, 150);
        this.setBackground(new Color(194, 194, 194));   
        initBankLogo();            
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
        this.bankLogo.setBounds(175, 10, 300, 130);
        this.add(this.bankLogo);
    }    
    
}
