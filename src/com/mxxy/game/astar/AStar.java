package com.mxxy.game.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AStar implements Searcher {
	private int width;
	private int height;
	private AStarNode[] nodes;

	protected List<AStarNode> constructPath(AStarNode node) {
		LinkedList path = new LinkedList();
		while (node.pathParent != null) {
			path.addFirst(node);
			node = node.pathParent;
		}
		return path;
	}

	public List<AStarNode> findPath(AStarNode startNode, AStarNode goalNode) {
		LinkedList openList = new LinkedList();
		Set closedList = new HashSet();
		startNode.costFromStart = 0;
		startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);
		startNode.pathParent = null;
		openList.add(startNode);
		while (!openList.isEmpty()) {
			AStarNode node = (AStarNode) openList.removeFirst();
			List neighbors = node.getNeighbors();
			for (int i = 0; i < neighbors.size(); i++) {
				AStarNode neighborNode = (AStarNode) neighbors.get(i);
				boolean isOpen = openList.contains(neighborNode);
				boolean isClosed = closedList.contains(neighborNode);
				int costFromStart = node.costFromStart + node.getCost(neighborNode);
				if (((isOpen) || (isClosed)) && (costFromStart >= neighborNode.costFromStart)) {
					continue;
				}
				neighborNode.pathParent = node;
				neighborNode.costFromStart = costFromStart;
				if (node != goalNode) {
					if (isClosed) {
						closedList.remove(neighborNode);
					}
					if (!isOpen) {
						openList.add(neighborNode);
					}
				}
			}
			closedList.add(node);
		}
		return constructPath(goalNode);
	}

	public static void main(String[] args) {
		AStarNode nodeA = new AStarNode("A", 0, 10);
		AStarNode nodeB = new AStarNode("B", 5, 15);
		AStarNode nodeC = new AStarNode("C", 10, 20);
		AStarNode nodeD = new AStarNode("D", 15, 15);
		AStarNode nodeE = new AStarNode("E", 20, 10);
		AStarNode nodeF = new AStarNode("F", 15, 5);
		AStarNode nodeG = new AStarNode("G", 10, 0);
		AStarNode nodeH = new AStarNode("H", 5, 5);

		// nodeA.neighbors.add(nodeC);
		// nodeA.neighbors.add(nodeE);
		//
		// nodeC.neighbors.add(nodeA);
		// nodeC.neighbors.add(nodeE);
		//
		// nodeE.neighbors.add(nodeA);
		// nodeE.neighbors.add(nodeC);
		// nodeE.neighbors.add(nodeF);

		nodeF.neighbors.add(nodeA);
		nodeE.neighbors.add(nodeC);
		nodeA.neighbors.add(nodeF);
		// nodeF.neighbors.add(nodeE);

		AStar bfs = new AStar();
		System.out.println("From A to F: " + bfs.findPath(nodeA, nodeF).toString());
		// System.out.println("From C to F: " + bfs.findPath(nodeC, nodeF).toString());
		// System.out.println("From F to C: " + bfs.findPath(nodeF, nodeC));
	}

	public void init(int width, int height, byte[] maskdata) {
		this.width = width;
		this.height = height;
		this.nodes = new AStarNode[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (maskdata[(x + y * width)] > 0) {
					this.nodes[(x + (height - y - 1) * width)] = new AStarNode(x, height - y - 1);
				}
			}

		}

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				AStarNode node = getNode(x, y);
				if (node == null)
					continue;
				AStarNode n = getNode(x - 1, y);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x + 1, y);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x, y - 1);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x, y + 1);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x - 1, y - 1);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x + 1, y - 1);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x + 1, y + 1);
				if (n != null) {
					node.neighbors.add(n);
				}

				n = getNode(x - 1, y + 1);
				if (n != null)
					node.neighbors.add(n);
			}
	}

	public AStarNode getNode(int x, int y) {
		try {
			return this.nodes[(x + y * this.width)];
		} catch (Exception localException) {
		}
		return null;
	}

	public AStarNode getNearstNode(int x, int y) {
		AStarNode node = null;
		try {
			node = this.nodes[(x + y * this.width)];
			while (node == null)
				;
		} catch (Exception localException) {
		}
		return node;
	}

	public boolean pass(int x, int y) {
		return this.nodes[(x + y * this.width)] != null;
	}

	public List<Point> findPath(int x1, int y1, int x2, int y2) {
		AStarNode startNode = getNode(x1, y1);
		AStarNode goalNode = getNode(x2, y2);
		if (goalNode == null || startNode == null) {
			return null;
		}
		List<AStarNode> nodepath = findPath(startNode, goalNode);
		List path = new ArrayList(nodepath.size());
		for (AStarNode node : nodepath) {
			path.add(new Point(node.x, node.y));
		}
		return path;
	}
}