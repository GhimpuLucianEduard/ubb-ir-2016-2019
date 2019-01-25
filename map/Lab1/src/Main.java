import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
//        System.out.println("wow");
//        int[] numere = {312,312,3214,43543,5234,2132,4,1,313,21};
//
//        Task task1 = new MessageTask(1,"daa","mesaj1");
//        Task task2 = new SortingTask(2,"weq",numere);
//        Task task3 = new SortingTask(2,"weq",numere);
//        Task task4 = new SortingTask(2,"weq",numere);
//        Task task5 = new MessageTask(1,"dadasdasdsadasa","mesaj2");
//        Task task6 = new MessageTask(1,"deeeeeeeeeeaa","mesaj1");
//        Task task7 = new MessageTask(1,"dzzzzzzzzaa","mesaj1");
//        Task task8 = new MessageTask(1,"daa","mesaj8");
//        Task task9 = new SortingTask(2,"weq",numere);
//        Task task10 = new MessageTask(1,"daa","mesaj3");
//        Task task11 = new SortingTask(2,"weq",numere);

//        TaskArray taskArray = new TaskArray();
//        taskArray.push_back(task1);
//        System.out.println(taskArray.get(0));
//        try
//        {
//            taskArray.push_back_byIndex(task2,1);
//            System.out.println(taskArray.get(1));
//        }
//        catch (ArrayExceptions ex)
//        {
//            System.out.println(ex.getMsg());;
//        }
//
//
//        task1.execute();
//        task2.execute();
//
//
//
//        StackContainer stack = new StackContainer(taskArray);
//        stack.add(task3);
//        System.out.println(stack.size());
//        stack.add(task4);
//        System.out.println(stack.size());
//        stack.add(task5);
//        System.out.println(stack.size());
//        System.out.println(stack.remove());
//        System.out.println(stack.size());
//
//        TaskArray ptq = new TaskArray();
//        QueueContainer que = new QueueContainer(ptq);
//        System.out.println(que.size());
//        que.add(task6);
//        System.out.println(que.size());
//        que.add(task7);
//        System.out.println(que.size());
//        que.add(task8);
//        System.out.println(que.size());
//        que.add(task9);
//        System.out.println(que.size());
//
//        System.out.println(que.remove());
//        System.out.println(que.size());
//        System.out.println(que.remove());
//        System.out.println(que.size());






//        try
//        {
//            TaskContainerFactory tf1 = new TaskContainerFactory();
//        }
//        catch (Exception ex)
//        {
//            ex.getCause();
//        }
//
//        try
//        {
//            TaskContainerFactory tf2 = new TaskContainerFactory();
//            tf2.createContainer(Strategy.FIFO);
//        }
//        catch (Exception ex)
//        {
//            System.out.println(ex.getMessage());
//        }

//




        int[] numere1 = {312,312,6214,43543,7234,2132,4,1,313,21};
        int[] numere2 = {231,-3,1000,2};
        int[] numere3 = {5,3,-121,43543,97234,27132,4521};



        Task task1 = new MessageTask(1,"daa","mesaj1");
        Task task2 = new SortingTask(2,"weq",numere1);
        Task task3 = new MessageTask(1,"daa","mesaj2");
        Task task4 = new SortingTask(2,"weq",numere2);
        Task task5 = new MessageTask(1,"daa","mesaj3");
        Task task6 = new SortingTask(2,"weq",numere3);



        System.out.println("=================== NORMAL  ====================");

        TaskArray tasks = new TaskArray();
        StrategyTaskRunner tr1 = new StrategyTaskRunner(Strategy.valueOf(args[0]));

        tr1.addTask(task1);
        tr1.addTask(task2);
        tr1.addTask(task3);
        tr1.addTask(task4);
        tr1.addTask(task5);
        tr1.addTask(task6);



        tr1.executeAll();


        System.out.println("=================== NORMAL + PRINTER  ====================");

        TaskRunner tr2 = new PrinterTaskRunner(tr1);

        tr2.addTask(task1);
        tr2.addTask(task2);
        tr2.addTask(task3);
        tr2.addTask(task4);
        tr2.addTask(task5);
        tr2.addTask(task6);

        tr2.executeAll();

        System.out.println("=================== NORMAL + DELAY  ====================");

        TaskRunner tr3 = new DelayTaskRunner(tr1);

        tr3.addTask(task1);
        tr3.addTask(task2);
        tr3.addTask(task3);
        tr3.addTask(task4);
        tr3.addTask(task5);
        tr3.addTask(task6);

        tr3.executeAll();

        System.out.println("=================== NORMAL + PRINTER + DELAY  ====================");

        TaskRunner tr4 = new PrinterTaskRunner(tr3);

        tr4.addTask(task1);
        tr4.addTask(task2);
        tr4.addTask(task3);
        tr4.addTask(task4);
        tr4.addTask(task5);
        tr4.addTask(task6);

        tr4.executeAll();


    }


}
