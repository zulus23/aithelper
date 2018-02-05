package ru.zhukov.ait;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.zhukov.ait.config.ApplicationConfig;
import ru.zhukov.ait.config.DatabaseConfig;
import ru.zhukov.ait.dao.ApplicationService;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Bootstrap extends Application {


    private class CreateBaseWindow{
        private Stage stage;
        private ApplicationContext context;
        public CreateBaseWindow(Stage stage) {

            this.stage = stage;
            context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        }


        private void showWindow(){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("BaseWindow.fxml"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle("aithelper");
            Locale.setDefault(new Locale("ru","RU"));
            BaseWindowController controller = new BaseWindowController(context.getBean(ApplicationService.class));
            fxmlLoader.setController(controller);
            fxmlLoader.setResources(resourceBundle);
            try {
                AnchorPane anchorPane = fxmlLoader.load();
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
