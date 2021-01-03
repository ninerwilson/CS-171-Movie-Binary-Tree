/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Niner Wilson
*/

import java.util.regex.Pattern;

public class BSTIndex {

		private class Node {
			private String key;
			private MovieInfo data;
			private Node left, right;

			public Node(MovieInfo info) {
				data = info;
				key = data.shortTitle;
			}
		}

		private Node root;

		public BSTIndex() {
			root = null;
		}

		// --------- [DO NOT MODIFY!] public BST methods  --------- //
		/* Important: Notice that each public method here calls another private method while passing it a reference to the tree root. This is a common way of structuring BST functions such that external client code will not have direct access to the tree root. You will be implementing the code in the private methods, not the public ones. */

		/* Calculate and return the height of the current tree. */
		public int calcHeight(){
				return calcNodeHeight(this.root);
		}

		/* Insert the given data element into the proper place in the BST structure. */
		public void insertMovie(MovieInfo data) {
			root = insertMovie(data, this.root);
		}

		/* Find and return the data element (i.e. the MovieInfo object)
		of the node where the movie's shortTitle matches the given key.
		Return null if the movie is not found. */
		public MovieInfo findMovie(String key) {
			return findMovie(this.root, key);
		}

		/* Print out all movies in the database whose shortTitle begins with
		the passed prefix string. If no movies match the given prefix, nothing
		will be printed. The order of printing does not matter, but make sure
		to print each match in a separate line. */
		public void printMoviesPrefix(String prefix) {
			printMoviesPrefix(this.root, prefix);
		}
		// ----------------- end of public methods ------------------ //


		//Private BST methods
		private int calcNodeHeight(Node t) {
			if(t == null) return 0; //Base case of an empty tree. Has a height of zero. T is the root!

			return (Math.max(calcNodeHeight(t.left), calcNodeHeight(t.right)) + 1); //The height of a binary tree is the height of the root's largest subtree + 1 for the root
		}

		private Node insertMovie(MovieInfo data, Node t) { //T is the root passed from the caller
			//Base case!!
			if (t == null) { //Checks for empty tree (or in the recursion, the end of the tree)
				return (new Node(data)); //establishes a new node!
			} else {
				//Must makes sure the data is not a duplicate
				if (findMovie(t, data.shortTitle) != null) {
					return t; //Returns to caller
				}

				//check if data is alphabetically before the current node (starting with the root), if it is, recure down the left side of the tree
				if (data.shortTitle.compareTo(t.data.shortTitle) < 0 ) {
					t.left = insertMovie(data, t.left);
				} else {
				//it must be alphabetically after the root so we recure down the right side of the root
					t.right = insertMovie(data, t.right);
				}

				return t; //Return the pointer (pointed at the root)

			}
		}

		private MovieInfo findMovie(Node t, String key) {
			if (t == null) { //Base case of empty tree or couldn't find the key
				return null;
			} else if (t.data.shortTitle.equals(key)) return t.data;

				//If the key is alphabetically before the current node, recure down the left side of the tree
				if (key.compareTo(t.data.shortTitle) < 0) {
					return findMovie(t.left, key);
				} else { //root is alphabetically before the key
					return findMovie(t.right, key);
				}
			}


		private void printMoviesPrefix(Node t, String prefix) {

			//Base case of the pointer pointing at 'nothing'
			if (t == null) {
				return;
				//Base case of the beginning of the shortTitle at the current node being equal to the prefix passed but without the * character
				//The math.min function solves the corner case of the prefix being longer than the movie short title
			} else if (prefix.substring(0,prefix.length()-1).equals(t.data.shortTitle.substring(0,(Math.min(prefix.length()-1,t.data.shortTitle.length()))))) {
				System.out.println(t.data.shortTitle);
			}
			//Recursively go through everything to the left
			printMoviesPrefix(t.left, prefix);
			//Recursively go through everything to the right
			printMoviesPrefix(t.right, prefix);
			}

}
