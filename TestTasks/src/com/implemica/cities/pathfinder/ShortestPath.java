package com.implemica.cities.pathfinder;

import java.util.List;
import java.util.PriorityQueue;
import com.implemica.cities.entity.Edge;
import com.implemica.cities.entity.Vertex;

public class ShortestPath {

	private List<Vertex> cities;

	public void computeShortestPaths(String sourceName) {
		clearVertexes();
		Vertex sourceVertex = null;
		for (Vertex city : cities) {
			if (city.getName().equals(sourceName)) {
				sourceVertex = city;
			}
		}
		sourceVertex.setDistance(0);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
		while (!priorityQueue.isEmpty()) {
			Vertex actualVertex = priorityQueue.poll();
			for (Edge edge : actualVertex.getAdjacenciesList()) {
				Vertex v = edge.getTargetVertex();
				if (!v.isVisited()) {
					int newDistance = actualVertex.getDistance() + edge.getWeight();
					if (newDistance < v.getDistance()) {
						priorityQueue.remove(v);
						v.setDistance(newDistance);
						v.setPredecessor(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			actualVertex.setVisited(true);
		}
	}

	public int getShortestLengthPathTo(String targetName) {
		Vertex targetVertex = null;
		for (Vertex city : cities) {
			if (city.getName().equals(targetName)) {
				targetVertex = city;
			}
		}
		return targetVertex.getDistance();

	}

	private void clearVertexes() {
		cities.forEach((Vertex vertex) -> {
			vertex.setVisited(false);
			vertex.setDistance(Integer.MAX_VALUE);
			vertex.setPredecessor(null);
		});
	}

	public void setVertexes(List<Vertex> cities) {
		this.cities = cities;
	}

}
