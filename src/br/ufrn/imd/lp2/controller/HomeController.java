package br.ufrn.imd.lp2.controller;

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
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController implements Initializable {

	@FXML
	private Text percentage;
	@FXML
	private Slider similarity ;
	@FXML
	private TextField url;
	@FXML
	private Button verifyButton;
	@FXML
	private TextArea textNew;
	
	private MainController MC;
	
	@FXML
	private void handleSubmit(ActionEvent event) throws IOException {
		System.out.println("Começando a análise");
		Double analysisResult = null;
		
		
		if(!url.getText().trim().isEmpty()) 
		{
			//SE O USUÁRIO TIVER DIGITADO URL, IREMOS REALIZAR WEBSCRAPING
			analysisResult = this.MC.analyze(true, url.getText(), "teste");
			
		}else 
		{
			if(!textNew.getText().trim().isEmpty())
			{
				//SE O USUÁRIO TIVER DIGITADO TEXTO, IREMOS ANALISAR SOMENTE PARA AQUELE TEXTO
				analysisResult = this.MC.analyze(false, textNew.getText(), "teste");
			}
		}
		
		//CASO TENHA SIDO SETADO VALOR DE RESULTADO
		if(analysisResult != null) 
		{
			//SE O RESULTADO FOR MAIOR  QUE O PADRÃO CONFIGURADO PELO USUÁRIO, É FAKENEWS
			if(analysisResult*100 > Double.parseDouble(percentage.getText())) 
			{
				System.out.println(analysisResult);
				System.out.println("Sua notícia é Falsa.");

				loadResult(true, analysisResult, textNew.getText(), "temporario");
			}else 
			{
				System.out.println(analysisResult);
				System.out.println("Sua notícia é Verdadeira. ");

				loadResult(false, analysisResult, textNew.getText(), "temporario");
			}
			
	        
		}else 
		{
			System.out.println("Algo deu errado com a comparação");
		}
		
	}
	
	public void loadResult(Boolean isFakenews, Double percentage, String content, String fakenews) throws IOException 
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/application/Result.fxml"));  
        Stage stage = new Stage();
        stage.initOwner(verifyButton.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));

       

        ResultController controller = loader.<ResultController>getController();

        controller.setData(isFakenews, percentage, content, fakenews);
        //ResultController controller = new ResultController();
        //loader.setController(controller);
        
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
        
        ///text1.setText(controller.getText());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.MC = new MainController();
		similarity.valueProperty().addListener((observable, oldValue, newValue) -> {
			percentage.setText(Double.toString(newValue.intValue()));
		});

	}

}
