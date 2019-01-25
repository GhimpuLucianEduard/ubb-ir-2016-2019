public abstract class Task
{
    private int taskID;
    private String descriere;

    public Task() {}
    public Task(int taskID, String descriere) {
        this.taskID = taskID;
        this.descriere = descriere;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Task{" + "taskID=" + taskID + ", descriere='" + descriere + '\'' + '}';
    }

    public abstract void execute();
}
