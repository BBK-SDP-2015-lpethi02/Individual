package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The array list of labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code
	
	
	private static final String SRC = "C:/sml/SML/src";

	
	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	// Label object is passed into this method as a parameter 
	// along with program array list
	// the labels are extracted from each line and stored in the lables
	//array list
	// program is stored in the prog array list
	
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
	// use scanner to read each line in the file
		
		try (Scanner sc = new Scanner(new File(fileName))) {
			//System.out.println(fileName);
			//System.out.println(lab);
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	@SuppressWarnings("unchecked")
	public Instruction getInstruction(String label) {
		int s1; // Possible operands of the instruction
		int s2;
		int r;
		int x;

		if (line.equals(""))
			return null;
// here, scan() returns the string between the start of the string and the first ' ' 
// since by now the label has been removed already
// scan now returns the instruction itself
// don'f forget, scan is used above in readAndTranslate
// to grab the label and store the labels in an array list
// so, when it comes to this method we have the line in the format
// instruction register-list add r s1 s2
		
		Class<Instruction> className=null;
		Constructor<Instruction> constructor=null; 
		
		
		String ins = scan();
		switch (ins) {
		case "add":
			r = scanInt();// register numbers allocated
			s1 = scanInt();
			s2 = scanInt();
			try {
				className = (Class<Instruction>) Class.forName("sml.AddInstruction");
				constructor = className.getConstructor(new Class[]{String.class,int.class,int.class,int.class});
				System.out.println(constructor.toString());
				Instruction myObject = constructor.newInstance(label, r, s1, s2);
				return myObject;
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			//return new AddInstruction(label, r, s1, s2);
		
		case "lin":
			r = scanInt();
			s1 = scanInt();
			try {
				className = (Class<Instruction>)Class.forName("sml.LinInstruction");
				constructor = className.getConstructor(new Class[]{String.class,int.class,int.class});
				Instruction myObject = constructor.newInstance(label, r, s1);
				System.out.println(constructor.toString());
				return myObject;
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			//return new LinInstruction(label, r, s1);
			
		case "mul":
			r = scanInt();
			s1 = scanInt();
			s2= scanInt();
			
			try {
				className = (Class<Instruction>)Class.forName("sml.MulInstruction");
				constructor = className.getConstructor(new Class[]{String.class,int.class,int.class,int.class});
				Instruction myObject = constructor.newInstance(label, r, s1, s2);
				System.out.println(constructor.toString());
				return myObject;
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			 //new MulInstruction(label, r, s1, s2);
		
		case "sub":
			
			r = scanInt();
			s1 = scanInt();
			s2= scanInt();
			
			try {
				className = (Class<Instruction>)Class.forName("sml.SubInstruction");
				constructor = className.getConstructor(new Class[]{String.class,int.class,int.class,int.class});
				Instruction myObject = constructor.newInstance(label, r, s1, s2);
				System.out.println(constructor.toString());
				return myObject;
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			//return new SubInstruction(label, r, s1, s2);
		
		case "bnz":
			r = scanInt();
			
			try {
				className = (Class<Instruction>)Class.forName("sml.BnzInstruction");
				constructor = className.getConstructor(new Class[]{String.class,int.class});
				Instruction myObject = constructor.newInstance(label, r);
				System.out.println(constructor.toString());
				return myObject;
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			//return new BnzInstruction(label, r);
			
		case "out":
			r = scanInt();
			s1 = scanInt();
			
			try {
				className = (Class<Instruction>)Class.forName("sml.OutInstruction");
				constructor = className.getConstructor(new Class[]{String.class,int.class, int.class});
				Instruction myObject = constructor.newInstance(label, s1, r);
				System.out.println(constructor.toString());
				return myObject;
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
			//return new OutInstruction(label,s1, r);
		
	}


		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
			i = i + 1;
		}
		
		/**
		 * trim the line to exclude the word, and
		 * assign into line the line without the word so it will be like
		 * lin 20 6 without f0 
		 * 
		 * when its used for the second time inside readAndTranslate, 
		 * the scan() method returns the instruction
		 * 
		 * now line contains only the register list, except for bnz 
		 */
		String word = line.substring(0, i);
		line = line.substring(i);//  

		
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}