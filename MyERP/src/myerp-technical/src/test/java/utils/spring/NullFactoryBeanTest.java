package utils.spring;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.dummy.myerp.technical.util.spring.NullFactoryBean;

public class NullFactoryBeanTest {
	
	private static NullFactoryBean<String> nullStringTest;
	
	@BeforeAll
	public static void initialiserNullFactoryBean() {
		nullStringTest = new NullFactoryBean<String>(String.class);
	}
	
	@Test
	public void nullBeanFactoryUnit() {
		try {
			assertNull(nullStringTest.getObject());
			assertFalse(nullStringTest.isSingleton());
			assertEquals(String.class, nullStringTest.getObjectType());
		}catch(Exception e) {
			fail("Le bean null factory créé n'est pas conforme");
		}
	}

}
