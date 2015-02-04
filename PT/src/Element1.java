/**
 * Created by Matthew on 04/02/2015.
 * Facilitates storing of element in memory with all stored attributes
 *
 */
public class Element1 {
    int atomicNo;
    String symbol;
    String name;
    Double mass;
    String electrons;



    public Element1(int newAtomicNo, String newSymbol, String newName, Double newMass,String newElectrons){ //Constructor taking all attributes
        atomicNo = newAtomicNo;
        symbol = newSymbol;
        name = newName;
        mass = newMass;
        electrons = newElectrons;
    }
    //Setters for all attributes
    public void setAtomicNo(int newAtomicNo){ atomicNo = newAtomicNo;}
    public void setSymbol(String newSymbol){ symbol = newSymbol;}
    public void setName(String newName){name = newName;}
    public void setMass(Double newMass){mass = newMass;}
    public void setElectrons(String newElectrons){electrons = newElectrons;}
}
