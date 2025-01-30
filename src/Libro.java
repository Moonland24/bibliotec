public class Libro {

// se hacen los atributos de la clase Libros
    private String titulo;
    private String autor;
    private String categoria;
    //-- falta el atributo privado de préstamo--
    private boolean prestado;

// se crean los constructores de la clase   
    public Libro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.prestado = false;
    }

// se empiezan con los getters y setter para obtener y modificar 
// los ateibutos del titulo

// se devuelve el valor de titulo
    public String getTitulo() {
        return titulo;
    }

// permite cambiar el calor del titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

// se empiezan con los getters y setter para obtener y modificar 
// los atributos del autor
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


// se empiezan con los getters y setter para obtener y modificar 
// los atributos de las categorias
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // los get/set para mostrar préstamos
    public boolean isPrestado(){
        return prestado;
    }
    public void setPrestado(boolean prestado){
        this.prestado = prestado;
    }
// sobreescritura para el método de conversión de libros en representación textual
    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", prestado=" + prestado + "]";
    }

}
