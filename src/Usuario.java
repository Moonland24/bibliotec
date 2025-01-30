// se crea la clase usuario con una esteuctura común a la clase liros:
public class Usuario {

    private String id;
    private String nombre;
    private String apellido;
    private String rol;
    private String contrasenia;
    private Libro[] librosPrestados;
    private int contadorPrestamos;

    public Usuario(String id, String nombre, String apellido, String rol, String contrasenia, int capacidadLibros) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.librosPrestados = new Libro[capacidadLibros];
        this.contadorPrestamos = 0;
    }

// se empiezan con los getters y setter para obtener y modificar 
// los atributos del id del usuario
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

// se empiezan con los getters y setter para obtener y modificar 
// los atributos del nombre del usuario
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

// se empiezan con los getters y setter para obtener y modificar 
// los atributos del apellido del usuario
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

 // se empiezan con los getters y setter para obtener y modificar 
// los atributos del rol del usuario )si es adminitrador o usuario comun)
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

// se empiezan con los getters y setter para obtener y modificar 
// los atributos de la contraseña del usuario
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Libro[] getLibrosPrestados() {
        return librosPrestados;
    }

    public int getContadorPrestamos() {
        return contadorPrestamos;
    }

    // metodo los metodos de prestamo y devolucion de libros
    
    // Método para agregar un nuevo préstamo de libro
public boolean agregarPrestamo(Libro libro) {
                                                                 
    if (contadorPrestamos < librosPrestados.length) {           // Verifica si hay espacio disponible en el array de libros prestados
        librosPrestados[contadorPrestamos] = libro;             // Agrega el libro en la siguiente posición disponible
        contadorPrestamos++;                                    // Incrementa el contador de préstamos
        return true;                                            // Devuelve "true" indicando que el préstamo se realizó con éxito
    }
    return false;                                               // Retorna false si no hay espacio para más préstamos
}

// Método para procesar la devolución de un libro
public boolean devolverLibro(Libro libro) {
    
    for (int i = 0; i < contadorPrestamos; i++){                            // Busca el libro en el array de libros prestados
        if (librosPrestados[i].getTitulo().equals(libro.getTitulo())) {     // Compara si el título del libro coincide con alguno prestado
            for (int j = i; j < contadorPrestamos - 1; j++) {               // Reorganiza el array moviendo los libros una posición hacia atrás
                librosPrestados[j] = librosPrestados[j + 1];
            }
            librosPrestados[contadorPrestamos - 1] = null;                  // Elimina la referencia al último libro (ahora duplicado)
            contadorPrestamos--;                                            // Reduce el contador de préstamos
            return true;                                                    // Retorna true indicando que la devolución fue exitosa
        }
    }
    return false;                                                           // Retorna false si el libro no se encontró en los préstamos
}

// sobreescritura para el método de conversión de usuario en representación textual
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", libros prestados=" + contadorPrestamos + "]";
    }
}