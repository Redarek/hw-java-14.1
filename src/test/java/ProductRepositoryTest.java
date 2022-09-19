import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {

    Product item1 = new Book(1, "The Witcher", 30);
    Product item2 = new Book(2, "Metro 2033", 20);
    Product item3 = new Smartphone(3, "iPhone", 1000);

    @Test
    public void removeByIdTest() {
        ProductRepository repository = new ProductRepository();
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.removeById(item2.getId());

        Product[] expected = {item1, item3};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void throwNotFoundExceptionTest() {
        ProductRepository repository = new ProductRepository();
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeById(4);
        });
    }
}
