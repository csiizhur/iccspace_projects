package test;
import java.util.EnumSet;

import org.junit.*;

import com.icc.common.Color;
import com.icc.common.EstatesType;

public class EnumTest {

	@Test
	public void testEnum(){
		EnumSet<EstatesType> set=EnumSet.noneOf(EstatesType.class); 
		set.addAll(EnumSet.complementOf(set));
		System.err.println(set);
	}
	@Test
	public void testEnum2(){
		Color c=Color.BLANK;
		System.err.println(c.getInfo());
	}
}
