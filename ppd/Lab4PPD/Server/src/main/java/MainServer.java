import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class MainServer {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext(
                String.valueOf(MainServer.class.getResource("spring.xml")));
    }
}
