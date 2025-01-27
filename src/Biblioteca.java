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

//Este método agrega un nuevo libro a un array de libros.
    public void addLibro(Libro libro) {
        if (contadorLibros < libros.length) {                           //Comprueba si hay espacio en el array libros comparando contadorLibros (número actual de libros) con la longitud de libros.
            libros[contadorLibros] = libro;                             //Coloca el libro que se quiere añadir en la posición contadorLibros.
            contadorLibros++;                                           //Suma 1 al contadorLibros para reflejar el número total de libros.
            System.out.println("Libro añadido: " + libro.toString());   //Muestra un mensaje indicando que el libro se ha añadido.
        } else {                                                        //Si no hay espacio
            System.out.println("No hay espacios para mas libros");    //Muestra un mensaje indicando que no hay espacio para más libros.
        }
    }

}