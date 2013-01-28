package com.naveen.reader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Launcher extends Application {

	final ReaderOperations rdrObj = new ReaderOperations();

	/**
	 * Prepares the application for operation by collecting the inputs and
	 * calling the respective methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome");
		launch(args);
		System.out.println("bye");
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("This is F I L E R E A D E R");

		GridPane mygrid = new GridPane();
		mygrid.setAlignment(Pos.TOP_CENTER);
		mygrid.setHgap(10);
		mygrid.setVgap(10);
		mygrid.setPadding(new Insets(25, 25, 25, 25));

		Text welcome = new Text("Welcome!!");
		welcome.setFont(Font.font("Tahoma", FontWeight.MEDIUM, 20));
		mygrid.add(welcome, 0, 0, 2, 1);

		Label location_label = new Label("Enter the location of Folder:");
		mygrid.add(location_label, 0, 1);
		final TextField locationTf = new TextField();
		mygrid.add(locationTf, 1, 1);

				
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.TOP_RIGHT);
		Button folderLocBtn = new Button("Browse 'n' Select");
		vbox.getChildren().add(folderLocBtn);
		folderLocBtn.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {				
				DirectoryChooser dc = new DirectoryChooser();				
				File folder = dc.showDialog(null);
				locationTf.setText(folder.getPath());
				
			}
		});
		mygrid.add(vbox, 2,1,1,3);
		
		Label criterion_label = new Label("Enter the Criteria:");
		mygrid.add(criterion_label, 0, 2);
		final TextField criterionTf = new TextField();
		mygrid.add(criterionTf, 1, 2);

		Label extension_label = new Label("Enter the Extension of the files:");
		mygrid.add(extension_label, 0, 3);
		final TextField extensionTf = new TextField();
		mygrid.add(extensionTf, 1, 3);

		final TextArea notify = new TextArea(" ");
		notify.setWrapText(true);
		mygrid.add(notify, 0, 6, 2, 2);

		HBox btn_box = new HBox(10);
		btn_box.setAlignment(Pos.CENTER_LEFT);
		Button filterBtn = new Button("Filter");
		Button renameBtn = new Button("Rename");
		btn_box.getChildren().add(filterBtn);
		filterBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				notify.clear();
				rdrObj.setExtension(extensionTf.getText());
				prepareRegex(criterionTf.getText());
				rdrObj.filter(Paths.get(locationTf.getText()));
				for (Path path : rdrObj.getFilteredPaths()) {
					notify.appendText(path.toString());
					notify.appendText("\\\n");
				}
			}
		});
		btn_box.getChildren().add(renameBtn);
		renameBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				rdrObj.renameFiles();
				notify.clear();
				notify.appendText("FILES HAVE BEEN MODIFIED");
				notify.appendText("\\\n");
				for (Path path : rdrObj.getFilteredPaths()) {
					notify.appendText(path.toString());
					notify.appendText("\\\n");
				}
			}
		});
		mygrid.add(btn_box, 1, 4);
		HBox btn_box2 = new HBox(10);
		btn_box2.setAlignment(Pos.CENTER);
		Button exitBtn = new Button("Exit");
		exitBtn.setMinWidth(169);
		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
		btn_box2.getChildren().add(exitBtn);
		mygrid.add(exitBtn, 1, 8, 2, 1);

		Scene scene = new Scene(mygrid, 700, 475);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * prepares a regex based on the criteria given by the user
	 * 
	 * @param userCriter
	 * @return newCriteria
	 */
	public void prepareRegex(String userCriter) {
		Pattern wild_pattern = Pattern.compile("[*]");
		String criter_parts[] = null;
		if (userCriter.contains("*")) {
			criter_parts = wild_pattern.split(userCriter);
			StringBuilder newCriteria = new StringBuilder();
			for (String part : criter_parts) {
				newCriteria.append(part);
				newCriteria.append(".*");
			}
			rdrObj.setCriterion(newCriteria.toString());
			rdrObj.setCriterias(criter_parts);
		} else
			rdrObj.setCriterion(userCriter);
	}
}
