public class Main {
    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca(100, 20);
        sistema = sistema.CargarDatos();                                                                // recoge los datos cargados en el sistema
        sistema.iniciar();                                                                              // inicia el sistema con sus resoectivos datos
    }
}