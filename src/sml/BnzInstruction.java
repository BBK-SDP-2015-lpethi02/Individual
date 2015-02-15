package sml;



public class BnzInstruction extends Instruction {
	private int result;
	

	public BnzInstruction(String label, String op) {
		super(label, op);
	}

	public BnzInstruction(String label,int result) {
		this(label, "bnz");
		this.result=result;
		
			
	}
	
	
	@Override
	public void execute(Machine m) {
		Labels labels = m.getLabels();
		int ind = labels.indexOf(label);
		int value = m.getRegisters().getRegister(result);
		if(value !=0){
			m.setPc(ind-2);
			
		}
	
		
	}
	
	@Override
	public String toString(){
		Machine m = new Machine();
		Labels labels = m.getLabels();
		int ind = labels.indexOf(label);
		
		return super.toString() + " " + result + " jump to instruction f3";
		
	}

}
