package packages.types;

import java.util.List;
import java.util.Objects;

import file.CustomFile;
import file.DirectoryManagerSingleton;

public class ControlPackage extends Base_Package{

    //Usa-se o @ como delimitador

    private short size;
    private byte[] files;



    public ControlPackage(int type, short size,byte[] files){
        super(type);
        this.size = size;
        this.files = files;
    }

    @Override
    public  byte[] execute() {
    	System.out.println(getType());
        System.out.println(getSize());
        List<CustomFile> customFiles= DirectoryManagerSingleton.getInstance().parseCustomFiles(new String(files));
        System.out.println(customFiles.toString());
        return null;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public byte[] getfiles() {
        return files;
    }

    public void setfiles(byte[] files) {
        this.files = files;
    }

	/*
	 * public String getStringOfFileNames() { StringBuilder sb = new
	 * StringBuilder();
	 * 
	 * for (int i = 0; i < files.size(); i++) { sb.append('@');
	 * sb.append(files.get(i)); } return sb.toString(); }
	 */
    
	/**
	 * @return int return the type
	 */
	public int getType() {
		return super.type;
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControlPackage that = (ControlPackage) o;
        return size == that.size && Objects.equals(files, that.files);
    }

    @Override
    public String toString() {
        return "ControlPackage{" +
                "type=" + type +
                ", size=" + size +
                ", files=" + files +
                '}';
    }
}
