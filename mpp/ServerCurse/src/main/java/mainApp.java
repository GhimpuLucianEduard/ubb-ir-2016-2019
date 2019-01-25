import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainApp {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("ServerCurse.xml");
        ServicePersonal  mainService = context.getBean(ServicePersonal.class);
        try {
            mainService.loginHandler();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
