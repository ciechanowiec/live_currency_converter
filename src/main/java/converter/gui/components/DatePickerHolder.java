package converter.gui.components;

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalDate;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

/* This class uses LGoodDatePicker library.
   Library description: https://github.com/LGoodDatePicker/LGoodDatePicker
   Library docs: https://javadoc.io/doc/com.github.lgooddatepicker/LGoodDatePicker/latest/index.html
   Library usage example: https://github.com/LGoodDatePicker/LGoodDatePicker/blob/master/Project/src/main/java/com/github/lgooddatepicker/demo/FullDemo.java */
public class DatePickerHolder extends JPanel {

    private DatePicker datePicker;

    public DatePickerHolder() {
        this.setLayout(new FlowLayout());

        LocalDate firstAllowedDate = LocalDate.parse("2002-01-02");
        LocalDate lastAllowedDate = LocalDate.now();

        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        dateSettings.setFormatForDatesCommonEra("d MMMM yyyy");
        dateSettings.setAllowKeyboardEditing(false);        
        dateSettings.setFontValidDate(new Font("Default", Font.PLAIN, 17));
        dateSettings.setSizeTextFieldMinimumWidth(201);            
        
        this.datePicker = new DatePicker(dateSettings);        
        this.datePicker.setDateToToday();        
        dateSettings.setDateRangeLimits(firstAllowedDate, lastAllowedDate);
        
        this.add(this.datePicker);
    }

    public DatePicker getDatePicker() {
        return this.datePicker;
    }

    public LocalDate getDate() {
        return this.datePicker.getDate();
    }
    
}
