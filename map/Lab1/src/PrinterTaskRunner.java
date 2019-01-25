import java.util.Calendar;
import java.util.Date;

public class PrinterTaskRunner extends TaskRunnerDecorator
{

    public PrinterTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }

    @Override
    public void executeOneTask() {
        super.executeOneTask();
        Date date = new Date();
        System.out.println("Ora la care s-a executat task-ul: " + date.getHours() + ":" + date.getMinutes()+":"+date.getSeconds());


    }

}
