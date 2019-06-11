package br.ufrn.imd.lp2.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultController implements Initializable{

    @FXML
    private Button goBack;

    @FXML
    private Text baloonText;

    @FXML
    private Text fakenews;

    @FXML
    private Text content;
    
    @FXML
    private Text fakenewsLabel;
    
    @FXML
    private ImageView character;
   
    @FXML
    void handleSubmit(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/application/Home.fxml"));  
        Stage stage = new Stage();
        stage.initOwner(goBack.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));

    	goBack.getScene().getWindow().hide();
        stage.showAndWait();
    }
    
    public void setData(Boolean isFakenews, Double percentage, String cnt, String fk) throws FileNotFoundException 
    {
    	String message;
    	Image image;
    	if(isFakenews) 
    	{
    		message = "Oh não! Isso é mentira do caixão. Sua notícia é "+percentage+" falsa";
    		fakenewsLabel.setText("Fakenews Existente");
    		fakenews.setText(fk);
            image = new Image(new FileInputStream("assets/akinator2.png"));
    	}else 
    	{
    		message = "Aeeee! Não temos registros de sua notícia como falsa";
    		fakenewsLabel.setText("");
            image = new Image(new FileInputStream("assets/akinator.png"));
    	}

		baloonText.setText(message);
        character.setImage(image);
    	content.setText(cnt);

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
