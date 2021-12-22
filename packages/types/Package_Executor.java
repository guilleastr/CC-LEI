package packages.types;

import java.io.IOException;
import java.util.List;

public interface Package_Executor {


    /**
     * For each Package, each one will produce it's own pacakges to respond to a certain request
     * @return
     * @throws IOException
     */
    public  List<byte[]> execute() throws IOException;
    
}
