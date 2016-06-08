import java.util.ArrayList;
import java.util.List;


public class Application {
    UserInputScanner scanner = new UserInputScanner();
    List<Payment> payments = new ArrayList<Payment>();
    Calculator calculator;
    char currencySymbol = '$';
    boolean quitApplication = true;

    public void run() {
        while (quitApplication) {
            printOptions();
            int option = userInputNumber();
            mainMenuOptionsSwitcher(option);
        }
    }

    public int userInputNumber() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("You typed wrong symbol, read options and try again");
                printOptions();
            }
        }
    }

    public void createPayment() {
        System.out.println("Type name of friend, service he paid and price of service");

        String friendName = scanner.next();
        String serviceName = scanner.next();
        int servicePrice = scanner.nextInt();

        Payment payment = new Payment(friendName, serviceName, servicePrice);
        payments.add(payment);
    }

    public void deleteLastPayment() {
        if (payments.size() == 0) {
            System.out.println("You cant delete payment," +
                    " because there are no payments left ");
            return;
        }
        payments.remove(payments.size()-1);
    }

    public void calculate() {
        calculator = new Calculator(payments);
        System.out.println("Examples");
        printPayments();
        System.out.println("Total: " + calculator.calculateTotal());
        System.out.println("Average: " +
                calculator.calculateAverage(calculator.calculateExpenses()) +
                "to be paid by mate");
        System.out.println("Expenses:");
        printExpenses(calculator.calculateExpenses());
        System.out.println("Transactions to be made:");
        printTransactions(calculator.calculateTransactions());
        System.out.println("Result:");
        printResults(calculator.calculateExpenses());

    }

    public void mainMenuOptionsSwitcher(int option) {
        switch (option) {
            case 1:
                createPayment();
                break;
            case 2:
                deleteLastPayment();
                break;
            case 3:
                calculate();
                break;
            case 4:
                printOptions();
                break;
            case 5:
                quitApplication = false;
                break;
            default:
                System.out.println("You typed wrong number try again");
                break;
        }

    }

    public void printOptions() {
        System.out.println("List of options");
        System.out.println("Type 1 to create payment");
        System.out.println("Type 2 to remove previous payment");
        System.out.println("Type 3 to view payment calculations");
        System.out.println("Type 4 to view options");
        System.out.println("Type 5 to quit application");
    }

    public void printPayments() {
        for (Payment payment : payments) {
            System.out.println(payment.getFriendName() + " " +
                    payment.getServiceName() + " " +
                    payment.getServiceCost() +
                    currencySymbol);
        }
    }

    public void printExpenses(List<Expenses> expenses) {
        for (Expenses expense : expenses) {
            System.out.println(expense.getName() + ": " +
                    expense.getSumPaid() +
                    currencySymbol);
        }
    }

    public void printTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getTransactionMadeByName() + " -> " +
                    transaction.getTransactionReceivedByName() + ": " +
                    transaction.getTransactionSum() +
                    currencySymbol);
        }
    }

    public void printResults(List<Expenses> expenses) {
        for (Expenses expense : expenses) {
            System.out.println(expense.getName() + ": " +
                    calculator.calculateAverage(expenses) + " " +
                    currencySymbol);
        }

    }


}

