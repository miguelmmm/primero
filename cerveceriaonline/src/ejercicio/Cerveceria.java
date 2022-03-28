package ejercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Cerveceria {

    private String nombre_cerveceria;
    private HashMap<String,Cerveza> almacen;
     
    
    //CONSTRUCTOR
    public Cerveceria(String nombre_cerveceria) {
        this.nombre_cerveceria = nombre_cerveceria;
        this.almacen = new HashMap<>();
    }

    //=====================================================================
    //OPCION 1
    //=====================================================================
    public String toString() {
        String res = "";

        res += "NOMBRE CERVECERIA:" + this.nombre_cerveceria + "\n";

        //almacen.values() --> ArraysList de Cerveza
        if (this.almacen.values().size() > 0) {
            for (Cerveza cer : this.almacen.values()) {
                res += cer.toString() + "\n";
            }
        } else {
            res += "NO HAY CERVEZAS TODAVIA\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 2
    //=====================================================================
    public String filtrarCervezas(String nombre) {
        String res = "";
        //boolean encontrado=false;

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getNombre().toLowerCase()
                    .contains(nombre.toLowerCase())) {
                res += cer.toString() + "\n";
                //    encontrado=true;
            }
        }

        if (res.equals("")) {
            res = "NO SE ENCONTRARON COINCIDENCIAS\n";
        }

//        if(!encontrado){
//            res="NO SE ENCONTRARON COINCIDENCIAS\n";
//        }
//        
        return res;
    }

    //=====================================================================
    //OPCION 3
    //=====================================================================
    public String verPorTipo(char tipo) {
        String res = "";

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getTipo() == tipo) {
                res += cer.toString() + "\n";
            }
        }

        if (res.equals("")) {
            res = "NO HAY CERVEZAS DE ESE TIPO\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 4
    //=====================================================================
    public String verPorDistribuidor(String dis) {
        String res = "";

        for (Cerveza cer : this.almacen.values()) {
            if (cer.getDistribuidor().equalsIgnoreCase(dis)) {
                res += cer.toString() + "\n";
            }
        }

        if (res.equals("")) {
            res = "NO HAY CERVEZAS DE ESE DISTRIBUIDOR\n";
        }

        return res;
    }

    //=====================================================================
    //OPCION 5
    //=====================================================================
    public void rebajar(double porciento) {
        //porciento 5%,10%,12%,15%
        for (Cerveza cer : this.almacen.values()) {
//            double precio_actual=cer.getPrecio();
//            
//            precio_actual-=precio_actual*porciento/100;
//            
//            cer.setPrecio(precio_actual);
            //OTRA FORMA 
            cer.setPrecio(cer.getPrecio() - cer.getPrecio() * porciento / 100);
        }

    }

    //=====================================================================
    //OPCION 6
    //=====================================================================
    public String masCara() {
        String res = "";

        if (this.almacen.values().size() > 0) {
            Cerveza mayor;
            //Meter los valores en un ArrayList para poder ordenarlos
            ArrayList<Cerveza> cervezas=
                    new ArrayList<>(this.almacen.values());
            cervezas.sort((a, b)
                    -> Double.compare(b.getPrecio(), a.getPrecio()));
            
            mayor = cervezas.get(0);
            res = mayor.toString();
        } else {
            res = "NO HAY CERVEZAS EN EL ALMANCEN";
        }

        return res;
    }

    //=====================================================================
    //OPCION 7
    //=====================================================================
    public String mostrarOrdenadasPorNombre() {
        String res = "";
        
        ArrayList<Cerveza> cervezas=
                    new ArrayList<>(this.almacen.values());
        cervezas.sort((a, b) -> a.getNombre().compareTo(b.getNombre()));
        for (Cerveza cer : cervezas) {
            res += cer.toString();
        }

        return res;
    }

    //=====================================================================
    //OPCION 8
    //=====================================================================
    private Cerveza buscarCerveza(String nombre) {
        return this.almacen.get(nombre);

    }

    public void añadirCerveza(String nombre, int stock, boolean artesanal, char tipo, double precio, String distribuidor) {
        //DEJAR ESTO PARA LA PLANTILLA PARA PODER PROBAR EL TOSTRING ANTES Y DESPUES PONER LO DE DOS CERVEZAS IGUALES ETC
        Cerveza nueva, busqueda;
        
        busqueda=this.buscarCerveza(nombre);
        
        if (busqueda == null){
            nueva = new Cerveza(nombre, stock, artesanal, tipo, precio, distribuidor);
            this.almacen.put(nombre,nueva);
            System.out.println("Cerveza añadida");
        }else{
            System.out.println("ERROR:Ya exista esa cerveza");
        }

    }

    //=====================================================================
    //OPCION 9
    //=====================================================================
    public void borrarCerveza(String nombre) {
        Cerveza busqueda=this.buscarCerveza(nombre);
        
//        if(busqueda==null){
//            System.out.println("ERROR Cerveza no encontrada");
//        }else{
//            this.almacen.remove(busqueda);
//            System.out.println("Cerveza borrada");
//        }
        
        if(busqueda!=null){
            this.almacen.remove(nombre);
            System.out.println("Cerveza borrada");
        }else{
            System.out.println("ERROR Cerveza no encontrada");
        }
        
        
    }

    //=====================================================================
    //OPCION 10
    //=====================================================================
    public void venderCerveza(String nombre, int cantidad) {
        Cerveza busqueda=this.buscarCerveza(nombre);
        
        if(busqueda!=null){
            busqueda.servir_cerveza(cantidad);
        }else{
            System.out.println("ERROR:No existe");
        }
        
        
        
    }

    //=====================================================================
    //OPCION 11
    //=====================================================================
    public void reponerCerveza(String nombre, int cantidad) {
        Cerveza busqueda=this.buscarCerveza(nombre);
        
        if(busqueda!=null){
            busqueda.reponer_cerveza(cantidad);
        }else{
            System.out.println("ERROR:No existe");
        }
    }

    //=====================================================================
    //OPCION 12
    //===================================================================== 
    private ArrayList<Cerveza> filtrarPorTipo(char tipo) {
        ArrayList<Cerveza> res = new ArrayList<>();
        
        for (Cerveza cer : this.almacen.values()) {           
            if(cer.getTipo()==tipo){
                res.add(cer);
            }
        }
    
        return res;
    }

    public String masBarataTipo(char tipo) {
        String res="";
        Cerveza menor;
        ArrayList<Cerveza> filtrada;
        
        filtrada=this.filtrarPorTipo(tipo);
        if(filtrada.size()>0){
            filtrada.sort((a,b)->
                    Double.compare(a.getPrecio(), b.getPrecio()));
        
            menor=filtrada.get(0);
            res=menor.toString();
        }else{
            res="NO HAY CERVEZAS DE TIPO:"+tipo+"\n";
        }
        
        return res;
    }

    //=====================================================================
    //OPCION 13
    //=====================================================================
    public void borrarSinStock() {
        Iterator<Cerveza> iter=this.almacen.values().iterator();
        
        while(iter.hasNext()){
            Cerveza actual=iter.next();
            if(actual.getStock()==0){
                iter.remove();
            }
        }
    }
    
    private HashMap<Character,Integer> sumarPorTipos(){
        HashMap<Character,Integer> sumas=new HashMap<>();
        
        //Sumas el stock de la tienda por tipo
        for (Cerveza cer:this.almacen.values()) {
            if(sumas.containsKey(cer.getTipo())){
                int actual=sumas.get(cer.getTipo());
                sumas.put(cer.getTipo(),actual+cer.getStock());
            }else{
                sumas.put(cer.getTipo(), cer.getStock());
            }
        }
        
        return sumas;
    }
    

    //=====================================================================
    //OPCION 14
    //=====================================================================
    public String stockPorTipo() {
        //r->350
        //n->47
        //R->371
        //t->0
        String res="";
        HashMap<Character,Integer> sumaTipos=this.sumarPorTipos();
//        //Construir un String con la informacion
        for(Entry<Character,Integer> entrada:sumaTipos.entrySet()){
            res+=Cerveza.completarTipo(entrada.getKey())+
                                       "->"+
                                       entrada.getValue()+"\n";
        }

//        SOLUCION SIN HASHMAP
//        PARA COMPARARLO CON EL METODO ANTERIOR
//        char[] tipos={'r','R','t','n'};
//        int[] sumas=new int[tipos.length];
//        //[0,0,30,0]
//        
//        for (Cerveza cer:this.almacen.values()) {
//            int i=0,pos=-1;
//            while(i<tipos.length && pos==-1){
//                if(tipos[i]==cer.getTipo()){
//                    pos=i;
//                }
//                i++;
//            }
//            sumas[pos]+=cer.getStock();
//        }
//
//        for(int i=0;i<sumas.length;i++){
//            res+=tipos[i]+"->"+sumas[i]+"\n";
//        }

        return res;
    }

    //=====================================================================
    public HashMap <Character,Integer> sumarDistri(){
        HashMap<Character,Integer> sumapro=new HashMap<>();
        //Sumas el stock de la tienda por distribuidor
        for (Cerveza cer:this.almacen.values()) {
            if(sumapro.containsKey(cer.getDistribuidor())){
                int actual=sumapro.get(cer.getDistribuidor());
                sumapro.put(cer.getTipo(),actual+cer.getStock());
            }else{
                sumapro.put(cer.getTipo(), cer.getStock());
            }
        }
        return sumapro;
    }
    //OPCION 15
    //=====================================================================
    public String stockPorDistribuidor() {
        String res;
        
        ArrayList<Entry<Character,Integer>> sumas=
                new ArrayList<>(this.sumarDistri().entrySet());
        
        sumas.sort((a,b)->Integer.compare(b.getValue(), a.getValue()));
        
        //bucle
        return "";
    }

    //=====================================================================
    //OPCION 16
    //=====================================================================
    public String tipoMasStock() {
        String res;
        //Meto las sumas en un ArrayList
        ArrayList<Entry<Character,Integer>> sumas=
                new ArrayList<>(this.sumarPorTipos().entrySet());
        
        //Lo ordeno por valor 
        sumas.sort((a,b)->Integer.compare(b.getValue(), a.getValue()));
        
        //cogemos la primera clave
        char tipo_mas_repetido=sumas.get(0).getKey();
        
        
        //DEVOLVEMOS EL TIPO CON SU NOMBRE COMPLETO
        res=Cerveza.completarTipo(tipo_mas_repetido);
        
        return res;
    }

    //=====================================================================
    //OPCION 17
    //=====================================================================
    public String tiposSinStock() {
        String res="";
        
        for(Entry<Character,Integer> entrada:this.sumarPorTipos().entrySet()){
            if(entrada.getValue()==0){
                res+=Cerveza.completarTipo(entrada.getKey())+
                                       "->"+
                                       entrada.getValue()+"\n";
            }
        }
        
        
        return res;
    }

    //=====================================================================
    //OPCION 18
    //=====================================================================
    public String proveedorMasImportante() {
        HashMap<Character,Integer> sumapro=new HashMap<>();
        
        //Sumas el stock de la tienda por distribuidor
        for (Cerveza cer:this.almacen.values()) {
            if(sumapro.containsKey(cer.getDistribuidor())){
                int actual=sumapro.get(cer.getDistribuidor());
                sumapro.put(cer.getTipo(),actual+cer.getStock());
            }else{
                sumapro.put(cer.getTipo(), cer.getStock());
            }
        }
        
        return sumapro;
    }

}
