

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.walmart.service.model.Cost;
import com.walmart.service.model.DijkstraAlgorithm;
import com.walmart.service.model.Graph;
import com.walmart.service.model.Route;
import com.walmart.service.model.Spot;

public class DijkstraAlgorithmTest {

	private List<Spot> nodes;
	private List<Route> routes;

	@Test
	public void testExcute() {
		nodes = new ArrayList<Spot>();
		routes = new ArrayList<Route>();
		for (int i = 65; i < 70; i++) {
			Spot location = new Spot(new Long(i), String.valueOf(Character.toChars(i)));
			nodes.add(location);
		}
		
		addRoute(1, 0, 1, 10);
		addRoute(2, 1, 3, 15);
		addRoute(3, 0, 2, 20);
		addRoute(4, 2, 3, 30);
		addRoute(5, 1, 4, 50);
		addRoute(6, 3, 4, 30);
		
		Graph graph = new Graph(routes);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(0));
		LinkedList<Spot> path = dijkstra.getPath(nodes.get(3));

		assertNotNull(path);
		assertTrue(path.size() > 0);
		
		LinkedList<Spot> expecteds = new LinkedList<Spot>();
		expecteds.add(new Spot(new Long(65), "A"));
		expecteds.add(new Spot(new Long(66), "B"));
		expecteds.add(new Spot(new Long(68), "D"));
		assertEquals(expecteds, path);
		
			
		Cost cost = new Cost();
		BigDecimal custo = cost.rate(dijkstra.getDistance(nodes.get(3)), 10, new BigDecimal(2.50));
		
		assertTrue(custo.equals(new BigDecimal(6.25)));
	}

	private void addRoute(int laneId, int sourceLocNo, int destLocNo, int duration) {
		Route lane = new Route(new Long(laneId), nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		routes.add(lane);
	}
}
