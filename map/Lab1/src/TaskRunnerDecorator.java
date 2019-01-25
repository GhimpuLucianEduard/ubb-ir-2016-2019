public abstract class TaskRunnerDecorator implements TaskRunner
{
    private TaskRunner taskRunner;

    public TaskRunnerDecorator(TaskRunner taskRunner)
    {
        this.taskRunner = taskRunner;
    }

    @Override
    public void executeOneTask()
    {
        taskRunner.executeOneTask();
    }

    @Override
    public void executeAll()
    {
        //taskRunner.executeAll();
        while(taskRunner.hasTask())
        {
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t)
    {
        taskRunner.addTask(t);
    }

    @Override
    public boolean hasTask()
    {
        return taskRunner.hasTask();
    }
}
