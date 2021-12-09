package packages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PackageBuilder {

    private static final byte[] CONTROL_TYPE = { 1 };
    private static final byte[] READ_TYPE = { 2 };
    private static final byte[] WRITE_TYPE = { 3 };
    private static final byte[] DATA_TYPE = { 4 };
    private static final byte[] ACK_TYPE = { 5 };
    private static final byte[] ERROR_TYPE = { 6 };

    public final static int MAX_PACKAGE_SIZE = 1400;

    public byte[] buildControlPackage(ArrayList<String> filenames) throws IOException {
        byte[] type = CONTROL_TYPE;

        String filenames_str = "";

        for (String filename : filenames) {
            filenames_str = filenames_str + filename.getBytes() + "\n";
        }

        int size = filenames_str.length();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size);
        baos.write(filenames_str.getBytes());

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public byte[] buildReadPacakge(String filename) throws IOException {

        byte[] type = READ_TYPE;

        byte[] filename_bytes = filename.getBytes();

        int size = filename_bytes.length;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size);
        baos.write(filename_bytes);

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        
        return pack;

    }

    public byte[] buildWritePackage(String filename) throws IOException {
        byte[] type = WRITE_TYPE;

        byte[] filename_bytes = filename.getBytes();

        int size = filename_bytes.length;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size);
        baos.write(filename_bytes);

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public byte[] buildDataPacakge(byte[] data, int segmentation) throws IOException {
        byte[] type = DATA_TYPE;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(data.length);
        baos.write(segmentation);
        baos.write(data);

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public byte[] buildAcknowledgementPackage(int segmentation, String filename) throws IOException {

        byte[] type = ACK_TYPE;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(segmentation);
        baos.write(filename.length());
        baos.write(filename.getBytes());

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public byte[] buildErrorPackage(int type_error, String error_msg) throws IOException {
        byte[] type = ERROR_TYPE;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(type_error);
        baos.write(error_msg.length());
        baos.write(error_msg.getBytes());

        byte[] pack = buildPackage(baos.toByteArray());

        checkPackageSize(pack);
        return pack;

    }

    private void checkPackageSize(byte[] bytes) {
        if (bytes.length > 1400) {
            throw new ExceptionInInitializerError("Data Too Big");
        }
    }

    private byte[] buildPackage(byte[] bytes) {
        byte[] pack = new byte[MAX_PACKAGE_SIZE];

        for (int i = 0; i < bytes.length; i++) {
            pack[i] = bytes[i];
        }
        return pack;
    }

}
