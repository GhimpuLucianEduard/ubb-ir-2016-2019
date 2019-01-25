public class  MessageTask extends Task
{
    private String mesaj;

    public MessageTask(int taskID, String descriere, String mesaj)
    {
        super(taskID, descriere);
        this.mesaj = mesaj;
    }

    public String getMesaj()
    {
        return mesaj;
    }

    public void setMesaj(String mesaj)
    {
        this.mesaj = mesaj;
    }


    @Override
    public void execute()
    {
        System.out.println(mesaj);
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + mesaj;
    }
}
