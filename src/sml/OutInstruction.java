package sml;

public class OutInstruction extends Instruction {
	private int result;
	public OutInstruction(String l, String op) {
		super(l, op);
	}
	public OutInstruction(String label, int opOne, int r) {
		this(label, "out");
		this.result=r;
	}
	@Override
	public void execute(Machine m) {
		//System.out.println(r);

	}
	
	
	@Override
	public String toString() {
		return super.toString() + " " + result;
	}

}
