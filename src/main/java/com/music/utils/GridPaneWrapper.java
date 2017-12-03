package com.music.utils;

import com.jfoenix.controls.JFXTextField;
import com.music.entity.AbstractModel;
import com.music.entity.AlbumEntity;
import com.music.entity.Test;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class GridPaneWrapper {
    private GridPane pane;
    boolean active = false;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public GridPane getPane() {
        return pane;
    }

    public void setPane(GridPane pane) {
        this.pane = pane;
    }

    public void clear() {
        pane.getChildren().clear();
    }

    public void clear(int cellIndex) {
        pane.getChildren().remove(cellIndex);
    }


    private void adjustConstraints(AbstractModel model) {
        int difference = model.getClass().getDeclaredFields().length - pane.getRowConstraints().size();
        if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                RowConstraints rc = new RowConstraints();
                pane.getRowConstraints().add(rc);
            }
        } else {
            for (int i = pane.getRowConstraints().size() - 1; i >= model.getClass().getDeclaredFields().length; --i) {
                pane.getRowConstraints().remove(i);
            }
        }
    }

    private void adjustGrid(int cols, int rows) {
        for (ColumnConstraints colConstr : pane.getColumnConstraints()) {
            colConstr.setPercentWidth(0);
        }

        for (int i = 0; i < cols; ++i) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / cols);
            pane.getColumnConstraints().set(i, colConst);

        }

        for (int i = 0; i < rows; ++i) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rows);
            pane.getRowConstraints().set(i, rowConst);
        }
    }

    private void setTextFieldsData(AbstractModel model) {
        BoolWrapper bool = new BoolWrapper();
        bool.setValue(false);
        Field[] fields = model.getClass().getDeclaredFields();
        List<String> fieldNames = Utils.getFieldNames(model);
        List<Method> getters = Utils.getMethods(model);
        int fieldsCount = fieldNames.size();
        JFXTextField[] textFields = new JFXTextField[2 * fieldsCount];
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JFXTextField();
        }
        for (int i = 0, j = fieldsCount; i < fieldsCount; i++, j++) {
            textFields[i].setText(fieldNames.get(i));
            try {
                if (getters.get(i).getReturnType().equals(List.class)) {
                    String fileName = Utils.getFileNameOfMtmListController(((ParameterizedType)(fields[i+1].getGenericType())).getActualTypeArguments()[0]);
                    System.out.println(((ParameterizedType)(fields[i+1].getGenericType())).getActualTypeArguments()[0].equals(AlbumEntity.class));
                    if (((List) getters.get(i).invoke(model)).size() == 0) {
                        textFields[j].setText("Press to add some...");
                        textFields[j].setEditable(false);
                        textFields[j].setOnMousePressed((e) -> labdaLoader(bool, fileName, false, "kekus"));
                    } else {
                        textFields[j].setEditable(false);
                        textFields[j].setText("Press to get view...");
                        textFields[j].setOnMousePressed((e) -> labdaLoader(bool, fileName, false, "kek"));
                    }
                } else {
                    if (getters.get(i).invoke(model) != null)
                        textFields[j].setText(getters.get(i).invoke(model).toString());
                    else textFields[j].setText("");

                    if (Utils.isFieldNonCollectionObject(fields[i + 1])) {
                        textFields[j].setEditable(false);
                        String fileName = Utils.getFileNameOfListController(fields[i + 1]);
                        textFields[j].setOnMousePressed((e) -> labdaLoader(bool, fileName, false, "List"));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        setTextFieldStyle(textFields);
    }

    private void setTextFieldStyle(JFXTextField[] textFields) {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setFont(new Font("System", 16));
            textFields[i].setStyle("-fx-text-fill: ALICEBLUE");
            textFields[i].setUnFocusColor(Color.ALICEBLUE);
            if (i < textFields.length / 2) {
                textFields[i].setEditable(false);
                pane.add(textFields[i], 0, i);
            } else {
                pane.add(textFields[i], 1, i - textFields.length / 2);
            }
            GridPane.setHalignment(textFields[i], HPos.CENTER);
        }
    }

    private void labdaLoader(BoolWrapper isLoaded, String path, boolean resizable, String title) {
        if (!isLoaded.isTrue()) {
            isLoaded.setValue(true);
            StageManager.getInstance().showStage(path, resizable, title)
                    .setOnCloseRequest(windowEvent -> isLoaded.setValue(false));
        }
    }

    private void printLogoInfo() {
        Label startLabel = new Label("Detailed information of selected row will be provided here");
        startLabel.setFont(new Font("System", 24));
        startLabel.setTextFill(Color.ALICEBLUE);
        startLabel.setWrapText(true);
        startLabel.setTextAlignment(TextAlignment.CENTER);
        pane.add(startLabel, 0, 0);
    }

    private void preparePane(AbstractModel model) {
        adjustConstraints(model);
        adjustGrid(2, model.getClass().getDeclaredFields().length - 1);
    }

    public void printLogo() {
        adjustConstraints(new Test());
        adjustGrid(1, 1);
        printLogoInfo();
    }

    public void printModelInfo(AbstractModel model) {
        preparePane(model);
        setTextFieldsData(model);
    }

    public void invalidateStringFields(AbstractModel model) {
        ObservableList<Node> list = pane.getChildren();
        System.out.println(model);
        List<Field> strFields = model.getStringFields();
        System.out.println(strFields.size());
        List<String> vals = new ArrayList<>();
        for (int i = 0; i < strFields.size(); i++) {
            vals.add(((JFXTextField) list.get(model.getClass().getDeclaredFields().length - 1 + i)).getText());
        }
        model.setStringFieldsValue(vals);
    }
}
