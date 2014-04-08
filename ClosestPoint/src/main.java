import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class main {

	// [id][x - y]
	static Point[] points;

	public static void main(String[] args) throws IOException {
		parser(args);
		// findClosest();
		// buildMap();
		// double d =Double.valueOf("7.03600e+02");
		// System.out.println(d);
		Arrays.sort(points);
		findclosestRec(points, points.length);

	}

	public static double findclosestRec(Point[] p, int n) {

		if (n < 4) {
			return findClosest(p);
		}
		int mid = p.length / 2;
		Point midPoint = p[mid];

		Point[] source = new Point[p.length];

		Point[] part1 = new Point[mid];
		Point[] part2 = new Point[mid];

		System.arraycopy(source, 0, part1, 0, part1.length);
		System.arraycopy(source, part1.length, part2, 0, part2.length);
		double left = findclosestRec(part1, mid);

		double right = findclosestRec(part2, n - mid);
		double smallestDistSides = Double.MAX_VALUE;
		if (left < right) {
			smallestDistSides = left;
		} else  {
			smallestDistSides = right;
		} 
		Point[] closeMiddle = new Point[n];
		int j = 0;
		for (int i = 0; i < n; i++){
	        if (Math.abs(p[i].x - midPoint.x) < smallestDistSides) {
	            closeMiddle[j] = p[i]; 
	            j++;
	        }
		}
		double closestMiddle = findNearMiddleClosest(closeMiddle, smallestDistSides);
		if(closestMiddle < smallestDistSides){
			return closestMiddle;
		} else {
			return smallestDistSides;
		}
	}

	public static double findNearMiddleClosest(Point[] closeP, double closestDistSides){
		//Sort after Y-koord
		//BruteForce
		
		return closestDistSides;
		
	}
	private static double findClosest(Point[] p) {
		double shortest = Double.MAX_VALUE;

		for (int i = 0; i < points.length; i++) {
			double x = points[i].x;
			double y = points[i].y;
			for (int j = 0; j < points.length; j++) {
				if (i != j) {
					// double otherId = points[j][0];
					double otherX = points[j].x;
					double otherY = points[j].y;
					// System.out.println("ID: " + id + " X: " + x + " Y: " +
					// y);
					// System.out.println("ID: " + otherId + "X: " + otherX +
					// " Y: " + otherY);
					double l = Math.hypot((otherX - x), (otherY - y));
					// System.out.println("Hypot: " + l);
					if (l < shortest) {
						// if(l == 0.0){
						// System.out.println("HEHE");
						// }
						shortest = l;
					}
				}
			}
			// System.out.println();
		}
		System.out.println("Shortest is: " + shortest);
		return shortest;
	}

	static class Point implements Comparable<Point> {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;

		}

		@Override
		public int compareTo(Point arg0) {
			if (x > arg0.x) {
				return 1;
			} else if (x < arg0.x) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	// 4 8.75100e+02 1.13610e+03
	public static void parser(String[] arg) throws IOException {
		FileReader fr = new FileReader(new File(
				"/Users/krantz/git/ClosestPoint/ClosestPoint/lab4/rd400.tsp"));
		BufferedReader br = new BufferedReader(fr);

		// NODE_COORD_SECTION
		// 1 245552.778 817827.778
		// 2 247133.333 810905.556
		// Alt.
		// 2 5.51200e+02 9.96400e+02
		// 3 6.27400e+02 9.96400e+02

		String trimRead;
		String read;
		while (br.ready()) {
			read = br.readLine();
			trimRead = read;
			trimRead = read.replaceAll("\\s+", " ");
			if (trimRead.charAt(0) == ' ') {
				trimRead = trimRead.substring(1);
			}

			String[] nbrs = trimRead.split(" ");

			if (Character.isDigit((nbrs[0].charAt(0)))) {
				int nodeNbr = Integer.parseInt(nbrs[0]);
				String xNbr = nbrs[1].trim();
				double x = Double.valueOf(xNbr);
				String yNbr = nbrs[2].trim();
				double y = Double.valueOf(yNbr);

				points[nodeNbr] = new Point(x, y);

				// points[nodeNbr][0] = nodeNbr;
				// points[nodeNbr][1] = x;
				// points[nodeNbr][2] = y;
				//
				// System.out.println("NodeNbr: " + nodeNbr);
				// System.out.println(x);
				// System.out.println(y);
			} else if (read.contains("DIMENSION")) {

				String[] dim = read.split(":");
				String l = dim[1].trim();
				int length = Integer.parseInt(l);
				points = new Point[length + 1];

			}

		}

		br.close();
	}
}
