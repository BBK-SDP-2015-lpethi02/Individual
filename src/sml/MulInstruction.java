package sml;

public class MulInstruction extends Instruction {
	private int result;
	private int op1;
	private int op2;
	

	public MulInstruction(String label, String opCode){
		super(label, opCode);
	}
	
	public MulInstruction(String label, int result , int op1, int op2){
		this(label,"mul");
		this.op1=op1;
		this.result= result;
		this.op2= op2;
	}
	
	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 * value2);

	}
	
	@Override 
	public String toString(){
		return super.toString()+ " Multply value in register " + result + " by value in register "  + op2 + " and store the result in " + op1;
	}

}
