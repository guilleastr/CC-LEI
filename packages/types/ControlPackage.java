package packages.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlPackage extends Base_Package{

    //Usa-se o @ como delimitador

    private short size;
    private List<String> file_name;

    public ControlPackage(int type) {
        super(type);
        this.size = 0;
        this.file_name = new ArrayList<>();
    }

    public ControlPackage(int type, short size, List<String> file_name){
        super(type);
        this.size = size;
        this.file_name = file_name;
    }

    @Override
    public Void execute() {
        System.out.println(getSize());
        System.out.println(getStringOfFileNames());
        return null;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public List<String> getFile_name() {
        return file_name;
    }

    public void setFile_name(List<String> file_name) {
        this.file_name = file_name;
    }

    public String getStringOfFileNames() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < file_name.size(); i++) {
            sb.append('@');
            sb.append(file_name.get(i));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlPackage that = (ControlPackage) o;
        return size == that.size && Objects.equals(file_name, that.file_name);
    }

    @Override
    public String toString() {
        return "ControlPackage{" +
                "type=" + type +
                ", size=" + size +
                ", file_name=" + file_name +
                '}';
    }
}
