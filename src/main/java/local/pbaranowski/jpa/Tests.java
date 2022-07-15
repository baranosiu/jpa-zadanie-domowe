package local.pbaranowski.jpa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Tests {

    public static void main(String[] args) {
        final RepositoriesFactory FACTORY = RepositoriesFactory.create();

        EntityRepository<Product> productEntityRepository = FACTORY.getRepository(Product.class);
        EntityRepository<InvoiceItem> invoiceItemEntityRepository = FACTORY.getRepository(InvoiceItem.class);
        Product product = new Product("product1", BigDecimal.TEN);
        Product product1 = new Product("product2", BigDecimal.ONE);
        productEntityRepository.save(product1);
        productEntityRepository.save(product);
        InvoiceItem item1 = new InvoiceItem(product, 10);
        InvoiceItem item2 = new InvoiceItem(product1, 12);
        invoiceItemEntityRepository.save(item1);
        invoiceItemEntityRepository.save(item2);
        Invoice invoice = new Invoice(InvoiceStatus.CREATED);
        invoice.add(item1);
        invoice.add(item2);
        InvoiceRepository invoiceRepository = FACTORY.InvoiceRepository(); // Rozszerzona o dodatkowe metody
        invoiceRepository.save(invoice);
        EntityRepository<Buyer> buyerEntityRepository = FACTORY.getRepository(Buyer.class);
        for (int i = 0; i < 10; i++) {
            Buyer buyer = new Buyer(new ContactDetails("login1", "phone1", "email1"));
            buyerEntityRepository.save(buyer);
        }
        printList(invoiceRepository.find());

        FACTORY.close();
    }

    private static void printList(List list) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        list.forEach(item -> System.out.println("------ " + item.getClass().getSimpleName() + System.lineSeparator() + gson.toJson(item).toString() + System.lineSeparator()));
    }
}
