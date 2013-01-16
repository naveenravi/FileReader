package com.naveen.reader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Launcher extends Application{

	/**
	 * Prepares the application for operation by collecting the inputs and
	 * calling the respective methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome");
		ArrayList<Path> filter_results = new ArrayList<>();
		//launch(args);
		
		System.out.println("enter the folder name");
		Scanner input = new Scanner(System.in);
		Path path = Paths.get(input.nextLine());		
		System.out.println("Enter the extension of the files");
		String ext = input.nextLine();
		ReaderOperations rop_obj = new ReaderOperations(path, ext);
		System.out.println("Enter your criteria for filtering:");
		String criteria = input.nextLine();
		
		Pattern wild_pattern = Pattern.compile("[*]");
		String criter_parts[] = null;
		if(criteria.contains("*")){
			criter_parts = wild_pattern.split(criteria);
			
			StringBuilder newCriteria = new StringBuilder();		
			int count = 1;
			for(String part : criter_parts){
				newCriteria.append(part);				
					newCriteria.append(".*");				
			}			
			filter_results = rop_obj.filter(newCriteria.toString());
			System.out.println("printing the filtered results");
			for(Path file : filter_results){
				System.out.println(file.toString());
				
			}
			rop_obj.renameFiles(criter_parts, filter_results);			
		}
		else {
			//filter_results.add(e)
			System.out.println("Yet to Implement");
			
		}		
		input.close();
		System.out.println("bye");
	}
	
	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("This is F I L E R E A D E R");
		
		GridPane mygrid = new GridPane();
		mygrid.setAlignment(Pos.TOP_CENTER);
		mygrid.setHgap(10);
		mygrid.setVgap(10);
		mygrid.setPadding(new Insets(25,25,25,25));
		
		Text welcome = new Text("Welcome!!");		
		welcome.setFont(Font.font("Tahoma", FontWeight.MEDIUM, 20));
		mygrid.add(welcome, 0, 0,2,1);
		
		Label location_label = new Label("Enter the location:");
		mygrid.add(location_label, 0,1);
		TextField locationTf = new TextField(); 
		mygrid.add(locationTf, 1, 1);
		Label criterion_label = new Label("Enter the Criteria:");
		mygrid.add(criterion_label, 0,2);
		TextField criterionTf = new TextField(); 
		mygrid.add(criterionTf, 1, 2);
		HBox btn_box = new HBox(10);
		btn_box.setAlignment(Pos.BOTTOM_RIGHT);
		Button filterBtn = new Button("Filter");		
		Button renameBtn = new Button("Rename");
		btn_box.getChildren().add(filterBtn);
		filterBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				
			}
		});
		btn_box.getChildren().add(renameBtn);
		mygrid.add(btn_box, 1, 3);
		
		TextArea notify = new TextArea(" ");	
		notify.setWrapText(true);
		mygrid.add(notify,0, 5, 2,2);
		

		Scene scene = new Scene(mygrid, 500, 275);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
