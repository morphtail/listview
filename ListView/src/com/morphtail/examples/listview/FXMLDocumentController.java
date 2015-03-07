/*
 * The MIT License
 *
 * Copyright 2015 Silverleaf Technology Ltd.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.morphtail.examples.listview;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Silverleaf Technology Ltd
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ListView listView;
    @FXML
    private Label id;
    @FXML
    private TextField name;
    @FXML
    private TextField value;
    @FXML
    private Button save;
    @FXML
    private Button create;
    @FXML
    private Button delete;
    @FXML
    private Button copy;

    Optional<ListViewItem> currentListItemEntity = Optional.ofNullable(null);

    @FXML
    private void listViewMouseClick(Event event) {
        userSaveAnyChangesOption(event);
        ObservableList item = listView.getSelectionModel().getSelectedItems();
        currentListItemEntity = Optional.ofNullable((ListViewItem) item.get(0));
        updateFields();

    }

    private void userSaveAnyChangesOption(Event event) {
        if (!save.disableProperty().get()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Save current changes");
            alert.setContentText(" Item " + currentListItemEntity.get().toString() + " has been updated do you want to save changes?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.saveAction(event);
            }
        }
    }

    @FXML
    private void createAction(ActionEvent event) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.INFO, "Create Action Called.");
        userSaveAnyChangesOption(event);

        currentListItemEntity = Optional.ofNullable(listService.createNewEmptyItem());
        updateFields();
        listView.getSelectionModel().selectLast();
    }

    @FXML
    private void saveAction(Event event) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.INFO, "Save Action Called.");
        this.currentListItemEntity.get().updateValues(name.getText(), value.getText());
        save.setDisable(true);
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.INFO, "Delete Action Called.");
        // Confirm action with user
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setContentText("Delete Item " + currentListItemEntity.get().toString());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            listService.observableListViewItems.remove(currentListItemEntity.get());
        }
        ObservableList item = listView.getSelectionModel().getSelectedItems();
        currentListItemEntity = Optional.ofNullable((ListViewItem) item.get(0));
        updateFields();

    }

    @FXML
    private void copyAction(ActionEvent event) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.INFO, "Copy Action Called.");
        ListViewItem newItem = new ListViewItem(currentListItemEntity.get());
        listService.observableListViewItems.add(newItem);
        currentListItemEntity = Optional.ofNullable(newItem);

        updateFields();
        listView.getSelectionModel().selectLast();
    }

    @FXML
    private void valueAction(ActionEvent event) {
        System.out.println("valueAction");

    }

    @FXML
    private void nameAction(ActionEvent event) {
        System.out.println("nameAction");

    }

    private void updateFields() {
        if (currentListItemEntity.isPresent()) {
            value.setText(currentListItemEntity.get().getValue());
            name.setText(currentListItemEntity.get().getName());
            id.setText(currentListItemEntity.get().getId());

        } else {
            value.setText("");
            name.setText("");
            id.setText("");
        }

    }

    ListViewService listService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listService = ListViewService.getInstance();
        listService.init();
        listView.setItems(listService.observableListViewItems);
        currentListItemEntity = Optional.of(listService.observableListViewItems.get(0));
        updateFields();

        // Handle TextField text changes.
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (currentListItemEntity.get().getName().equals(newValue)) {
                save.setDisable(true);
            } else {
                save.setDisable(false);
            }
        });
        // Handle TextField text changes.
        value.textProperty().addListener((observable, oldValue, newValue) -> {
            if (currentListItemEntity.get().getValue().equals(newValue)) {
                save.setDisable(true);
            } else {
                save.setDisable(false);
            }
        });
        save.setDisable(true);
    }

}
