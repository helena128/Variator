package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Scene scene = new Scene(new Group(), 450, 250);


        GridPane grid = new GridPane();


        Group root = (Group) scene.getRoot();
        addTextField("Фамилия:", grid, 0);
        addTextField("Имя:", grid, 1);
        addTextField("Отчество:", grid, 2);

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
                    if (!(Controller.validate(((TextField)grid.getChildren().get(1)).getText()) &&
                            Controller.validate(((TextField)grid.getChildren().get(3)).getText()))){

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Ошибка ввода имени");
                        alert.showAndWait();
                    } else {
                        // calculate
                    }
                }
            });
        } else if (label.equals("Clear")){

            rowindex = 1;

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int j = 1; j < 6; j += 2){
                        TextField text = (TextField) grid.getChildren().get(j);
                        text.clear();
                    }
                }
            });
        }

        grid.add(button, 2, rowindex);
    }
}
