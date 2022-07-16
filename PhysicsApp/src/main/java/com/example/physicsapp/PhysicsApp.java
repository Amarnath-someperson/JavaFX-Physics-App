package com.example.physicsapp;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PhysicsApp extends Application {

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Physics Numericals - 5");
        Text text = new Text("Please enter the signs accordingly");
        text.setFont(Font.font("Default", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        Label fLabel = new Label("f ");
        Label vLabel = new Label("v ");
        Label uLabel = new Label("u ");
        Label mLabel = new Label("m ");
        TextField fField = new TextField();
        TextField vField = new TextField();
        TextField uField = new TextField();
        TextField mField = new TextField();
        VBox elseIO = new VBox(10);
        Label output = new Label("No calculations yet.");
        Button button = new Button("Find doable values");
        output.setFont(Font.font("Baskerville", FontWeight.MEDIUM, FontPosture.ITALIC, 13));
        button.defaultButtonProperty().bind(button.focusedProperty());
        button.setOnAction(e -> {
            elseIO.getChildren().clear();
            output.setAlignment(Pos.CENTER_LEFT);
            String fString = fField.getText();
            String vString = vField.getText();
            String uString = uField.getText();
            String mString = mField.getText();
            if (!fString.equals("") && !vString.equals("")) {
                double f = Double.parseDouble(fString);
                double v = Double.parseDouble(vString);
                double u = (v * f) / (f - v);
                double m = v / u;
                output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
            } else if (!fString.equals("") && !uString.equals("")) {
                double f = Double.parseDouble(fString);
                double u = Double.parseDouble(uString);
                double v = (u * f) / (u + f);
                double m = v / u;
                output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
            } else if (!fString.equals("") && !mString.equals("")) {
                double f = Double.parseDouble(fString);
                double m = 0;
                if (!mString.equals("")) {
                    m = Double.parseDouble(mString);
                }
                if (!vString.equals("")) {
                    double v = Double.parseDouble(vString);
                    double u = (v * f) / (f - v);
                    if (mString.equals("")) {
                        m = v / u;
                    }
                    output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
                } else if (!uString.equals("")) {
                    double u = Double.parseDouble(uString);
                    double v = (u * f) / (u + f);
                    if (mString.equals("")) {
                        m = v / u;
                    }
                    output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
                } else {

                    /*For some other purpose :
                    Text fieldText = new Text("Please enter I & O for Proper execution");
                    fieldText.setFill(Color.RED);
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), fieldText);
                    fadeTransition.setFromValue(1.0);
                    fadeTransition.setToValue(0.0);
                    fadeTransition.setCycleCount(Animation.INDEFINITE);
                    fadeTransition.play();
                    Label iLabel = new Label("I");
                    Label oLabel = new Label("O");
                    TextField iField = new TextField();
                    TextField oField = new TextField();
                    window.setWidth(600);
                    window.setHeight(500);
                    elseIO.getChildren().add(fieldText);
                    elseIO.getChildren().addAll(iLabel, iField);
                    elseIO.getChildren().addAll(oLabel,oField);*/
                    Double u = null;
                    for (int check = -10000; check < 10000; check++) {//limit upto 10000 otherwise u will not be found
                        double value = ((double) 1 / ((double) 1 / (m * check) - (double) 1 / check));
                        if (approximatelyEqual((float) value, (float) f, 20)) {
                            u = (double) check;
                            break;
                        }
                    }
                    if (u == null) {
                        window.setWidth(630);
                        Text fieldText = new Text("The value for u returns null until u = 9999,\nthe values entered for f & m cannot be used to\ncalculate v or u in this app.");
                        fieldText.setFill(Color.RED);
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), fieldText);
                        fadeTransition.setFromValue(1.0);
                        fadeTransition.setToValue(0.0);
                        fadeTransition.setCycleCount(Animation.INDEFINITE);
                        fadeTransition.play();
                        elseIO.getChildren().add(fieldText);
                    } else {
                        double v = (u * f) / (u + f);

                        output.setText("Values are only appoximately equal in this case.\n" + "f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
                    }
                }

            } else if (!vString.equals("") && !uString.equals("")) {
                double u = Double.parseDouble(uString);
                double v = Double.parseDouble(vString);
                double m = v / u;
                double f = (double) 1 / v - (double) 1 / u;
                output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
            } else if (!vString.equals("") && !mString.equals("")) {
                double v = Double.parseDouble(vString);
                double m = Double.parseDouble(mString);
                double u = v / m;
                double f = (double) 1 / v - (double) 1 / u;
                output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
            } else if (!uString.equals("") && !mString.equals("")) {
                double u = Double.parseDouble(uString);
                double m = Double.parseDouble(mString);
                double v = m * u;
                double f = (double) 1 / v - (double) 1 / u;
                output.setText("f = " + f + "\n" + "v = " + v + "\n" + "u = " + u + "\n" + "m = " + m);
            }
           /* if(!fField.equals("")&& !vField.equals("")|| fField.equals("")&& !uField.equals("")||fField.equals("")&& !mField.equals("")|| !vField.equals("") && !uField.equals("") ||
            !vField.equals("") && !uField.equals("") ||!vField.equals("") && !mField.equals("") || !uField.equals("")&& mField.equals("")){

            }*/
        });
        GridPane rootPane = new GridPane();

        VBox displays = new VBox(10);
        displays.getChildren().add(text);


        GridPane root = new GridPane();
        root.addRow(2, fLabel, fField);
        root.addRow(3, vLabel, vField);
        root.addRow(4, uLabel, uField);
        root.addRow(5, mLabel, mField);
        root.setAlignment(Pos.TOP_CENTER);
        rootPane.addRow(1, displays);
        rootPane.addRow(2, root);
        rootPane.addRow(2, elseIO);
        VBox results = new VBox(10);
        results.setPadding(new Insets(10));
        results.setSpacing(5);
        results.setAlignment(Pos.CENTER);
        results.getChildren().add(button);
        Text resHead = new Text("Results");
        resHead.setFont(Font.font("Default", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        results.getChildren().add(resHead);
        results.getChildren().add(output);

        rootPane.addRow(3, results);
        rootPane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(rootPane, 400, 330);
        window.setMinHeight(340);
        window.setMinWidth(320);
        window.setScene(scene);
        window.show();

    }

    public static boolean approximatelyEqual(float desiredValue, float actualValue, float tolerancePercentage) {
        float diff = Math.abs(desiredValue - actualValue);         //  1000 - 950  = 50
        float tolerance = tolerancePercentage / 100 * desiredValue;  //  20/100*1000 = 200
        return diff < tolerance;                                   //  50<200      = true
    }

    public static void main(String[] args) {
        launch();
    }
}

