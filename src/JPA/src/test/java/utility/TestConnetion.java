package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

public class TestConnetion {

	@Test
	public void test() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	}

}
