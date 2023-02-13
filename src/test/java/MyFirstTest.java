
import com.tms.service.ActorCrudService;
import org.junit.jupiter.api.*;

@DisplayName("Моё название какое хочу в классе")
public class MyFirstTest {

    static ActorCrudService actorCrudService;

    @BeforeAll
    static void beforeAllTest() {
        System.out.println("beforeAllTest");
        actorCrudService = new ActorCrudService();
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println("beforeEachTest");
    }

    @AfterAll
    static void afterAllTest() {
        System.out.println("afterAllTest");
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("afterEachTest");
    }

    @Test
    void getSummaTest() {
        // сравнить работу метода, и эталон который вы создаёте заранее.
        int firstNumber = 5;
        int secondNumber = 6;
        int expected = 11;
        Assertions.assertEquals(expected,actorCrudService.summa(firstNumber, secondNumber));
        Assertions.assertNotNull(actorCrudService.summa(firstNumber, secondNumber));
        Assertions.assertDoesNotThrow(() -> actorCrudService.summa(firstNumber, secondNumber));
    }

    @Test
    @Tag("example")
    void getSummaSecondTest() {
        System.out.println("Second test");
    }
}
