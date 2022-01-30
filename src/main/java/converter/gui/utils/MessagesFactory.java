package converter.gui.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class MessagesFactory {

    public static enum MessageType {
        USUAL, BACKWARD, FAILED,
        INVALID_AMOUNT_GENERAL, INVALID_AMOUNT_BIG
    }

    public static String getMessage(LocalDate responseDate, MessageType messageType) {
        switch (messageType) {
            case USUAL:
                return getUsualMessage(responseDate);
            case BACKWARD:
                return getBackwardMessage(responseDate);
            case FAILED:
                return getFailedMessage();
            case INVALID_AMOUNT_GENERAL:
                return getInvalidAmountGeneralMessage();
            case INVALID_AMOUNT_BIG:
                return getInvalidAmountBigMessage();
            default:
                throw new IllegalArgumentException("Provided message type doesn't exist.");
        }
    }

    private static String getUsualMessage(LocalDate responseDate) {
        String formattedDate = responseDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        return String.format("""
                             <html><center><div style='font-size: 13px; font-family: Arial;'>
                             <br><br>The result of the conversion for <b>%s</b>:<br>
                             </div></center></html>        
                             """, formattedDate);
    }

    private static String getBackwardMessage(LocalDate responseDate) {
        String formattedDate = responseDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        return String.format("""
                             <html><center><div style='font-size: 13px; font-family: Arial;'>
                             National Bank of Poland hasn’t published the exchange<br>
                             rate for the inquired day and for the inquired currencies. <br>
                             The previous available exchange rate was released <br>
                             on <b>%s</b>. The result of the conversion for that day is:
                             </div></center></html>                 
                             """, formattedDate);
    }

    private static String getFailedMessage() {
        return """
               <html><center><div style='font-size: 13px; font-family: Arial;'>
               National Bank of Poland hasn’t published the exchange<br>
               rate for the inquired day and for the inquired currencies. <br>
               Neither has the exchange rate been published in<br>
               the period of 5 days preceding the inquired day.
               </div></center></html>                                    
               """;
    }

    private static String getInvalidAmountGeneralMessage() {
        return """
               <html><center><div style='font-size: 13px; font-family: Arial;'>
               <br>Invalid input amount.<br>
               The program cannot proceed with conversion.<br>
               </div></center></html>                     
               """;
    }

    private static String getInvalidAmountBigMessage() {
        return """
               <html><center><div style='font-size: 13px; font-family: Arial;'>
               <br>Too big input amount.<br>
               The program cannot proceed with conversion.<br>
               </div></center></html>                     
               """;
    }
        
}
