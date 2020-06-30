package fxcalendar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


public class FXCalendar{

    private ArrayList<CalendarCell> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private YearMonth currentYearMonth;
    private Text calendarTitle;
    private Label[] daysOfWeek = new Label[]{
            new Label("Hétfő"), new Label("Kedd"), new Label("Szerda"),
            new Label("Csütörtök"), new Label("Péntek"),
            new Label("Szombat"), new Label("Vasárnap")
    };

    public FXCalendar(YearMonth yearMonth) {
        currentYearMonth=yearMonth;

        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(700);
        Integer col =0;
        for (Label dayName : daysOfWeek) {
            StackPane sp = new StackPane();
            sp.setPrefSize(200, 10);
            sp.getChildren().add(dayName);
            dayLabels.add(sp, col++, 0);
        }

        GridPane calendar = new GridPane();
        calendar.setPrefSize(700, 400);
        calendar.setGridLinesVisible(true);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                CalendarCell ap = new CalendarCell();
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }

        calendarTitle = new Text("");
        HBox titleBar = new HBox(calendarTitle);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(10));
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar);
        view.setSpacing(10);
    }

    private void populateCalendar(YearMonth yearMonth) {
        Locale local = new Locale("hu", "HU");
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (CalendarCell ap : allCalendarDays) {
            int txt= calendarDate.getDayOfMonth();
            ap.setLabelDateText(txt);
            if (calendarDate.getDayOfWeek().toString().equals("SUNDAY")) ap.setWeekEndCells();
            if (calendarDate.getDayOfWeek().toString().equals("SATURDAY")) ap.setWeekEndCells();
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        DateTimeFormatter hungarian = DateTimeFormatter.ofPattern("yyyy MMMM", new Locale("hu", "HU"));
        calendarTitle.setText(yearMonth.format(hungarian));
        calendarTitle.setStyle("-fx-font-size: 20px;");
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<CalendarCell> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<CalendarCell> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }
}