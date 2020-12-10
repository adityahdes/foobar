public class Solution {
	public static int solution(int[][] map) {
		int height = map.length;
		int width = map[0].length;

		int vertex = height * width;

		Graph graph = new Graph(vertex, height, width);

		int n = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (j < width - 1)
					graph.addEdge(n, n + 1, Math.max(map[i][j], map[i][j + 1]));
				if (i < height - 1)
					graph.addEdge(n, n + width, Math.max(map[i][j], map[i + 1][j]));
				n++;
			}
		}
		return graph.dijkstra_GetMinDistances(0);
	}


	public static class Graph {
		int vertices;
		int[][] matrix;
		int height;
		int width;

		public Graph(int vertex, int h, int w) {
			this.vertices = vertex;
			matrix = new int[vertex][vertex];
			this.height = h;
			this.width = w;
		}

		void addEdge(int source, int destination, int wt) {
			//add edge
			int weight = 0;

			if (wt == 1)
			{
				weight = 1000;
				matrix[source][destination] += weight;

				//add back edge for undirected graph
				matrix[destination][source] += weight;
			}
			else if (wt == 0)
			{
				weight = 1;

				matrix[source][destination] = weight;

				//add back edge for undirected graph
				matrix[destination][source] = weight;
			}
			else if (wt == -1)
			{
				weight = -1000;
				matrix[source][destination] += weight;
				if (matrix[source][destination] == 0)
					matrix[source][destination] =1;
				//add back edge for undirected graph
				matrix[destination][source] += weight;
				if (matrix[source][destination] == 0)
					matrix[source][destination] =1;
			}

		}

		//get the vertex with minimum distance which is not included in SPT
		int getMinimumVertex(boolean [] mst, int [] key){
			int minKey = Integer.MAX_VALUE;
			int vertex = -1;
			for (int i = 0; i <vertices ; i++) {
				if(!mst[i] && minKey>key[i]){
					minKey = key[i];
					vertex = i;
				}
			}
			return vertex;
		}

		int dijkstra_GetMinDistances(int sourceVertex)
		{
			int [][] distance = new int[vertices][vertices];
			int INFINITY = Integer.MAX_VALUE;

			//Initialize all the distance to infinity
			for (int i = 0; i <vertices ; i++)
			{
				for (int j = 0; j <vertices ; j++)
				{
					distance[i][j] = INFINITY;
				}
			}

			distance[sourceVertex][sourceVertex] = 1;
			dijkstra_GetMinDistances(distance[sourceVertex]);

			for (int j = 0; j < vertices -1 ; j++)
			{
				if (distance[sourceVertex][j] > 1000)
				{
					if (j < vertices-1)
						addEdge(j, j+1, -1);
					if (j < vertices - width)
						addEdge(j, j+ width, -1);

					for (int k = 0; k < vertices; k++)
					{
						if (k == j)
						{
							distance[j][k] = distance[sourceVertex][k] - 999;
						}
						else
						{
							distance[j][k] = distance[sourceVertex][k];
						}
					}

					dijkstra_GetMinDistances(distance[j]);
					if (j < vertices-1)
						addEdge(j, j+1, 1);
					if (j < vertices - width)
						addEdge(j, j+ width, 1);
				}
				else
				{
					distance[j] = distance[sourceVertex];
				}
			}

			int minDist = INFINITY;

			for (int i = 0; i <vertices ; i++)
			{
				if (minDist > distance[i][vertices-1])
					minDist = distance[i][vertices-1];
			}
			return minDist;
		}

		public void dijkstra_GetMinDistances(int [] distance)
		{
			boolean[] spt = new boolean[vertices];

			//start from the vertex 0
			//distance[sourceVertex] = 1;

			//create SPT
			for (int i = 0; i <vertices ; i++) {

				//get the vertex with the minimum distance
				int vertex_U = getMinimumVertex(spt, distance);

				//include this vertex in SPT
				spt[vertex_U] = true;

				//iterate through all the adjacent vertices of above vertex and update the keys
				for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
					//check of the edge between vertex_U and vertex_V
					if(matrix[vertex_U][vertex_V]>0){
						//check if this vertex 'vertex_V' already in spt and
						// if distance[vertex_V]!=Infinity

						if(!spt[vertex_V] && matrix[vertex_U][vertex_V]!=Integer.MAX_VALUE){
							//check if distance needs an update or not
							//means check total weight from source to vertex_V is less than
							//the current distance value, if yes then update the distance

							int newKey =  matrix[vertex_U][vertex_V] + distance[vertex_U];
							if(newKey<distance[vertex_V])
								distance[vertex_V] = newKey;
						}
					}
				}
			}
		}
	}
}