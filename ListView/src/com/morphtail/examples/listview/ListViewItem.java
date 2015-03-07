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

import java.util.Objects;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Silverleaf Technology Ltd
 */
public class ListViewItem {

    public StringProperty id;
    public StringProperty name;
    public StringProperty value;

    public ListViewItem() {
        id = new SimpleStringProperty(UUID.randomUUID().toString());
        name = new SimpleStringProperty("");
        value = new SimpleStringProperty("");
    }

    public ListViewItem(String name) {
        this();
        this.name.setValue(name);
    }

    public ListViewItem(String name, String value) {
        this();
        this.name.setValue(name);
        this.value.setValue(value);
    }

    public ListViewItem(ListViewItem anotherItem) {
        this();
        this.name.setValue(anotherItem.getName());
        this.value.setValue(anotherItem.getValue());
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.getValue();
    }

    public String getValue() {
        return value.getValue();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void updateValues(String n, String v) {
        value.setValue(v);
        name.setValue(n);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListViewItem other = (ListViewItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

}
