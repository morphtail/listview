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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;

/**
 *
 * @author Silverleaf Technology Ltd
 */
public class ListViewListener implements ListChangeListener<ListViewItem> {

    @Override
    public void onChanged(Change c) {
        Logger.getLogger(ListChangeListener.class.getName()).log(Level.INFO, "List change called.");
        while (c.next()) {
            if (c.wasAdded()) {
                List<ListViewItem> allAdded = c.getAddedSubList();
                for (ListViewItem added : allAdded) {
                    Logger.getLogger(ListChangeListener.class.getName()).log(Level.INFO, "Added item: " + added.toString());
                }
            }
            if (c.wasUpdated()) {
                for (int i = c.getFrom(); i < c.getTo(); ++i) {
                    //permutate
                    ListViewItem item = (ListViewItem) c.getList().get(i);
                    Logger.getLogger(ListChangeListener.class.getName()).log(Level.INFO, "Changed item: " + item.getName());
                }
            }
            if (c.wasPermutated()) {
                Logger.getLogger(ListChangeListener.class.getName()).log(Level.INFO, "Permutated item");

            }
            if (c.wasRemoved()) {
                List<ListViewItem> allRemoved = c.getRemoved();
                for (ListViewItem added : allRemoved) {
                    Logger.getLogger(ListChangeListener.class.getName()).log(Level.INFO, "Item Removed: " + added.toString());
                }

            }
        }
    }
}
