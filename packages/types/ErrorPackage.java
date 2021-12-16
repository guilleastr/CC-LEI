package packages.types;

import java.util.Objects;

public class ErrorPackage extends Base_Package{

    private int typeError;
    private short size;
    private String message;

    public ErrorPackage(int type) {
        super(type);
        this.typeError = 0;
        this.size = 0;
        this.message = "";
    }

    public ErrorPackage(int type, int typeError, short size, String msg){
        super(type);
        this.typeError = typeError;
        this.size = size;
        this.message = msg;
    }

    @Override
    public Void execute() {
        System.out.println(typeError);
        System.out.println(size);
        System.out.println(message);
        return null;
    }

    public int getTypeError() {
        return typeError;
    }

    public void setTypeError(int typeError) {
        this.typeError = typeError;
    }

    public short getSize() {
        return size;
    }

    public void setSize(short size) {
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
