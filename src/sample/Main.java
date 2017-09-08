package sample;

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
        Scene scene = new Scene(new Group(), 450, 250);


        GridPane grid = new GridPane();


        Group root = (Group) scene.getRoot();
        addTextField("Фамилия:", grid, 0);
        addTextField("Имя:", grid, 1);
        addTextField("Отчество:", grid, 2);

        addTextField("А:", grid, 3);
        addTextField("Б:", grid, 4);
        addTextField("В:", grid, 5);
        addTextField("Г:", grid, 6);
        addTextField("Д:", grid, 7);
        addTextField("Е:", grid, 8);
        addTextField("Ё:", grid, 9);

        //changeVisibility(grid, 6, 21, false);

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
                            perforAction(grid, i, numbers.get(j));
                            i += 2;
                        }

                    }
                }
            });
        } else if (label.equals("Clear")){

            rowindex = 1;

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int j = 0; j < grid.getChildren().size(); j ++){
                        performAction(grid, j);
                    }
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

    private void perforAction(GridPane grid, int i, Integer val){
        Object obj = grid.getChildren().get(i);
        if ((obj.getClass().getName()).equals("javafx.scene.control.TextField" )) {
            TextField text = (TextField) obj;
            text.setText(val.toString());
        }
    }

    private void changeVisibility(GridPane grid, int min, int max, boolean visible){
        List<Node> nodes = grid.getChildren();
        for (int i = min; i <= max; i++){
            nodes.get(i).setVisible(visible);
        }
    }
}
