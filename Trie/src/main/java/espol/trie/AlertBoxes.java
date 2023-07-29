package com.mycompany.emojiapp;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
/**
 * @private Clase que se encarga de manipular las alertas
 * @author Ace Homework Developers
 * @since 16/06/2016
 */
public class AlertBoxes {
	/**
	 * @private Matriz de constantes que representan los tipos de alertas de contiene la clase
	 */
	public static final String CONSTANTS[][] = {{"info", "warnning", "error"},
												{"confirm", "custom", "inputText"}}; 
	/**
	 * @private Metodo que se encarga de invocar una alerta de Informacion
	 * @param title El titulo de la alerta
	 * @param header El encabezado de la alerta
	 * @param content El contenido de la alerta
	 */
	public static void infoAlert(String title, String header, String content){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();	
	}
	/**
	 * @private Metodo que se encarga de invocar una alerta de Precaucion/Atencion
	 * @param title El titulo de la alerta
	 * @param header El encabezado de la alerta
	 * @param content El contenido de la alerta
	 */
	public static void warnningAlert(String title, String header, String content){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();	
	}
	/**
	 * @public Metodo que se encarga de invocar una alerta de Error
	 * @param title El titulo de la alerta
	 * @param header El encabezado de la alerta
	 * @param content El contenido de la alerta
	 */
	public static void errorAlert(String title, String header, String content){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();	
	}
	/**
	 * @public Metodo que se encarga de invocar una alerta de Confirmacion
	 * @param title El titulo de la alerta
	 * @param header El encabezado de la alerta
	 * @param content El contenido de la alerta
	 */
	public static void confirmAlert(String title, String header, String content){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		   AlertBoxes.infoAlert("Excelente", "Subscribete", "Dale like y continua codificando :D!!");
		} else {
			AlertBoxes.errorAlert("Tristesa", "ohhh que mal..", "No te preocupes subiremos mas y mejor contenido :D!\nGracias por tu feedback");
		}
	}
	/**
	 * @private Metodo que se encarga de invocar una alerta Personalizada (Pueden modificarla a su gusto si se requiere)
	 * @param title El titulo de la alerta
	 * @param header El encabezado de la alerta
	 * @param content El contenido de la alerta
	 */
	public static void multiButtons(String title, String header, String content){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		ButtonType buttonTypeOne = new ButtonType("INFO");
		ButtonType buttonTypeTwo = new ButtonType("WARNING");
		ButtonType buttonTypeThree = new ButtonType("ERROR");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		   AlertBoxes.infoAlert("Info", "Alerta de tipo..", "INFO");
		} else if (result.get() == buttonTypeTwo) {
			AlertBoxes.warnningAlert("Warning", "Alerta de tipo..", "WARNING");
		} else if (result.get() == buttonTypeThree) {
			AlertBoxes.errorAlert("Error", "Alerta de tipo..", "ERROR");
		} 
	}
	/**
	 * @public Metodo que se encarga de invocar una alerta Personalizada (Pueden modificarla a su gusto si se requiere)
	 * @param title El titulo de la alerta
	 * @param header El encabezado de la alerta
	 * @param content El contenido de la alerta
	 */
	public static void inputTextAlert(String title, String header, String content){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);

		// Manera tipica de ibtener el valor responsivo del textfield
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			AlertBoxes.infoAlert("Info", "Tu nombre es..", result.get());
		}

		// Forma de conseguir el valor responsivo del textfield utilizando Java 8 (con expresion lambda).
		//result.ifPresent(name -> System.out.println("Your name: " + name));
	}
}