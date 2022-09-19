import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ProductManagerTest {
    ProductRepository repository = Mockito.mock(ProductRepository.class);
    ProductManager manager = new ProductManager(repository);
    Product item1 = new Book(1, "The Witcher", 30);
    Product item2 = new Book(2, "Metro 2033", 20);
    Product item3 = new Smartphone(3, "iPhone", 1000);

    @Test
    public void foundOneProduct() {
        Product[] items = {item3, item1, item2, item3};
        doReturn(items).when(repository).findAll();

        Product[] expected = {item2};
        Product[] actual = manager.searchBy("Metro 2033");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void foundTwoProducts() {
        Product[] items = {item3, item1, item2, item3};
        doReturn(items).when(repository).findAll();

        Product[] expected = {item3, item3};
        Product[] actual = manager.searchBy("iPhone");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void notFound() {
        Product[] items = {item3, item1, item2, item3};
        doReturn(items).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("Coca-Cola");

        Assertions.assertArrayEquals(expected, actual);
    }
}
