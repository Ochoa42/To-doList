package ClassToList;

import java.util.Date;

public class List {
    String ListName;
    String CreationDate;
    String Description;

    public List(String listName, String creationDate, String description) {
        ListName = listName;
        CreationDate = creationDate;
        Description = description;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
