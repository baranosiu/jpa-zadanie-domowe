package local.pbaranowski.jpa;

import java.util.List;
import java.util.UUID;

public class Tests {
    public static final RepositoriesFactory FACTORY = RepositoriesFactory.create();

    public static void main(String[] args) {
        BuyerRepository buyerRepository = FACTORY.BuyersRepository();
        ContactDetails contactDetails1 = new ContactDetails("login1", "phone1", "mail1");
        ContactDetails contactDetails2 = new ContactDetails("login2", "phone2", "mail2");
        ContactDetails contactDetails3 = new ContactDetails("login3", "phone3", "mail3");
        UUID buyer1 = buyerRepository.save(new Buyer(contactDetails1));
        UUID buyer2 = buyerRepository.save(new Buyer(contactDetails2));
        UUID buyer3 = buyerRepository.save(new Buyer(contactDetails3));
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
        SellerRepository sellerRepository = FACTORY.SellerRepository();
        contactDetails1.setMail("nowyEmail");
        UUID seller1 = sellerRepository.save(new Seller(contactDetails1));
        UUID seller2 = sellerRepository.save(new Seller(contactDetails2));
        UUID seller3 = sellerRepository.save(new Seller(contactDetails3));
        printList(sellerRepository.find(null));
        printList(buyerRepository.find(null));
        BuyerRepository buyerRepository2 = FACTORY.BuyersRepository();
        printList(buyerRepository2.find(null));
        buyerRepository.update(buyer1);
        printList(buyerRepository.find(null));
    }

    private static void printList(List list) {
        list.forEach(System.out::println);
    }
}
