package Models;

public abstract class BaseModel {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseModel() {

    }

    public BaseModel(String id) {
        this.id = id;
    }
}
