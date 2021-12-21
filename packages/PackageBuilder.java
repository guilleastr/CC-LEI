package packages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import file.CustomFile;
import file.DirectoryManager;
import file.DirectoryManagerSingleton;

public class PackageBuilder {

    private static final byte CONTROL_TYPE =  1 ;
    private static final byte READ_TYPE =  2 ;
    private static final byte WRITE_TYPE =  3 ;
    private static final byte DATA_TYPE =  4 ;
    private static final byte ACK_TYPE =  5 ;
    private static final byte ERROR_TYPE =  6 ;

    public final static int MAX_PACKAGE_SIZE = 1400;
    
    

    public static byte[] buildControlPackage(List<String> filenames) throws IOException {
        byte type = CONTROL_TYPE;

        String filenames_str = "";
        
        DirectoryManager dm= DirectoryManagerSingleton.getInstance();
        
        List<CustomFile> customFiles=dm.generateCustomFiles(filenames);
        
        for (CustomFile file : customFiles) {
            filenames_str = filenames_str+ file.toStringFormat();
        }
        

        short size = (short) filenames_str.length();
        byte[] size_write=new byte[]{(byte)(size>>>8),(byte)(size&0xFF)};
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size_write);
        baos.write(filenames_str.getBytes());

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public static byte[] buildReadPacakge(String filename) throws IOException {

        byte type = READ_TYPE;

        byte[] filename_bytes = filename.getBytes();

        short size = (short) filename_bytes.length;
        byte[] size_write=new byte[]{(byte)(size>>>8),(byte)(size&0xFF)};
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size_write);
        baos.write(filename_bytes);

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        
        return pack;

    }

    public static byte[] buildWritePackage(String filename) throws IOException {
        byte type = WRITE_TYPE;

        byte[] filename_bytes = filename.getBytes();

        short size = (short) filename_bytes.length;
        byte[] size_write=new byte[]{(byte)(size>>>8),(byte)(size&0xFF)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size_write);
        baos.write(filename_bytes);

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public static byte[] buildDataPacakge(byte[] data, int segmentation) throws IOException {
        byte type = DATA_TYPE;
        short size= (short) data.length;
        byte[] size_write=new byte[]{(byte)(size>>>8),(byte)(size&0xFF)};
        byte[] seg_write=new byte[]{(byte)(segmentation>>>8),(byte)(segmentation&0xFF)};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(size_write);
        baos.write(seg_write);
        baos.write(data);

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public static byte[] buildAcknowledgementPackage(int segmentation, String filename) throws IOException {

        byte type = ACK_TYPE;
        
        short seg=(short) segmentation;
        byte[] seg_write=new byte[]{(byte)(seg>>>8),(byte)(seg&0xFF)};
        
        short size=(short) filename.length();
        byte[] size_write=new byte[]{(byte)(size>>>8),(byte)(size&0xFF)};
        
        short ack_s=(short) filename.length();
        byte[] ack_write=new byte[]{(byte)(ack_s>>>8),(byte)(ack_s&0xFF)};
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(seg_write);
        baos.write(size_write);
        baos.write(ack_write);
        baos.write(filename.getBytes());

        byte[] pack = buildPackage(baos.toByteArray());
        checkPackageSize(pack);

        return pack;

    }

    public static byte[] buildErrorPackage(int type_error, String error_msg) throws IOException {
        byte type = ERROR_TYPE;

        short error_t=(short)type_error;
        byte[] error_write=new byte[]{(byte)(error_t>>>8),(byte)(error_t&0xFF)};
        
        short error_msg_size=(short) error_msg.length();
        byte[] error_msg_size_write=new byte[]{(byte)(error_msg_size>>>8),(byte)(error_msg_size&0xFF)};
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(type);
        baos.write(error_write);
        baos.write(error_msg_size_write);
        baos.write(error_msg.getBytes());

        byte[] pack = buildPackage(baos.toByteArray());

        checkPackageSize(pack);
        return pack;

    }

    private static void checkPackageSize(byte[] bytes) {
        if (bytes.length > MAX_PACKAGE_SIZE) {
            throw new ExceptionInInitializerError("Data Too Big");
        }
    }

    private static byte[] buildPackage(byte[] bytes) {
        byte[] pack = new byte[MAX_PACKAGE_SIZE];

        for (int i = 0; i < bytes.length; i++) {
            pack[i] = bytes[i];
        }
        return pack;
    }

}
