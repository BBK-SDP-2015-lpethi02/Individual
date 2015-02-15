package sml;

import static org.junit.Assert.*;

import org.junit.Test;

import sml.*;

public class FactorialTest {

	@Test
	public void testFactorialValue() {
		Machine m = new Machine();
		Translator t = new Translator("code.sml");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
		//MulInstruction mul = new MulInstruction("f3", 21 , 21, 20);
		
		int y = m.getRegisters().getRegister(21);
		int z = m.getRegisters().getRegister(22);
		System.out.println(z);
		assertEquals(720, y);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testValueInReg22() {
		Machine m = new Machine();
		Translator t = new Translator("code.sml");
		t.readAndTranslate(m.getLabels(), m.getProg());
		m.execute();
	
		int z = m.getRegisters().getRegister(22);
		System.out.println(z);
		assertEquals(1, z);
	}
		
		@Test
		public void testValueInReg20() {
			Machine m = new Machine();
			Translator t = new Translator("code.sml");
			t.readAndTranslate(m.getLabels(), m.getProg());
			m.execute();
		
			int x = m.getRegisters().getRegister(20);
			System.out.println(x);
			assertEquals(0, x);
	}

}
