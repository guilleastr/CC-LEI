package packages.types;

import java.util.List;

public class ControlPackage extends Base_Package{

    //Usa-se o @ como delimitador

    private int type;
    private int size;
    private List<String> file_name;

    public ControlPackage(int type) {
        super(type);
        //TODO Auto-generated constructor stub
    }

    public ControlPackage(int type, int size, List<String> file_name){
        this.type = type;
        this.size = size;
        this.file_name = file_name;
    }

    @Override
    public void execute() {
        System.out.println(getType());
        System.out.println(getSize());
        System.out.println(getStringOfFileNames());
    }

    public int getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getStringOfFileNames() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < file_name.size(); i++) {
            sb.append('@');
            sb.append(file_name.get(i));
        }
        return sb.toString();
    }
}
