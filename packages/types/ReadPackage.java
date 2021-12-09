package packages.types;

public class ReadPackage extends Base_Package {

    private int type;
    private int size;
    private String file_name;

    public ReadPackage(int type) {
        super(type);
        // TODO Auto-generated constructor stub
    }

    public ReadPackage(int type, int size, String file_name) {
        super(type);
        this.size = size;
        this.file_name = file_name;
    }

    @Override
    public Void execute() {
        System.out.println(getType());
        System.out.println(getSize());
        System.out.println(getFile_name());
        return null;
    }

    /**
     * @return int return the type
     */
    public int getType() {
        return super.type;
    }


    /**
     * @return int return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * @return String return the file_name
     */
    public String getFile_name() {
        return file_name;
    }

    /**
     * @param file_name the file_name to set
     */
    private void setFile_name(String file_name) {
        this.file_name = file_name;
    }

}
