package AddressBook;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Zaid Kahev De La Garza Hernandez
 */
public class AddressBook {

    public static void main(String[] args) {

        HashMap<String, String> Mapa = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        String Telefono;
        String Nombre;
        int opcion;
        int salida = 0;


        do
        {
            try{
                System.out.println("\nBienvenido a tu agenda personal\n");
                System.out.println("LOAD .......................  (1)");
                System.out.println("SAVE .......................  (2)");
                System.out.println("LIST .......................  (3)");
                System.out.println("CREATE  ....................  (4)");
                System.out.println("DELETE .....................  (5)");
                System.out.println("EXIT  ......................  [6]");

                opcion = scanner.nextInt();

                switch(opcion)
                {
                    case 1 -> load(Mapa);
                    case 2 -> save(Mapa);
                    case 3 -> list(Mapa);
                    case 4 -> {
                        System.out.println("Ingresa el numero telefonico: ");
                        Telefono = scanner.next();
                        System.out.println("Ingresa el nombre del contacto: ");
                        Nombre = scanner.next();
                        create(Mapa,Telefono,Nombre);
                    }
                    case 5 -> {
                        System.out.println("Ingresa el numero de telefono del contacto a eliminar");
                        Telefono = scanner.next();
                        delete(Mapa, Telefono);
                    }
                    case 6 -> {
                        System.out.println("Hasta la proxima\n");
                        salida = 1;
                    }

                    default -> System.out.println("Opción incorrecta\n");
                }
            }
            catch (Exception e)
            {
                System.out.println("A ocurrido un error inesperado \n");
                break;
            }
        }while(salida == 0);
    }
    public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\zaidd\\Documents\\Agenda.csv";
        String[] a;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");
                m.put(a[0],a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("Carga de datos exitosa\n");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }
    public static void save(HashMap<String, String> m)
    {
        Iterator<String> iterator = m.keySet().iterator();
        String inputFilename = "C:\\Users\\zaidd\\Documents\\Agenda.csv";
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));
            while(iterator.hasNext())
            {
                String llave = iterator.next();
                bufferedWriter.write(llave+","+m.get(llave)+"\n");
            }
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    System.out.println("Cambios guardados\n");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
    public static void list(HashMap<String, String> Mapa)
    {
        Iterator<String> iterator = Mapa.keySet().iterator();

        System.out.println("Agenda:\n");
        System.out.println("  Telefono   \t|\tNombre  ");
        System.out.println("....................................");
        while(iterator.hasNext())
        {
            String llave = iterator.next();
            System.out.println("  "+llave+"\t|\t"+Mapa.get(llave));
        }
    }
    public static void create(HashMap<String, String> Mapa, String tel, String nom)
    {

        if(Mapa.containsKey(tel))
        {
            System.out.println("El contacto ya se encuentra en la agenda");
        }
        else
        {
            Mapa.put(tel, nom);
            System.out.println("Contacto añido exitosamente");
        }

    }

    public static void delete(HashMap<String, String> Mapa, String tel)
    {
        if(Mapa.containsKey(tel))
        {
            System.out.println("\nContacto eliminado de la agenda: "+Mapa.get(tel)+"\n");
            Mapa.remove(tel);
        }
        else
            System.out.println("El telefono no se encuentra registrado en la agenda");
    }
}