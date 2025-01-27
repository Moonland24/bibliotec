// se crea la clase usuario con una esteuctura común a la clase liros:
public class Usuario {

    private String id;
    private String nombre;
    private String apellido;
    private String rol;
    private String contrasenia;
    //-- falta el atributo de los libros prestados--


    public Usuario(String id, String nombre, String apellido, String rol, String contrasenia, int capacidadLibros) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.contrasenia = contrasenia;
        //-- falta el constructo de prestamos a un usuario junto a la cantidad de los mismos--
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

//-- faltan los get/set para mostrar y modificar los libros prestados de cada usuario--

// sobreescritura para el método de conversión de usuario en representación textual
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + "]";
    }
}