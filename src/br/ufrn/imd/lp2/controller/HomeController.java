package src.br.ufrn.imd.lp2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
	private void handleSubmit(ActionEvent event) {
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
			}else 
			{
				System.out.println(analysisResult);
				System.out.println("Sua notícia é Verdadeira. ");
			}
		}else 
		{
			System.out.println("Algo deu errado com a comparação");
		}
		
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
