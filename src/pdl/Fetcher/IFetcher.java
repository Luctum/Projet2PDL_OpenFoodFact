package pdl.Fetcher;

import pdl.Model.Config;

import java.util.List;

public interface IFetcher {
    public List<String> getProducts();
    public void prettify();
}
