package pdl.Fetcher;

import pdl.Model.Config;

public interface IFetcher {

    public String getProducts(Config config);
    public void prettify();
}
