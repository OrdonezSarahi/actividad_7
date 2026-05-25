package Prendas;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class ListaPrendas {
    private ArrayList<Prenda> prendas;
    private ArrayList<Lote> lotes;
    private PrendaTexto pt;
    private PrendaBinara pb;
    private LoteTexto lp;
    private LoteBinario lb;
    public ListaPrendas(String nombreArchivo) {
        pt = new PrendaTexto(nombreArchivo);
        pb = new PrendaBinara(nombreArchivo);
        lp = new LoteTexto("lotes");
        lb = new LoteBinario("lotes");
        recuperarDatos();
    }
    private void recuperarDatos() {
        try{
            prendas = pt.leerDatos();
        }catch(FileNotFoundException e){
            prendas = new ArrayList<>();
        }
        try{
            lotes = lp.leerDatos(prendas);
        }catch(FileNotFoundException e){
            lotes = new ArrayList<>();
        }
    }
    public void recuperarBinarios(){
        try{
            prendas = pb.leerDatos();
        }catch(FileNotFoundException e){
            prendas = new ArrayList<>();
        }
        try{
            lotes = lb.leerDatos(prendas);
        }catch(FileNotFoundException e){
            lotes = new ArrayList<>();
        }
    }
    // guardamos los archivos de prenda y lote texto
    public void salvarDatos() throws FileNotFoundException {
        pt.guardarDatos(prendas);
        lp.guardarDatos(lotes);
    }
    // guardamos los archivos de prenda y lote binarios
    public void salvarBinarios() throws FileNotFoundException {
        pb.guardarDatos(prendas);
        lb.guardarDatos(lotes);
    }
    public boolean existePrenda(Prenda prenda){
        return prendas.contains(prenda);
    }
    // agregamos las prendas
    public void addPrenda(Prenda prenda){
        if(prenda != null && !existePrenda(prenda)){
            prendas.add(prenda);
        }else{
            throw new IllegalArgumentException("La prenda ya existe");
        }
    }
    // eliminamos tanto prendas como lotes
    public void deletePrenda(Prenda prenda){
        if(prendas.contains(prenda)){
            lotes.removeIf(l -> l.getPrenda().equals(prenda));
            prendas.remove(prenda);
        }else{
            throw new IllegalArgumentException("La prenda no existe");
        }
    }
    public void addLote(Lote lote){
        if(lote != null){
            lotes.add(lote);
            lote.getPrenda().agregarLote(lote);
        }else{
            throw new IllegalArgumentException("Lote invalido");
        }
    }
    public ArrayList<Prenda> getPrendas() {
        return prendas;
    }
    public ArrayList<Lote> getLotes() {
        return lotes;
    }
}