package datagram_socket.packages.types;

public abstract class Base_Package {

    private int type;

    public Base_Package(int type) {
        this.type = type;

    }

    public abstract void execute();

}
