package ClassToList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskDescript {

    private final StringProperty CNombreTarea;
    private final StringProperty Stado;
    private final StringProperty Prioridad;

    private final StringProperty FechaInicio;
    private final StringProperty FechaFinal;

    public TaskDescript(String CNombreTarea, String stado, String prioridad, String fechainicio, String fechafinal) {
        this.CNombreTarea = new SimpleStringProperty(CNombreTarea);
        this.Stado = new SimpleStringProperty(stado);
        this.Prioridad = new SimpleStringProperty(prioridad);
        this.FechaInicio = new SimpleStringProperty(fechainicio);
        this.FechaFinal = new SimpleStringProperty(fechafinal);
    }



    public String getCNombreTarea() {
        return CNombreTarea.get();
    }

    public void setCNombreTarea(String CNombreTarea) {
        this.CNombreTarea.set(CNombreTarea);
    }

    public String getStado() {
        return Stado.get();
    }

    public void setStado(String stado) {
        this.Stado.set(stado);
    }

    public String getPrioridad() {
        return Prioridad.get();
    }
    public String getFechainicio(){
        return FechaInicio.get();
    }
    public String getFechafinal(){
        return FechaFinal.get();
    }
    public void setFechaInicio(String fechaInicio){
        this.FechaInicio.set(fechaInicio);
    }

    public void setFechaFinal(String fechaFinal){
        this.FechaFinal.set(fechaFinal);
    }

    public void setPrioridad(String prioridad) {
        this.Prioridad.set(prioridad);
    }

    public StringProperty getNameProperty(){
        return CNombreTarea;
    }
    public StringProperty getStadoProperty(){
        return Stado;
    }
    public StringProperty getPriorityProperty(){
        return Prioridad;
    }

    public StringProperty getFechaInicioProperty(){
        return FechaInicio;
    }
    public StringProperty getFechaFinalProperty(){
        return FechaFinal;
    }
}
