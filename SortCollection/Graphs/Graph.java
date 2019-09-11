import java.util.Arrays;

public class Graph {
	
	
	Vertex[] Varr;
	
	int[][] weightArr;
	
	public Graph(int n) {
		weightArr = new int[n][n];
		Arrays.fill(weightArr, 0);

	}


	
	public void setEdge(int w){
		//Zero weight means no link btw V1 & V2
		if(w == 0){
			System.out.println("Weight Cannot be zero");
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
