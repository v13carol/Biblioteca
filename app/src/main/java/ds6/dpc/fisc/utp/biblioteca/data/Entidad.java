package ds6.dpc.fisc.utp.biblioteca.data;

import android.content.Intent;

/**Entidad tabla de Libros*/
public class Entidad {
    private Integer id;
    private String isbn;
    private String titulo;
    private String autor;
    private String area;
    private String editorial;
    private String year;

    public Entidad(Integer id, String isbn, String titulo, String autor, String area, String editorial, String year) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.area = area;
        this.editorial = editorial;
        this.year = year;
    }

    public Entidad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
