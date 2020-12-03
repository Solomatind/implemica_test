package com.implemica.cities.runner;

import com.implemica.cities.consoleworker.ShortestPathFinderWorker;

public class ShortestPathRunner {

	public static void main(String[] args) {
		ShortestPathFinderWorker worker = new ShortestPathFinderWorker();
		worker.start();
		worker.stop();
	}

}
