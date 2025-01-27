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

// Este método elimina un libro que ya etá cargado en la bibioteca de datos
    public void deleteLibro(Libro libro) {                              
        boolean encontrado = false;                                             //primero se comprueba si el libro a eliminar se encuentra o no.
        for (int i = 0; i < contadorLibros; i++) {                              //Este bucle recorre el array libros desde la posición 0 hasta...                                                                               //...contadorLibros (que indica la cantidad de libros almacenados actualmente).
            if (libros[i].getTitulo().equalsIgnoreCase(libro.getTitulo())) {    //Para cada posición i, compara el título del libro en esa posición (libros[i].getTitulo()) con el título del libro proporcionado (libro.getTitulo()), usando equalsIgnoreCase (que no distingue entre mayúsculas y minúsculas).
                encontrado = true;                                              //si lo encientra, procede a borra el libro.
                for (int j = i; j < contadorLibros - 1; j++) {                  //al encontrar el libro el proceso de eliminado se hace de tal manera que se desplaza a la izquierda y sobreescribe el libro a eliminar.
                    libros[j] = libros[j + 1];                                  //Al mover todos los elementos posteriores hacia la izquierda, el último libro del arreglo queda "duplicado". Este valor duplicado será eliminado más adelante.
                }
                libros[contadorLibros - 1] = null;                              //Una vez que los elementos han sido desplazados, asigna null a la última posición válida (contadorLibros - 1), que antes contenía el duplicado.
                contadorLibros--;                                               //Decrementa contadorLibros para reflejar que ahora hay un libro menos en el array de los libretes.
                System.out.println("Libro eliminado: " + libro.toString());     //imprime el mensaje que confirma que se ha borrado el libro.
                break;
            }
        }       
        if (!encontrado) {                                                      //si no se encuentra el libro
            System.out.println("Libro no encontrado: " + libro.toString());     //se muestra un mensaje como que el libro no se ha encontrado.
        }

    }
}