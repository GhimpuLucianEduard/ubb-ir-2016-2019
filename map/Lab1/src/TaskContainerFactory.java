public class TaskContainerFactory implements Factory
{

    private static TaskContainerFactory instance = null;

    private TaskContainerFactory() {};

    public static TaskContainerFactory getInstance()
    {
        if(instance == null)
        {
            instance = new TaskContainerFactory();
        }

        return instance;
    }


//    private static int nr=0;
//
//    public TaskContainerFactory() throws Exception {
//        nr++;
//        if(nr>1)
//        {
//            throw new Exception("Can't create more TaskContainerFactories");
//        }
//    }

    @Override
    public Container createContainer(Strategy strategy)
    {
        if(strategy == Strategy.FIFO)
        {
            return new QueueContainer();
        }
        else if(strategy == Strategy.LIFO)
        {
            return new StackContainer();
        }
        else
            return null;
    }
}
