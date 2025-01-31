public class Libro {

// se hacen los atributos de la clase Libros
    private String titulo;
    private String autor;
    private String categoria;
    private boolean prestado;
    private int vecesPrestado;

// se crean los constructores de la clase   
    public Libro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.prestado = false;
        this.vecesPrestado = 0;
    }

// se empiezan con los getters y setter para obtener y modificar los atributos del título se devuelve el valor del título
    public String getTitulo() {
        return titulo;
    }

// permite cambiar el calor del titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

// se empiezan con los getters y setter para obtener y modificar los atributos del autor
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

// se empiezan con los getters y setter para obtener y modificar los atributos de las categorías
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

    // los get/set para contar las veces que ha sido prestado
    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }

    // sobreescritura para el método de conversión de libros en representación textual
    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + ", prestado=" + prestado + ", Veces prestado=" + vecesPrestado + "]";
    }
}
