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
		assertEquals(720, y);
		
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
		
		@Test
		public void testMulInstruction() {
			Machine m = new Machine();
			Translator t = new Translator("code.sml");
			t.readAndTranslate(m.getLabels(), m.getProg());
			m.execute();
		
			
			MulInstruction mul = new MulInstruction("f3", 21 , 21, 20);
			mul.execute(m);
			int z =m.getRegisters().getRegister(21);
			System.out.println(z);
			assertEquals(0, z);
	}
		
		@Test
		public void testSubInstruction() {
			Machine m = new Machine();
			Translator t = new Translator("code.sml");
			t.readAndTranslate(m.getLabels(), m.getProg());
			m.execute();
		
			//int x = m.getRegisters().getRegister(20);
			//int y = m.getRegisters().getRegister(22);
			
			SubInstruction sub = new SubInstruction("f4", 20 , 5,4);
			sub.execute(m);
			int z = m.getRegisters().getRegister(20);
			System.out.println(z);
			assertEquals(0, z);
	}
		
		@Test
		public void testValueInReg21() {
			Machine m = new Machine();
			Translator t = new Translator("code.sml");
			t.readAndTranslate(m.getLabels(), m.getProg());
			m.execute();
		
			int x = m.getRegisters().getRegister(21);
			System.out.println(x);
			assertEquals(720, x);
	}

}
