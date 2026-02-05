package com.pepe.j;

import java.io.IOException;
import java.util.Stack;

import com.pepe.j.modelos.Estudiante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

public class SecondaryController {
	@FXML
	private ImageView imageView;
	@FXML
    private TextField txtApellido;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNombre;
    @FXML
    private Label lblPilaEstudiantes;
	
	Stack<Estudiante> pilaEstudiantes;
	@FXML
	public void initialize() {
	    var url = getClass().getResource("/imagenes/mapache2.jpg");
	    System.out.println(url);
	    //imageView.setStyle("-fx-background-color: red;");
	    imageView.setPreserveRatio(true);
	    imageView.setFitWidth(487);  // ancho del AnchorPane
	    imageView.setFitHeight(590); // alto del AnchorPane

	    imageView.setImage(new Image(url.toExternalForm()));
	    pilaEstudiantes = new Stack<>();
	}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    private void agregarEstudiante(Estudiante e) {
    	pilaEstudiantes.push(e);
    }
    private void eliminarEstudiante(int matricula) {
    	Stack<Estudiante> aux = new Stack<>();
    	while(!pilaEstudiantes.isEmpty()) {
    		Estudiante estAux = pilaEstudiantes.pop();
    		if(estAux.getMatricula() != matricula) {
    			aux.push(estAux);
    		}
    	}
    	while(!aux.isEmpty()) {
    		pilaEstudiantes.push(aux.pop());
    	}
    }
    @FXML
    void btnEliminar(ActionEvent event) {
    	eliminarEstudiante(Integer.parseInt(txtMatricula.getText()));
    	mostrarEstudiantes();
    }

    @FXML
    void btnRegistrar(ActionEvent event) {
    	Estudiante est = new Estudiante(
    			Integer.parseInt(txtMatricula.getText()),
    			txtNombre.getText(),
    			txtApellido.getText());
    	agregarEstudiante(est);
    	mostrarEstudiantes();
    }
    private void mostrarEstudiantes() {
    	Stack<Estudiante> aux = new Stack<>();
    	lblPilaEstudiantes.setText("");
    	while(!pilaEstudiantes.isEmpty()) {
    		Estudiante estAux = pilaEstudiantes.pop();
    		lblPilaEstudiantes.setText(estAux.getApellido()+", ");
    			aux.push(estAux);
    		
    	}
    	while(!aux.isEmpty()) {
    		pilaEstudiantes.push(aux.pop());
    	}
    }
}