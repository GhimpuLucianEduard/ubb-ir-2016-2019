import Repository.RepoDbClienti;
import Repository.RepoDbDestinatie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class ConfigurationClass {
    @Bean
    @Primary
    public Properties jdbsProps() {
        Properties serverProps=new Properties();
        try {
            serverProps.load(getClass().getResourceAsStream("/db.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);

        }

        return serverProps;
    }

    @Bean(name="RepoDbDestinatie")
    public RepoDbDestinatie createRepository(Properties jdbcProps){
        return new RepoDbDestinatie(jdbcProps);
    }

    @Bean(name="RepoDbClienti")
    public RepoDbClienti createRepository2(Properties jdbcProps){
        return new RepoDbClienti(jdbcProps);
    }
}
