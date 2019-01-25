public class ArrayExceptions extends Throwable
{
    private String msg;

    public ArrayExceptions(String msg)
    {
        this.msg = msg;
    }


    public String getMsg()
    {
        return msg;
    }

}
