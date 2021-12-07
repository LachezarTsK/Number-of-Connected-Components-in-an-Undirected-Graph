
using namespace std;

class Solution {
public:

	int* parent;
	int* rank;
	int numberOfNodes;
	int numberOfConnectedComponents;


	int countComponents(int n, vector<vector<int>>& edges) {

		numberOfNodes = n;
		numberOfConnectedComponents = n;
		rank = new int[numberOfNodes];
		initialize_arrayParent();

		int size = edges.size();
		for (int i = 0; i < size; i++) {
			unionFind(edges[i][0], edges[i][1]);
		}

		return numberOfConnectedComponents;
	}

	void initialize_arrayParent() {
		parent = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes; i++) {
			parent[i] = i;
		}
	}

	int findParent(int index) {
		if (parent[index] != index) {
			parent[index] = findParent(parent[index]);
		}
		return parent[index];
	}

	void unionFind(int indexOne, int indexTwo) {
		indexOne = findParent(indexOne);
		indexTwo = findParent(indexTwo);

		if (indexOne != indexTwo) {
			joinByRank(indexOne, indexTwo);
			numberOfConnectedComponents--;
		}
	}

	void joinByRank(int indexOne, int indexTwo) {
		if (rank[indexOne] > rank[indexTwo]) {
			parent[indexOne] = indexTwo;
		}
		else if (rank[indexOne] > rank[indexTwo]) {
			parent[indexTwo] = indexOne;
		}
		else {
			parent[indexOne] = indexTwo;
			rank[indexTwo]++;
		}
	}
};
