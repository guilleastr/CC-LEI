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
        System.out.println("ACK #"+ackNumber);
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
