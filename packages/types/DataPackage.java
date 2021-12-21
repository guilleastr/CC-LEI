package packages.types;

import java.util.Objects;

public class DataPackage extends Base_Package  implements Package_Executor{

    private short sizeSegment;
    private short segmentation;
    private byte[] data;




    public DataPackage(int type, short sizeSegment, short segmentation, byte[] bytes){
        super(type);
        this.sizeSegment = sizeSegment;
        this.segmentation = segmentation;
        this.data = bytes;
    }

    
    public  byte[] execute() {
    	System.out.println(getType());
        System.out.println(sizeSegment);
        System.out.println(segmentation);
        System.out.println(new String(data).toString());
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

    public void setSegmentation(short segmentation) {
        this.segmentation = segmentation;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
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
        DataPackage that = (DataPackage) o;
        return sizeSegment == that.sizeSegment && segmentation == that.segmentation && Objects.equals(data, that.data);
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "type=" + type +
                ", sizeSegment=" + sizeSegment +
                ", segmentation=" + segmentation +
                ", data='" + data.toString() + '\'' +
                '}';
    }
}
