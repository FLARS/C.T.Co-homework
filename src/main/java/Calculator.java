import java.util.*;
import java.util.stream.Collectors;

public class Calculator {
    List<Payment> payments;

    public Calculator(List<Payment> payments) {
        this.payments = payments;
    }


    public int calculateTotal() {
        int sum = 0;
        for (int i = 0; i < payments.size(); i++) {
            sum += payments.get(i).getServiceCost();
        }
        return sum;
    }


    public int calculateAverage(List<Expenses> expenses) {
        return calculateTotal() / expenses.size();
    }

    public List<Expenses> calculateExpenses() {
        Map<String, Integer> expences = new HashMap<String, Integer>();
        for (Payment payment : payments) {
            Integer sum = expences.get(payment.getFriendName());

            if (sum == null) {
                sum = payment.serviceCost;
            } else {
                sum += payment.serviceCost;
            }

            expences.put(payment.getFriendName(), sum);
        }

        List<Expenses> listOfExpences = new ArrayList<Expenses>();

        for (Map.Entry<String, Integer> stringIntegerEntry : expences.entrySet()) {
            Expenses expenses = new Expenses(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
            listOfExpences.add(expenses);
        }

        return listOfExpences;
    }

    public List<Transaction> calculateTransactions() {
        List<Expenses> expenses = calculateExpenses();
        List<Transaction> transactions = new ArrayList<>();
        List<Expenses> negativeExpenses = new ArrayList<>();
        List<Expenses> positiveExpenses = new ArrayList<>();


        for (Expenses expense : expenses) {
            int sum = expense.getSumPaid() - calculateAverage(expenses);
            expense.setSumPaid(Math.abs(sum));
            if (sum > 0) {
                positiveExpenses.add(expense);
            } else if(sum < 0) {
                negativeExpenses.add(expense);
            }

        }


        Collections.sort(negativeExpenses, new Comparator<Expenses>() {
            @Override
            public int compare(Expenses o1, Expenses o2) {
                Integer sum1 = Math.abs(o1.getSumPaid());
                Integer sum2 = Math.abs(o2.getSumPaid());
                return sum2.compareTo(sum1);
            }
        });
        Collections.sort(positiveExpenses, new Comparator<Expenses>() {
            @Override
            public int compare(Expenses o1, Expenses o2) {
                return o2.getSumPaid().compareTo(o1.getSumPaid());
            }
        });


        for (Expenses negative : negativeExpenses) {
            List<Expenses> transactionsToBeMade = findTransactionsByCombining(negative.getSumPaid(), new ArrayList<>(), positiveExpenses);


            if (!transactionsToBeMade.isEmpty()) {
                for (Expenses aTransactionsToBeMade : transactionsToBeMade) {
                    transactions.add(new Transaction(negative.getName(),
                            aTransactionsToBeMade.getSumPaid(),
                            aTransactionsToBeMade.getName()));
                    positiveExpenses = positiveExpenses
                            .stream()
                            .filter(item -> !item.getName().equals(aTransactionsToBeMade.getName()))
                            .collect(Collectors.toList());
                }


            } else {
                transactionsToBeMade = findTransactionsByTransferingFromLargestToLargest(negative, positiveExpenses);
                for (Expenses aTransactionsToBeMade : transactionsToBeMade) {
                    transactions.add(new Transaction(negative.getName(),
                            aTransactionsToBeMade.getSumPaid(),
                            aTransactionsToBeMade.getName()));
                }

            }
        }

        transactions.sort(
                Comparator.comparing(Transaction::getTransactionMadeByName)
                        .thenComparing(Transaction::getTransactionReceivedByName));

        return transactions;
    }

    public List<Expenses> findTransactionsByCombining(int negative,
                                                      List<Expenses> candidatesForTransactions,
                                                      List<Expenses> positiveList) {
        int sumCandidates = 0;
        for (Expenses candidatesForTransaction : candidatesForTransactions) {
            sumCandidates += candidatesForTransaction.getSumPaid();
        }

        int sum = sumCandidates;

        if (sum == negative) {
            return candidatesForTransactions;
        } else if (sum > negative) {
            return Collections.emptyList();
        }

        for (int index = 0; index < positiveList.size(); index++) {

            Expenses positive = positiveList.get(index);
            List<Expenses> nextCandidatesForTransactions = new ArrayList<>(candidatesForTransactions);
            nextCandidatesForTransactions.add(positive);

            List<Expenses> transactions = findTransactionsByCombining(negative, nextCandidatesForTransactions, subList(index, positiveList));
            if (!transactions.isEmpty()) {
                return transactions;
            }
        }
        return Collections.emptyList();
    }

    public List<Expenses> subList(int currentIndex, List<Expenses> list) {
        List<Expenses> temp = new ArrayList<>();

        if (list.size() > currentIndex + 1) {
            temp.addAll(list.subList(currentIndex + 1, list.size()));
        }

        return temp;
    }


    public List<Expenses> findTransactionsByTransferingFromLargestToLargest(Expenses underPaid, List<Expenses> overPaid) {
        List<Expenses> transactionsByLargest = new ArrayList<>();
        do {
            if (underPaid.getSumPaid() >= overPaid.get(0).getSumPaid()) {
                transactionsByLargest.add(overPaid.get(0));
                int sum = underPaid.getSumPaid() - overPaid.get(0).getSumPaid();
                underPaid.setSumPaid(sum);
                overPaid.remove(0);
            } else if (underPaid.getSumPaid() < overPaid.get(0).getSumPaid() && underPaid.getSumPaid() > 0) {
                int sum = overPaid.get(0).getSumPaid() - underPaid.getSumPaid();
                transactionsByLargest.add(new Expenses(overPaid.get(0).getName(), underPaid.getSumPaid()));
                underPaid.setSumPaid(0);
                overPaid.get(0).setSumPaid(sum);
            }
        } while (underPaid.getSumPaid() != 0);


        return transactionsByLargest;
    }
}
