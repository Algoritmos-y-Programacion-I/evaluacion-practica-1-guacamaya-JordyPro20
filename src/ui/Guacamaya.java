package ui;

import java.util.Scanner;

public class Guacamaya {

    // Scanner global para todo el programa
    public static Scanner reader;
    // Arreglos de precios y unidades para todo el programa
    public static double[] precios;
    public static int[] unidades;
    public static int referencias;
    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

      reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

        solicitarDatos();

    }

     // Descripcion: se encarga de solicitar los precios y cantidades de los productos vendidos la cantidad de veces necesaria
     // pre: El Scanner reader se inicializa antes
     // pre: Los precios y unidades deben estar declarados
     // pos: Los precios y unidades quedan llenados con los datos que de el usuario
    

    public static void solicitarDatos(){
        for (int i = 0; i < referencias; i++) {
            System.out.print("Ingrese el precio del producto " + (i + 1) + ": ");
            precios[i] = reader.nextDouble();
            System.out.print("Ingrese la cantidad vendida del producto " + (i + 1) + ": ");
            unidades[i] = reader.nextInt();
        }
        
    }

    
     // Descripcion: Este metodo se encarga de calcular el total de unidades vendidas en el dia
     // pre: Los precios y unidades deben estar inicializados
     // pos: Se devuelve el total de unidades vendidas
    

    public static int calcularTotalUnidadesVendidas(){
        int total = 0;
        for (int i = 0; i < referencias; i++) {
            total += unidades[i];}
        return total;
        }

    
     // Descripcion: Este metodo se encarga de calcular el precio promedio de los productos vendidos en el dia
     // pre: Los precios deben estar inicializados
     // pos: Se devuelve el precio promedio de los productos vendidos
    

    public static double calcularPrecioPromedio(){
        double suma = 0;
        for (int i = 0; i < referencias; i++) {
            suma += precios[i];
        }
        if (referencias > 0) {
            return suma / referencias;
        } else {
            return 0;
        }
    }
    
     // Descripcion: Este metodo se encarga de calcular las ventas totales durante el dia
     // pre: Los arreglos precios y unidades deben estar inicializados
     // pos: Se devuelve el total de las ventas (dinero recaudado)
    
    public static double calcularVentasTotales(){
        double totalVentas = 0;
        for (int i = 0; i < referencias; i++) {
            totalVentas += precios[i] * unidades[i];
        }
        return totalVentas;
    }

     // Descripcion: Este metodo se encarga de consultar cuantas referencias de productos han superado un limite de ventas
     // pre: Los precios y unidades deben estar inicializados
     // pos: Se devuelve el número de referencias que han superado el limite de ventas
    
    public static int consultarReferenciasSobreLimite(double limite){
        int contador = 0;
        for (int i = 0; i < referencias; i++) {
            if (precios[i] * unidades[i]> limite) {
                contador++;
            }
        }
        return contador;
    }

}
