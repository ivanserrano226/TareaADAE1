import java.io.*;
import java.util.ArrayList;

public class GestorCoches {

    private ArrayList<Coche> coches;
    private String ruta = "src/main/resources/coches.dat";

    public GestorCoches() {
        coches = new ArrayList<>();
    }

    public void comprobarArchivo() {
        File archivo = new File(ruta);
        if(archivo.exists()) {
           //Leer archivo y añadir los coches
            ObjectInputStream objectInputStream = null;

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(archivo));
                ArrayList<Coche> cochesLeidos = (ArrayList<Coche>) objectInputStream.readObject();
                coches = cochesLeidos;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Se ha producido un error. Por favor, inténtalo otra vez.");
            }
        }
    }

    public boolean añadirCoche(Coche coche) {
        for(Coche cocheLista: coches) {
            if(cocheLista.getId() == coche.getId() || cocheLista.getMatricula() == coche.getMatricula()) {
                return false;
            }
        }
        coches.add(coche);
        return true;
    }
    public boolean borrarCoche(int id) {
        for(Coche coche: coches) {
            if(coche.getId() == id) {
                coches.remove(coche);
                return true;
            }
        }
        return false;
    }
    public Coche consultarCoche(int id) {
        for(Coche coche: coches) {
            if(coche.getId() == id) {
                return coche;
            }
        }
        return null;
    }

    public ArrayList<Coche> listarCoches() {
        return coches;
    }

    public void guardarCoches() {
        if(coches == null || coches.isEmpty()) return;

        File archivo = new File(ruta);
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(archivo));
            objectOutputStream.writeObject(coches);

        } catch (IOException e) {
            System.out.println("Se ha producido un error. Por favor, inténtalo otra vez.");
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Se ha producido un error. Por favor, inténtalo otra vez.");
            }
        }
    }

    public boolean exportarCoches() {
        if(coches == null || coches.isEmpty()) return false;

        File csv = new File("src/main/resources/coches.csv");
        FileWriter fileWriter = null;
        BufferedWriter bufferedReader = null;

        try {
            fileWriter = new FileWriter(csv);
            bufferedReader = new BufferedWriter(fileWriter);
            //Escribir el encabezado del CSV
            bufferedReader.write("ID,");
            bufferedReader.write("Matrícula,");
            bufferedReader.write("Marca,");
            bufferedReader.write("Modelo,");
            bufferedReader.write("Color");
            bufferedReader.newLine();
            for(Coche coche: coches) {
                //Escribir cada fila del CSV
                bufferedReader.write(coche.getId() + ",");
                bufferedReader.write(coche.getMatricula() + ",");
                bufferedReader.write(coche.getMarca() + ",");
                bufferedReader.write(coche.getModelo() + ",");
                bufferedReader.write(coche.getColor());
                bufferedReader.newLine();
            }
            return true;

        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
            return false;
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar el archivo.");
            }
        }
    }
}
