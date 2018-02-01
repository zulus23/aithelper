package ru.zhukov.ait;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Bootstrap extends Application {


    private class CreateBaseWindow{
        private Stage stage;
        public CreateBaseWindow(Stage stage) {
            this.stage = stage;
        }

        private void showWindow(){
            FXMLLoader fxmlLoader = new FXMLLoader();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("aithelper");
            Locale.setDefault(new Locale("ru","RU"));
            BaseWindowController controller = new BaseWindowController();
            fxmlLoader.setController(controller);
            try {
                AnchorPane anchorPane = fxmlLoader.load(this.getClass().getResource("BaseWindow.fxml"),resourceBundle);
                Scene scene = new Scene(anchorPane);
                stage.setScene(scene);
                stage.setTitle(resourceBundle.getString("main.window.title"));
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
