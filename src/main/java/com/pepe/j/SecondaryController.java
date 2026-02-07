package com.pepe.j;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.pepe.j.modelos.Estudiante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

import java.sql.*;

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
	Queue<Estudiante> colaEstudiantes;
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
	    colaEstudiantes = new LinkedList<>();
	}
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    private void agregarEstudiante(Estudiante e) {
    	pilaEstudiantes.push(e);
    	colaEstudiantes.add(e);
    }
    private void eliminarEstudiante(int matricula) {
    	Stack<Estudiante> aux = new Stack<>();
    	Queue<Estudiante> auxC = new LinkedList<>();
    	while(!pilaEstudiantes.isEmpty()) {
    		Estudiante estAux = pilaEstudiantes.pop();
    		Estudiante estAuxC = colaEstudiantes.poll();
    		if(estAux.getMatricula() != matricula) {
    			aux.push(estAux);
    			auxC.add(estAuxC);
    		}
    	}
    	while(!aux.isEmpty()) {
    		pilaEstudiantes.push(aux.pop());
    		colaEstudiantes.add(auxC.poll());
    		//Estudiante ee = pilaEstudiantes.peek();
    		//colaEstudiantes.peek();
    		double pi = Math.PI;
    		double res = Math.cos(32);
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
    	
    	// Conectandome a la DDBB con JDBC
    	try {
    		// 2.2 Registrar el driver
    		Class.forName("org.postgresql.Driver");
    		
    		// Paso 3: Establecer la conexión
        	Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Universidad", "postgres", "123456ABCxyz");
        	
        	// Paso 4: Crear statement
        	// Sin parametros: createStatement()
        	Statement st = con.createStatement();
        	
        	// Paso 5: Ejecución de Query
        	ResultSet rs = st.executeQuery("select * from Estudiante;");
        	/*
        	rs.next();
        	String ap = rs.getString("apellido");
        	
        	System.out.println("***** BASES DE DATOS *****");
        	System.out.println("El estudiante de la base de datos es: "+ap);
        	rs.next();
        	ap = rs.getString("apellido");
        	System.out.println("El estudiante de la base de datos es: "+ap);
        	*/
        	System.out.println("***** BASES DE DATOS *****");
        	while(rs.next()) {
        		String ap = rs.getString("apellido");
        		System.out.println("El estudiante de la base de datos es: "+ap);
        	}
        	
        	// Paso 7: Cierre de conexión
        	con.close();
        	st.close();
        	rs.close();
    	}catch(ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
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