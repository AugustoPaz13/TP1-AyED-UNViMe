import java.util.Scanner;

public class Main {
    private static final Scanner consola = new Scanner(System.in);

    public static void main(String[] args) {

        Articulo[] inventario = new Articulo[1000];
        inicializarInventario(inventario);
        int op = 0;
        do {
            System.out.print("""
                -------------------------------
                Gestión de articulos perfumeria
                -------------------------------
                Menú:
                1. Ingresar articulo
                2. Ver articulos
                3. Modificar articulo
                4. Eliminar articulo
                5. Salir
                -------------------------------
                Opción: """);
            op = Integer.parseInt(consola.nextLine());
            switch (op){
                case 1: ingresarArticulo(inventario);
                    break;
                case 2: verArticulos(inventario);
                    break;
                case 3: modificarArticulo(inventario);
                    break;
                case 4: eliminarArticulo(inventario);
                    break;
                case 5: System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no valida, intente de nuevo...");
                    break;
            }
        }while(op!=5);

    }

    public static void inicializarInventario(Articulo[] inventario){
        for(int i=0;i<1000;i++){
            inventario[i] = new Articulo("000x0", "Vacio", "Vacio", 0.0,0);
        }
    }

    public static void ingresarArticulo(Articulo[] inventario){

        for (int i=0;i<1000;i++){
            if(inventario[i].getId().equals("000x0")){
                inventario[i].setId(solicitarIdValido());
                inventario[i].setNombre(solicitarNombreValido());
                inventario[i].setMarca(solicitarNombreMarcaValido());
                inventario[i].setPrecio(solicitarPrecioValido());
                inventario[i].setStock(solicitarCantidadValida());

                System.out.println("""
                        ------------------------------
                        ¡Articulo ingresado con éxito!
                        ------------------------------
                        """);

                break; //Salimos del bucle
            }
        }
    }

    public static void verArticulos(Articulo[] inventario){
        int contador = 0;
        for (int i=0;i<1000;i++){
            if(!inventario[i].getId().equals("000x0")){
                System.out.println(inventario[i]);
                contador++;
            }
        }
        if(contador==0){
            System.out.println("""
                    ------------------------------
                    ¡No hay articulos disponibles!
                    ------------------------------
                    """);
        }
    }

    public static void modificarArticulo(Articulo[] inventario){
        System.out.print("Ingrese el ID del articulo a eliminar: ");
        String idAEncontrar = consola.nextLine();

        for (int i=0;i<1000;i++){
            if(inventario[i].getId().equalsIgnoreCase(idAEncontrar)){
                int op = 0;
                do {
                    System.out.println("""
                        ---------------------
                        ¡Articulo encontrado!
                        ---------------------
                        ¿Qué desea modificar?
                        ---------------------
                        1. ID
                        2. Nombre
                        3. Marca
                        4. Precio
                        5. Stock
                        6. Volver a menú
                        ---------------------
                        Opción:""");
                    op = Integer.parseInt(consola.nextLine());
                    switch (op){
                        case 1:
                            System.out.println("ID actual del articulo: " + inventario[i].getId());
                            inventario[i].setId(solicitarIdValido());
                            mensajeModificacion();
                            break;
                        case 2:
                            System.out.println("Nombre actual del articulo: " + inventario[i].getNombre());
                            inventario[i].setNombre(solicitarNombreValido());
                            mensajeModificacion();
                            break;
                        case 3:
                            System.out.println("Nombre marca actual: " + inventario[i].getMarca());
                            inventario[i].setMarca(solicitarNombreMarcaValido());
                            mensajeModificacion();
                            break;
                        case 4:
                            System.out.println("Precio actual: " + inventario[i].getPrecio());
                            inventario[i].setPrecio(solicitarPrecioValido());
                            mensajeModificacion();
                            break;
                        case 5:
                            System.out.println("Cantidad actual: " + inventario[i].getStock());
                            inventario[i].setStock(solicitarCantidadValida());
                            mensajeModificacion();
                            break;
                        case 6:
                            System.out.println("Volviendo al menú...");
                            break;
                        default:
                            System.out.println("¡Opción no valida, intente de nuevo!");
                            break;
                    }
                }while(op!=6);

                break;
            }

        }
    }

