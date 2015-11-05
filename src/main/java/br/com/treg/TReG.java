package br.com.treg;

import br.com.treg.presentation.presenter.MainViewPresenter;
import br.com.treg.presentation.view.impl.MainViewImpl;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class TReG extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        //StackPane root = new StackPane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        MainViewPresenter viewPresenter = new MainViewPresenter();
        
        Scene scene = new Scene((Parent)viewPresenter.getView(), 500, 200);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("TR&G");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
