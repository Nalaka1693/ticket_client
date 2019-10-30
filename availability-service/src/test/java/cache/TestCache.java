package cache;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCache {
	static Cache cache;

	@BeforeClass
	public static void setUpBeforClass() throws Exception {
		cache = Cache.getInstance();
		cache.populatePriceMap("");
		cache.populateInventory();
	}

	@Test
	public void testGetAvailability() {
		// only unit journies accepted here eg: no A --> D
		UnitJourney uj1 = new UnitJourney("A", "B");
		UnitJourney uj2 = new UnitJourney("B", "C");
		UnitInventory ui1 = new UnitInventory(uj1, null, true);
		UnitInventory ui2 = new UnitInventory(uj1, null, false);
		UnitInventory ui3 = new UnitInventory(uj2, null, true);
		
		assertEquals(cache.getAvailability(ui1, 4), 50);
		assertEquals(cache.getAvailability(ui2, 4), 50);
		assertEquals(cache.getAvailability(ui3, 4), 50);
	}

	@Test
	public void testSetReservation() {
		UnitJourney uj1 = new UnitJourney("A", "B");
		UnitJourney uj2 = new UnitJourney("B", "C");
		UnitInventory ui1 = new UnitInventory(uj1, null, true);
		UnitInventory ui2 = new UnitInventory(uj1, null, false);
		UnitInventory ui3 = new UnitInventory(uj2, null, true);
		
		// for above testGetAvailability to be sccess keep at least 4 seats available
		assertEquals(cache.setReservation(ui1, 35), 50);
		assertEquals(cache.setReservation(ui2, 35), 50);
		assertEquals(cache.setReservation(ui3, 35), 50);
	}

}