    public static void eliminarArticulo(Articulo[] inventario){
        System.out.println("Ingrese el ID del articulo a eliminar: ");
        String articuloAEliminar = consola.nextLine();
        int op = 0;
        boolean encontrado = false;
        boolean eliminado = false;
        for (int i=0;i<1000;i++){
            if(inventario[i].getId().equalsIgnoreCase(articuloAEliminar)) {
                encontrado = true;
                System.out.println("""
                            ---------------------
                            ¡Articulo encontrado!
                            ---------------------
                            """);
                do{
                    System.out.println(inventario[i]);
                    System.out.println("""
                        -------------------------
                        ¿Está seguro de eliminar el articulo?
                        -------------------------
                        1. SI
                        2. NO
                        -------------------------
                        Opción:""");
                    op = Integer.parseInt(consola.nextLine());
                    switch (op){
                        case 1:
                            inventario[i].setId("000x0");
                            inventario[i].setMarca("Vacio");
                            inventario[i].setNombre("Vacio");
                            inventario[i].setPrecio(0.0);
                            inventario[i].setStock(0);

                            System.out.println("""
                              ------------------------------
                              ¡Articulo eliminado con éxito!
                              ------------------------------
                              """);
                            eliminado = true;
                            break;
                        case 2:
                            System.out.println("Volviendo al menú...");
                            break;
                        default:
                            System.out.println("Opción invalida, intente de nuevo...");
                            break;
                    }
                }while (op!=2 || eliminado == false);

                break;
            }
        }
        if(encontrado==false){
            System.out.println("""
                    ------------------------
                    ¡Articulo no encontrado!
                    ------------------------
                    """);
        }
    }

    public static String solicitarIdValido() {
        String id;
        do {
            System.out.print("Ingrese el id del articulo (máximo 10 caracteres alfanuméricos, sin espacios ni caracteres especiales): ");
            id = consola.nextLine();
            if (!id.matches("^[a-zA-Z0-9]{1,10}$")) {
                System.out.println("ID inválido. Debe ser alfanumérico, sin espacios ni caracteres especiales, y de máximo 10 caracteres.");
            }
        } while (!id.matches("^[a-zA-Z0-9]{1,10}$"));
        return id;
    }

    public static String solicitarNombreValido() {
        String nombre;
        do {
            System.out.print("Ingrese el nombre del producto (entre 3 y 50 caracteres): ");
            nombre = consola.nextLine();
            if (nombre.length() < 3 || nombre.length() > 50) {
                System.out.println("Nombre inválido. Debe tener entre 3 y 50 caracteres.");
            }
        } while (nombre.length() < 3 || nombre.length() > 50);
        return nombre;
    }

    public static String solicitarNombreMarcaValido() {
        String nombre;
        do {
            System.out.print("Ingrese el nombre de la marca producto (entre 3 y 50 caracteres): ");
            nombre = consola.nextLine();
            if (nombre.length() < 2 || nombre.length() > 50) {
                System.out.println("Nombre inválido. Debe tener entre 3 y 50 caracteres.");
            }
        } while (nombre.length() < 2 || nombre.length() > 50);
        return nombre;
    }

    public static double solicitarPrecioValido() {
        double precio;
        do {
            System.out.print("Ingrese el precio del articulo (debe ser mayor a 0): ");
            precio = Double.parseDouble(consola.nextLine());
            if (precio <= 0) {
                System.out.println("Precio inválido. Debe ser un valor positivo mayor a 0.");
            }
        } while (precio <= 0);
        return precio;
    }

    public static int solicitarCantidadValida() {
        int cantidad;
        do {
            System.out.print("Ingrese la cantidad de articulos (no puede ser negativa): ");
            cantidad = Integer.parseInt(consola.nextLine());
            if (cantidad < 0) {
                System.out.println("Cantidad inválida. No puede ser un valor negativo.");
            }
        } while (cantidad < 0);
        return cantidad;
    }

    public static void mensajeModificacion(){
        System.out.println("""
              -------------------------------
              ¡Articulo modificado con éxito!
              -------------------------------
              """);
    }
}
