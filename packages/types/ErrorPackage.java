package packages.types;

import java.util.Objects;

public class ErrorPackage extends Base_Package{

    private short typeError;
    private short size;
    private byte[] message;


    public ErrorPackage(int type, short typeError, short size, byte[] msg){
        super(type);
        this.typeError = typeError;
        this.size = size;
        this.message = msg;
    }

    @Override
    public  byte[] execute() {
    	System.out.println(getType());
    	System.out.println(getTypeError());
        System.out.println(getSize());
        System.out.println(new String(getMessage()).toString());
        return null;
    }

    public int getTypeError() {
        return typeError;
    }

    public void setTypeError(short typeError) {
        this.typeError = typeError;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
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
        ErrorPackage that = (ErrorPackage) o;
        return typeError == that.typeError && size == that.size && Objects.equals(message, that.message);
    }

    @Override
    public String toString() {
        return "ErrorPackage{" +
                "type=" + type +
                ", typeError=" + typeError +
                ", size=" + size +
                ", message='" + message + '\'' +
                '}';
    }
}
