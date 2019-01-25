public class StrategyTaskRunner implements TaskRunner
{
    private Container container;

    public StrategyTaskRunner(Strategy st)
    {
        container = TaskContainerFactory.getInstance().createContainer(st);
    }

    @Override
    public void executeOneTask()
    {
        container.remove().execute();
    }

    @Override
    public void executeAll()
    {
        while(hasTask())
        {
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t)
    {
        container.add(t);
    }

    @Override
    public boolean hasTask()
    {
        return !container.isEmpty();
    }
}
