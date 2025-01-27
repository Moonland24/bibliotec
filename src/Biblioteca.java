import java.util.Arrays;

// se crea la clase biblioteca donde se empiezan a cargar los datos iniciales así como funcionalidades

//atributos de la clase biblioteca
public class Biblioteca {
    private Libro[] libros;
    private Usuario[] usuarios;
    private int contadorLibros;
    private int contadorUsuarios;

// se establecen los constructores de los atributos
    public Biblioteca(int capacidadLibros, int capacidadUsuarios) {
        this.libros = new Libro[capacidadLibros];
        this.usuarios = new Usuario[capacidadUsuarios];
        contadorLibros = 0;
        contadorUsuarios = 0;
    }

// set/get de modificación y obtención de los atributos
    public Libro[] getLibros() {
        return libros;
    }

    public void setLibros(Libro[] libros) {
        this.libros = libros;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

// sobreescritura para el metodo de conversión a una representación textual
    @Override
    public String toString() {
        return "Biblioteca [libros=" + Arrays.toString(libros) + ", usuarios=" + Arrays.toString(usuarios) + "]";
    }

}
