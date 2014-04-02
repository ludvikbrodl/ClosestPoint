import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

	// [id][x - y]
	double[][] points = new double[4][3];

	public static void main(String[] args) {
		new main();
	}

	public main() {
		buildMap();
		findClosest();

	}

	// 4 8.75100e+02 1.13610e+03
	public void parser(String[] arg) throws IOException {
		FileReader fr = new FileReader(new File(arg[0]));
		BufferedReader br = new BufferedReader(fr);
		boolean ok = true;
		String line = br.readLine();
		String[] nbrs = line.split(" ");
		
		while(ok){
		 try { 
		        Double id = Double.parseDouble(nbrs[0]); 
		        ok = false;
		    } catch(NumberFormatException e) { 
		    	
		    }
		}
		while (br.ready()) {
				
		}
		
		br.close();
	}

	private void findClosest() {
		double shortest = Double.MAX_VALUE;

		for (int i = 0; i < points.length; i++) {
			double id = points[i][0];
			double x = points[i][1];
			double y = points[i][2];
			for (int j = 0; j < points.length; j++) {
				if (i != j) {
					double otherId = points[j][0];
					double otherX = points[j][1];
					double otherY = points[j][2];
					System.out.println("ID: " + id + " X: " + x + " Y: " + y);
					System.out.println("ID: " + otherId + "X: " + otherX
							+ " Y: " + otherY);

					double l = Math.hypot((otherX - x), (otherY - y));
					System.out.println("Hypot: " + l);
					if (l < shortest) {
						shortest = l;
					}
				}
			}
			System.out.println();
		}
		System.out.println(shortest);

	}

	private void buildMap() {
		// id 0-1 closest
		points[0][0] = 0;
		points[1][0] = 1;
		points[2][0] = 2;
		points[3][0] = 3;

		points[0][1] = 0;
		points[1][1] = 5;
		points[2][1] = 6;
		points[3][1] = 7;

		points[0][2] = 0;
		points[1][2] = 4;
		points[2][2] = 8;
		points[3][2] = 9;
	}

}
