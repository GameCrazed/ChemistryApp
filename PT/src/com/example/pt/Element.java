package com.example.pt;

public class Element {

	private int atomicNo;
	private String atomicSymbol;
	private String atomicName;
	private double atomicMass;
	private String electrons;

	public Element() {
		super();
	}
    public Element(String newSymbol, Double newMass){
        atomicSymbol = newSymbol;
        atomicMass = newMass;
    }

	public Element(int atomicNo, String atomicSymbol, String atomicName,
			double atomicMass, String electrons) {
		super();

		this.atomicNo = atomicNo;
		this.atomicSymbol = atomicSymbol;
		this.atomicName = atomicName;
		this.atomicMass = atomicMass;
		this.electrons = electrons;

	}

	public int getAtomicNumber() {
		return atomicNo;
	}

	public String getAtomicSymbol() {
		return atomicSymbol;
	}

	public String getAtomicName() {
		return atomicName;
	}

	public double getAtomicMass() {
		return atomicMass;
	}

	public String getElectrons() {
		return electrons;
	}

	public void setAtomicNo(int atomicNo) {
		this.atomicNo = atomicNo;
	}

	public void setAtomicSymbol(String atomicSymbol) {
		this.atomicSymbol = atomicSymbol;
	}

	public void setAtomicName(String atomicName) {
		this.atomicName = atomicName;
	}

	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	public void setElectrons(String electrons) {
		this.electrons = electrons;
	}

	@Override
	public String toString() {

		return "Atomic Number: " + atomicNo + "\nAtomic Mass: " + atomicMass
				+ "\nElectrons: " + electrons;

	}

}
