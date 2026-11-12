package controllers;

import java.net.URL;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class fxmlLoaderHome {
    private Pane view;

    public Pane getPage(String fileName) {
        try {
            // Use the classpath to find the views folder
            URL fileUrl = getClass().getResource("/views/" + fileName + ".fxml");

            if(fileUrl == null){
                System.out.println("ERROR: Could not find /views/" + fileName + ".fxml");
                return null;
            }

            view = new FXMLLoader().load(fileUrl);
        } catch(Exception e){
            System.out.println("Error loading: " + fileName);
            e.printStackTrace();
        }
        return view;
    }
}