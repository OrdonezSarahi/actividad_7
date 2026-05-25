package Prendas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
public class PrendaTexto {
    private Formatter output;
    private Scanner input;
    private String nombreArchivo;
    public PrendaTexto(String nombreArchivo){
        this.nombreArchivo =
                nombreArchivo + ".txt";
    }
    public void guardarDatos(ArrayList<Prenda> prendas) throws FileNotFoundException {
        output = new Formatter(nombreArchivo);
        for(Prenda p : prendas){
            output.format("%s%n", p);
        }
        output.flush();
        output.close();
    }
    private Prenda procesaDatos(String line){
        Scanner lectura = new Scanner(line).useDelimiter(",");
        try{
            String modelo = lectura.next();
            String tela = lectura.next();
            double costo = lectura.nextDouble();
            String genero = lectura.next();
            String temporada = lectura.next();
            lectura.close();
            return new Prenda(modelo,tela,costo,genero,temporada);
        }catch(Exception e){
            return null;
        }
    }
    public ArrayList<Prenda> leerDatos() throws FileNotFoundException {
        ArrayList<Prenda> prendas = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        input = new Scanner(archivo);
        while(input.hasNextLine()){
            String line = input.nextLine();
            Prenda p = procesaDatos(line);
            if(p != null){
                prendas.add(p);
            }
        }
        input.close();
        return prendas;
    }
}