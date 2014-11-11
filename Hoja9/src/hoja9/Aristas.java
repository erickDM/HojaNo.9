/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hoja9;

/**
 *
 * @author E. de Mata
 */
public class Aristas {
    private Vertice origen;
    private Vertice destino;
    private double peso;
    public Aristas(Vertice origen, Vertice destino, double peso){
        this.origen=origen;
        this.destino=destino;
        this.peso=peso;
    }

    public boolean equals(Aristas ar){
        if((origen.getName().equals(ar.getOrigen().getName()))&&(destino.getName().equals(ar.getDestino().getName()))){
            return true;
        }
        return false;
    }
    public Vertice getOrigen() {
        return origen;
    }

    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
   
}
