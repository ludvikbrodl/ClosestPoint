import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
public class main {

	static Point[] pointsX, pointsY;

	public static void main(String[] args) throws IOException {
		parser(args);
		System.out.println(args[0] + " " + pointsX.length + " " + closestPair());

	}

	private static double closestPair() {
		//sort the points, tested - works
		Arrays.sort(pointsY, new ComparatorY());
		Arrays.sort(pointsX);
		
		double closest = closestPairRec(pointsX, pointsY);
		return closest;
	}

	private static double closestPairRec(Point[] pointsX, Point[] pointsY) {
		if (pointsX.length < 3) {
			return findClosestBrute(pointsX);
		}
		Point[] leftX = new Point[pointsX.length/2]; 
		Point[] rightX = new Point[pointsX.length-(pointsX.length/2)]; 
		System.arraycopy(pointsX, 0, leftX, 0, pointsX.length/2);
		System.arraycopy(pointsX, (pointsX.length/2), rightX, 0, pointsX.length-(pointsX.length/2));
		
		double xMiddle = pointsX[pointsX.length/2].x;

		Point[] leftY = new Point[leftX.length];
		Point[] rightY = new Point[rightX.length];
		System.arraycopy(leftX, 0, leftY, 0, leftX.length);
		System.arraycopy(rightX, 0, rightY, 0, rightX.length);
		
		double distanceLeft = closestPairRec(leftX, leftY);
		double distanceRight = closestPairRec(rightX, rightY);
		double minDistance = Math.min(distanceLeft, distanceRight);
		
		ArrayList<Point> middlePointsArray = new ArrayList<Point>();
		for (int i = 0; i < pointsY.length; i++) {
			if (Math.abs(xMiddle - pointsY[i].x) < minDistance) {
				middlePointsArray.add(pointsY[i]);
			}
		}
		Point[] middlePoints = new Point[middlePointsArray.size()];
		for (int i = 0; i < middlePoints.length; i++) {
			middlePoints[i] = middlePointsArray.get(i);
		}
		double distanceMiddle = findClosestBrute(middlePoints);
		minDistance = Math.min(minDistance, distanceMiddle);
		return minDistance;
	}

	private static double findClosestBrute(Point[] p) {
		double shortest = Double.MAX_VALUE;

		for (int i = 0; i < p.length; i++) {
			double x = p[i].x;
			double y = p[i].y;
			for (int j = 0; j < p.length; j++) {
				if (i != j) {
					double otherX = p[j].x;
					double otherY = p[j].y;
					double l = Math.hypot((otherX - x), (otherY - y));
					if (l < shortest) {
						shortest = l;
					}
				}
			}
		}
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
		
		@Override
		public String toString() {
			return x + " " + y;
		}

	}

	public static void parser(String[] arg) throws IOException {
		long st = System.currentTimeMillis();
		FileReader fr = new FileReader(new File(arg[0]));
		BufferedReader br = new BufferedReader(fr);

		String trimRead;
		String read;
		while (br.ready()) {

			read = br.readLine();
			trimRead = read.trim();
			
			String[] nbrs = trimRead.split("\\s+");
			
			if (!read.equals("") && Character.isDigit((nbrs[0].charAt(0)))) {
				
				int nodeNbr = Integer.parseInt(nbrs[0]);
				String xNbr = nbrs[1].trim();
				double x = Double.valueOf(xNbr);
				String yNbr = nbrs[2].trim();
				double y = Double.valueOf(yNbr);

				pointsX[nodeNbr - 1] = new Point(x, y);
				pointsY[nodeNbr - 1] = new Point(x, y);

			} else if (read.contains("DIMENSION")) {
				String[] dim = read.split(":");
				String l = dim[1].trim();
				int length = Integer.parseInt(l);
				pointsX = new Point[length];
				pointsY = new Point[length];

			}

		}
		br.close();
		long end = System.currentTimeMillis();
//		System.out.println("Parsiong time:  "  + (end-st));

	}

	static class ComparatorY implements Comparator<Point> {

		@Override
		public int compare(Point o1, Point o2) {
			
			double y1 = o1.y;
			double y2 = o2.y;
			if (y1 > y2) {
				return 1;
			} else if (y1 == y2) {
				return 0;
			} else {
				return -1;
			}
		}

	}
}
