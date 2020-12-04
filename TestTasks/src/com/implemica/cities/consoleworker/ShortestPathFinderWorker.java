package com.implemica.cities.consoleworker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.implemica.cities.entity.Edge;
import com.implemica.cities.entity.Vertex;
import com.implemica.cities.pathfinder.ShortestPath;

/**
 * Class that works with console.
 */
public class ShortestPathFinderWorker {

	private Scanner scanner;
	/** The field which store the object of algorithm. */
	private ShortestPath shortestPath;

	public ShortestPathFinderWorker() {
		scanner = new Scanner(System.in);
		shortestPath = new ShortestPath();

	}

	/**
	 * The method for starting to work with console.
	 */
	public void start() {
		int countOfTests;
		System.out.print("the number of tests:(<= 10) ");
		countOfTests = scanner.nextInt();
		for (int i = 0; i < countOfTests; i++) {
			setCities();
			getPaths();
		}
	}

	/**
	 * The method gets input parameters of source and destination cities from input
	 * stream. Set parameters to paths array.
	 */
	private void getPaths() {
		int countOfPaths;
		System.out.print("the number of paths to find:(<= 100) ");
		countOfPaths = scanner.nextInt();
		String[] paths = new String[countOfPaths];
		for (int i = 0; i < countOfPaths; i++) {
			System.out.print("Source city + whitespace + destination city ");
			String src = scanner.next();
			String dest = scanner.next();
			paths[i] = src + " " + dest;
		}
		System.out.println();
		printShortestPathsLength(paths);
	}

	/**
	 * The method prints the result of the calculating shortest paths.
	 * 
	 * @param paths the array which store the paths to be calculated.
	 */
	private void printShortestPathsLength(String[] paths) {
		for (String path : paths) {
			String[] sourceDest = path.split(" ");
			shortestPath.computeShortestPaths(sourceDest[0]);
			System.out.println(
					"the minimum transportation cost is " + shortestPath.getShortestLengthPathTo(sourceDest[1]));
		}
	}

	/**
	 * The method gets names of cities from input stream and sets them to the map
	 * object.
	 */
	private void setCities() {
		int countOfCities;
		System.out.print("the number of cities:(<= 10000) ");
		Map<Integer, String> cities = new HashMap<>();
		countOfCities = scanner.nextInt();
		String[][] edges = new String[countOfCities][];
		for (int i = 0; i < countOfCities; i++) {
			System.out.print("city name: ");
			String name = scanner.next();
			cities.put(i + 1, name);
			edges[i] = getEdges();
		}
		setCitiesNamesToVertexes(cities, edges);
	}

	/**
	 * The method makes vertex list from an object of cities.
	 * 
	 * @param cities store all cities.
	 * @param edges  store edges with weights of each city.
	 */
	private void setCitiesNamesToVertexes(Map<Integer, String> cities, String[][] edges) {
		List<Vertex> vertexes = new ArrayList<>();
		for (int i = 0; i < cities.size(); i++) {
			vertexes.add(new Vertex(cities.get(i + 1)));
		}
		setEdges(vertexes, edges);
	}

	/**
	 * The method set edges to the list of vertexes and set them to the field of
	 * shortestPath.
	 * 
	 * @param vertexes store the list of all vertexes.
	 * @param edges    store the matrix of edges with weights of each vertexes.
	 */
	private void setEdges(List<Vertex> vertexes, String[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			for (int j = 0; j < edges[i].length; j++) {
				String[] edgeValues = edges[i][j].split(" ");
				vertexes.get(i).addNeighbour(new Edge(Integer.parseInt(edgeValues[1]), vertexes.get(i),
						vertexes.get(Integer.parseInt(edgeValues[0]) - 1)));
			}
		}
		shortestPath.setVertexes(vertexes);
	}

	/**
	 * The method gets neighbour's indexes and their weights from input stream and
	 * sets them to the array of edges.
	 * 
	 * @return the array of edges with weights.
	 */
	private String[] getEdges() {
		int countOfEdges;
		System.out.print("the number of neighbors of city: ");
		countOfEdges = scanner.nextInt();
		String[] edges = new String[countOfEdges];
		for (int i = 0; i < countOfEdges; i++) {
			System.out.print(
					"index of neighbour(the index of the first city is 1) + whitespace + the transportation cost: ");
			String neighbour = scanner.next();
			String weight = scanner.next();
			edges[i] = neighbour + " " + weight;
		}
		return edges;
	}

	/**
	 * The method closes the input stream.
	 */
	public void stop() {
		scanner.close();
	}

}
