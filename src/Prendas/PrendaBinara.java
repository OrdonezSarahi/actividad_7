package Prendas;

import java.io.*;
import java.util.ArrayList;

public class PrendaBinara {

    private DataInputStream input;
    private DataOutputStream output;

    private FileInputStream archivoInput;
    private FileOutputStream archivoOutput;

    private String nombreArchivo;

    public PrendaBinara(
            String nombreArchivo){

        this.nombreArchivo =
                nombreArchivo + ".dat";
    }

    public void guardarDatos(
            ArrayList<Prenda> prendas)
            throws FileNotFoundException {

        archivoOutput =
                new FileOutputStream(
                        nombreArchivo);

        output =
                new DataOutputStream(
                        archivoOutput);

        try{

            for(Prenda p : prendas){

                output.writeUTF(
                        p.getModelo());

                output.writeUTF(
                        p.getTela());

                output.writeDouble(
                        p.getCostoProduccion());

                output.writeUTF(
                        p.getGenero());

                output.writeUTF(
                        p.getTemporada());
            }

        }catch(IOException e){}

        try{
            output.close();
        }catch(IOException e){}
    }

    public ArrayList<Prenda> leerDatos()
            throws FileNotFoundException {

        archivoInput =
                new FileInputStream(
                        nombreArchivo);

        input =
                new DataInputStream(
                        archivoInput);

        ArrayList<Prenda> prendas =
                new ArrayList<>();

        try{

            while(true){

                String modelo =
                        input.readUTF();

                String tela =
                        input.readUTF();

                double costo =
                        input.readDouble();

                String genero =
                        input.readUTF();

                String temporada =
                        input.readUTF();

                prendas.add(
                        new Prenda(
                                modelo,
                                tela,
                                costo,
                                genero,
                                temporada));
            }

        }catch(Exception e){}

        try{
            input.close();
        }catch(IOException e){}

        return prendas;
    }
}