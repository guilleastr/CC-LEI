package packages.types;

public class AcknowlegementPacakge extends Base_Package{

    private int ackNumber;

    public AcknowlegementPacakge(int type) {
        super(type);
        //TODO Auto-generated constructor
        this.ackNumber = 0;
    }

    public AcknowlegementPacakge(int type, int ackNR){
        super(type);
        this.ackNumber = ackNR;
    }

    @Override
    public Void execute() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getType() {
        return super.type;
    }

    public int getAckNumber(){
        return this.ackNumber;
    }

    public int setAckNumber(int nr){
        this.ackNumber = nr;
    }



    
}
