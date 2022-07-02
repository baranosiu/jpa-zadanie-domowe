package local.pbaranowski.jpa;

import java.util.List;
import java.util.UUID;

public class Tests {
    public static final RepositoriesFactory FACTORY = RepositoriesFactory.create();

    public static void main(String[] args) {
        BuyerRepository buyerRepository = FACTORY.BuyersRepository();
        UUID buyer1 = buyerRepository.save(new Buyer(new ContactDetails("login1", "phone1", "mail1")));
        UUID buyer2 = buyerRepository.save(new Buyer(new ContactDetails("login2", "phone2", "mail2")));
        UUID buyer3 = buyerRepository.save(new Buyer(new ContactDetails("login3", "phone3", "mail3")));
        printList(buyerRepository.find(null));
        System.out.println(buyer2.toString());
        buyerRepository.delete(buyer2);
        printList(buyerRepository.find(null));
        InvoiceRepository invoiceRepository = FACTORY.InvoiceRepository();
        UUID invoice1 = invoiceRepository.save(new Invoice(InvoiceStatus.CREATED));
        UUID invoice2 = invoiceRepository.save(new Invoice(InvoiceStatus.SENT));
        UUID invoice3 = invoiceRepository.save(new Invoice(InvoiceStatus.PAYED));
        printList(invoiceRepository.find(null));
        invoiceRepository.delete(invoice1);
        invoiceRepository.changeStatus(invoice2, InvoiceStatus.PAYED);
        printList(invoiceRepository.find(null));
    }

    private static void printList(List list) {
        list.forEach(System.out::println);
    }
}
