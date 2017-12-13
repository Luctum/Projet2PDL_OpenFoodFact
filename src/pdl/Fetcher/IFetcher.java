package pdl.Fetcher;

import pdl.Model.Config;
import pdl.Model.Product;

import java.util.List;

public interface IFetcher {
    public List<String> getProducts() throws NoSuchMethodException;
    public void prettify();
    public List<Product> getListProduct();
}
