/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoja9;

/**
 *
 * @author E. de Mata
 */
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Graph {
    private ArrayList pesos;
    private ArrayList vertices;
    private double[][] adyacencia;
    private String[][] recorrido;
    /*Definir entrada*/
    public Graph(){
        pesos = new ArrayList<Aristas>();
        vertices = new ArrayList<Vertice>();
    }
    public void addVertex(String name){
        vertices.add(new Vertice(name));
    }
    public void addEdge(String origen, String destino, double peso) throws Exception{
        if (vertices.isEmpty()){
            throw new Exception("No hay vertices");
        }
        int indice1=0;
        int indice2=0;
        boolean ver1=false;
        boolean ver2=false;
        Vertice aux;
        for (int x=0;x<vertices.size();x++){
            aux=(Vertice) vertices.get(x);
            if (aux.getName().equals(origen)){
                indice1=x;
                ver1=true;
            }
            else if(aux.getName().equals(destino)){
                indice2=x;
                ver2=true;
            }
           
        }
        Aristas arista =new Aristas((Vertice)vertices.get(indice1),(Vertice)vertices.get(indice2),peso);
        pesos.add(arista);
       
    }
    public boolean removeEdge(String origen, String destino){
        Aristas aux;
        Vertice ver1;
        Vertice ver2;
        for (int x=0;x<pesos.size();x++){
            aux=(Aristas)pesos.get(x);
            ver1=aux.getOrigen();
            ver2=aux.getDestino();
            if((ver1.getName().equals(origen)&&(ver2.getName().equals(destino)))){
                pesos.remove(x);
                return true;
            }
        }
        return false;
    }
    public double getPeso(String origen, String destino){
        if(origen.equals(destino)){
            return 0.0;
        }
        Aristas aux = new Aristas(new Vertice(origen),new Vertice(destino),1000.0);
        for (int x=0;x<pesos.size();x++){
            if(aux.equals((Aristas)pesos.get(x))){
                aux=(Aristas)pesos.get(x);
                return aux.getPeso();
            }
        }
        return 1000.0;
    }
   
    public void crearMatriz(){
        adyacencia= new double[vertices.size()][vertices.size()];
        recorrido = new String[vertices.size()][vertices.size()];
       
        for (int x=0;x<vertices.size();x++){
            for(int j=0;j<vertices.size();j++){
                recorrido[x][j]=((Vertice)vertices.get(j)).getName();
                adyacencia[x][j]=getPeso(((Vertice)vertices.get(x)).getName(),((Vertice)vertices.get(j)).getName());
            }
        }
    }
    public void imprimirMatriz(){
        System.out.println("Longitud del viaje");
        String cadena="\t\t";
        for(int x=0;x<vertices.size();x++){
            cadena+=((Vertice)vertices.get(x)).getName()+"\t\t";
        }
        System.out.println(cadena);
       
        for (int x=0;x<vertices.size();x++){
            cadena=((Vertice)vertices.get(x)).getName()+"\t\t";
            for(int j=0;j<vertices.size();j++){
                cadena+=adyacencia[x][j]+"\t\t";
            }
            System.out.println(cadena);
        }
        System.out.println("Recorrido del viaje");
        cadena="\t\t";
        for(int x=0;x<vertices.size();x++){
            cadena+=((Vertice)vertices.get(x)).getName()+"\t\t";
        }
        System.out.println(cadena);
       
        for (int x=0;x<vertices.size();x++){
            cadena=((Vertice)vertices.get(x)).getName()+"\t\t";
            for(int j=0;j<vertices.size();j++){
                cadena+=recorrido[x][j]+"\t\t";
            }
            System.out.println(cadena);
        }
    }
    public void algoritmoFloyd(){
       
        double val1;
        double val2;
        for(int i=0;i<vertices.size();i++){
           
            for(int x=0;x<vertices.size();x++){
                for(int j=0;j<vertices.size();j++){
                    val1=adyacencia[x][j];
                    val2=adyacencia[x][i]+adyacencia[i][j];
                    if (val2<val1){
                        adyacencia[x][j]=val2;
                        recorrido[x][j]=((Vertice)vertices.get(i)).getName();
                    }
                }
            }
        }
    }
    public String getCenter(){
        double[] excen = new double[vertices.size()];
        double val;
        int centro=0;
        for (int x=0;x<vertices.size();x++){
            val=0.0;
            for (int j=0;j<vertices.size();j++){
                    if(val<adyacencia[j][x]){
                        val=adyacencia[j][x];
                    }
            }
            excen[x]=val;
        }
        val=5000000000.0;
        for (int x=0;x<vertices.size();x++){
            if(val>excen[x]){
                centro=x;
            }
        }
        return "El centro del grafo es: "+((Vertice)vertices.get(centro)).getName();
    }
    public String getRecorrido(String origen, String destino){
        String aux1=origen;
        String aux2=destino;
        String aux3="";
        String camino="";
        double peso =0;
        int index1=0;
        int index2=0;
        for(int x=0;x<vertices.size();x++){
                if(aux1.equals(((Vertice)vertices.get(x)).getName())){
                    index1=x;
                }
                if(aux2.equals(((Vertice)vertices.get(x)).getName())){
                    index2=x;
                }
            }
        peso=adyacencia[index1][index2];
        if(peso==1000.0){
            return "No hay forma de llegar hasta la ciudad";
        }
        do{
            for(int x=0;x<vertices.size();x++){
                if(aux1.equals(((Vertice)vertices.get(x)).getName())){
                    index1=x;
                }
                if(aux2.equals(((Vertice)vertices.get(x)).getName())){
                    index2=x;
                }
            }
            camino+="-"+recorrido[index1][index2];
            aux3=aux2;
            aux2=recorrido[index1][index2];
        }
        while(!(aux3.equals(recorrido[index1][index2])));
        StringBuffer aux = new StringBuffer(camino);
        aux.deleteCharAt(aux.length()-1);
        aux.reverse();
        return "El Recorrido es: "+aux+" y la distancia hacia la ciudad es de "+peso+" KM";
    }
   
}
