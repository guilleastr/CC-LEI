package datagram_socket.packages;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PackageParser {

    public byte[] type;
    public byte[] size;
    public byte[] bytes;


    public void parsePackage(byte[] bytes){
        this.type=Arrays.copyOfRange(bytes, 0, 1);
        
        switch (type[0]){
            case 1:
                parseControlPackage(bytes);
            case 2:
                parseReadPackage(bytes);
            case 3:
                parseWritePackage(bytes);
            case 4: 
                parseDataPackage(bytes);
            case 5:
                parseAckPackage(bytes);
            case 6:
                parseErrorPackage(bytes);
        }



    }


    private void parseReadPackage(byte[] bytes2) {
        this.size=Arrays.copyOfRange(bytes2, 1, 3);
        this.bytes=Arrays.copyOfRange(bytes2, 3, 1400);

    }


    private void parseErrorPackage(byte[] bytes2) {
    }


    private void parseAckPackage(byte[] bytes2) {
    }


    private void parseWritePackage(byte[] bytes2) {
        this.size=Arrays.copyOfRange(bytes2, 1, 3);
        this.bytes=Arrays.copyOfRange(bytes2, 3, 1400);
    }


    private void parseDataPackage(byte[] bytes2) {
    }


    private void parseControlPackage(byte[] bytes2) {
        this.size=Arrays.copyOfRange(bytes2, 1, 3);
        this.bytes=Arrays.copyOfRange(bytes2, 3, 1400);
    }
    
}
