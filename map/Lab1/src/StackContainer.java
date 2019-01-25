public class StackContainer extends AbstractContainer
{

    public StackContainer() {}


    @Override
    public Task remove()
    {
        Task aux = null;
        try {
            aux = super.tasks.delete(super.tasks.size() - 1);

        } catch (ArrayExceptions ex) {
            ex.getMsg();
        }
        return aux;
    }



}
