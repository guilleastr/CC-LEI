
import java.io.IOException;

import packages.PackageBuilder;
import packages.PackageParser;
import packages.types.Package_Executor;

public class Probador {

    public static void main(String[] args) {
        PackageBuilder pb= new PackageBuilder();

        PackageParser pp= new PackageParser();
        try {
            byte[] pack= pb.buildReadPacakge("word.opd");
            
            Package_Executor pe=pp.parsePackage(pack);

            pe.execute();
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    
}