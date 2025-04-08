
class Solution {

    int[] parent;
    int[] rank;
    int numberOfNodes;
    int numberOfConnectedComponents;

    public int countComponents(int n, int[][] edges) {
        numberOfNodes = n;
        numberOfConnectedComponents = n;

        parent = IntStream.range(0, numberOfNodes).toArray();
        rank = new int[numberOfNodes];
        Arrays.fill(rank, 1);

        int size = edges.length;
        for (int i = 0; i < size; i++) {
            unionFind(edges[i][0], edges[i][1]);
        }

        return numberOfConnectedComponents;
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
        if (rank[indexOne] >= rank[indexTwo]) {
            parent[indexTwo] = indexOne;
            rank[indexOne] += rank[indexTwo];
        } else if (rank[indexOne] < rank[indexTwo]) {
            parent[indexOne] = indexTwo;
            rank[indexTwo] += rank[indexOne];
        }
    }
}
