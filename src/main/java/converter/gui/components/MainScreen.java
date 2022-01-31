package converter.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import converter.controller.Currency;
import converter.gui.MainFrame;
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
    private JComboBox<String> currencyFROMBox;
    private JLabel rightArrow;
    private JComboBox<String> currencyTOBox;
    /* Row 4 */
    private JTextField amountFROMBox;    
    private JTextField amountTOBox;
    /* Row 5 */
    private DatePickerHolder datePickerHolder;
    /* Row 6 */
    private JTextPane statusMessage;
    /* Row 7 */
    private JTextPane resultMessage;
    /* Row 8 */
    private JButton convertButton;
        
    /* -=-=-=-=-=- TABLE OF CONTENTS -=-=-=-=-=- 
        1. Graphics initialisation
        2. Communication with logic
        3. Convertion processing
        4. Action listener 
    */    

    public MainScreen(MainFrame mainFrame) {        
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.setBounds(50, 0, 540, 600);        
        initComponents();        
    }

    /* -=-=-=-=-=- GRAPHICS INITIALIZATION -=-=-=-=-=- */

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
        this.statusMessage = new JTextPane();
        /* Row 7 */
        this.resultMessage = new JTextPane();
        /* Row 8 */
        this.convertButton = new JButton("CONVERT");                        
    }

    private void setAlignment() {
        /* Row 1 */
        this.convertTextLabel.setHorizontalAlignment(JLabel.CENTER);        
        /* Row 2 */
        this.fromLabel.setHorizontalAlignment(JLabel.CENTER);                
        this.toLabel.setHorizontalAlignment(JLabel.CENTER);                
        /* Row 3 */
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
        /* Font for the DatePicker held in the DatePickerHolder is defined in 
           the DatePickerHolder that class directly, because it should be 
           set before creating THE DatePicker inside the DatePickerHolder. */
        /* Rows 6-7 */
        /* Fonts for rows 6-7 are defined in the html text provided for that rows. */
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
        this.statusMessage.setBounds(0, 280, 540, 90);
        /* Row 7 */
        this.resultMessage.setBounds(0, 385, 540, 40);
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
        this.add(this.statusMessage);
        /* Row 7 */
        this.add(this.resultMessage);
        /* Row 8 */
        this.add(this.convertButton);                        
    }

    private void setSpecificParams() {
        /* Row 3 */
        this.currencyFROMBox.setSelectedIndex(33); // Makes a default selection of USD
        this.currencyTOBox.setSelectedIndex(9); // Makes a default selection of EUR
        /* Row 4 */
        this.amountFROMBox.setText("0.00");
        this.amountTOBox.setText("0.00");      
        this.amountTOBox.setEditable(false);
        /* Row 6 */
        this.statusMessage.setContentType("text/html");          
        this.statusMessage.setText(MessagesFactory.getMessage(LocalDate.now(), MessageType.WELCOME));        
        this.statusMessage.setOpaque(false);
        this.statusMessage.setEditable(false);
        /* Row 7 */
        this.resultMessage.setContentType("text/html");          
        this.resultMessage.setOpaque(false);
        this.resultMessage.setEditable(false);
        showCorrectResult(Currency.USD, Currency.EUR, 0, 0);        
        /* Row 8 */
        this.convertButton.setFocusPainted(false);        
        this.convertButton.addActionListener(getConvertAction());
    }

    /* -=-=-=-=-=- COMMUNICATION WITH LOGIC -=-=-=-=-=- */
    
    public void showUsualResultInGUI(Currency currencyCodeFROM, Currency currencyCodeTO,
                                     double amount, LocalDate responseDate, double result) {
        showCorrectResult(currencyCodeFROM, currencyCodeTO, amount, result);
        String statusMessage = MessagesFactory.getMessage(responseDate, MessageType.USUAL);
        showStatusMessage(statusMessage);        
    }

    public void showBackwardResultInGUI(Currency currencyCodeFROM, Currency currencyCodeTO,
                                        double amount, LocalDate responseDate, double result) {
        showCorrectResult(currencyCodeFROM, currencyCodeTO, amount, result);
        String statusMessage = MessagesFactory.getMessage(responseDate, MessageType.BACKWARD);
        showStatusMessage(statusMessage);
    }

    public void showFailedResultInGUI() {
        String statusMessage = MessagesFactory.getMessage(LocalDate.now(), MessageType.FAILED);
        showStatusMessage(statusMessage);
        showEmptyResult();        
    }

    /* -=-=-=-=-=- CONVERTION PROCESSING -=-=-=-=-=- */    
        
    private void showCorrectResult(Currency currencyCodeFROM, Currency currencyCodeTO,
                                   double amount, double result) {            
        String formattedAmount = getFormattedNumber(amount);
        String formattedResult = getFormattedNumber(result);
        String resultMessageFormatted = getResultMessageFormatted(currencyCodeFROM, currencyCodeTO,
                                                                  formattedAmount, formattedResult);
        this.resultMessage.setText(resultMessageFormatted);                                                     
        this.amountFROMBox.setText(new DecimalFormat("0.00").format(amount));
        this.amountTOBox.setText(new DecimalFormat("0.00").format(result));
    }
    
    private void showEmptyResult() {
        Currency currencyCodeFROM = getCurrencyCodeFROM();
        Currency currencyCodeTO = getCurrencyCodeTO();
        String formattedAmount = getFormattedNumber(0);
        String formattedResult = getFormattedNumber(0);
        String resultMessageFormatted = getResultMessageFormatted(currencyCodeFROM, currencyCodeTO,
                                                                  formattedAmount, formattedResult);        
        this.resultMessage.setText(resultMessageFormatted);  
        resetAmountTOBox();
    }

    private String getResultMessageFormatted(Currency currencyCodeFROM, Currency currencyCodeTO,
                                             String formattedAmount, String formattedResult) {
        /* Show message with precision up to 2 digits after a dot.
           E.g.: 10.23 PLN = 2.60 USD  */        
        String resultMessage = String.format("<b>%s %s</b> = <b> %s %s</b>",
                                            formattedAmount,                                               
                                            currencyCodeFROM.toString(),
                                            formattedResult,
                                            currencyCodeTO.toString());
        String resultMessageFormatted = String.format("""
                                                     <html><center><div style='font-size: 20px; font-family: Arial;'>
                                                     %s
                                                     </div></center></html>        
                                                     """, resultMessage);
        return resultMessageFormatted;
    }

    private void showStatusMessage(String statusMessage) {
        this.statusMessage.setText(statusMessage);
    }

    private void resetAmountTOBox() {
        this.amountTOBox.setText("0.00");
    }

    private Currency getCurrencyCodeFROM() {
        String chosenCurrencyFROMFullRepresentation = this.currencyFROMBox.getSelectedItem().toString();
        return Currency.getCurrencyByFullRepresentation(chosenCurrencyFROMFullRepresentation);
    }

    private Currency getCurrencyCodeTO() {
        String chosenCurrencyTOFullRepresentation = this.currencyTOBox.getSelectedItem().toString();
        return Currency.getCurrencyByFullRepresentation(chosenCurrencyTOFullRepresentation);
    }

    private String getFormattedNumber(double number) {
        return new DecimalFormat("###,##0.00").format(number);
    }    

    /* -=-=-=-=-=- ACTION LISTENER -=-=-=-=-=- */    

    private ActionListener getConvertAction() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!isValidInputAmount()) {
                    String statusMessage = MessagesFactory.getMessage(LocalDate.now(), 
                                                                      MessageType.INVALID_AMOUNT_GENERAL);
                    showStatusMessage(statusMessage);                    
                    showEmptyResult();
                } else if (isExtremeInputAmount()) {
                    String statusMessage = MessagesFactory.getMessage(LocalDate.now(), 
                                                                      MessageType.INVALID_AMOUNT_BIG);
                    showStatusMessage(statusMessage);                    
                    showEmptyResult();
                } else {
                    proceedConversion();
                }                
            }

            private boolean isValidInputAmount() {                
                try {
                    String amountFormatted = amountFROMBox.getText().replaceAll(",", ".");
                    Double.parseDouble(amountFormatted);
                    return true;
                } catch (Exception e) {                    
                    return false;
                }
            }

            private boolean isExtremeInputAmount() {
                String amountFormatted = amountFROMBox.getText().replaceAll(",", ".");
                double inputAmount = Double.parseDouble(amountFormatted);
                return inputAmount > 999_999_999.999_999_999_999;                
            }
            
            private void proceedConversion() {
                Currency currencyCodeFROM = getCurrencyCodeFROM();
                Currency currencyCodeTO = getCurrencyCodeTO();
                String amountFormatted = amountFROMBox.getText().replaceAll(",", ".");                    
                double amount = Double.parseDouble(amountFormatted);
                LocalDate inquiredDate = datePickerHolder.getDate();
                /* Omit conversion through the bank API if both currencies are of the same type. */
                if (currencyCodeFROM == currencyCodeTO) {
                    showUsualResultInGUI(currencyCodeFROM, currencyCodeTO, amount, inquiredDate, amount);                    
                } else {
                    mainFrame.processConvertActionInLogic(currencyCodeFROM, currencyCodeTO,
                                                          amount, inquiredDate);
                }
            }
        };
    }
    
}
