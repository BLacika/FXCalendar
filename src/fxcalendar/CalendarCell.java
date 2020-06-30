package fxcalendar;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.util.Optional;


public class CalendarCell extends AnchorPane {

    private Label lbDate;
    private Label lbStatus;
    private LocalDate date;

    public CalendarCell() {

        lbDate = new Label();
        lbStatus= new Label();

        lbDate.setFont(Font.font("System", FontWeight.BOLD, 25));
        lbStatus.setFont(Font.font("System", 12));
        lbStatus.setWrapText(true);
        lbStatus.setTextAlignment(TextAlignment.LEFT);
        lbStatus.setMaxWidth(90.0);

        setMaxSize(100,80);
        setPrefSize(100, 80);
        setMinSize(100,80);
        setTopAnchor(lbDate, 5.0);
        setRightAnchor(lbDate, 5.0);
        setBottomAnchor(lbStatus, 5.0);
        setLeftAnchor(lbStatus, 5.0);

        getChildren().addAll(lbDate, lbStatus);

        setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Választóablak");
            alert.setContentText("Válaszd ki a megfelelőt!");

            ButtonType buttonTypeOne = new ButtonType("Munkanap");
            ButtonType buttonTypeTwo = new ButtonType("Szabi");
            ButtonType buttonTypeThree = new ButtonType("Táppénz");
            ButtonType buttonTypeFour = new ButtonType("Ünnep");
            ButtonType buttonTypeCancel = new ButtonType("Mégse", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                setLabelStatusText("Munkanap");
            } else if (result.get() == buttonTypeTwo) {
                setLabelStatusText("Fizetett szabadság");
            } else if (result.get() == buttonTypeThree) {
                setLabelStatusText("Beteg szabadság");
            } else if (result.get() == buttonTypeFour){
                setLabelStatusText("Fizetett ünnep");
            } else if (result.get() == buttonTypeCancel){
                setLabelStatusText("");
            }
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }

    public Label getLbDate() {
        return lbDate;
    }

    public Label getLbStatus() {
        return lbStatus;
    }

    public void setLabelDateText(int dayOfMonth) {
        this.lbDate.setText(Integer.toString(dayOfMonth));
    }

    public void setLabelStatusText(String status){
        switch (status){
            case "Munkanap":
                this.lbStatus.setText("Munkanap");
                this.lbStatus.setTextFill(Color.GREEN);
                break;
            case "Fizetett szabadság":
                this.lbStatus.setText("Fizetett szabadság");
                this.lbStatus.setTextFill(Color.BLUE);
                break;
            case "Beteg szabadság":
                this.lbStatus.setText("Beteg szabadság");
                this.lbStatus.setTextFill(Color.ORANGE);
                break;
            case "Fizetett ünnep":
                this.lbStatus.setText("Fizetett ünnep");
                this.lbStatus.setTextFill(Color.RED);
                break;
            case "":
                this.lbStatus.setText("");
                break;
            default:
                this.lbStatus.setText("Hiba");
                this.lbStatus.setTextFill(Color.DARKRED);
        }
    }

    public void setLabels(int dayOfMonth, String status) {
        setLabelDateText(dayOfMonth);
        setLabelStatusText(status);
    }

    public void setWeekEndCells(){
        this.setStyle("-fx-background-color: lightgrey;"+
                "-fx-border-color: grey;");
        this.getLbDate().setTextFill(Color.GREY);
    }
    public void setNotCurrentMonthDay() {
        this.getLbDate().setTextFill(Color.GREY);
    }
    public void setTodayColor() {
        this.getLbDate().setTextFill(Color.RED);
    }
}