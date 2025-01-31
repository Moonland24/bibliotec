import java.util.Scanner;

// creacion de la clase sistema de biblioteca que contendrá los diferentes datos y funciones del sistema y menú
public class SistemaBiblioteca {
    private Biblioteca biblioteca;
    private Usuario usuarioActual;
    private Scanner scanner;

    public SistemaBiblioteca(int capacidadLibros, int capacidadUsuarios) {
        this.biblioteca = new Biblioteca(capacidadLibros, capacidadUsuarios);
        this.usuarioActual = null;
        this.scanner = new Scanner(System.in);
    }

// creación del método de la función de inicio de sesión
    public void iniciar() {
        mostrarBanner();                                            // muestra la decoración del programa
        while (true) {                                              // enseña el menu de login para iniciar el programa
            if (usuarioActual == null) {                            // si está registrado
                mostrarMenuLogin();
            } else {
                mostrarMenuPrincipal();                             // muestra el menú principal
            }
        }
    }

// banner decorativo del programa
    private void mostrarBanner() {
        System.out.println("\n" +
            "╔════════════════════════════════════════╗\n" +
            "║      SISTEMA DE GESTIÓN BIBLIOTECA     ║\n" +
            "╚════════════════════════════════════════╝\n");
    }

// funciones para iniciar sesión
    private void mostrarMenuLogin() {
        System.out.println("\n--- INICIAR SESIÓN ---");
        System.out.print("ID de usuario: ");
        String id = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        if (login(id, contrasenia)) {
            System.out.println("\n Inicio de sesión exitoso!");
        } else {
            System.out.println("\n Credenciales incorrectas. Intente nuevamente.");
        }
    }

// creación del menú principal
    private void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("Bienvenido, " + usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");
        System.out.println("-----------------------------");
        System.out.println("1.  Préstamo de Libro");
        System.out.println("2.  Devolución de Libro");
        System.out.println("3.  Mostrar Libros Disponibles");
        
        if (usuarioActual.getRol().equalsIgnoreCase("ADMIN")) {
            System.out.println("4.  Estadísticas de Biblioteca");
            System.out.println("5.  Libros Prestados");
        }
        
        System.out.println("0.  Cerrar Sesión");
        System.out.print("\nSeleccione una opción: ");

        String opcion = scanner.nextLine();
        procesarOpcion(opcion);
    }

// diferentes ociones ejecutables con sus respectivas funciones
    private void procesarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                realizarPrestamo();
                break;
            case "2":
                realizarDevolucion();
                break;
            case "3":
                biblioteca.mostrarLibros();
                break;
            case "4":
                if (usuarioActual.getRol().equalsIgnoreCase("ADMIN")) {
                    mostrarEstadisticas();
                } else {
                    System.out.println(" Opción no válida.");
                }
                break;
            case "5":
    
                if (usuarioActual.getRol().equalsIgnoreCase("ADMIN")) {
                    biblioteca.mostrarLibrosPrestados(usuarioActual.getId());
                } else {
                    System.out.println(" Opción no válida.");
                }
                break;
            case "0":
                logout();
                break;
            default:
                System.out.println(" Opción no válida. Intente nuevamente.");
        }
    }

// método de la función de realizar préstamo
    private void realizarPrestamo() {
        System.out.print("Ingrese el título del libro a prestar: ");
        String titulo = scanner.nextLine();                                               // se lee lo introducido por el usuario en teclado
        
        if (biblioteca.prestarLibro(titulo, usuarioActual.getId())) {                     // si lo que ha introducido el usuario está en la bbdd
            usuarioActual.setContadorPrestamos(usuarioActual.getContadorPrestamos() + 1); // aumentamos el contador de prestamos del usuario actual
            System.out.println(" Préstamo realizado con éxito.");                       // mendaje de confirmación
        } else {
            System.out.println(" No se pudo realizar el préstamo.");                    // si no se encientra el libro, pues da error
        }
    }

// método para la función de realizar una devolución.
    private void realizarDevolucion() {
        System.out.print("Ingrese el título del libro a devolver: ");
        String titulo = scanner.nextLine();                                         // se lee el libro que se quiere devolver por el usuario
        if (biblioteca.devolverLibro(titulo, usuarioActual.getId())) {              // si el libro está dentro de los prestamos del usuario
            System.out.println(" Devolución realizada con éxito.");               // se realiza la devolución
        } else {
            System.out.println(" No se pudo realizar la devolución.");            // si no, se muestra el error
        }
    }

// método para la funcionalidad de las estadísticas de la biblioteca
    private void mostrarEstadisticas() {
        System.out.println("\n--- ESTADÍSTICAS DE BIBLIOTECA ---");
        System.out.println("Total de préstamos: " + biblioteca.contarTotalPrestamos());                 //cmuestra la cantidad de prétamos totales 
        
        Usuario usuarioConMasPrestamos = biblioteca.usuarioConMasPrestamos();                           //muestra el usuario con más préstamos
        System.out.println("Illo toy aqui");
        System.out.println(usuarioConMasPrestamos);
        if (usuarioConMasPrestamos != null) {
            System.out.println("Usuario con más préstamos: " + usuarioConMasPrestamos.getNombre() + 
                               " (" + usuarioConMasPrestamos.getContadorPrestamos() + " préstamos)");   //enseña ambas cosas por mensaje
        }
        System.out.println("\n--- LIBROS MÁS PRESTADOS ---");
        biblioteca.mostrarLibrosMasPrestados();                                                         //se muestra cual es el libro que ha sido prestado más veces
    }

