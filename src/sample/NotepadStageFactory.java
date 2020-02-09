package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotepadStageFactory {
    public static Stage newStage() {
        Stage stage = new Stage();
        stage.setTitle("FX Notepad");
        Notepad notepad = new Notepad();
        Controller controller = new Controller(notepad, stage);
        stage.setScene(new Scene(notepad, 700, 700));
        return stage;
    }
}
