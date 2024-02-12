package ClassToList;

public class Task {
    String TaskName;
    String CreationDate;
    String ExpirationDate;
    String State;
    String Priority;
    String Grades;

    public Task(String taskName, String creationDate, String expirationDate, String state, String priority, String grades) {
        TaskName = taskName;
        CreationDate = creationDate;
        ExpirationDate = expirationDate;
        State = state;
        Priority = priority;
        Grades = grades;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getGrades() {
        return Grades;
    }

    public void setGrades(String grades) {
        Grades = grades;
    }
}
