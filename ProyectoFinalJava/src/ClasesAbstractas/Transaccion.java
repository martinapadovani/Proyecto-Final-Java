package ClasesAbstractas;
import Interfaces.*;

public abstract class Transaccion implements GestionDeFacturas<Transaccion>{
	
	// ATRIBUTOS:
	private int id;
	private int fecha;
	private String medioDePago;
	private int montoTotal;
	
	// CONSTRUCTOR:
	public Transaccion(int id, int fecha, String medioDePago, int montoTotal) {
		this.id = id;
		this.fecha = fecha;
		this.medioDePago = medioDePago;
		this.montoTotal = montoTotal;
	}
	
	// MÉTODOS: 
	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", fecha=" + fecha + ", medioDePago=" + medioDePago + ", montoTotal="
				+ montoTotal + "]";
	}
	
	@Override 
	public void generarFactura(Transaccion transaccion) {
		
	}
	
	@Override 
	public void buscarFactura(int id) {
		
	}
	
	@Override
	public void verInfoFactura(Transaccion transaccion) {
		
	}
	
	@Override 
	public void eliminarFactura(Transaccion transaccion) {
		
	}
	
	// Getters y setters:
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	public int getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(int montoTotal) {
		this.montoTotal = montoTotal;
	}

	
	
	
	
	
}
