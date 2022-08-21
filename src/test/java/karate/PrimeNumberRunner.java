package karate;

import com.assignment.nat.Application;
import static com.intuit.karate.junit5.Karate.run;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import static com.intuit.karate.junit5.Karate.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {Application.class})
public class PrimeNumberRunner {

    @Test
    Karate dummyTest() {
        return run("classpath:karate/primenumbers/primenumbers.feature");
    }
}
