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
        for (int i = 0; i < contadorLibros; i++) {                              //Este bucle recorre el array libros desde la posición 0 hasta contadorLibros (que indica la cantidad de libros almacenados actualmente).
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

// se crea e método que permite buscar libros
    public Libro[] buscarLibro(String caracterBusqueda, String valor) {
        int coincidencias = 0;                                                                        //Se cuentan cuantos libros coinciden con el criterio de búsqueda, dando la primera repsada del array
        System.out.println("Resultados de la búsqueda para " + caracterBusqueda + ": " + valor);      //imprime el criterio por el que se busca y el numero de coincidencias

        for (int i = 0; i < contadorLibros; i++) {                                                                                      // se recorre nuevamente el array                                                                      
            if ((caracterBusqueda.equalsIgnoreCase("titulo") && libros[i].getTitulo().equalsIgnoreCase(valor)) ||         // toma en cuenta la misma búsqueda para todo
                    (caracterBusqueda.equalsIgnoreCase("categoria") && libros[i].getCategoria().equalsIgnoreCase(valor))
                    ||
                    (caracterBusqueda.equalsIgnoreCase("autor") && libros[i].getAutor().equalsIgnoreCase(valor))) {
                coincidencias++;                                                                                                        // incrementa las coincidencias si las hubiera
            }
        }

        Libro[] resultados = new Libro[coincidencias];                                                                                    // se crea un nuevo array para almacenar esas coincidencias
        int indice = 0;                                                                                                                   // incrementa el ídice para el siguiente array de resultados

        for (int i = 0; i < contadorLibros; i++) {
            if ((caracterBusqueda.equalsIgnoreCase("titulo") && libros[i].getTitulo().equalsIgnoreCase(valor)) ||                   // vuelve a recorrer el array de libros actuales, pero esta vez se asigna a
                    (caracterBusqueda.equalsIgnoreCase("categoria") && libros[i].getCategoria().equalsIgnoreCase(valor))
                    ||
                    (caracterBusqueda.equalsIgnoreCase("autor") && libros[i].getAutor().equalsIgnoreCase(valor))) {
                resultados[indice] = libros[i];                                                                                                   // resultados el valor del libro
                indice++;                                                                                               
            }
        }


        return resultados;          // devuelve la lista de libros que se ha encontrado con el caracterBusqueda y el valor que se ha dicho
    }

//se crea el método que permite mostrar libros
    public void mostrarLibros() {                                   
        System.out.println("Lista de libros: ");                // muestra una lista de libros
        if(contadorLibros > 0){                                   // si el contador de libros es mayor a 0
            for (int i = 0; i < contadorLibros; i++) {            //lo recorre
                System.out.println(libros[i].toString());         // e imprime todos los libros que hay registrados
            }
        }else{                                                    // si no, lo que hace es 
            System.out.println("No hay libros registrados.");   // mostrar un mensaje que diga que no hay libros registrados
        }

    }

//se crea el método de añadir usuarios
    public void addUsuario(Usuario usuario) {                                 //se llama a la clase de usuario y sus características
        if (contadorUsuarios < usuarios.length) {                             //compara la cantidad de usuarios actuales en el array con la capacidad que hay para ellos para determinar si se puede o no añadir a más usuarios
            usuarios[contadorUsuarios] = usuario;                             //Accede a la posición actual del array donde se debe guardar el nuevo usuario y Signa el objeto usuario al array en la posición indicada por contadorUsuarios
            contadorUsuarios++;                                               // una vez guardado, incrmeenta el contador de usuarios
            System.out.println("Usuario añadido: " + usuario.toString());     //imprime la confirmación de que se ha añadido el usuario
        } else {
            System.out.println("No hay espacios para mas usuarios");        // en caso de que no haya habido espacio, lo informa
        }
    }

// se crea el método de mostrar usuario
    public void mostrarUsuarios() {
        System.out.println("Lista de usuarios actuales: ");         // se imprime un mensaje de lista en caso de que
        if (contadorUsuarios > 0) {                                   //la cantidad de usuarios sea mayor a cero
            for (int i = 0; i < contadorUsuarios; i++) {              // que para ello recorre la lista de usurios añadidos
                System.out.println(usuarios[i].toString());           // y por lo tanto las imprime
            }
        } else {                                                      // en caso contrario de que no se cumpla esa cndición
            System.out.println("No hay usuarios registrados.");     // manda un mensaje de aviso como que no los hay registrados
        }
    }

}