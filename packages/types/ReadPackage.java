package packages.types;

import java.util.Objects;

public class ReadPackage extends Base_Package {

    private int size;
    private String file_name;

    public ReadPackage(int type) {
        super(type);
        this.size = 0;
        this.file_name= "";
    }

    public ReadPackage(int type, int size, String file_name) {
        super(type);
        this.size = size;
        this.file_name = file_name;
    }

    @Override
    public Void execute() {
        System.out.println(getSize());
        System.out.println(getFile_name());
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadPackage that = (ReadPackage) o;
        return size == that.size && Objects.equals(file_name, that.file_name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ").append(size).append(" | File name: ").append(file_name);
        return sb.toString();
    }
}
