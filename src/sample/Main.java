package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FX Notepad");
        Notepad notepad = new Notepad();
        Controller controller = new Controller(notepad, primaryStage);
        primaryStage.setScene(new Scene(notepad, 700, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
