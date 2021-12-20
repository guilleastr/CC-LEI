package packages.types;

import java.io.IOException;

public abstract class Base_Package implements Package_Executor{

    protected int type;

    public Base_Package(int type) {
        this.type = type;
    }

    public abstract  byte[] execute()throws IOException;

}
