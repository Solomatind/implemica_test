package com.implemica.cities.consoleworker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.implemica.cities.entity.Edge;
import com.implemica.cities.entity.Vertex;
import com.implemica.cities.pathfinder.ShortestPath;

public class ShortestPathFinderWorker {

	private Scanner scanner;
	private ShortestPath shortestPath;

	public ShortestPathFinderWorker() {
		scanner = new Scanner(System.in);
		shortestPath = new ShortestPath();

	}

	public void start() {
		int countOfTests;
		System.out.print("the number of tests:(<= 10) ");
		countOfTests = scanner.nextInt();
		for (int i = 0; i < countOfTests; i++) {
			setCities();
			getPaths();
		}
	}

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

	private void printShortestPathsLength(String[] paths) {
		for (String path : paths) {
			String[] sourceDest = path.split(" ");
			shortestPath.computeShortestPaths(sourceDest[0]);
			System.out.println("the minimum transportation cost is " + shortestPath.getShortestLengthPathTo(sourceDest[1]));
		}
	}

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

	private void setCitiesNamesToVertexes(Map<Integer, String> cities, String[][] edges) {
		List<Vertex> vertexes = new ArrayList<>();
		for (int i = 0; i < cities.size(); i++) {
			vertexes.add(new Vertex(cities.get(i + 1)));
		}
		setEdges(vertexes, edges);
	}

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

	public void stop() {
		scanner.close();
	}

}
