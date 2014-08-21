package creamylatte;


import creamylatte.presenter.main.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hadouken
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new MainView().getView());
//        stage.getIcons().add(new Image("file:resources/images/appicon.png"));
        stage.setTitle("JVoting System");
//        final String uri = getClass().getResource("app.css").toExternalForm();
//        scene.getStylesheets().add(uri);
        stage.setScene(scene);
        stage.show();
    }

//    @Override
//    public void stop() throws Exception {
//        Injector.forgetAll();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
