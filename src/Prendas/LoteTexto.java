package Prendas;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
public class LoteTexto {
    private Formatter output;
    private Scanner input;
    private String nombreArchivo;
    public LoteTexto(String nombreArchivo){
        this.nombreArchivo = nombreArchivo + ".txt";
    }
    public void guardarDatos(ArrayList<Lote> lotes) throws FileNotFoundException {
        output = new Formatter(nombreArchivo);
        for(Lote l : lotes){
            output.format("%s%n", l);
        }
        output.flush();
        output.close();
    }
    private Lote procesaDatos(String line, ArrayList<Prenda> prendas){
        Scanner lectura = new Scanner(line).useDelimiter(",");
        try{
            int numeroLote = lectura.nextInt();
            int numeroPiezas = lectura.nextInt();
            LocalDate fecha = LocalDate.parse(lectura.next());
            String modelo = lectura.next();
            lectura.close();
            Prenda prenda = null;
            //Aca buscamos la prenda para despues asociarla
            for(Prenda p : prendas){
                if(p.getModelo().equalsIgnoreCase(modelo)){
                    prenda = p;
                    break;
                }
            }
            if(prenda == null){
                return null;
            }
            return new Lote(numeroLote, numeroPiezas, fecha, prenda);
        }catch(Exception e){
            return null;
        }
    }

    public ArrayList<Lote> leerDatos(ArrayList<Prenda> prendas) throws FileNotFoundException {
        ArrayList<Lote> lotes = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        input = new Scanner(archivo);
        while(input.hasNextLine()){
            String line = input.nextLine();
            Lote lote = procesaDatos(line, prendas);
            if(lote != null){
                lotes.add(lote);
                // aca asociamos a prendas con los lotes
                lote.getPrenda().agregarLote(lote);
            }
        }
        input.close();
        return lotes;
    }
}