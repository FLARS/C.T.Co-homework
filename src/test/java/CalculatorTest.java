import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 08/06/2016.
 */
public class CalculatorTest {
    List<Payment> payments;
    List<Transaction> testTransactions;
    Calculator calculator;

    @Before
    public void setup() {
        payments = new ArrayList<>();
        testTransactions = new ArrayList<>();
        calculator = new Calculator(payments);
    }


    @Test
    public void testCalculateTransactions_Case1() throws Exception {

        testTransactions.add(new Transaction("Rob",15,"Bob"));

        payments.add(new Payment("Bob", "milk",50));
        payments.add(new Payment("Rob", "milk",20));
        List<Transaction> result =  calculator.calculateTransactions();

        compareTwoArrays(result);

    }

    @Test
    public void testCalculateTransactions_Case2() throws Exception {
        testTransactions.add(new Transaction("Aike",10,"Rob"));
        testTransactions.add(new Transaction("Mike",20,"Bob"));

        payments.add(new Payment("Bob", "milk",110));
        payments.add(new Payment("Rob", "milk",100));
        payments.add(new Payment("Mike", "milk",70));
        payments.add(new Payment("Aike", "milk",80));

        List<Transaction> result =  calculator.calculateTransactions();

        compareTwoArrays(result);

    }

    @Test
    public void testCalculateTransactions_Case3() throws Exception {
        payments.add(new Payment("Bob", "milk",37));
        payments.add(new Payment("Rob", "milk",23));
        payments.add(new Payment("Mike", "milk",22));
        payments.add(new Payment("Aike", "milk",22));
        payments.add(new Payment("John", "milk",10));
        payments.add(new Payment("Jax", "milk",11));
        payments.add(new Payment("Melisa", "milk",16));
        payments.add(new Payment("Alisa", "milk",19));


        List<Transaction> result =  calculator.calculateTransactions();

        Assert.assertEquals(6, result.size());

        compareTransactions(new Transaction("Alisa",1,"Aike"),result.get(0));
        compareTransactions(new Transaction("Jax",7,"Bob"),result.get(1));
        compareTransactions(new Transaction("Jax",2,"Mike"),result.get(2));
        compareTransactions(new Transaction("John",10,"Bob"),result.get(3));
        compareTransactions(new Transaction("Melisa",1,"Aike"),result.get(4));
        compareTransactions(new Transaction("Melisa",3,"Rob"),result.get(5));
    }

    @Test
    public void testCalculateTransactions_Case4() throws Exception {
        payments.add(new Payment("Bob", "milk",50));
        payments.add(new Payment("Rob", "milk",47));
        payments.add(new Payment("Mike", "milk",20));
        payments.add(new Payment("Aike", "milk",20));
        payments.add(new Payment("John", "milk",13));



        List<Transaction> result =  calculator.calculateTransactions();

        Assert.assertEquals(3, result.size());

        compareTransactions(new Transaction("Aike",10,"Bob"),result.get(0));
        compareTransactions(new Transaction("John",17,"Rob"),result.get(1));
        compareTransactions(new Transaction("Mike",10,"Bob"),result.get(2));

    }



//    @Test
//    public void testFindTransactionsByTransferingFromLargestToLargest_Case1() throws Exception {
//        Expenses underPaid = new Expenses("John",10);
//        List<Expenses> overPaid = new ArrayList<>();
//        overPaid.add(new Expenses("Bob",17));
//        overPaid.add(new Expenses("Rob",3));
//        overPaid.add(new Expenses("Mike",2));
//        overPaid.add(new Expenses("Aike",2));
//
//        List<Expenses> result = calculator.findTransactionsByTransferingFromLargestToLargest(underPaid, overPaid);
//        System.out.println();
//
//
//    }

    public void compareTransactions(Transaction expected, Transaction actual) {
        Assert.assertEquals(expected.getTransactionMadeByName(),actual.getTransactionMadeByName());
        Assert.assertEquals(expected.getTransactionReceivedByName(),actual.getTransactionReceivedByName());
        Assert.assertEquals(expected.getTransactionSum(), actual.getTransactionSum());
    }




    public void compareTwoArrays(List<Transaction> result) {
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(testTransactions.size(), result.size());
            Assert.assertEquals(testTransactions.get(i).getTransactionMadeByName(),result.get(i).getTransactionMadeByName());
            Assert.assertEquals(testTransactions.get(i).getTransactionReceivedByName(),result.get(i).getTransactionReceivedByName());
            Assert.assertEquals(testTransactions.get(i).getTransactionSum(), result.get(i).getTransactionSum());
        }


    }
}