package sample;


import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    Notepad view;
    Stage primaryStage;
    boolean saved;
    File file;

    public Controller(Notepad notepad, Stage stage) {
        view = notepad;
        saved = true;
        view.setController(this);
        primaryStage = stage;
        setFileMenuActions();
        setEditMenuActions();
        view.textArea.clear();
        stage.setOnCloseRequest(e -> {
            exit();
            e.consume();
        });
    }

    private void setFileMenuActions() {
        for (MenuItem item : view.fileMenu.getItems()) {
            FileMenu fm = FileMenu.getFileMenu(item.getText());
            switch (fm) {
                case EXIT:
                    item.setOnAction(this::exit);
                    break;
                case NEW:
                    item.setOnAction(this::newFile);
                    break;
                case OPEN:
                    item.setOnAction(this::openFile);
                    break;
                default:
                    item.setOnAction(this::save);
            }
        }
    }

    private void setEditMenuActions() {
        for (MenuItem item : view.editMenu.getItems()) {
            EditMenu em = EditMenu.getEditMenu(item.getText());
            switch (em) {
                case COPY:
                    item.setOnAction(e -> view.copy());
                    break;
                case PASTE:
                    item.setOnAction(e -> view.paste());
                    break;
                case UNDO:
                    item.setOnAction(e -> view.undo());
                    break;
                case DELETE:
                    item.setOnAction(e -> view.deleteSelected());
                    break;
                case SELECT_ALL:
                    item.setOnAction(e -> view.selectAll());
                    break;
                default:
                    item.setOnAction(e -> view.cut());
            }
        }
    }

    private void exit(ActionEvent e) {

        exit();
    }

    private void exit() {
        if (!saved) {
            Stage exitAlertStage = ExitAlertStageFactory.newStage();
            ExitAlertDialog dialog = (ExitAlertDialog) exitAlertStage.getScene().getRoot();
            dialog.yesButton.setOnAction(ae -> {
                exitAlertStage.close();
                save(new ActionEvent());
                primaryStage.close();
            });
            dialog.noButton.setOnAction(ae -> {
                exitAlertStage.close();
                primaryStage.close();
            });
            dialog.cancelButton.setOnAction(ae -> exitAlertStage.close());
            exitAlertStage.initOwner(primaryStage);
            exitAlertStage.showAndWait();
        } else {
            primaryStage.close();
        }
    }

    private void newFile(ActionEvent e) {
        Stage stage = NotepadStageFactory.newStage();
        stage.show();
    }

    private void save(ActionEvent e) {
        if(file == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.setInitialFileName("sample.txt");
            file = fileChooser.showSaveDialog(primaryStage);
        }
        if(file != null) {
            writeCharacterStream();
            saved = true;
        }
    }

    private void openFile(ActionEvent e) {
        FileChooser fileDialog = new FileChooser();
        fileDialog.setTitle("Open File");
        file = fileDialog.showOpenDialog(primaryStage);
        if(file != null) {
            readFileByteStream(file);
        }

    }

    private void readFileCharacterStream() {
        view.textArea.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                view.textArea.appendText(line);
                view.textArea.appendText("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCharacterStream() {
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            bf.write(view.textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeByteStream() {
        try(FileOutputStream fos = new FileOutputStream(file)) {
            byte[] bytes = view.textArea.getText().getBytes();
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileByteStream(File file) {
        view.textArea.clear();
        try(FileInputStream fis = new FileInputStream(file)) {
            int b;
            while ((b = fis.read()) != -1) {
                char c = (char) b;
                view.textArea.appendText(c + "");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
