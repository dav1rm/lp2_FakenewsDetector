package br.ufrn.imd.lp2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
	private void handleSubmit(ActionEvent event) {
		System.out.println("acionou evento");
		url.setText("algum valor de teste");
	}

	@FXML
	private void handleChangeSlider(ActionEvent event) {
		System.out.println("alterou slider");
		similarity.valueProperty().addListener((observable, oldValue, newValue) -> {
			percentage.setText(Double.toString(newValue.intValue()));

		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
