package packages;

import java.util.Arrays;

import packages.types.Package_Executor;
import packages.types.ReadPackage;

public class PackageParser {

    public int type;
    public int size;
    public byte[] bytes;

    public Package_Executor parsePackage(byte[] bytes) {
        this.type = Arrays.copyOfRange(bytes, 0, 1)[0];

        switch (type) {
            case 1:
                return parseControlPackage(bytes);
            case 2:
                return parseReadPackage(bytes);
            case 3:
                return parseWritePackage(bytes);
            case 4:
                return parseDataPackage(bytes);
            case 5:
                return parseAckPackage(bytes);
            case 6:
                return parseErrorPackage(bytes);
        }
        return null;

    }

    private Package_Executor parseReadPackage(byte[] bytes2) {
        this.size = Arrays.copyOfRange(bytes2, 1, 2)[0];
        this.bytes = Arrays.copyOfRange(bytes2, 2, 1400);
        String file_name = new String(this.bytes).toString();

        ReadPackage rp = new ReadPackage(type, size, file_name);
        return rp;

    }

    private Package_Executor parseErrorPackage(byte[] bytes2) {
        return null;
    }

    private Package_Executor parseAckPackage(byte[] bytes2) {
        return null;
    }

    private Package_Executor parseWritePackage(byte[] bytes2) {
        this.size = Arrays.copyOfRange(bytes2, 1, 2)[0];
        this.bytes = Arrays.copyOfRange(bytes2, 2, 1400);
        return null;
    }

    private Package_Executor parseDataPackage(byte[] bytes2) {
        return null;
    }

    private Package_Executor parseControlPackage(byte[] bytes2) {
        this.size = Arrays.copyOfRange(bytes2, 1, 2)[0];
        this.bytes = Arrays.copyOfRange(bytes2, 2, 1400);
        return null;
    }

}
