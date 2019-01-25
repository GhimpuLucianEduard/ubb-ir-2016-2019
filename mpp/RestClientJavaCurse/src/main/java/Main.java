import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.util.Date;
import java.util.Locale;


public class Main {

    public static void main(String[] args) throws ParseException {
        String baseUrl = "http://localhost:52008/api/Curse/";
        RestTemplate restTemplate = new RestTemplate();
        Arrays.stream(restTemplate.getForObject(baseUrl, Cursa[].class)).forEach( x->{
            System.out.println(x.toString());
        });

        Cursa c = new Cursa();
        c.setIdDestinatie(1);
        c.setLocPlecare("khrfsdjklfhlsskhgljshfdl");
        c.setNrLocuriDisponibile(12);


        Date d = new Date();
        c.setData(new Date());
        restTemplate.postForObject(baseUrl,  c, Cursa.class);

        Arrays.stream(restTemplate.getForObject(baseUrl, Cursa[].class)).forEach( x->{
            System.out.println(x.toString());
        });
    }
}
