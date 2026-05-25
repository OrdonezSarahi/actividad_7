package Prendas;
import java.io.FileNotFoundException;
import java.time.LocalDate;
public class App {
    public static void main(String[] args) {
        try {
            ListaPrendas lista = new ListaPrendas("prendas");
            Prenda p1 = new Prenda("Camisa","Algodon", 300,"Masculino","Verano");
            Prenda p2 = new Prenda("Blusa","Seda",350,"Femenino","primavera");
            Prenda p3 = new Prenda("Falda","Lino",200,"Femenino","Verano");
            Prenda p4 = new Prenda("Short","Algodon",300,"Femenino","Verano");
            Prenda p5 = new Prenda("Vestido","Seda",3300,"Femenino","Primavera");
            Prenda p6 = new Prenda("Sueter","Lana",300,"Mixto","Invierno");
            Prenda p7 = new Prenda("Pantalon","Algodon",300,"Masculino","Verano");
            Prenda p8 = new Prenda("tines","Algodon",400,"Mixto","Invierno");
            Prenda p9 = new Prenda("Mayon","Algodon",270,"Femenino","Verano");
            Prenda p10 = new Prenda("Corbata","Algodon",300,"Masculino","Verano");

            lista.addPrenda(p1);
            lista.addPrenda(p2);
            lista.addPrenda(p3);
            lista.addPrenda(p4);
            lista.addPrenda(p5);
            lista.addPrenda(p6);
            lista.addPrenda(p7);
            lista.addPrenda(p8);
            lista.addPrenda(p9);
            lista.addPrenda(p10);

            //Lotes de las prendas que se pusieron
            Lote l1 = new Lote(101,100, LocalDate.now(), p1);
            Lote l2 = new Lote(102,80,LocalDate.now(), p2);
            Lote l3 = new Lote(103,50,LocalDate.now(), p3);
            Lote l4 = new Lote(104,70,LocalDate.now(), p4);
            Lote l5 = new Lote(105,90,LocalDate.now(), p5);
            Lote l6 = new Lote(106,150,LocalDate.now(), p6);
            Lote l7 = new Lote(107,180,LocalDate.now(), p7);
            Lote l8 = new Lote(108,70,LocalDate.now(), p8);
            Lote l9 = new Lote(109,110,LocalDate.now(), p9);
            Lote l10 = new Lote(110,80,LocalDate.now(), p10);
            lista.addLote(l1);
            lista.addLote(l2);
            lista.addLote(l3);
            lista.addLote(l4);
            lista.addLote(l5);
            lista.addLote(l6);
            lista.addLote(l7);
            lista.addLote(l8);
            lista.addLote(l9);
            lista.addLote(l10);
            //A qui mostramos las prendas y los lotes
            System.out.println("Prendas");
            for(Prenda p : lista.getPrendas()) {
                System.out.println(p);
                System.out.println("Precio venta: " + p.calcularPrecioVenPieza());
            }
            System.out.println();
            System.out.println("Lotes");
            for(Lote l : lista.getLotes()) {
                System.out.println(l);
                System.out.println("Costo lote: " + l.calcularCostoProduccionLote());
                System.out.println("Recuperacion: " + l.calcularMontoRecuperacion());

                System.out.println();
            }
            //aca es donde eliminamos la prenda
            lista.deletePrenda(p1);

            System.out.println();
            //aca vuelve a poner la lista de las prendas que quedaron y los lotes
            System.out.println("Despues de eliminar");
            for(Prenda p : lista.getPrendas()) {
                System.out.println(p);
            }
            System.out.println();
            System.out.println("Lotes que quedaron");
            for(Lote l : lista.getLotes()) {
                System.out.println(l);
            }

            //Aca vuelve a guardar los datos
            lista.salvarDatos();
            lista.salvarBinarios();
        }
        catch (GeneroInvalidoException e) {
            System.out.println(e.getMessage());
        }
        catch (TemporadaInvalidaException e) {
            System.out.println(e.getMessage());
        }
        catch (CostoProduccionException e) {
            System.out.println(e.getMessage());
        }
        catch (NumeroPiezasException e) {
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e) {
            System.out.println("Error en el archivo :(");
        }
    }
}