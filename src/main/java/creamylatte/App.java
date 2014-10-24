package creamylatte;


import creamylatte.presentation.main.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Hadouken
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new MainView().getView());
//        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("file:resources/images/appicon.png"));
        stage.setTitle("JVoting System");
        final String uri = getClass().getResource("app.css").toExternalForm();
        scene.getStylesheets().add(uri);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
