package br.ufrn.imd.lp2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.ufrn.imd.lp2.model.AnalysisResult;
import br.ufrn.imd.lp2.view.Main;
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
	private Slider similarity;
	@FXML
	private TextField url;
	@FXML
	private Button verifyButton;
	@FXML
	private TextArea textNew;
    @FXML
    private Text balloonText;
    
	private MainController MC;

	@FXML
	private void handleSubmit(ActionEvent event) throws IOException {
		System.out.println("Começando a análise...");
		balloonText.setText("Aguarde! Estou analisando...");
		
		AnalysisResult analysisResult = null;
		
		if (!url.getText().trim().isEmpty()) {
			// SE O USU�RIO TIVER DIGITADO URL, IREMOS REALIZAR WEBSCRAPING
			analysisResult = this.MC.analyze(true, url.getText(), "teste");
		} else {
			if (!textNew.getText().trim().isEmpty()) {
				// SE O USU�RIO TIVER DIGITADO TEXTO, IREMOS ANALISAR SOMENTE PARA AQUELE TEXTO
				analysisResult = this.MC.analyze(false, textNew.getText(), "teste");
			}
		}

		// CASO TENHA SIDO SETADO VALOR DE RESULTADO
		if (analysisResult != null) {
			// SE O RESULTADO FOR MAIOR QUE O PADR�O CONFIGURADO PELO USU�RIO, � FAKENEWS
			if (analysisResult.getAccuracy() * 100 > Double.parseDouble(percentage.getText())) {
//				System.out.println(analysisResult.getAccuracy());
//				System.out.println("Sua notícia é falsa.");
				Main.setDado(analysisResult);
				loadResult(true, analysisResult.getAccuracy(), analysisResult.getContent(),
						analysisResult.getFakenews());
			} else {
//				System.out.println(analysisResult.getAccuracy());
//				System.out.println("Sua notícia é verdadeira");
				Main.setDado(analysisResult);
				loadResult(false, analysisResult.getAccuracy(), analysisResult.getContent(),
						analysisResult.getFakenews());
			}
		} else {
			balloonText.setText("Algo deu errado com a comparação");
		}

	}

	public void loadResult(Boolean isFakenews, Double percentage, String content, String fakenews) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/br/ufrn/imd/lp2/view/Result.fxml"));
		loader.load();

		ResultController controller = loader.getController();
		controller.setData(isFakenews, percentage, content, fakenews);

		Parent p = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		
		verifyButton.getScene().getWindow().hide();
		stage.showAndWait();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.MC = new MainController();

		similarity.valueProperty().addListener((observable, oldValue, newValue) -> {
			percentage.setText(Double.toString(newValue.intValue()));
		});

	}

}
