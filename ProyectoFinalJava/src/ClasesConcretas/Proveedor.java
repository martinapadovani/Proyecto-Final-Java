package ClasesConcretas;
import java.sql.*;

import java.util.ArrayList;

import Interfaces.GestionDeDatos;
import ConexionDB.Conexion;
import java.util.Scanner;
public class Proveedor implements GestionDeDatos<Proveedor>{


	//ATRIBUTOS PARA CONEXION
	Conexion conexion = new Conexion();
	private Connection cn = null;
	
	//SCANNER
	Scanner scanner = new Scanner (System.in);
	
	//CONSTRUCTOR
	public Proveedor() {
	}
	
	

	//OVERRIDES
	
	@Override
	public void Ver() {
		
		try{
			cn = conexion.conectar();
			
			String query = "SELECT * FROM proveedor";
			
			Statement declaracion = cn.createStatement();
			ResultSet resultados = declaracion.executeQuery(query);
			
			while(resultados.next()) { //mientras haya datos por leer
			System.out.println(
						"ID: " + resultados.getInt("idProveedor") + ". Nombre: " +resultados.getString("nombreProveedor") + 
						 ". Telefono: " + resultados.getInt("telefonoProveedor"));
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}

	@Override
	public void Buscar(int ID) {
		
		
			
			try{
				cn = conexion.conectar();
				
				String query = "SELECT * FROM proveedor WHERE  idProveedor = ?";
				
				PreparedStatement declaracion  = cn.prepareStatement(query);
			
					declaracion.setInt(1, ID);
					ResultSet resultados = declaracion.executeQuery();
					
				
					
				if(resultados.next()) { //mientras haya datos por leer
						System.out.println(
									"ID: " + resultados.getInt("idProveedor") + ". Nombre: " +resultados.getString("nombreProveedor") + 
									 ". Telefono: " + resultados.getInt("telefonoProveedor"));
				}else {
					System.out.println("ID inválido! Vuelva a intentarlo.");
				}
				
			} catch(SQLException e){
				e.printStackTrace();
			} 
	}
	
	@Override
	public void Actualizar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String querySelect = "SELECT * FROM proveedor WHERE  idProveedor = ?";
			
			PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
		
			declaracionSelect.setInt(1, id);
			ResultSet resultados = declaracionSelect.executeQuery();
			
			if(resultados.next()) {
				
				System.out.println("Por favor, ingrese los datos correspondientes");
				System.out.println("Nombre: ");
				String nombreProveedor = (scanner.nextLine()).trim().replace(" ", "_");
				System.out.println("Telefono: ");
				int telefono = scanner.nextInt();
				
				//ACTUALIZACION
				
				String queryUpdate = "UPDATE proveedor SET nombreProveedor = ?, telefonoProveedor = ? WHERE  idProveedor = ?";
				
				PreparedStatement declaracionUpdate  = cn.prepareStatement(queryUpdate);
			
				declaracionUpdate.setString(1, nombreProveedor);
				declaracionUpdate.setInt(2, telefono);
				declaracionUpdate.setInt(3, id);
					
				declaracionUpdate.executeUpdate();
				
				//VER DATOS
				
				while(resultados.next()) { //mientras haya datos por leer
					System.out.println("Proceso exitoso! Datos actualizados: ");
						System.out.println(
									"ID: " + resultados.getInt("idProveedor") + ". Nombre: " +resultados.getString("nombreProveedor") + 
									 ". Telefono: " + resultados.getInt("telefonoProveedor"));
				}
				
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Agregar() {
		
		System.out.println("Por favor, ingrese los datos correspondientes");
		System.out.println("Nombre: ");
		String nombreProveedor = (scanner.nextLine()).trim().replace(" ", "_");
		System.out.println("Telefono: ");
		int telefono = scanner.nextInt();

		try{
			cn = conexion.conectar();
			
			String query = "INSERT INTO proveedor (nombreProveedor, telefonoProveedor) VALUES (?, ?)";
			//excluyo el id ya que es autoincremental
			
			PreparedStatement declaracion  = cn.prepareStatement(query);
			
				declaracion.setString(1, nombreProveedor);
				declaracion.setInt(2, telefono);
				
				declaracion.executeUpdate();
				
				System.out.println("Datos cargados exitosamente!");
			
		} catch(SQLException e){
			e.printStackTrace();
		} 
	}
	
	@Override
	public void Eliminar(int id) {
		
		try{
			cn = conexion.conectar();
			
			String querySelect = "SELECT * FROM proveedor WHERE  idProveedor = ?";
			
			PreparedStatement declaracionSelect  = cn.prepareStatement(querySelect);
		
			declaracionSelect.setInt(1, id);
			ResultSet resultados = declaracionSelect.executeQuery();
				
			if(resultados.next()) { //mientras haya datos por leer
				System.out.println("Seguro que desea eliminar el proveedor: ");
					System.out.println(
								"ID: " + resultados.getInt("idProveedor") + ". Nombre: " +resultados.getString("nombreProveedor") + 
								 ". Telefono: " + resultados.getInt("telefonoProveedor"));
					System.out.println("Si eliminas el proveedor, ten en cuenta que tambien seran eliminados todos los productos que provee");
					System.out.println("Ingrese si/no segun desee.");
					String confirmacion = scanner.nextLine();
					
					 if(confirmacion.equalsIgnoreCase("si")) {
						 
						 try{
							 cn = conexion.conectar();
						
							 String queryDelete = "DELETE FROM proveedor WHERE idProveedor = ?";
							 //excluyo el id ya que es autoincremental
						
								PreparedStatement declaracionDelete  = cn.prepareStatement(queryDelete);
								
								declaracionDelete.setInt(1, id);
								declaracionDelete.executeUpdate();
							
							 	System.out.println("Proveedor eliminado del sistema!");
						
						 } catch(SQLException e){
							 e.printStackTrace();
						 }
						 
					  }else {
						  System.out.println("Perfecto! El proveedor no será eliminado");
					  }
			}else {
				System.out.println("ID inválido! Vuelva a intentarlo.");
			}
		}catch(SQLException e){
				e.printStackTrace();
			} 
	}
}
