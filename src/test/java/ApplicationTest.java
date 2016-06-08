import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Alex on 08/06/2016.
 */
public class ApplicationTest {
    List<Payment> testPayments;
    Calculator calculator;

    @Mock
    UserInputScanner scanner;

    @InjectMocks
    Application application = new Application();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testPayments = new ArrayList<Payment>();
    }


    @Test
    public void testCreatePayment() throws Exception {
        Payment testPayment = new Payment("Boris","milk",30);
        testPayments.add(testPayment);

        List<Payment> resultPayments = createStandratPayment();

        compareTwoArrays(resultPayments);
    }


    @Test
    public void testDeleteLastPayment() throws Exception {
        Payment testPayment = new Payment("Boris","milk",30);
        testPayments.add(testPayment);

        List<Payment> resultPayments = createStandratPayment();

        Assert.assertEquals(testPayments.size(),resultPayments.size());

    }

    @Test
    public void testMainMenuOptionsSwitcher() throws Exception {

    }

    public void compareTwoArrays(List<Payment> result) {
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(testPayments.get(0).getFriendName(),result.get(0).getFriendName());
        Assert.assertEquals(testPayments.get(0).getServiceName(),result.get(0).getServiceName());
        Assert.assertEquals(testPayments.get(0).getServiceCost(), result.get(0).getServiceCost());

    }

    public List<Payment> createStandratPayment() {
        when(scanner.next()).thenReturn("Boris","milk");
        when(scanner.nextInt()).thenReturn(30);
        application.createPayment();

        return application.payments;
    }


}