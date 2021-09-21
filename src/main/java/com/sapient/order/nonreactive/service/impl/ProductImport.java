package com.sapient.order.nonreactive.service.impl;

import com.sapient.order.model.Product;
import com.sapient.order.model.enums.Category;
import com.sapient.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnExpression("${product.import:false}")
@RequiredArgsConstructor
public class ProductImport {

    public static final String INVENTORY_CREATE = "inventory.create";
    private final ProductRepository productRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    int count = 500;

    @PostConstruct
    public void initProduct() {
        List<Product> products = new ArrayList<>();

        addAppliances(products);
        products = productRepository.saveAll(products);
        products.forEach(product -> kafkaTemplate.send(INVENTORY_CREATE, String.valueOf(product.getId())));
        products.clear();

        addBook(products);
        products = productRepository.saveAll(products);
        products.forEach(product -> kafkaTemplate.send(INVENTORY_CREATE, String.valueOf(product.getId())));
        products.clear();

        addCosmetics(products);
        products = productRepository.saveAll(products);
        products.forEach(product -> kafkaTemplate.send(INVENTORY_CREATE, String.valueOf(product.getId())));
        products.clear();

        addElectronics(products);
        products = productRepository.saveAll(products);
        products.forEach(product -> kafkaTemplate.send(INVENTORY_CREATE, String.valueOf(product.getId())));
        products.clear();

        addOutdoor(products);
        products = productRepository.saveAll(products);
        products.forEach(product -> kafkaTemplate.send(INVENTORY_CREATE, String.valueOf(product.getId())));
        products.clear();
    }

    private void addOutdoor(List<Product> products) {
        for (int i = 0; i < count; i++) {
            products.add(buildProduct("Plants " + i, 9.75, Category.OUTDOOR));
            products.add(buildProduct("Power tools " + i, 73.50, Category.OUTDOOR));
            products.add(buildProduct("pools " + i, 111.50, Category.OUTDOOR));
        }
    }

    private void addElectronics(List<Product> products) {
        for (int i = 0; i < count; i++) {
            products.add(buildProduct("Sony 4k tv " + i, 999.50, Category.ELECTRONICS));
            products.add(buildProduct("Headphone " + i, 133.20, Category.ELECTRONICS));
            products.add(buildProduct("Macbook " + i, 4000.50, Category.ELECTRONICS));
            products.add(buildProduct("Speaker " + i, 200.40, Category.ELECTRONICS));
            products.add(buildProduct("Mobile  " + i, 900.00, Category.ELECTRONICS));
        }
    }


    private void addCosmetics(List<Product> products) {
        for (int i = 0; i < count; i++) {
            products.add(buildProduct("Brush " + i, 9.50, Category.COSMETICS));
            products.add(buildProduct("Face wash " + i, 13.00, Category.COSMETICS));
            products.add(buildProduct("Makeup mirror " + i, 17.50, Category.COSMETICS));
            products.add(buildProduct("Make up Kit " + i, 345.00, Category.COSMETICS));
            products.add(buildProduct("Kids Cosmetics Make-Up " + i, 437.00, Category.COSMETICS));
        }
    }

    private void addBook(List<Product> products) {
        for (int i = 0; i < count; i++) {
            products.add(buildProduct("How to win friends and influence " + i, 13.00, Category.BOOKS));
            products.add(buildProduct("Ds and algorithms " + i, 70.00, Category.BOOKS));
            products.add(buildProduct("Effective java " + i, 40.00, Category.BOOKS));
            products.add(buildProduct("Clean architecture " + i, 32.00, Category.BOOKS));
            products.add(buildProduct("Microservices  " + i, 16.00, Category.BOOKS));
        }
    }

    private void addAppliances(List<Product> products) {
        for (int i = 0; i < count; i++) {
            products.add(buildProduct("Oven " + i, 500.00, Category.APPLIANCES));
            products.add(buildProduct("Dishwasher " + i, 125.00, Category.APPLIANCES));
            products.add(buildProduct("Heater " + i, 65.00, Category.APPLIANCES));
            products.add(buildProduct("Vacuum cleaner " + i, 48.00, Category.APPLIANCES));
            products.add(buildProduct("Refrigerator " + i, 1250.00, Category.APPLIANCES));
        }
    }

    private Product buildProduct(String name, double price, Category category) {
        Product product = Product.builder()
                .name(name).description(name).price(price).
                category(category).createdDate(LocalDateTime.now(ZoneOffset.UTC))
                .updatedDate(LocalDateTime.now(ZoneOffset.UTC)).build();
        return product;
    }
}
