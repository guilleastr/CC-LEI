package packages.types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import packages.PackageBuilder;

public class DataPackage extends Base_Package  implements Package_Executor{

	private byte[] filename;
    private short sizeSegment;
    private short segmentation;
    private byte[] data;





    public DataPackage(short type, byte[] filename, short size, short segment, byte[] bytes) {
    	super(type);
    	this.filename=filename;
        this.sizeSegment = size;
        this.segmentation = segment;
        this.data = bytes;
	}


	public  List<byte[]> execute() {
    	List<byte[]> responses = new ArrayList<>();    	
    	try {
			responses.add(PackageBuilder.buildAcknowledgementPackage(this.segmentation,PackageBuilder.DATA_TYPE, this.getFileName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(getType());
        System.out.println(sizeSegment);
        System.out.println(segmentation);
        System.out.println(new String(data).toString());
        return responses;
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
    
    public String getFileName() {
    	return Arrays.toString(this.filename);
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
