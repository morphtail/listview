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

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Silverleaf Technology Ltd
 */
public class ListViewService {

    private ListViewService() {
    }

    public static ListViewService getInstance() {
        return ListServiceHolder.INSTANCE;
    }

    private static class ListServiceHolder {

        private static final ListViewService INSTANCE = new ListViewService();
    }

    public void init() {
        observableListViewItems.addListener(new ListViewListener());
        this.observableListViewItems.add(new ListViewItem("Item1"));
        this.observableListViewItems.add(new ListViewItem("Item2"));
        this.observableListViewItems.add(new ListViewItem("Item3"));
    }
    public ObservableList<ListViewItem> observableListViewItems = FXCollections.observableArrayList(myListItem -> new Observable[]{myListItem.name});
}
