import java.util.ArrayList;
import java.util.Scanner;

public class Tarea {

    public static void main(String[] args) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        GestorCoches gestorCoches = new GestorCoches();
        gestorCoches.comprobarArchivo();
        do {
            System.out.print("""
                            
                            Introduce una de estas opciones:
                            1. Añadir nuevo coche
                            2. Borrar coche por id
                            3. Consulta coche por id
                            4. Listado de coches
                            5. Exportar coches a archivo CSV
                            6. Terminar el programa
                            
                            """
                    );
            try {
                System.out.print("Elige una de las opciones de arriba: ");
                opcion = scanner.nextInt();
                switch(opcion) {
                    case 1:
                        try {
                            System.out.print("Introduce un ID: ");
                            int idCoche = scanner.nextInt();
                            System.out.print("Introduce una matricula: ");
                            String matricula = scanner.next();
                            System.out.print("Introduce una marca: ");
                            String marca = scanner.next();
                            System.out.print("Introduce un modelo: ");
                            String modelo = scanner.next();
                            System.out.print("Introduce un color: ");
                            String color = scanner.next();
                            Coche coche = new Coche(idCoche, matricula, marca, modelo, color);
                            boolean resultadoAñadir = gestorCoches.añadirCoche(coche);
                            if(resultadoAñadir) {
                                System.out.print("Se ha añadido el coche correctamente.");
                            }
                            else {
                                System.out.print("Se ha producido un error. Por favor, inténtalo otra vez y no uses matriculas o IDs duplicados.");
                            }
                        } catch (Exception e) {
                            System.out.print("Se ha producido un error. Por favor, inténtalo otra vez");
                        }
                        break;
                    case 2:
                        System.out.print("Introduce un id para borrar el coche: ");
                        int idBorrado = scanner.nextInt();
                        boolean resultadoBorrar = gestorCoches.borrarCoche(idBorrado);
                        if(resultadoBorrar) {
                            System.out.print("Se ha borrado el coche correctamente.");
                        }
                        else {
                            System.out.print("Se ha producido un error. Por favor, asegurate de que el coche existe primero.");
                        }
                        break;
                    case 3:
                        System.out.print("Introduce un id para consultar el coche: ");
                        int idConsulta = scanner.nextInt();
                        Coche cocheConsultado = gestorCoches.consultarCoche(idConsulta);
                        if(cocheConsultado != null) {
                            System.out.println("Coche con ID " + cocheConsultado.getId() + ", con matrícula " + cocheConsultado.getMatricula() + ", de la marca " + cocheConsultado.getMarca()
                                    + ", con modelo " + cocheConsultado.getModelo() + "y de color " + cocheConsultado.getColor());
                        }
                        else {
                            System.out.println("No se ha encontrado el coche. Por favor, inténtalo de nuevo.");
                        }
                        break;
                    case 4:
                        ArrayList<Coche> coches = gestorCoches.listarCoches();
                        if(coches != null && !coches.isEmpty()) {
                            for(Coche cocheLista: coches) {
                                System.out.println("Coche con ID " + cocheLista.getId() + ", con matrícula " + cocheLista.getMatricula() + ", de la marca " + cocheLista.getMarca()
                                + ", con modelo " + cocheLista.getModelo() + "y de color " + cocheLista.getColor());
                            }
                        }
                        else {
                            System.out.println("No hay coches que mostrar.");
                        }
                        break;
                    case 5:
                        boolean resultadoExportado = gestorCoches.exportarCoches();
                        if(resultadoExportado) {
                            System.out.print("Se ha exportado como CSV correctamente.");
                        }
                        else {
                            System.out.print("Se ha producido un error. Por favor, inténtalo de nuevo.");
                        }
                        break;
                    case 6:
                        gestorCoches.guardarCoches();
                        System.out.println("Fin del programa.");
                        break;
                    default:
                        System.out.println("La opción no es válida. Por favor, inténtalo de nuevo.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ha habido un error. Inténtalo de nuevo. " + e);
                scanner.nextLine();
            }
        }
        while(opcion != 6);
    }
}