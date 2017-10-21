package com.music.utils;

import com.jfoenix.controls.JFXTextField;
import com.music.model.AbstractModel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.reflect.Reflection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Utils {

    public static void adjustConstraints(GridPane gridPane, AbstractModel model) {
        int difference = model.getClass().getDeclaredFields().length - gridPane.getRowConstraints().size();
        if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                RowConstraints rc = new RowConstraints();
                gridPane.getRowConstraints().add(rc);
            }
        } else {
            for (int i = gridPane.getRowConstraints().size() - 1; i >= model.getClass().getDeclaredFields().length; --i) {
                gridPane.getRowConstraints().remove(i);
            }
        }
    }

    public static void adjustGrid(GridPane gridPane, int cols, int rows) {

        for (ColumnConstraints colConstr : gridPane.getColumnConstraints()) {
            colConstr.setPercentWidth(0);
        }

        for (int i = 0; i < cols; ++i) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / cols);
            gridPane.getColumnConstraints().set(i, colConst);

        }

        for (int i = 0; i < rows; ++i) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rows);
            gridPane.getRowConstraints().set(i, rowConst);
        }
    }

    public static Optional<ButtonType> showWarningAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(text);
        alert.setHeaderText("Warning");
        return alert.showAndWait();
    }

    public static Optional<ButtonType> showSuccessAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(text);
        alert.setHeaderText("Success");
        return alert.showAndWait();
    }

    public static String makeStringBeautiful(String uglyString) {
        StringBuffer res = new StringBuffer(uglyString);
        int count = 1;
        for (int i : findAllCApitalLetters(uglyString)) {
            res.insert(i + count - 1, " ");
            res.replace(i + count, i + count + 1, res.substring(i + count, i + count + 1).toLowerCase());
            count++;
        }
        res.replace(0, 1, res.substring(0, 1).toUpperCase());
        return res.toString();
    }

    private static List<Integer> findAllCApitalLetters(String s) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                res.add(i);
            }
        }
        return res;
    }

    public static List<String> getFieldNames(AbstractModel model) {
        Field fields[] = model.getClass().getDeclaredFields();
        List<String> res = new ArrayList<>();
        for (Field field : fields) {
            res.add(makeStringBeautiful(field.getName()));
        }
        res.remove(0);
        return res;
    }

    public static List<Method> getMethods(AbstractModel model) {
        try {
            return AnnotationAnalyzer.findGetters(model);
        } catch (Exception e) {
            return null;
        }
    }

    public static void setTextFieldStyle(GridPane gridPane, JFXTextField[] textFields) {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setFont(new Font("System", 16));
            textFields[i].setStyle("-fx-text-fill: ALICEBLUE");
            textFields[i].setUnFocusColor(Color.ALICEBLUE);
            if (i < textFields.length / 2) {
                textFields[i].setEditable(false);
                gridPane.add(textFields[i], 0, i);
            } else {
                textFields[i].setEditable(true);
                gridPane.add(textFields[i], 1, i - textFields.length / 2);
            }
            GridPane.setHalignment(textFields[i], HPos.CENTER);
        }
    }

    public static void setTextFieldsData(GridPane gridPane, AbstractModel model) {
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
                    if (((List) getters.get(i).invoke(model)).size() == 0) {
                        textFields[j].setText("Press to add some...");
                        textFields[j].setOnMousePressed((e) -> {

                            //Reflection.getCallerClass()
                            try {
                                Parent parent = FXMLLoader.load(Reflection.getCallerClass(2).getResource("/sample/view/sample.fxml"));
                                Stage stage = new Stage(StageStyle.DECORATED);
                                stage.setTitle("111");
                                stage.setScene(new Scene(parent));
                                stage.setResizable(true);
                                stage.show();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                    } else {
                        textFields[j].setText("Press to get view...");
                    }
                } else {
                    if (getters.get(i).invoke(model) != null)
                        textFields[j].setText(getters.get(i).invoke(model).toString());
                    else textFields[j].setText("");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        Utils.setTextFieldStyle(gridPane, textFields);
    }

}
