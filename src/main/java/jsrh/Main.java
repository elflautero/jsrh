package jsrh;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application{

	public void start(Stage stage) {
		
		WebView w = new WebView();
		WebEngine we = w.getEngine();
		we.load(getClass().getResource("/web/index.html").toExternalForm());
		
		
        Scene scene = new Scene(new Group(w));

        stage.setTitle("Welcome to JavaFX!"); 
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show(); 
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


