package packages.types;

public abstract class Base_Package implements Package_Executor{

    protected int type;

    public Base_Package(int type) {
        this.type = type;

    }

    public abstract Void  execute();

}
