package sml;

public class SubInstruction extends Instruction {
	private int result;
	private int op1;
	private int op2;
	
	public SubInstruction(String label, String op) {
		super(label, op);
	}
	
	public SubInstruction(String label, int result, int opOne, int opTwo ) {
		this(label, "sub");
		this.result=result;
		this.op1=opOne;
		this.op2=opTwo;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 - value2);

	}

	@Override
	public String toString(){
		return super.toString() + " " + op1 + " - " + op2 + " to " + result;
		
	}
}