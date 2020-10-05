package dad.javafx.adivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinAppFX extends Application {

	private Label introduccionLabel;
	private Button comprobarButton;
	private TextField numeroText;
	int numpremio;
	int intentos;
	
	
	private void empezar() {
		numpremio = (int) (Math.random() * (100 + 1));
		intentos = 0;
	}

	private void alertaError() {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("AdivinApp");
		alerta.setHeaderText("Error");
		alerta.setContentText("El número introducido no es válido");

		alerta.showAndWait();
	}

	private void alertaAcierto() {
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("AdivinApp");
		alerta.setHeaderText("¡Has ganado!");
		alerta.setContentText("Sólo has necesitado " + intentos + " intentos. \n" +
								"Vuelve a jugar y hazlo mejor");

		alerta.showAndWait();
	}

	private void alertaFallo(int numero) {
		if (numero > numpremio) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("AdivinApp");
			alerta.setHeaderText("¡Has fallado!");
			alerta.setContentText("El número a adivinar es menor que " + numero + ".");

			alerta.showAndWait();
		} else {
			Alert alerta2 = new Alert(AlertType.WARNING);
			alerta2.setTitle("AdivinApp");
			alerta2.setHeaderText("¡Has fallado!");
			alerta2.setContentText("El número a adivinar es mayor que " + numero + ".");
			
			alerta2.showAndWait();
		}
	}
	
	private void onComprobarButtonAction(ActionEvent e) {
		intentos++;
		try {
			int numero = Integer.parseInt(numeroText.getText());
			if (numero == numpremio) {
				alertaAcierto();
				empezar();
			} else {
				alertaFallo(numero);
			}

		} catch (NumberFormatException excepcion) {
			alertaError();
		}

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Generar el numero aleatorio
		empezar();
		
		// creamos un cuadro de texto
		numeroText = new TextField();
		numeroText.setPrefColumnCount(5);
		numeroText.setPromptText("Introduce un numero"); // ponemos un texto de ayuda
		numeroText.setMaxWidth(150); // ponemos el tamaño maximo del componente
		numeroText.setAlignment(Pos.CENTER); // ponemos el texto centrado

		// creamos una etiqueta
		introduccionLabel = new Label();
		introduccionLabel.setText("Introduce un número del 1 al 100");

		// creamos el boton
		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e)); // on + nombre componente + evento
		comprobarButton.setDefaultButton(true);

		// creamos un panel con disposicon vertical
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(introduccionLabel, numeroText, comprobarButton);

		// creamos la escena
		Scene escena = new Scene(root, 320, 200);

		// configuramos la ventana
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
