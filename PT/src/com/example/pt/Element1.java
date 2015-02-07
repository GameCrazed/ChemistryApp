package com.example.pt;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Matthew on 04/02/2015.
 * Facilitates storing of element in memory with all stored attributes
 *
 */
public class Element1 implements Parcelable {
    int atomicNo;
    String symbol;
    String name;
    Double mass;
    String electrons;



    public Element1(int newAtomicNo, String newSymbol, String newName, Double newMass, String newElectrons){ //Constructor taking all attributes
        atomicNo = newAtomicNo;
        symbol = newSymbol;
        name = newName;
        mass = newMass;
        electrons = newElectrons;
    }
    public  Element1(){} //Empty constructor
    //Setters for all attributes
    public void setAtomicNo(int newAtomicNo){ atomicNo = newAtomicNo;}
    public void setSymbol(String newSymbol){ symbol = newSymbol;}
    public void setName(String newName){name = newName;}
    public void setMass(Double newMass){mass = newMass;}
    public void setElectrons(String newElectrons){electrons = newElectrons;}

    //Getters for all attributes
    public int getAtomicNo(){return atomicNo;}
    public String getSymbol(){return  symbol;}
    public String getName(){return  name;}
    public Double getMass(){return mass;}
    public String getElectrons(){return  electrons;}

    protected Element1(Parcel in) {
        atomicNo = in.readInt();
        symbol = in.readString();
        name = in.readString();
        mass = in.readByte() == 0x00 ? null : in.readDouble();
        electrons = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(atomicNo);
        dest.writeString(symbol);
        dest.writeString(name);
        dest.writeDouble(mass);
        dest.writeString(electrons);

    }


    public static final Parcelable.Creator<Element1> CREATOR = new Parcelable.Creator<Element1>() {
        @Override
        public Element1 createFromParcel(Parcel in) {
            return new Element1(in);
        }

        @Override
        public Element1[] newArray(int size) {
            return new Element1[size];
        }
    };
}