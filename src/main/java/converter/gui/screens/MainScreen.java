package converter.gui.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class MainScreen extends JPanel {

    private MainFrame mainFrame;
    /* Row 1 */
    private JLabel convertTextLabel;
    /* Row 2 */
    private JLabel fromLabel;
    private JLabel toLabel;
    /* Row 3 */
    private JComboBox currencyFROMBox;
    private JLabel rightArrow;
    private JComboBox currencyTOBox;
    /* Row 4 */
    private JTextField amountFROMBox;    
    private JTextField amountTOBox;
    /* Row 5 */
    private DatePickerHolder datePickerHolder;
    /* Row 6 */
    private JTextPane message;
    /* Row 7 */
    private JTextPane result;
    /* Row 8 */
    private JButton convertButton;
    
    //TODO: deal with incorrect amount
    //TODO: Reset result after incorrect input
    //TODO: opening inquiry

    public MainScreen(MainFrame mainFrame) {        
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBounds(50, 0, 540, 600);        
        initComponents();            
    }

    /* -=-=-=-=-=- GRAPHICS -=-=-=-=-=- */

    private void initComponents() {                
        createComponents();          
        setAlignment();
        setFonts();                  
        setBounds();
        addComponentsToPanel();      
        setSpecificParams();
    }    

    private void createComponents() {
        /* Row 1 */
        this.convertTextLabel = new JLabel("CONVERT");
        /* Row 2 */
        this.fromLabel = new JLabel("FROM");        
        this.toLabel = new JLabel("TO");        
        /* Row 3 */
        this.currencyFROMBox = new JComboBox<String>(Currency.getAllFullRepresentations());
        this.rightArrow = new JLabel("→");
        this.currencyTOBox = new JComboBox<String>(Currency.getAllFullRepresentations());
        /* Row 4 */
        this.amountFROMBox = new JTextField();
        this.amountTOBox = new JTextField();
        /* Row 5 */
        this.datePickerHolder = new DatePickerHolder();
        /* Row 6 */
        this.message = new JTextPane();
        /* Row 7 */
        this.result = new JTextPane();
        /* Row 8 */
        this.convertButton = new JButton("CONVERT");                        
    }

    private void setAlignment() {
        this.convertTextLabel.setHorizontalAlignment(JLabel.CENTER);        
        this.fromLabel.setHorizontalAlignment(JLabel.CENTER);                
        this.rightArrow.setHorizontalAlignment(JLabel.CENTER);                                   
    }

    private void setFonts() {
        /* Row 1 */
        this.convertTextLabel.setFont(convertTextLabel.getFont().deriveFont(25f));      
        /* Row 2 */
        this.fromLabel.setFont(fromLabel.getFont().deriveFont(18f));                
        this.toLabel.setFont(toLabel.getFont().deriveFont(18f));                
        /* Row 3 */
        this.currencyFROMBox.setFont(this.currencyFROMBox.getFont().deriveFont(15f));
        this.rightArrow.setFont(this.currencyFROMBox.getFont().deriveFont(65f));
        this.currencyTOBox.setFont(this.currencyTOBox.getFont().deriveFont(15f));
        /* Row 4 */
        this.amountFROMBox.setFont(this.amountFROMBox.getFont().deriveFont(17f));
        this.amountTOBox.setFont(this.amountTOBox.getFont().deriveFont(17f));
        /* Row 5 */
        /* Font for the DatePicker held in the DatePickerHolder is defined in that class directly, 
           because it should be set before creating DatePicker inside DatePickerHolder. */
        /* Rows 6-7 */
        /* Fonts for rows 6-7 are defined in the html text input for that rows. */
        /* Row 8 */    
        this.convertButton.setFont(this.convertButton.getFont().deriveFont(17f));                
    }

    private void setBounds() {
        /* Row 1 */
        this.convertTextLabel.setBounds(0, 30, 540, 20);
        /* Row 2 */
        this.fromLabel.setBounds(90, 80, 60, 20);        
        this.toLabel.setBounds(400, 80, 60, 20);        
        /* Row 3 */
        this.currencyFROMBox.setBounds(0, 110, 230, 40);
        this.rightArrow.setBounds(230, 105, 80, 40);
        this.currencyTOBox.setBounds(310, 110, 230, 40);
        /* Row 4 */
        this.amountFROMBox.setBounds(0, 160, 231, 40);
        this.amountTOBox.setBounds(310, 160, 230, 40);
        /* Row 5 */
        this.datePickerHolder.setBounds(0, 205, 230, 40);
        /* Row 6 */
        this.message.setBounds(0, 280, 540, 90);
        /* Row 7 */
        this.result.setBounds(0, 385, 540, 40);
        /* Row 8 */
        this.convertButton.setBounds(200, 490, 140, 40);        
    }

    private void addComponentsToPanel() {
        /* Row 1 */
        this.add(this.convertTextLabel);        
        /* Row 2 */
        this.add(this.fromLabel);    
        this.add(this.toLabel);    
        /* Row 3 */
        this.add(this.currencyFROMBox);
        this.add(this.rightArrow);
        this.add(this.currencyTOBox);
        /* Row 4 */
        this.add(this.amountFROMBox);
        this.add(this.amountTOBox);
        /* Row 5 */
        this.add(this.datePickerHolder);
        /* Row 6 */
        this.add(this.message);
        /* Row 7 */
        this.add(this.result);
        /* Row 8 */
        this.add(this.convertButton);                        
    }

    private void setSpecificParams() {
        this.currencyFROMBox.setSelectedIndex(33);
        this.currencyTOBox.setSelectedIndex(9);

        this.amountFROMBox.setText("10.00");
        this.amountTOBox.setEditable(false);
        this.amountTOBox.setText("230.00");      
        
        this.message.setContentType("text/html");  
        //TODO: remove message
        this.message.setText(MessagesFactory.getMessage(LocalDate.now(), MessageType.INVALID_AMOUNT_GENERAL));        
        this.message.setOpaque(false);
        this.message.setEditable(false);

        this.result.setContentType("text/html");  
        //TODO: remove this        
        showResult(Currency.USD, Currency.PLN, 12.01, 6.2134);        
        this.result.setOpaque(false);
        this.result.setEditable(false);

        this.convertButton.setFocusPainted(false);        
        this.convertButton.addActionListener(getConvertAction());
    }
    
    /* -=-=-=-=-=- CONVERTION PROCESSING -=-=-=-=-=- */

    private void showMessage(String message) {
        this.message.setText("");
        this.message.setText(message);
    }

    private void resetAmountTOBox() {
        this.amountTOBox.setText("0.00");
    }
    
    /* General method for showing result, i.a. to be used by logic */
    private void showResult(Currency currencyCodeFROM, Currency currencyCodeTO,
                            double amount, double result) {
        //TODO: reject showing result if amount or result > 999999999.99999999
        /* Show with the precision up to 2 digits after a dot.
           E.g.: 10.23 PLN = 2.60 USD  */
        String formattedAmount = new DecimalFormat("###,##0.00").format(amount);
        String formattedResult = new DecimalFormat("###,##0.00").format(result);
        String resultMessage = String.format("<b>%s %s</b> = <b> %s %s</b>",formattedAmount,                                               
                                                             currencyCodeFROM.toString(),
                                                             formattedResult,
                                                             currencyCodeTO.toString());
        String resultMessageFormatted = String.format("""
                                                     <html><center><div style='font-size: 20px; font-family: Arial;'>
                                                     %s
                                                     </div></center></html>        
                                                     """, resultMessage);
        this.result.setText(resultMessageFormatted);                                                     
    }

    /* Specific  method for showing result to be used internally by GUI */
    private void showEmptyResult() {

        Currency currencyCodeFROM = Currency.getCurrencyByFullRepresentation
                                    (this.currencyFROMBox.getSelectedItem().toString());
        Currency currencyCodeTO = Currency.getCurrencyByFullRepresentation
                                    (this.currencyTOBox.getSelectedItem().toString());
        double amount = 0;
        double result = 0;
        showResult(currencyCodeFROM, currencyCodeTO, amount, result);
    }

    private ActionListener getConvertAction() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isValidInputAmount()) {
                    showMessage(MessagesFactory.getMessage(LocalDate.now(), MessageType.INVALID_AMOUNT_GENERAL));
                    resetAmountTOBox();
                    showEmptyResult();
                } else if (isExtremeInputAmount()) {
                    showMessage(MessagesFactory.getMessage(LocalDate.now(), MessageType.INVALID_AMOUNT_BIG));
                    resetAmountTOBox();
                    showEmptyResult();
                }
                
            }

            private boolean isValidInputAmount() {
                String amountFormatted = amountFROMBox.getText().replaceAll(",", ".");
                try {
                    Double.parseDouble(amountFormatted);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            private boolean isExtremeInputAmount() {
                double inputAmount = Double.parseDouble(amountFROMBox.getText());
                return inputAmount > 999_999_999.999_999_999_999;                
            }
            
            private void proceedConversion() {

            }

        };
    }

    
    
}
