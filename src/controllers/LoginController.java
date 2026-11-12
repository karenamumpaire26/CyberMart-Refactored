/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseConnection;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import java.sql.PreparedStatement;





/**
 * FXML Controller class
 *
 * @author majid
 */

public class LoginController implements Initializable {
        @FXML
    private JFXButton loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private JFXButton signupButton;

    @FXML
    private TextField usernameField;
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label LoginMsgLabel;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        signupButton.setOnMouseClicked(event->{
            try {
                root = FXMLLoader.load(getClass().getResource("signup.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Signup Page");
                stage.resizableProperty().setValue(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
        
        loginButton.setOnMouseClicked(event-> {
                
         if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {

            validateLogin();

        } else {
            LoginMsgLabel.setText("Please enter Username & Password");
        }
                
                
                });

    }
    public void validateLogin() {

        Connection connectDB = DatabaseConnection.getInstance().getConnection();


        String verifyLogin = "SELECT customer_id, count(1) FROM customer_info WHERE d_username=? AND d_password=?";

        try {
            PreparedStatement pstmt = connectDB.prepareStatement(verifyLogin);
            pstmt.setString(1, usernameField.getText());
            pstmt.setString(2, passwordField.getText());
            ResultSet queryResult = pstmt.executeQuery();

            while (queryResult.next()) {
                if (queryResult.getInt(2) == 1) { // If user is found
                    LoginMsgLabel.setText("Welcome to Cyber Mart family");

                    int cus_id = queryResult.getInt(1); // Get customer ID
                    String loggedinName = usernameField.getText();

                    // Update the current session user (c_user table)
                    String queryRow = "UPDATE c_user SET customer_ID=?, customer_name=? WHERE c_ID=1";
                    PreparedStatement updateStmt = connectDB.prepareStatement(queryRow);
                    updateStmt.setInt(1, cus_id);
                    updateStmt.setString(2, loggedinName);
                    updateStmt.executeUpdate();

                    // Load the next screen
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("homepage.fxml"));
                    try {
                        Parent root = fxmlloader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    HomepageController hpage = fxmlloader.getController();
                    hpage.setLabel(loggedinName);

                    LoginPane.getScene().getWindow().hide();
                } else {
                    LoginMsgLabel.setText("Invalid login Please try again");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}