package ru.zhukov.ait;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Bootstrap extends Application {


    private class CreateBaseWindow{
        private Stage stage;
        public CreateBaseWindow(Stage stage) {
            this.stage = stage;
        }

        private void showWindow(){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("BaseWindow.fxml"));
            BaseWindowController controller = new BaseWindowController();
            fxmlLoader.setController(controller);
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                this.stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       new CreateBaseWindow(primaryStage).showWindow();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
