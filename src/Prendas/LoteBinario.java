package Prendas;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class LoteBinario {
    private DataInputStream input;
    private DataOutputStream output;
    private FileInputStream archivoInput;
    private FileOutputStream archivoOutput;
    private String nombreArchivo;
    public LoteBinario(String nombreArchivo){
        this.nombreArchivo = nombreArchivo + ".dat";
    }
    public void guardarDatos(ArrayList<Lote> lotes) throws FileNotFoundException {
        archivoOutput = new FileOutputStream(nombreArchivo);
        output = new DataOutputStream(archivoOutput);
        try{
            for(Lote l : lotes){
                output.writeInt(l.getNumeroLote());
                output.writeInt(l.getNumeroPiezas());
                output.writeUTF(l.getFechaFabricacion().toString());
                output.writeUTF(l.getPrenda().getModelo());
            }
        }catch(IOException e){}
        try{
            output.close();
        }catch(IOException e){}
    }
    public ArrayList<Lote> leerDatos(ArrayList<Prenda> prendas) throws FileNotFoundException {
        archivoInput = new FileInputStream(nombreArchivo);
        input = new DataInputStream(archivoInput);
        ArrayList<Lote> lotes = new ArrayList<>();
        try{
            while(true){
                int numeroLote = input.readInt();
                int numeroPiezas = input.readInt();
                LocalDate fecha = LocalDate.parse(input.readUTF());
                String modelo = input.readUTF();
                Prenda prenda = null;
                for(Prenda p : prendas){
                    if(p.getModelo().equalsIgnoreCase(modelo)){
                        prenda = p;
                        break;
                    }
                }
                if(prenda != null){
                    Lote lote = new Lote(numeroLote, numeroPiezas, fecha, prenda);
                    lotes.add(lote);
                    // y agregamos prendas al lote
                    prenda.agregarLote(lote);
                }
            }
        }catch(Exception e){}
        try{
            input.close();
        }catch(IOException e){}
        return lotes;
    }
}
