
public class Solution {

    int[] parent;
    int[] rank;
    int numberOfNodes;
    int numberOfConnectedComponents;

    public int countComponents(int n, int[][] edges) {
        numberOfNodes = n;
        numberOfConnectedComponents = n;
        rank = new int[numberOfNodes];
        initialize_arrayParent();

        int size = edges.length;
        for (int i = 0; i < size; i++) {
            unionFind(edges[i][0], edges[i][1]);
        }

        return numberOfConnectedComponents;
    }

    public void initialize_arrayParent() {
        parent = new int[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            parent[i] = i;
        }
    }

    public int findParent(int index) {
        if (parent[index] != index) {
            parent[index] = findParent(parent[index]);
        }
        return parent[index];
    }

    public void unionFind(int indexOne, int indexTwo) {
        indexOne = findParent(indexOne);
        indexTwo = findParent(indexTwo);

        if (indexOne != indexTwo) {
            joinByRank(indexOne, indexTwo);
            numberOfConnectedComponents--;
        }
    }

    public void joinByRank(int indexOne, int indexTwo) {
        if (rank[indexOne] > rank[indexTwo]) {
            parent[indexOne] = indexTwo;
        } else if (rank[indexOne] > rank[indexTwo]) {
            parent[indexTwo] = indexOne;
        } else {
            parent[indexOne] = indexTwo;
            rank[indexTwo]++;
        }
    }
}
