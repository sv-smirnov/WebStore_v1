package ru.geekbrains.Lesson4.Repositiry;

import org.springframework.stereotype.Repository;
import ru.geekbrains.Lesson4.Entities.Product;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepository extends Perository<Product> {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> getProducts() {
        return entityManager
                .createQuery("Select a from Product a", Product.class)
                .getResultList();
    }

    public Product getProductById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void addProduct(Product product) {
        entityManager.merge(product);
    }

    @Transactional
    public void deleteProduct(Product product) {
        entityManager.remove(product);
    }
}

