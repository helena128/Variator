package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setResizable(false);
        stage.setTitle("Variator");

        Scene scene = new Scene(new Group(), 450, 410);
        scene.getStylesheets().add("resources/style.css");

        GridPane grid = new GridPane();

        Group root = (Group) scene.getRoot();
        addTextField("Фамилия:", grid, 0);
        addTextField("Имя:", grid, 1);
        addTextField("Отчество:", grid, 2);

        char[] letters = "АБВГДЕЁ".toCharArray();
        for (int i = 3, j = 0; j < letters.length; i++, j++)
            addTextField(letters[j] + "", grid, i);

        changeVisibility(grid, 6,  19, false);
        changeEditability(grid, 6, 19);

        addButton("Go", grid);
        addButton("Clear", grid);

        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void addTextField(String label, GridPane grid, int num){

        TextField notification = new TextField ();
        notification.setText("Label");

        notification.clear();

        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label(label), 0, num);
        grid.add(notification, 1, num);

    }

    public void addButton(String label, GridPane grid){
        Button button = new Button(label);
        int rowindex = 0;

        if (label.equals("Go")){

            button.setStyle(
                    "-fx-background-color: darkseagreen"
            );

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // validating
                    // if not correct information ==> alert window
                    if (!(Controller.validate(((TextField)grid.getChildren().get(1)).getText()) &&
                            Controller.validate(((TextField)grid.getChildren().get(3)).getText()))){

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Ошибка ввода имени");
                        alert.showAndWait();
                    } else {
                        // calculate
                        String name = ((TextField)grid.getChildren().get(3)).getText();
                        String surname = ((TextField)grid.getChildren().get(1)).getText();
                        String middlename = ((TextField)grid.getChildren().get(5)).getText();
                        Variant var = new Variant(name, surname, middlename);
                        //System.out.println(var.toString());

                        ArrayList<Integer> numbers = var.generateNumbers();
                        /* for (Integer num: numbers)
                            System.out.println(num.intValue()); */

                        for (int i = 7, j = 0; j < numbers.size(); j++){
                            performAction(grid, i, numbers.get(j));
                            i += 2;
                        }

                        changeVisibility(grid, 6, 19,true);
                    }
                }
            });
        } else if (label.equals("Clear")){

            rowindex = 1;

            button.setStyle(
                    "-fx-background-color: lightskyblue"
            );

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int j = 0; j < grid.getChildren().size(); j ++){
                        performAction(grid, j);
                    }
                    changeVisibility(grid, 6, 19, false);
                }
            });
        }


        grid.add(button, 2, rowindex);
    }

    private void performAction(GridPane grid, int j){
        Object obj = grid.getChildren().get(j);
        if ((obj.getClass().getName()).equals("javafx.scene.control.TextField" )) {
            TextField text = (TextField) obj;
            text.clear();
        }
    }

    private void performAction(GridPane grid, int i, Integer val){
        Object obj = grid.getChildren().get(i);
        if ((obj.getClass().getName()).equals("javafx.scene.control.TextField" )) {
            TextField text = (TextField) obj;
            text.setText(val.toString());
        }
    }

    private void changeVisibility(GridPane grid, int min, int max, boolean visible){
        List<Node> nodes = grid.getChildren();
        for (int i = min; i < nodes.size() && i <= max; i++){
            nodes.get(i).setVisible(visible);
        }
    }

    private void changeEditability(GridPane grid, int min, int max){
        List<Node> nodes = grid.getChildren();
        for (int i = min; i < nodes.size() && i <= max; i++){
            if (nodes.get(i).getClass().getName().equals("javafx.scene.control.TextField")){
                ((TextField)nodes.get(i)).setEditable(false);
            }
        }
    }
}
