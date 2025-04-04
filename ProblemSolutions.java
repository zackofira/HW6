
/******************************************************************
 *
 *   Zach Kofira / Section 002
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {

      //
      // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME / SECTION # ABOVE
      //
      //stores the top 2 on the max heap
      int top = 0;
      int second = 0;

      //initialize a max heap with the boulders inserted
      PriorityQueue<Integer> holder = new PriorityQueue<Integer>(Collections.reverseOrder());
      for(int i = 0; i < boulders.length; i++) {
          holder.add(boulders[i]);
      }

      //compare and destroy boulders until there's not enough to compare
      while(holder.size() > 1) {
          top = holder.poll();
          second = holder.poll();
          top = top - second;
          holder.add(top);
      }

      //if there's none left, return becomes 0, otherwise return becomes the final boulder
      if( holder.isEmpty()) {
          top = 0;
      } else {
          top = holder.poll();
      }

      //return the value
      return top;
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        //
        //  YOUR CODE GOES HERE
        //

        //arrayList to hold duplicates
        ArrayList<String> finish = new ArrayList<String>(0);

        //temp string for swapping
        String temp = "";

        //reverse bubble sort because n is small, so it works fine.  Based on alphabetical sorting
        for (int i = 0; i < input.size() - 1; i++) {
            for (int j = 0; j < input.size() - i - 1; j++) {
                if (input.get(j).compareTo(input.get(j + 1)) > 0) {
                    temp = input.get(j + 1);
                    input.set(j + 1, input.get(j));
                    input.set(j, temp);
                }
            }
        }

        //adding a buffer at the end to avoid going out of bounds
        input.add(null);

        //traverse from 2nd value, a string is selected as a duplicate if the previous is equal and the next is something different
        //this avoids adding duplicates to the duplicate list
        for (int i = 1; i < input.size() - 1; i++) {
            if (!input.get(i).equals(null) && !input.get(i).equals(input.get(i+1)) && input.get(i).equals(input.get(i - 1))) {
                finish.add(input.get(i));
            }
        }



        return finish;  // Make sure result is sorted in ascending order

    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {

        //
        //  YOUR CODE GOES HERE
        //
        //create arrayList for holding return pairs and hashset for used values
        ArrayList<String> finish = new ArrayList<String>(0);
        HashSet<Integer> blacklist = new HashSet<Integer>();

        //iterate through each combination, if there is a pair add the used number to the blacklist and save the pair
        for(int i = 0; i < input.length; i++) {
            for(int j = i + 1; j < input.length; j++) {
                if(input[i] + input[j] == k && !blacklist.contains(input[i])) {

                    //make sure values are ordered
                    if(input[i] < input[j]) {
                        finish.add("(" + input[i] + ", " + input[j] + ")");
                    } else {
                        finish.add("(" + input[j] + ", " + input[i] + ")");
                    }

                    blacklist.add(input[i]);
                    blacklist.add(input[j]);
                }
            }
        }

        //sort to make sure of order
        Collections.sort(finish);

        //return final list
        return finish;  // Make sure returned lists is sorted as indicated above
    }
}