// método para la función de logear un usuario
    public boolean login(String id, String contrasenia) {
        Usuario[] usuarios = biblioteca.getUsuarios();                  // se recoge de la biblioteca a los usuarios registrados
        for (Usuario usuario : usuarios) {
            if (usuario != null && 
                usuario.getId().equals(id) &&                           // el usuario tenfrá que proporcionar su respectivo id
                usuario.getContrasenia().equals(contrasenia)) {         // y seguidamente tendrá que proporcionar la contraseña
                usuarioActual = usuario;
                return true;                                            // admite seguir adelante
            }
        }
        return false;                                           
    }

// método para la función de salir del programa
    public void logout() {
        System.out.println("\n🚪 Cerrando sesión...");
        usuarioActual = null;
        iniciar();
    }

// carga de datos de libros y de usuarios
    public SistemaBiblioteca CargarDatos() {
        SistemaBiblioteca sistema = new SistemaBiblioteca(100, 20);
        
        sistema.biblioteca.addUsuario(new Usuario("Inma1", "Inmaculada", "Martin", "ADMIN", "admin123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Brayan1", "Brayan", "Stiven", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Rodas1", "Manuel", "Pinilla", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Trinidad1", "José", "Trinidad", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Mario1", "Mario", "Lopez", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Gabino1", "Jose", "Gabino", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Miguel1", "Miguel", "Gonzalez", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Juanfran1", "Juan", "Francisco", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Antonio1", "Antonio", "Gonzalez", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Alberto1", "Alberto", "Garcia", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Angel1", "Angel", "Villorina", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Ronic1", "Ronic", "Leal", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Ana1", "Ana", "Rubio", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Rebeca1", "Rebeca", "Poma", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Teresa1", "Teresa", "Calvo", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Lucia1", "Lucía", "Fernández", "USER", "user123", 20));
        sistema.biblioteca.addUsuario(new Usuario("Diego1", "Diego", "Guerrero", "USER", "user123", 20));
//------------------------------------------------------------------------------------------------------------------------------------------------------------//
        sistema.biblioteca.addLibro(new Libro("El misterio del tiempo", "Carlos Ruiz", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Ciencia para todos", "Ana Martínez", "No Ficción"));
        sistema.biblioteca.addLibro(new Libro("La sombra del viento", "Javier Castillo", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Historia del mundo", "Luis Gómez", "Historia"));
        sistema.biblioteca.addLibro(new Libro("El arte de la guerra", "Sun Tzu", "Estrategia"));
        sistema.biblioteca.addLibro(new Libro("Programación en Java", "James Gosling", "Tecnología"));
        sistema.biblioteca.addLibro(new Libro("Los secretos del universo", "Neil Tyson", "Ciencia"));
        sistema.biblioteca.addLibro(new Libro("Viaje al centro de la Tierra", "Julio Verne", "Aventura"));
        sistema.biblioteca.addLibro(new Libro("Las matemáticas y la vida", "María Fernández", "Educación"));
        sistema.biblioteca.addLibro(new Libro("Cocina para principiantes", "Gordon Ramsay", "Gastronomía"));
        sistema.biblioteca.addLibro(new Libro("El alquimista", "Paulo Coelho", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Marketing digital", "Philip Kotler", "Negocios"));
        sistema.biblioteca.addLibro(new Libro("Crimen y castigo", "Fiódor Dostoievski", "Clásico"));
        sistema.biblioteca.addLibro(new Libro("Física cuántica para todos", "Stephen Hawking", "Ciencia"));
        sistema.biblioteca.addLibro(new Libro("Psicología del comportamiento", "Daniel Kahneman", "Psicología"));
        sistema.biblioteca.addLibro(new Libro("Los pilares de la Tierra", "Ken Follett", "Histórica"));
        sistema.biblioteca.addLibro(new Libro("El código Da Vinci", "Dan Brown", "Thriller"));
        sistema.biblioteca.addLibro(new Libro("El Hobbit", "J.R.R. Tolkien", "Fantasía"));
        sistema.biblioteca.addLibro(new Libro("Empresas que sobresalen", "Jim Collins", "Negocios"));
        sistema.biblioteca.addLibro(new Libro("La revolución de la inteligencia artificial", "Andrew Ng", "Tecnología"));
        sistema.biblioteca.addLibro(new Libro("Hábitos atómicos", "James Clear", "Desarrollo Personal"));
        sistema.biblioteca.addLibro(new Libro("El hombre en busca de sentido", "Viktor Frankl", "Psicología"));
        sistema.biblioteca.addLibro(new Libro("1984", "George Orwell", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Breve historia del tiempo", "Stephen Hawking", "Ciencia"));
        sistema.biblioteca.addLibro(new Libro("El principito", "Antoine de Saint-Exupéry", "Infantil"));
        sistema.biblioteca.addLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Aprendiendo Python", "Guido van Rossum", "Tecnología"));
        sistema.biblioteca.addLibro(new Libro("Mindset: La actitud del éxito", "Carol Dweck", "Psicología"));
        sistema.biblioteca.addLibro(new Libro("El poder del ahora", "Eckhart Tolle", "Espiritualidad"));
        sistema.biblioteca.addLibro(new Libro("Un mundo feliz", "Aldous Huxley", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Manual de finanzas personales", "Robert Kiyosaki", "Finanzas"));
        sistema.biblioteca.addLibro(new Libro("Guía del inversionista", "Benjamin Graham", "Negocios"));
        sistema.biblioteca.addLibro(new Libro("El señor de los anillos", "J.R.R. Tolkien", "Fantasía"));
        sistema.biblioteca.addLibro(new Libro("Los hombres me explican cosas", "Rebecca Solnit", "Feminismo"));
        sistema.biblioteca.addLibro(new Libro("Sapiens: De animales a dioses", "Yuval Noah Harari", "Historia"));
        sistema.biblioteca.addLibro(new Libro("El arte de amar", "Erich Fromm", "Psicología"));
        sistema.biblioteca.addLibro(new Libro("Meditaciones", "Marco Aurelio", "Filosofía"));
        sistema.biblioteca.addLibro(new Libro("La guerra de los mundos", "H.G. Wells", "Ciencia Ficción"));
        sistema.biblioteca.addLibro(new Libro("Inteligencia emocional", "Daniel Goleman", "Psicología"));
        sistema.biblioteca.addLibro(new Libro("El poder de los hábitos", "Charles Duhigg", "Desarrollo Personal"));
        sistema.biblioteca.addLibro(new Libro("El gran Gatsby", "F. Scott Fitzgerald", "Clásico"));
        sistema.biblioteca.addLibro(new Libro("Guerra y paz", "León Tolstói", "Clásico"));
        sistema.biblioteca.addLibro(new Libro("La Divina Comedia", "Dante Alighieri", "Poesía"));
        sistema.biblioteca.addLibro(new Libro("Las 48 leyes del poder", "Robert Greene", "Estrategia"));
        sistema.biblioteca.addLibro(new Libro("Cómo ganar amigos e influir sobre las personas", "Dale Carnegie", "Negocios"));
        sistema.biblioteca.addLibro(new Libro("El nombre del viento", "Patrick Rothfuss", "Fantasía"));
        sistema.biblioteca.addLibro(new Libro("Sherlock Holmes: Estudio en escarlata", "Arthur Conan Doyle", "Misterio"));
        sistema.biblioteca.addLibro(new Libro("Los juegos del hambre", "Suzanne Collins", "Juvenil"));
        sistema.biblioteca.addLibro(new Libro("Percy Jackson y el ladrón del rayo", "Rick Riordan", "Fantasía"));
        sistema.biblioteca.addLibro(new Libro("La Iliada", "Homero", "Historia"));
        sistema.biblioteca.addLibro(new Libro("Steve Jobs: La biografía", "Walter Isaacson", "Biografía"));
        sistema.biblioteca.addLibro(new Libro("La odisea", "Homero", "Historia"));
        sistema.biblioteca.addLibro(new Libro("Orgullo y prejuicio", "Jane Austen", "Romance"));
        sistema.biblioteca.addLibro(new Libro("Drácula", "Bram Stoker", "Terror"));
        sistema.biblioteca.addLibro(new Libro("Frankestein", "Mary Shelley", "Terror"));
        sistema.biblioteca.addLibro(new Libro("El perfume", "Patrick Süskind", "Thriller"));
        sistema.biblioteca.addLibro(new Libro("Matar a un ruiseñor", "Harper Lee", "Clásico"));
        sistema.biblioteca.addLibro(new Libro("El extranjero", "Albert Camus", "Filosofía"));
        sistema.biblioteca.addLibro(new Libro("La casa de los espíritus", "Isabel Allende", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("El lobo de Wall Street", "Jordan Belfort", "Negocios"));
        sistema.biblioteca.addLibro(new Libro("El psicoanalista", "John Katzenbach", "Thriller"));
        sistema.biblioteca.addLibro(new Libro("Los miserables", "Victor Hugo", "Clásico"));
        sistema.biblioteca.addLibro(new Libro("Moby Dick", "Herman Melville", "Aventura"));
        sistema.biblioteca.addLibro(new Libro("Las venas abiertas de América Latina", "Eduardo Galeano", "Historia"));
        sistema.biblioteca.addLibro(new Libro("El poder del pensamiento positivo", "Norman Vincent Peale", "Autoayuda"));
        sistema.biblioteca.addLibro(new Libro("El laberinto de los espíritus", "Carlos Ruiz Zafón", "Ficción"));
        sistema.biblioteca.addLibro(new Libro("Hacia rutas salvajes", "Jon Krakauer", "Aventura"));

        return sistema;
    }
}