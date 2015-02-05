package com.example.pt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "ElementsDB";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// SQL statement to create table
		String CREATE_TABLE = "CREATE TABLE PeriodicElements ( "
				+ "atomicNo INTEGER PRIMARY KEY NOT NULL,"
				+ "atomicSymbol	CHAR(4) NOT NULL,"
				+ "atomicName CHAR(25) NOT NULL,"
				+ "atomicMass DOUBLE NOT NULL," + "electrons CHAR(5)" + ")";

		// create table
		db.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		db.execSQL("DROP TABLE IF EXISTS element");

		// create fresh table
		this.onCreate(db);

	}

	public static final String TABLE_NAME = "PeriodicElements";

	public static final String COLUMN_ATOMIC_NO = "atomicNo";
	public static final String COLUMN_ATOMIC_SYMBOL = "atomicSymbol";
	public static final String COLUMN_ATOMIC_NAME = "atomicName";
	public static final String COLUMN_ATOMIC_MASS = "atomicMass";
	public static final String COLUMN_ELECTRONS = "electrons";

	private static final String[] COLUMNS = { COLUMN_ATOMIC_NO,
			COLUMN_ATOMIC_SYMBOL, COLUMN_ATOMIC_NAME, COLUMN_ATOMIC_MASS,
			COLUMN_ELECTRONS };

	public void addElement(Element element) {
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(COLUMN_ATOMIC_NO, element.getAtomicNumber());
		values.put(COLUMN_ATOMIC_SYMBOL, element.getAtomicSymbol());
		values.put(COLUMN_ATOMIC_NAME, element.getAtomicName());
		values.put(COLUMN_ATOMIC_MASS, element.getAtomicMass());
		values.put(COLUMN_ELECTRONS, element.getElectrons());

		// 3. insert
		db.insert(TABLE_NAME, null, values);
		// Log.d("addElement", element.toString());

		// 4. close
		db.close();
	}

	public void getElement(String atomicSymbol) {

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, COLUMNS, "atomicSymbol = ?",
				new String[] { (atomicSymbol) }, null, null, null, null);

		// 3. if we got results get the first one
		if (cursor != null) {
			cursor.moveToFirst();
		}

		// 4. build book object
		Element element = new Element();

		element.setAtomicNo(Integer.parseInt(cursor.getString(0)));
		element.setAtomicSymbol(cursor.getString(1));
		element.setAtomicName(cursor.getString(2));
		element.setAtomicMass(Double.parseDouble(cursor.getString(3)));
		element.setElectrons(cursor.getString(4));

		Log.d("getElement1(" + atomicSymbol + ")", element.toString());

		// 5. return book
		// return element;
	}

	public void addALL(MySQLiteHelper db) {

		addElement(new Element(1, "H", "Hydrogen", 1.00794, "1"));
		addElement(new Element(2, "He", "Helium", 4.00260, "2"));
		addElement(new Element(3, "Li", "Lithium", 6.941, "2-1"));
		addElement(new Element(4, "Be", "Beryllium", 9.01218, "2-2"));
		addElement(new Element(5, "B", "Boron", 10.811, "2-3"));
		addElement(new Element(6, "C", "Carbon", 12.011, "2-4"));
		addElement(new Element(7, "N", "Nitrogen", 14.00674, "2-5"));
		addElement(new Element(8, "O", "Oxygen", 15.9994, "2-6"));
		addElement(new Element(9, "F", "Fluorine", 18.998403, "2-7"));
		addElement(new Element(10, "Ne", "Neon", 20.1797, "2-8"));
		addElement(new Element(11, "Na", "Sodium", 22.989768, "2-8-1"));
		addElement(new Element(12, "Mg", "Magnesium", 24.305, "2-8-2"));
		addElement(new Element(13, "Al", "Aluminium", 26.981539, "2-8-3"));
		addElement(new Element(14, "Si", "Silicon", 28.0855, "2-8-4"));
		addElement(new Element(15, "P", "Phosphorus", 30.973762, "2-8-5"));
		addElement(new Element(16, "S", "Sulfur", 32.066, "2-8-6"));
		addElement(new Element(17, "Cl", "Chlorine", 35.4527, "2-8-7"));
		addElement(new Element(18, "Ar", "Argon", 39.948, "2-8-8"));
		addElement(new Element(19, "K", "Potassium", 39.0983, "2-8-1"));
		addElement(new Element(20, "Ca", "Calcium", 40.078, "2-8-2"));
		addElement(new Element(21, "Sc", "Scandium", 44.95591, "2-8-9-2"));
		addElement(new Element(22, "Ti", "Titanium", 47.88, "2-8-10-2"));
		addElement(new Element(23, "V", "Vanadium", 50.9415, "2-8-11-2"));
		addElement(new Element(24, "Cr", "Chromium", 51.9961, "2-8-13-1"));
		addElement(new Element(25, "Mn", "Manganese", 54.938, "2-8-13-2"));
		addElement(new Element(26, "Fe", "Iron", 55.847, "2-8-14-2"));
		addElement(new Element(27, "Co", "Cobalt", 58.9332, "2-8-15-2"));
		addElement(new Element(28, "Ni", "Nickel", 58.6934, "2-8-16-2"));
		addElement(new Element(29, "Cu", "Copper", 63.546, "2-8-18-1"));
		addElement(new Element(30, "Zn", "Zinc", 65.39, "2-8-18-2"));
		addElement(new Element(31, "Ga", "Gallium", 69.732, "2-8-18-3"));
		addElement(new Element(32, "Ge", "Germanium", 72.64, "2-8-18-4"));
		addElement(new Element(33, "As", "Arsenic", 74.92159, "2-8-18-5"));
		addElement(new Element(34, "Se", "Selenium", 78.96, "2-8-18-6"));
		addElement(new Element(35, "Br", "Bromine", 79.904, "2-8-18-7"));
		addElement(new Element(36, "Kr", "Krypton", 83.80, "2-8-18-8"));
		addElement(new Element(37, "Rb", "Rubidium", 85.4678, "2-8-18-8-1"));
		addElement(new Element(38, "Sr", "Strontium", 87.62, "2-8-18-8-2"));
		addElement(new Element(39, "Y", "Yttrium", 88.90585, "2-8-18-9-2"));
		addElement(new Element(40, "Zr", "Zirconium", 91.224, "2-8-18-10-2"));
		addElement(new Element(41, "Nb", "Niobium", 92.90638, "2-8-18-12-1"));
		addElement(new Element(42, "Mo", "Molybdenum", 95.94, "2-8-18-13-1"));
		addElement(new Element(43, "Tc", "Technetium", 98.9072, "2-8-18-14-1"));
		addElement(new Element(44, "Ru", "Ruthenium", 101.07, "2-8-18-15-1"));
		addElement(new Element(45, "Rh", "Rhodium", 102.9055, "2-8-18-16-1"));
		addElement(new Element(46, "Pd", "Palladium", 106.42, "2-8-18-18"));
		addElement(new Element(47, "Ag", "Silver", 107.8682, "2-8-18-18-1"));
		addElement(new Element(48, "Cd", "Cadmium", 112.411, "2-8-18-18-2"));
		addElement(new Element(49, "In", "Indium", 114.818, "2-8-18-18-3"));
		addElement(new Element(50, "Sn", "Tin", 118.71, "2-8-18-18-4"));
		addElement(new Element(51, "Sb", "Antimony", 121.760, "2-8-18-18-5"));
		addElement(new Element(52, "Te", "Tellurium", 127.6, "2-8-18-18-6"));
		addElement(new Element(53, "I", "Iodine", 126.90447, "2-8-18-18-7"));
		addElement(new Element(54, "Xe", "Xenon", 131.29, "2-8-18-18-8"));
		addElement(new Element(55, "Cs", "Caesium", 132.90543, "2-8-18-18-8-1"));
		addElement(new Element(56, "Ba", "Barium", 137.327, "2-8-18-18-8-2"));
		addElement(new Element(57, "La", "Lanthanum", 138.9055, "2-8-18-18-9-2"));
		addElement(new Element(58, "Ce", "Cerium", 140.115, "2-8-18-19-9-2"));
		addElement(new Element(59, "Pr", "Praseodymium", 140.90765,
				"2-8-18-21-8-2"));
		addElement(new Element(60, "Nd", "Neodymium", 144.24, "2-8-18-22-8-2"));
		addElement(new Element(61, "Pm", "Promethium", 144.9127,
				"2-8-18-23-8-2"));
		addElement(new Element(62, "Sm", "Samarium", 150.36, "2-8-18-24-8-2"));
		addElement(new Element(63, "Eu", "Europium", 151.9655, "2-8-18-25-8-2"));
		addElement(new Element(64, "Gd", "Gadolinium", 157.25, "2-8-18-25-9-2"));
		addElement(new Element(65, "Tb", "Terbium", 158.92534, "2-8-18-27-8-2"));
		addElement(new Element(66, "Dy", "Dysprosium", 162.50, "2-8-18-28-8-2"));
		addElement(new Element(67, "Ho", "Holmium", 164.93032, "2-8-18-29-8-2"));
		addElement(new Element(68, "Er", "Erbium", 167.26, "2-8-18-30-8-2"));
		addElement(new Element(69, "Tm", "Thulium", 168.93421, "2-8-18-31-8-2"));
		addElement(new Element(70, "Yb", "Ytterbium", 173.04, "2-8-18-32-8-2"));
		addElement(new Element(71, "Lu", "Lutetium", 174.967, "2-8-18-32-9-2"));
		addElement(new Element(72, "Hf", "Hafnium", 178.49, "2-8-18-32-10-2"));
		addElement(new Element(73, "Ta", "Tantalum", 180.9479, "2-8-18-32-11-2"));
		addElement(new Element(74, "W", "Tungsten", 183.85, "2-8-18-32-12-2"));
		addElement(new Element(75, "Re", "Rhenium", 186.207, "2-8-18-32-13-2"));
		addElement(new Element(76, "Os", "Osmium", 190.23, "2-8-18-32-14-2"));
		addElement(new Element(77, "Ir", "Iridium", 192.22, "2-8-18-32-15-2"));
		addElement(new Element(78, "Pt", "Platinum", 195.08, "2-8-18-32-17-1"));
		addElement(new Element(79, "Au", "Gold", 196.9665, "2-8-18-32-18-1"));
		addElement(new Element(80, "Hg", "Mercury", 200.59, "2-8-18-32-18-2"));
		addElement(new Element(81, "Tl", "Thallium", 204.3833, "2-8-18-32-18-3"));
		addElement(new Element(82, "Pb", "Lead", 207.2, "2-8-18-32-18-4"));
		addElement(new Element(83, "Bi", "Bismuth", 208.98037, "2-8-18-32-18-5"));
		addElement(new Element(84, "Po", "Polonium", 208.9824, "2-8-18-32-18-6"));
		addElement(new Element(85, "At", "Astatine", 209.9871, "2-8-18-32-18-7"));
		addElement(new Element(86, "Rn", "Radon", 222.0176, "2-8-18-32-18-8"));
		addElement(new Element(87, "Fr", "Francium", 223.0197,
				"2-8-18-32-18-8-1"));
		addElement(new Element(88, "Ra", "Radium", 226.0254, "2-8-18-32-18-8-2"));
		addElement(new Element(89, "Ac", "Actinium", 227.0278,
				"2-8-18-32-18-9-2"));
		addElement(new Element(90, "Th", "Thorium", 232.0381,
				"2-8-18-32-18-10-2"));
		addElement(new Element(91, "Pa", "Protactinium", 231.03588,
				"2-8-18-32-20-9-2"));
		addElement(new Element(92, "U", "Uranium", 238.0289, "2-8-18-32-21-9-2"));
		addElement(new Element(93, "Np", "Neptunium", 237.0482,
				"2-8-18-32-22-9-2"));
		addElement(new Element(94, "Pu", "Plutonium", 244.0642,
				"2-8-18-32-24-8-2"));
		addElement(new Element(95, "Am", "Americium", 243.0614,
				"2-8-18-32-25-8-2"));
		addElement(new Element(96, "Cm", "Curium", 247.0703, "2-8-18-32-25-9-2"));
		addElement(new Element(97, "Bk", "Berkelium", 247.0703,
				"2-8-18-32-27-8-2"));
		addElement(new Element(98, "Cf", "Californium", 251.0796,
				"2-8-18-32-28-8-2"));
		addElement(new Element(99, "Es", "Einsteinium", 254, "2-8-18-32-29-8-2"));
		addElement(new Element(100, "Fm", "Fermium", 257.0951,
				"2-8-18-32-30-8-2"));
		addElement(new Element(101, "Md", "Mendelevium", 258.1,
				"2-8-18-32-31-8-2"));
		addElement(new Element(102, "No", "Nobelium", 259.1009,
				"2-8-18-32-32-8-2"));
		addElement(new Element(103, "Lr", "Lawrencium", 262, "2-8-18-32-32-9-2"));
		addElement(new Element(104, "Rf", "Rutherfordium", 261,
				"2-8-18-32-32-10-2"));
		addElement(new Element(105, "Db", "Dubnium", 262, "2-8-18-32-32-11-2"));
		addElement(new Element(106, "Sg", "Seaborgium", 266,
				"2-8-18-32-32-12-2"));
		addElement(new Element(107, "Bh", "Bohrium", 264, "2-8-18-32-32-13-2"));
		addElement(new Element(108, "Hs", "Hassium", 269, "2-8-18-32-32-14-2"));
		addElement(new Element(109, "Mt", "Meitnurium", 268,
				"2-8-18-32-32-15-2"));
		addElement(new Element(110, "Ds", "Darmstadium", 269,
				"2-8-18-32-32-16-2"));
		addElement(new Element(111, "Rg", "Roentgenium", 272,
				"2-8-18-32-32-17-2"));
		addElement(new Element(112, "Cn", "Ununbium", 277, "2-8-18-32-32-18-2"));
		addElement(new Element(113, "Uut", "Ununtrium", 284,
				"2-8-18-32-32-18-3"));
		addElement(new Element(114, "Fl", "Ununquadium", 289,
				"2-8-18-32-32-18-4"));
		addElement(new Element(115, "Uup", "Ununpentium", 288,
				"2-8-18-32-32-18-5"));
		addElement(new Element(116, "Lv", "Ununhexium", 298,
				"2-8-18-32-32-18-6"));
		addElement(new Element(117, "Uus", "Ununseptium", 294,
				"2-8-18-32-32-18-7"));
		addElement(new Element(118, "Uuo", "Ununoctium", 294,
				"2-8-18-32-32-18-8"));

	}

}
