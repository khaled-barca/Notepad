package sample;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;

import java.util.LinkedList;

public class Notepad extends BorderPane {
    MenuBar menuBar;
    Menu fileMenu;
    Menu editMenu;
    Menu helpMenu;
    TextArea textArea;
    LinkedList<String> oldStates;
    Controller controller;

    public Notepad() {
        super();
        oldStates = new LinkedList<>();
        oldStates.addLast("");
        initNodes();
        createEditMenu();
        createFileMenu();
        setMenuBar();
        placeNodes();
        textArea.paste();
    }

    public void setController(Controller controller) {
        this.controller = controller;
        textArea.setOnKeyPressed(this::keyPressed);
    }

    private void keyPressed(KeyEvent keyEvent) {
        if (oldStates.size() >= 100) {
            for (int i = 0; i < 50; i++) {
                oldStates.removeFirst();
            }
        }
        oldStates.addLast(textArea.getText());
        controller.saved = false;
    }

    private void initNodes() {
        menuBar = new MenuBar();
        textArea = new TextArea();
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");
        helpMenu = new Menu("Help");
    }


    private void createFileMenu() {
        for (FileMenu item : FileMenu.values()) {
            MenuItem menuItem = new MenuItem(item.toString());
            fileMenu.getItems().add(menuItem);
        }
    }

    private void createEditMenu() {
        for (EditMenu item : EditMenu.values()) {
            MenuItem menuItem = new MenuItem(item.toString());
            editMenu.getItems().add(menuItem);
        }
    }

    private void setMenuBar() {

        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
    }

    public void cut() {
        copy();
        deleteSelected();
    }

    public void undo() {
        if (!oldStates.isEmpty()) {
            textArea.setText(oldStates.removeLast());
        }
    }

    public void copy() {
        String selectedText = textArea.getSelectedText();
        if (selectedText.length() > 0) {
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedText);
            Clipboard.getSystemClipboard().setContent(content);
        }
    }

    public void paste() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasString()) {
            IndexRange selection = textArea.getSelection();
            textArea.replaceText(selection, clipboard.getString());
        }
    }

    public void deleteSelected() {
        IndexRange selection = textArea.getSelection();
        textArea.replaceText(selection, "");
    }

    private void placeNodes() {
        setTop(menuBar);
        setCenter(textArea);
    }

    public void selectAll() {

        textArea.selectRange(0, textArea.getLength());
    }
}
