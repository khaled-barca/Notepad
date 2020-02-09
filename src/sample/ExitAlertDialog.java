package sample;

import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ExitAlertDialog extends DialogPane {
    Text message;
    Button yesButton;
    Button noButton;
    Button cancelButton;

    public ExitAlertDialog() {
        super();
        initNodes();
        placeNodes();
    }

    private void initNodes() {
        message = new Text("Do you want to save the changes you made to this file ?");
        yesButton = new Button("Yes");
        noButton = new Button("No");
        cancelButton = new Button("Cancel");
    }

    private void placeNodes() {
        setHeader(message);
        HBox hbox = new HBox(yesButton, noButton, cancelButton);
        hbox.setSpacing(20);
        setContent(hbox);
    }
}
