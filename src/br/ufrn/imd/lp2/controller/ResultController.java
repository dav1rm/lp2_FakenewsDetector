package br.ufrn.imd.lp2.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
    void handleSubmit(ActionEvent event) {
    	goBack.getScene().getWindow().hide();
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
		System.out.println("anh");
		// TODO Auto-generated method stub
		//this.baloonText.setText(message);
    	//this.content.setText(content_);
        //this.character.setImage(image);
		
	}
}
