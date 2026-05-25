package Prendas;
import java.time.LocalDate;
import java.util.Objects;

public class Lote {

    private int numeroLote;
    private int numeroPiezas;
    private LocalDate fechaFabricacion;
    /*aca es donde un lote pertenece a una prenda y es donde se aplica lo
      de si se elimina una prenda tambien se eliminan sus lotes
    */
    private Prenda prenda;
    public Lote(int numeroLote,int numeroPiezas,LocalDate fechaFabricacion,Prenda prenda)throws NumeroPiezasException {
        if(numeroPiezas < 50 || numeroPiezas > 350){
            throw new NumeroPiezasException("El numero de piezas debe estar entre 50 y 350 :(");
        }
        this.numeroLote = numeroLote;
        this.numeroPiezas = numeroPiezas;
        this.fechaFabricacion = fechaFabricacion;
        this.prenda = prenda;
    }
    public int getNumeroLote() {
        return numeroLote;
    }
    public int getNumeroPiezas() {
        return numeroPiezas;
    }
    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }
    public Prenda getPrenda() {
        return prenda;
    }
    public void setNumeroPiezas(int numeroPiezas) {
        this.numeroPiezas = numeroPiezas;
    }
    public double calcularCostoProduccionLote(){
        return numeroPiezas * prenda.getCostoProduccion();
    }
    public double calcularMontoRecuperacion(){
        double precioPieza = prenda.calcularPrecioVenPieza();
        double precioLote = precioPieza * 1.05;
        return numeroPiezas * precioLote;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lote l)) {
            return false;
        }
        return numeroLote == l.numeroLote;
    }
    @Override
    public int hashCode() {
        return Objects.hash(numeroLote);
    }
    @Override
    public String toString() {
        return String.format("%d,%d,%s,%s",numeroLote,numeroPiezas,fechaFabricacion,prenda.getModelo());
    }
}