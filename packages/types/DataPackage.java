package packages.types;

import java.util.Objects;

public class DataPackage extends Base_Package{

    private short sizeSegment;
    private int segmentation;
    private String data;



    public DataPackage(int type) {
        super(type);
        this.sizeSegment = 0;
        this.segmentation = 0;
        this.data = "";
    }

    public DataPackage(int type, short sizeSegment, int segmentation, String data){
        super(type);
        this.sizeSegment = sizeSegment;
        this.segmentation = segmentation;
        this.data = data;
    }

    @Override
    public Void execute() {
        System.out.println(sizeSegment);
        System.out.println(segmentation);
        System.out.println(data);
        return null;
    }

    public short getSizeSegment() {
        return sizeSegment;
    }

    public void setSizeSegment(short sizeSegment) {
        this.sizeSegment = sizeSegment;
    }

    public int getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(int segmentation) {
        this.segmentation = segmentation;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPackage that = (DataPackage) o;
        return sizeSegment == that.sizeSegment && segmentation == that.segmentation && Objects.equals(data, that.data);
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "type=" + type +
                ", sizeSegment=" + sizeSegment +
                ", segmentation=" + segmentation +
                ", data='" + data + '\'' +
                '}';
    }
}
