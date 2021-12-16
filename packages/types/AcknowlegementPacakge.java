package packages.types;

import java.util.Objects;

public class AcknowlegementPacakge extends Base_Package{

    private int ackNumber;
    private int segmentation;
    private short size_file_name;
    private String file_name;

    public AcknowlegementPacakge(int type) {
        super(type);
        this.ackNumber = 0;
        this.segmentation = 0;
        this.size_file_name = 0;
        this.file_name = "";
    }

    public AcknowlegementPacakge(int type, int ackNR, int seg, short size, String name){
        super(type);
        this.ackNumber = ackNR;
        this.segmentation = seg;
        this.size_file_name = size;
        this.file_name = name;
    }

    @Override
    public Void execute() {
        System.out.println("ACK #"+getAckNumber()+ "| Seg #"+ getSegmentation()+ " | FileName: "+ getFile_name());
        return null;
    }

    public int getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(int ackNumber) {
        this.ackNumber = ackNumber;
    }

    public int getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(int segmentation) {
        this.segmentation = segmentation;
    }

    public short getSize_file_name() {
        return size_file_name;
    }

    public void setSize_file_name(short size_file_name) {
        this.size_file_name = size_file_name;
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
        AcknowlegementPacakge that = (AcknowlegementPacakge) o;
        return ackNumber == that.ackNumber && segmentation == that.segmentation
                && size_file_name == that.size_file_name && Objects.equals(file_name, that.file_name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type:").append(type)
                .append("ACK#").append(ackNumber)
                .append("Seg#").append(segmentation)
                .append("Name size: ").append(size_file_name)
                .append("File name: ").append(file_name);
        return sb.toString();
    }
}
