package com.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class ClientInitilizable implements Initializable {
	//javafx scene builder arayüzüne bağlanmak için kullnıldı bu klass
	@FXML
	private Pane pane;

	public ClientInitilizable() {

	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}
//şekil listesindeki bütün elemanleri pane de göstermeye yarar
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.getPane().getChildren().addAll(Client.shapeList);

	}

}
