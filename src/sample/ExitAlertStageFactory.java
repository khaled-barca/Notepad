package sample;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExitAlertStageFactory {
    public static Stage newStage() {
        Stage stage = new Stage();
        ExitAlertDialog exitAlertDialog = new ExitAlertDialog();
        stage.setScene(new Scene(exitAlertDialog, 400, 100));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        return stage;
    }
}
