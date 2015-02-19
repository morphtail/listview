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
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private TextField name;

    @FXML
    private TextField value;
    
    ListViewItem listItemEntity = new ListViewItem();
    
    @FXML
    private void listViewMouseClick(MouseEvent event) {
        System.out.println("You clicked me!");
       // ObservableList item = listView.getSelectionModel().getSelectedItems();
        //listItemEntity = (ListViewItem) item.get(0);
        //label.setText("Hllo World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListViewService listService = ListViewService.getInstance();
        listService.init();
        listView.setItems(listService.observableListViewItems);

        name.textProperty().bindBidirectional(listItemEntity.name);
        value.textProperty().bindBidirectional(listItemEntity.value);

    }    
    
}
