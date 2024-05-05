import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titulo;
    private String autor;
    private String categoria;
    private int ejemplaresDisponibles;
    private String date;
    private List<Integer> calificaciones;
    private List<String> comentarios = new ArrayList<>();

    public Libro(String titulo, String autor, String categoria, int ejemplaresDisponibles, String date, List<Integer> calificaciones ) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.date = date;
        this.calificaciones = calificaciones;
    }

    public int obtenerMejorValoracion() {
        int mejorValoracion = Integer.MIN_VALUE;
        for (Integer calificacion : calificaciones) {
            if (calificacion > mejorValoracion) {
                mejorValoracion = calificacion;
            }
        }
        return mejorValoracion;
    }

    public int obtenerPeorValoracion() {
        int peorValoracion = Integer.MAX_VALUE;
        for (Integer calificacion : calificaciones) {
            if (calificacion < peorValoracion) {
                peorValoracion = calificacion;
            }
        }
        return peorValoracion;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDate() {
        return date;
    }
    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public List<Integer> getCalificaciones() {
        return calificaciones;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Titulo= " + titulo + " | autor= " + autor + " | Categoria= " + categoria +" | EjemplaresDisponibles= " + ejemplaresDisponibles +"| Fecha= "+ date + " | Calificaciones= " + calificaciones + " | Comentarios= " + comentarios;
    }


}
