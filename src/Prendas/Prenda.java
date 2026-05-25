package Prendas;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
public class Prenda implements Comparable<Prenda>{
    private String modelo;
    private String tela;
    private double costoProduccion;
    private String genero;
    private String temporada;
    //Aca es dodnde metimos lo de una prenda asociada a varios lotes
    private ArrayList<Lote> lotes;
    private static final double LIMITE_COSTO = 5000;
    public Prenda(String modelo, String tela, double costoProduccion, String genero, String temporada)
            throws GeneroInvalidoException, TemporadaInvalidaException, CostoProduccionException {
        if (!(genero.equalsIgnoreCase("Masculino") || genero.equalsIgnoreCase("Femenino")
                || genero.equalsIgnoreCase("Mixto"))) {
            throw new GeneroInvalidoException("Genero invalido");
        }
        if (!(temporada.equalsIgnoreCase("Primavera") || temporada.equalsIgnoreCase("Verano")
                || temporada.equalsIgnoreCase("Otoño") || temporada.equalsIgnoreCase("Invierno"))) {
            throw new TemporadaInvalidaException("Temporada invalida");
        }
        if (costoProduccion <= 0 || costoProduccion > LIMITE_COSTO) {
            throw new CostoProduccionException("Costo invalido");
        }
        this.modelo = modelo;
        this.tela = tela;
        this.costoProduccion = costoProduccion;
        this.genero = genero;
        this.temporada = temporada;
        lotes = new ArrayList<>();
    }
    public String getModelo() {
        return modelo;
    }
    public String getTela() {
        return tela;
    }
    public double getCostoProduccion() {
        return costoProduccion;
    }
    public String getGenero() {
        return genero;
    }
    public String getTemporada() {
        return temporada;
    }
    public ArrayList<Lote> getLotes() {
        return lotes;
    }
    public void setCostoProduccion(double costoProduccion) {
        this.costoProduccion = costoProduccion;
    }
    //continuacion de una prenda asociada a varios lotes
    public void agregarLote(Lote lote) {
        lotes.add(lote);
    }
    public double calcularPrecioVenPieza() {
        return costoProduccion * 1.15;
    }
    @Override
    public int compareTo(Prenda p) {
        return modelo.compareTo(p.modelo);
    }

    public static class CompararPorCosto implements Comparator<Prenda>{
        @Override
        public int compare(Prenda p1, Prenda p2) {
            return Double.compare(p1.getCostoProduccion(), p2.getCostoProduccion());
        }
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prenda p))
            return false;
        return modelo.equalsIgnoreCase(p.modelo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(modelo);
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%.2f,%s,%s",modelo,tela,costoProduccion,genero,temporada);
    }
}
