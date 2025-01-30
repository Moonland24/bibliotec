public class SistemaBiblioteca { 
    private Biblioteca biblioteca; // Declara una variable para almacenar la instancia de la biblioteca
    private Usuario usuarioActual; // Declara una variable para almacenar el usuario que ha iniciado sesión

    public SistemaBiblioteca(int capacidadLibros, int capacidadUsuarios) {      // Constructor que inicializa el sistema con capacidades específicas
        this.biblioteca = new Biblioteca(capacidadLibros, capacidadUsuarios);   // Crea una nueva biblioteca con las capacidades especificadas
        this.usuarioActual = null;                                              // Inicializa el usuario actual como null ya que nadie ha iniciado sesión
    }

// Método para iniciar sesión en el sistema
    public boolean login(String id, String contrasenia) {                       
        Usuario[] usuarios = biblioteca.getUsuarios();                          // Obtiene el array de usuarios registrados
        for (Usuario usuario : usuarios) {                                      // Recorre todos los usuarios del sistema
            if (usuario != null &&                                              // Verifica que el usuario existe
                usuario.getId().equals(id) &&                                   // Comprueba si el ID coincide
                usuario.getContrasenia().equals(contrasenia)) {                 // Comprueba si la contraseña coincide
                usuarioActual = usuario;                                        // Establece el usuario actual si las credenciales son correctas
                System.out.println("Bienvenido " + usuario.getNombre() + 
                    " (" + usuario.getRol() + ")");                             // Muestra mensaje de bienvenida con nombre y rol
                return true;                                                    // Retorna verdadero si el login fue exitoso
            }
        }
        System.out.println("Credenciales inválidas");                         // Muestra mensaje de error si las credenciales son incorrectas
        return false;                                                           // Retorna falso si el login falló
    }

// Método para cerrar sesión
    public void logout() { 
        usuarioActual = null;                                     // Elimina la referencia al usuario actual
        System.out.println("Sesión cerrada exitosamente");      // Muestra mensaje de confirmación
    }

// Método para realizar diferentes operaciones en la biblioteca
    public void realizarOperacion(String operacion, String... parametros) { 
        if (usuarioActual == null) {                                                        // Verifica si hay un usuario con sesión iniciada
            System.out.println("Debe iniciar sesión primero");                            // Muestra mensaje si no hay sesión iniciada
            return;                                                                         // Sale del método si no hay usuario
        }

        switch (operacion.toLowerCase()) { 

    // Caso para realizar préstamos
            case "prestamo":                                                            
                if (parametros.length == 1) {                                               // Verifica que se proporcione un parámetro
                    if (biblioteca.prestarLibro(parametros[0], usuarioActual.getId())) {    // Intenta realizar el préstamo
                        System.out.println("Préstamo realizado con éxito");               // Muestra mensaje de éxito
                    } else {
                        System.out.println("No se pudo realizar el préstamo");            // Muestra mensaje de error
                    }
                }
                break;

 // Caso para realizar devoluciones
            case "devolucion":
                if (parametros.length == 1) {                                               // Verifica que se proporcione un parámetro
                    if (biblioteca.devolverLibro(parametros[0], usuarioActual.getId())) {   // Intenta realizar la devolución
                        System.out.println("Devolución realizada con éxito");             // Muestra mensaje de éxito
                    } else {
                        System.out.println("No se pudo realizar la devolución");          // Muestra mensaje de error
                    }
                }
                break;

// Caso para ver préstamos
            case "ver_prestamos": 
                if (usuarioActual.getRol().equalsIgnoreCase("ADMIN")) {     // Verifica si el usuario es administrador
                    biblioteca.mostrarLibrosPrestados(usuarioActual.getId());             // Muestra los libros prestados
                } else {
                    System.out.println("No tiene permisos para esta operación");        // Muestra mensaje si no tiene permisos
                }
                break;
            }
        }

// Método para salir del sistema
    public void salirSistema() { 
        if (usuarioActual != null) {                                                            // Verifica si hay un usuario con sesión iniciada
            System.out.println("Gracias por usar el sistema, " + usuarioActual.getNombre());    // Muestra mensaje de despedida
            logout();                                                                           // Cierra la sesión del usuario
        }
        System.out.println("Cerrando el sistema...");                                         // Muestra mensaje de cierre
        System.exit(0);                                                                  // Termina la ejecución del programa
    }
}
