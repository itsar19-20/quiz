package utility;

import javax.persistence.EntityManager;

import org.junit.Test;

public class EntityManagerTest {

	@Test
	public void test() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	}

}
