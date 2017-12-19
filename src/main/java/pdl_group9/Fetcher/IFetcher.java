package pdl_group9.Fetcher;

import pdl_group9.Model.Product;

import java.util.List;

public interface IFetcher {
    public List<String> getProducts() throws NoSuchMethodException;
    public void prettify();
    public List<Product> getListProduct();
}
