package ClassToList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskUpdate {

    private final StringProperty lista_ID;
    private final StringProperty usuario_ID;
    private final StringProperty nombreTarea;
    private final StringProperty fechaCreacion;
    private final StringProperty fechaVencimiento;
    private final StringProperty estado;
    private final StringProperty prioridad;
    private final StringProperty notas;

    public TaskUpdate(int lista_ID, int usuario_ID, String nombreTarea, String fechaCreacion, String fechaVencimiento, String estado, String prioridad, String notas) {
        this.lista_ID = new SimpleStringProperty(Integer.toString(lista_ID));
        this.usuario_ID = new SimpleStringProperty(Integer.toString(usuario_ID));
        this.nombreTarea = new SimpleStringProperty(nombreTarea);
        this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
        this.fechaVencimiento = new SimpleStringProperty(fechaVencimiento);
        this.estado = new SimpleStringProperty(estado);
        this.prioridad = new SimpleStringProperty(prioridad);
        this.notas = new SimpleStringProperty(notas);
    }

    public String getLista_ID() {
        return lista_ID.get();
    }

    public void setLista_ID(String lista_ID) {
        this.lista_ID.set(lista_ID);
    }

    public String getUsuario_ID() {
        return usuario_ID.get();
    }

    public void setUsuario_ID(String usuario_ID) {
        this.usuario_ID.set(usuario_ID);
    }

    public String getNombreTarea() {
        return nombreTarea.get();
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea.set(nombreTarea);
    }

    public String getFechaCreacion() {
        return fechaCreacion.get();
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion.set(fechaCreacion);
    }

    public String getFechaVencimiento() {
        return fechaVencimiento.get();
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento.set(fechaVencimiento);
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public String getPrioridad() {
        return prioridad.get();
    }

    public void setPrioridad(String prioridad) {
        this.prioridad.set(prioridad);
    }

    public String getNotas() {
        return notas.get();
    }

    public void setNotas(String notas) {
        this.notas.set(notas);
    }

    public StringProperty lista_IDProperty() {
        return lista_ID;
    }

    public StringProperty usuario_IDProperty() {
        return usuario_ID;
    }

    public StringProperty nombreTareaProperty() {
        return nombreTarea;
    }

    public StringProperty fechaCreacionProperty() {
        return fechaCreacion;
    }

    public StringProperty fechaVencimientoProperty() {
        return fechaVencimiento;
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public StringProperty prioridadProperty() {
        return prioridad;
    }

    public StringProperty notasProperty() {
        return notas;
    }
}
