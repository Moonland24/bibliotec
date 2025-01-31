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

// se empiezan con los getters y setter para obtener y modificar los atributos del id de usuario
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

// se empiezan con los getters y setter para obtener y modificar  los tributo del nombre de usuario
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

// se empiezan con los getters y setter para obtener y modificar los atributos del apellido del uss
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

 // se empiezan con los getters y setter para obtener y modificar  los atributos de rol del uss o adm
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

// se empiezan con los getters y setter para obtener y modificar los ateibutos de contraseña 
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getContadorPrestamos() {
        return contadorPrestamos;
    }

    public void setContadorPrestamos(int contadorPrestamos){
        this.contadorPrestamos = contadorPrestamos;
    }

    // Método para agregar un nuevo préstamo de libro
    public boolean agregarPrestamo(Libro libro) {
                                                                 
        if (contadorPrestamos < librosPrestados.length) {           // Verifica si hay espacio disponible en el array de libros prestados
            librosPrestados[contadorPrestamos] = libro;             // Agrega el libro en la siguiente posición disponible
            contadorPrestamos++;                                    // Incrementa el contador de préstamos
            return true;                                            // Devuelve "true" indicando que el préstamo se realizó con éxito
        }
        return false;                                               // Retorna false si no hay espacio para más préstamos
    }

    public void eliminarPrestamo(Libro libro) {
        for (int i = 0; i < contadorPrestamos; i++) {
            if (librosPrestados[i].getTitulo().equals(libro.getTitulo())) {
            librosPrestados[i] = null;
            contadorPrestamos--;
            break;
            }
        }
    }

    public Libro[] getLibrosPrestados() {
        return librosPrestados;
    }

// sobreescritura para el método de conversión de usuario en representación textual
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", libros prestados=" + contadorPrestamos + "]";
    }
}