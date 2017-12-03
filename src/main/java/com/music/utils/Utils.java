package com.music.utils;

import com.music.entity.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Utils {

    private static StageManager stageManager = StageManager.getInstance();

//    public static void adjustConstraints(GridPane gridPane, AbstractModel entity) {
//        int difference = entity.getClass().getDeclaredFields().length - gridPane.getRowConstraints().size();
//        if (difference > 0) {
//            for (int i = 0; i < difference; i++) {
//                RowConstraints rc = new RowConstraints();
//                gridPane.getRowConstraints().add(rc);
//            }
//        } else {
//            for (int i = gridPane.getRowConstraints().size() - 1; i >= entity.getClass().getDeclaredFields().length; --i) {
//                gridPane.getRowConstraints().remove(i);
//            }
//        }
//    }
//
//    public static void adjustGrid(GridPane gridPane, int cols, int rows) {
//
//        for (ColumnConstraints colConstr : gridPane.getColumnConstraints()) {
//            colConstr.setPercentWidth(0);
//        }
//
//        for (int i = 0; i < cols; ++i) {
//            ColumnConstraints colConst = new ColumnConstraints();
//            colConst.setPercentWidth(100.0 / cols);
//            gridPane.getColumnConstraints().set(i, colConst);
//
//        }
//
//        for (int i = 0; i < rows; ++i) {
//            RowConstraints rowConst = new RowConstraints();
//            rowConst.setPercentHeight(100.0 / rows);
//            gridPane.getRowConstraints().set(i, rowConst);
//        }
//    }


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

//    public static void setTextFieldStyle(GridPane gridPane, JFXTextField[] textFields) {
//        for (int i = 0; i < textFields.length; i++) {
//            textFields[i].setFont(new Font("System", 16));
//            textFields[i].setStyle("-fx-text-fill: ALICEBLUE");
//            textFields[i].setUnFocusColor(Color.ALICEBLUE);
//            if (i < textFields.length / 2) {
//                textFields[i].setEditable(false);
//                gridPane.add(textFields[i], 0, i);
//            } else {
//                gridPane.add(textFields[i], 1, i - textFields.length / 2);
//            }
//            GridPane.setHalignment(textFields[i], HPos.CENTER);
//        }
//    }
//
//    public static void setTextFieldsData(GridPane gridPane, AbstractModel entity) {
//        BoolWrapper bool = new BoolWrapper();
//        bool.setValue(false);
//        Field[] fields = entity.getClass().getDeclaredFields();
//        List<String> fieldNames = Utils.getFieldNames(entity);
//        List<Method> getters = Utils.getMethods(entity);
//        int fieldsCount = fieldNames.size();
//        JFXTextField[] textFields = new JFXTextField[2 * fieldsCount];
//        for (int i = 0; i < textFields.length; i++) {
//            textFields[i] = new JFXTextField();
//        }
//        for (int i = 0, j = fieldsCount; i < fieldsCount; i++, j++) {
//            textFields[i].setText(fieldNames.get(i));
//            try {
//                if (getters.get(i).getReturnType().equals(List.class)) {
//                    if (((List) getters.get(i).invoke(entity)).size() == 0) {
//                        textFields[j].setText("Press to add some...");
//                        textFields[j].setEditable(false);
//                        textFields[j].setOnMousePressed((e) -> {
//                           loadInLambda(bool,"../views/sample.fxml", false, "kekus");
//                        });
//                    } else {
//                        textFields[j].setEditable(false);
//                        textFields[j].setText("Press to get view...");
//                        textFields[j].setOnMousePressed((e) -> {
//                            loadInLambda(bool,"../views/sample.fxml", false, "kek");
//                        });
//                    }
//                } else {
//                    if (getters.get(i).invoke(entity) != null)
//                        textFields[j].setText(getters.get(i).invoke(entity).toString());
//                    else textFields[j].setText("");
//
//                    if (isFieldNonCollectionObject(fields[i + 1])) {
//                        textFields[j].setEditable(false);
//                        String fileName = getFileNameOfListController(fields[i+1]);
//                        textFields[j].setOnMousePressed((e) -> {
//                            loadInLambda(bool,fileName,false,"List");
//                        });
//                    }
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//        Utils.setTextFieldStyle(gridPane, textFields);
//    }
//
//    private static void loadInLambda(BoolWrapper isLoaded, String path, boolean resizable, String title){
//        if (!isLoaded.isTrue()){
//            isLoaded.setValue(true);
//            stageManager.showStage(path, resizable, title)
//                    .setOnCloseRequest(windowEvent -> isLoaded.setValue(false));
//        }
//    }

    //    public static void printInfoLogo(GridPane gridPane) {
//        Label startLabel = new Label("Detailed information of selected row will be provided here");
//        startLabel.setFont(new Font("System", 24));
//        startLabel.setTextFill(Color.ALICEBLUE);
//        startLabel.setWrapText(true);
//        startLabel.setTextAlignment(TextAlignment.CENTER);
//        gridPane.add(startLabel, 0, 0);
//    }
//
    public static boolean isFieldNonCollectionObject(Field f) {
        //AbstractModel o = f.getType();
        if (f.getType().getSuperclass() != null) {
            return ((Class) (f.getType())).getSuperclass().equals(AbstractModel.class);
        }
        return false;
    }

    public static String getFileNameOfMtmListController(Type type) {
        if (type.equals(SongHasAlbumEntity.class)) {
            return "../views/albumsList.fxml";
        }
        if (type.equals(ArtistBandEntity.class)) {
            if(SelectionModel.getInstance().getModel().equals(ArtistEntity.class)){
                System.out.println("WANNA GET BANDS=======");
                return "../views/bandsOfArtist.fxml";
            }
            if(SelectionModel.getInstance().getModel().equals(BandEntity.class)){
                System.out.println("WANNA GET ARTISTS=======");
                return "../views/artistsBands.fxml";
            }
        }
        if (type.equals(SongEntity.class)) {
            return "../views/songsOfBand.fxml";
        }
        return null;
    }

    public static String getFileNameOfListController(Field f) {
        if (((Class) f.getType()).equals(BandEntity.class))
            return "../views/bandList.fxml";
        if (((Class) f.getType()).equals(GenreEntity.class))
            return "../views/genreList.fxml";
        if (((Class) f.getType()).equals(AlbumEntity.class))
            return "";
        return "../views/sample.fxml";
    }

    //TODO: list controllers, (when add button pressed on controller -> reload gridpane by Utils methods)
    //TODO: to update info possible solution is to send an event to parent view or somehow pass gridPane entity
}
