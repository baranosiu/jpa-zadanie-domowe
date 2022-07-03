package local.pbaranowski.jpa;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public class Tests {

    public static void main(String[] args) {
        final RepositoriesFactory FACTORY = RepositoriesFactory.create();

        ContactDetails contactDetails1 = new ContactDetails("login1", "phone1", "mail1");
        ContactDetails contactDetails2 = new ContactDetails("login2", "phone2", "mail2");
        ContactDetails contactDetails3 = new ContactDetails("login3", "phone3", "mail3");

        EntityRepository<Buyer> buyerRepository = FACTORY.getRepository(Buyer.class);
        UUID buyer1 = buyerRepository.save(new Buyer(contactDetails1));
        UUID buyer2 = buyerRepository.save(new Buyer(contactDetails2));
        UUID buyer3 = buyerRepository.save(new Buyer(contactDetails3));
        printList(buyerRepository.find());

        buyerRepository.delete(buyer2);
        printList(buyerRepository.find());

        InvoiceRepository invoiceRepository = FACTORY.InvoiceRepository(); // Rozszerzona o dodatkowe metody

        UUID invoice1 = invoiceRepository.save(new Invoice(InvoiceStatus.CREATED));
        UUID invoice2 = invoiceRepository.save(new Invoice(InvoiceStatus.SENT));
        UUID invoice3 = invoiceRepository.save(new Invoice(InvoiceStatus.PAYED));
        printList(invoiceRepository.find());
        invoiceRepository.delete(invoice1);
        invoiceRepository.changeStatus(invoice2, InvoiceStatus.PAYED);
        printList(invoiceRepository.find());

        EntityRepository<Seller> sellerRepository = FACTORY.getRepository(Seller.class);
        contactDetails1.setMail("nowyEmail");
        UUID seller1 = sellerRepository.save(new Seller(contactDetails1));
        UUID seller2 = sellerRepository.save(new Seller(contactDetails2));
        UUID seller3 = sellerRepository.save(new Seller(contactDetails3));
        printList(sellerRepository.find());

        printList(sellerRepository.find(seller2));

        printList(buyerRepository.find());

        EntityRepository<Buyer> buyerRepository2 = FACTORY.getRepository(Buyer.class);
        printList(buyerRepository2.find());
        buyerRepository.update(buyer1);

        printList(buyerRepository.find());
        printList(buyerRepository2.find());

        FACTORY.close();
    }

    private static void printList(List list) {
        System.out.println("------------------------------------------");
        list.forEach(System.out::println);
    }
}
