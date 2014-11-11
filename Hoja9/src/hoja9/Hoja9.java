/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hoja9;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author E. de Mata
 */
public class Hoja9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int cont=0;
        String[] lista = null;
        Graph grafo= new Graph();
        try{
            /*String direccion = System.getProperty("user.dir");
            System.out.println(direccion);
            FileInputStream fstream = new FileInputStream(direccion + "\\src\\hoja\\de\\trabajo\\pkg9\\grafo.txt"); /*Para que corra fuera
             de netbeans hay que poner new FileInputStream("grafo.txt");*/
            /*DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
            */
            File f = new File( "src/grafo.txt" );
            BufferedReader entrada;
            entrada = new BufferedReader( new FileReader( f ) );
            String strLinea;
            while ((strLinea = entrada.readLine()) != null)   {
               
                lista=strLinea.split(" ");
                if(cont==0){
                   
                    System.out.println("Los vertices son ("+lista.length+"): ");
                    for(int x=0;x<lista.length;x++){
                        System.out.print(lista[x]+" ");
                        grafo.addVertex(lista[x]);
                    }
                }
                else if(lista.length==3){
                    grafo.addEdge(lista[0], lista[1], Double.parseDouble(lista[2]));
                    System.out.println("\n Se creo la arista con  origen "+lista[0]+", destino "+lista[1]+" y peso "+lista[2]);
                }
                cont++;
            }
            // Cerramos el archivo
            entrada.close();
        }catch (Exception e){
            System.err.println("Ha ocurrido un error durante la ejecucion del programa: " + e.getMessage());
        }
        int opc = 1;
        //Scanner scan = new Scanner(System.in);
        
        while(opc!=4){
            grafo.crearMatriz();
            grafo.algoritmoFloyd();
            /*System.out.println("1. Conocer la ruta más corta entre dos ciudades");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Realizar modificaciones en las rutas");
            System.out.println("4. Salir");*/
            JOptionPane.showMessageDialog(null, "1. Conocer la ruta más corta entre dos ciudades\n" + "2. Centro del grafo\n" + "3. Realizar modificaciones en las rutas\n" + "4. Salir");
            //opc = scan.nextInt();
            opc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea:"));
            switch(opc){
                case 1:
                    String principio = "";
                    String fin = "";
                    //scan.nextLine();
                    //System.out.println("Ingrese la ciudad de origen");
                    //principio = scan.nextLine();
                    principio = JOptionPane.showInputDialog("Ingrese la ciudad de origen:");
                    //System.out.println("Ingrese la ciudad de destino");
                    //fin = scan.nextLine();
                    fin = JOptionPane.showInputDialog("Ingrese la ciudad de destino:");
                    double peso=0;
                    peso = grafo.getPeso(principio,fin);
                    if(peso==1000){
                        //System.out.println("No existe ruta entre las ciudades o ha ingresado una ciudad no existente");
                         JOptionPane.showMessageDialog(null,"No existe ruta entre las ciudades o ha ingresado una ciudad no existente");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El peso entre las ciudades es de: " + peso);
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,grafo.getCenter());
                    break;
                case 3:
                    int opc2 = 0;
                    while(opc2!=3){
                       
                        JOptionPane.showMessageDialog(null,"1. Crear interrupcion entre dos ciudades\n2. Crear conexión entre dos ciudades\n3. Salir");
                        //opc2 = scan.nextInt();
                        opc2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la opcion que desea:"));
                        switch(opc2){
                            case 1:
                                //System.out.println("Ciudad de origen: ");
                                //scan.nextLine();
                                //String origen = scan.nextLine();
                                String origen = JOptionPane.showInputDialog(null, "Ciudad de origen:");
                                //System.out.println("Ciudad destino: ");
                                //String destino = scan.nextLine();
                                String destino = JOptionPane.showInputDialog(null, "Ciudad de destino:");
                                if(!grafo.removeEdge(origen, destino)){
                                    //System.out.println("No se pudo crear la interrupcion");
                                    JOptionPane.showMessageDialog(null,"No se pudo crear la interrupcion exitosamente");
                                }else{
                                    //System.out.println("Se creo la interrupcion");
                                    JOptionPane.showMessageDialog(null,"Se creo la interrupcion exitosamente");
                                }
                               
                                break;
                            case 2:
                               
                                //System.out.println("Ciudad de origen: ");
                                //scan.nextLine();
                                //String origen2 = scan.nextLine();
                                String origen2 = JOptionPane.showInputDialog(null, "Ciudad de origen: ");
                                //System.out.println("Ciudad destino: ");
                                //String destino2 = scan.nextLine();
                                String destino2 = JOptionPane.showInputDialog(null, "Ciudad de destino: ");
                                //System.out.println("Distancia entre ambas ciudades: ");
                                peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Distancia entre ambas ciudades: "));
                                try {
                                    grafo.addEdge(origen2, destino2, peso);
                                    //System.out.println("Se creo la conexion");
                                    JOptionPane.showMessageDialog(null,"Se creo la conexion exitosamente");
                                } catch (Exception ex) {
                                    //System.out.println("No se pudo crear la conexion");
                                    JOptionPane.showMessageDialog(null,"No se pudo crear la conexion exitosamente");
                                }
                                break;
                            case 3:
                                break;
                            default:
                                //System.out.println("opcion incorrecta!!");
                                JOptionPane.showMessageDialog(null,"Ha ingresado una opcion incorrecta");
                        }
                    }
                    grafo.imprimirMatriz();
                    break;
               
                   
                   
                case 4:
                    System.out.println("Usted a salido");
                    break;
                default:
                    System.out.println("opcion incorrecta!!");
            }
           
           
           
           
        }
    }
    
}
