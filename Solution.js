
/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var countComponents = function (n, edges) {
    const unionFind = new ConnectedComponents(n);
    unionFind.searchForConnectedComponents(edges);
    return unionFind.numberOfConnectedComponents;

};

class ConnectedComponents {

    /**
     * @param {number} n
     */
    constructor(n) {
        this.numberOfNodes = n;
        this.numberOfConnectedComponents = n;
        this.rank = new Array(n).fill(0);
        this.initialize_arrayParent();
    }

    /**
     * @param {number[][]} edges
     */
    searchForConnectedComponents(edges) {
        let size = edges.length;
        for (let i = 0; i < size; i++) {
            this.unionFind(edges[i][0], edges[i][1]);
        }
    }

    initialize_arrayParent() {
        this.parent = [];
        for (let i = 0; i < this.numberOfNodes; i++) {
            this.parent[i] = i;
        }
    }

    /**
     * @param {number} index
     * @return {number}
     */
    findParent(index) {
        if (this.parent[index] !== index) {
            this.parent[index] = this.findParent(this.parent[index]);
        }
        return this.parent[index];
    }

    /**
     * @param {number} indexOne
     * @param {number} indexTwo
     */
    unionFind(indexOne, indexTwo) {
        indexOne = this.findParent(indexOne);
        indexTwo = this.findParent(indexTwo);

        if (indexOne !== indexTwo) {
            this.joinByRank(indexOne, indexTwo);
            this.numberOfConnectedComponents--;
        }
    }

    /**
     * @param {number} indexOne
     * @param {number} indexTwo
     */
    joinByRank(indexOne, indexTwo) {
        if (this.rank[indexOne] > this.rank[indexTwo]) {
            this.parent[indexOne] = indexTwo;
        } else if (this.rank[indexOne] > this.rank[indexTwo]) {
            this.parent[indexTwo] = indexOne;
        } else {
            this.parent[indexOne] = indexTwo;
            this.rank[indexTwo]++;
        }
    }
}
