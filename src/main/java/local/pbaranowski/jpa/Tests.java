package local.pbaranowski.jpa;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

public class Tests {
    public static final RepositoriesFactory FACTORY = RepositoriesFactory.create();

    public static void main(String[] args) {
        ContactDetails contactDetails1 = new ContactDetails("login1", "phone1", "mail1");
        ContactDetails contactDetails2 = new ContactDetails("login2", "phone2", "mail2");
        ContactDetails contactDetails3 = new ContactDetails("login3", "phone3", "mail3");

        BuyerRepository buyerRepository = FACTORY.BuyersRepository();
        UUID buyer1 = buyerRepository.save(new Buyer(contactDetails1));
        UUID buyer2 = buyerRepository.save(new Buyer(contactDetails2));
        UUID buyer3 = buyerRepository.save(new Buyer(contactDetails3));
        printList(buyerRepository.find());

        buyerRepository.delete(buyer2);
        printList(buyerRepository.find());

        InvoiceRepository invoiceRepository = FACTORY.InvoiceRepository();
        UUID invoice1 = invoiceRepository.save(new Invoice(InvoiceStatus.CREATED));
        UUID invoice2 = invoiceRepository.save(new Invoice(InvoiceStatus.SENT));
        UUID invoice3 = invoiceRepository.save(new Invoice(InvoiceStatus.PAYED));
        printList(invoiceRepository.find());
        invoiceRepository.delete(invoice1);
        invoiceRepository.changeStatus(invoice2, InvoiceStatus.PAYED);
        printList(invoiceRepository.find());

        SellerRepository sellerRepository = FACTORY.SellerRepository();
        contactDetails1.setMail("nowyEmail");
        UUID seller1 = sellerRepository.save(new Seller(contactDetails1));
        UUID seller2 = sellerRepository.save(new Seller(contactDetails2));
        UUID seller3 = sellerRepository.save(new Seller(contactDetails3));
        printList(sellerRepository.find());

        printList(buyerRepository.find());

        BuyerRepository buyerRepository2 = FACTORY.BuyersRepository();
        printList(buyerRepository2.find());
        buyerRepository.update(buyer1);

        printList(buyerRepository.find());
        printList(buyerRepository2.find());
    }

    private static void printList(List list) {
        list.forEach(System.out::println);
    }
}
