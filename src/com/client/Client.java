package com.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Client extends Application {

	public static Stage primareStage;
	private Pane mainLayout;
	public static ClientInitilizable clientController;
	public static ConnectionClientThread clientThread;
	public static Integer screenWidth;
	public static Integer screenHeight;
	public static List<Shape> shapeList = new ArrayList<>();

	public static Client client;

	public Client() {
		// TODO Auto-generated constructor stub
		client = this;
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primareStage = primaryStage;
		this.primareStage.setTitle("Şekil Çizme Penceresi");
		// showMainView();

		clientThread = new ConnectionClientThread();
		clientThread.start();

	}
//yeni bir sahne oluşturulurve boyutları belirlenir fxml uzantılı
	public void showMainView() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Client.class.getResource("/com/client/clientView.fxml"));
		mainLayout = loader.load();
		clientController = (ClientInitilizable) loader.getController();
		Scene scene = new Scene(mainLayout, screenWidth, screenHeight);
		primareStage.setWidth(screenWidth);
		primareStage.setHeight(screenHeight);
		mainLayout.setPrefWidth(screenWidth);
		mainLayout.setPrefHeight(screenHeight);
		mainLayout.setStyle("-fx-background-color: pink;");
		primareStage.setScene(scene);
		primareStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
}
