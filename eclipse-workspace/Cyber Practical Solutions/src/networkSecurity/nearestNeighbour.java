package networkSecurity;

import java.util.ArrayList;

public class nearestNeighbour {

	public static void main(String[] args) {
		int[] nums = {132, 135, 120, 139, 19, 117, 112, 285, 135, 103}; 
		int[][] ans = getAllNearestNeighbour(nums, 2);
		prnt2dArry(ans);
		
		int[] ans2 = getNearestNeighbour(nums, 3, 1);
		System.out.println(prntArry(ans2));
		
		ArrayList<Integer[]> ans3 = getAnomalyPoints(nums, 3, 0.3);
		for(Integer[] x: ans3) {
			System.out.print("[" + prntArry(x) + "], ");
		}
		System.out.println();
		
		int[] nums2 = {190, 127, 48, 108, 122, 190, 75, 145, 54, 69}; 
		int[][] ans4 = getAllNearestNeighbour(nums2, 3);
		prnt2dArry(ans4);
	}
	
	/**
	 * 
	 * @param nums
	 * @param k
	 * @param threshold
	 * @return - an array list in the format [id, size of nearest neighbour, diff]
	 */
	private static ArrayList<Integer[]> getAnomalyPoints(int[] nums, int k, double threshold) {
		int[][] distances = getAllNearestNeighbour(nums, k);
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		int topDists = (int) Math.round(distances.length * threshold);
		bubbleSort(distances);
		
		for(int i=1; i<=topDists; i++) {
			Integer[] tempResult = {distances[distances.length-i][0], distances[distances.length-i][1], distances[distances.length-i][2]};
			result.add(tempResult);
		}
		return result;
	}
	
	/**
	 * 
	 * @param nums
	 * @param k
	 * @return - [[id, the nearest neighbour size, difference]]
	 */
	private static int[][] getAllNearestNeighbour(int[] nums, int k) {
		int[][] result = new int[nums.length][3];
		for(int i=0; i<nums.length; i++) {
			result[i] = getNearestNeighbour(nums, k, i);
 		}
		return result;
	}
	
	/**
	 * 
	 * @param nums
	 * @param k
	 * @param pos
	 * @return [id of orig, size of kth neighbour, difference]
	 */
	private static int[] getNearestNeighbour(int[] nums, int k, int pos) {
		int[][] allDiffs = new int[nums.length][3];
		
		allDiffs = getDifferences(nums, pos);
		bubbleSort(allDiffs);
		return allDiffs[k];
	}
	
	/**
	 * 
	 * @param nums
	 * @param pos
	 * @return [[id of orig, size of the other packet, diff]]
	 */
	private static int[][] getDifferences(int[] nums, int pos) {
		int[][] result = new int[nums.length][3];
		for(int i=0; i<nums.length; i++) {
			result[i][0] = pos+1;
			result[i][1] = nums[i];
			if(i != pos) {
				result[i][2] = Math.abs(nums[i] - nums[pos]);
			} else {
				
				result[i][2] = -1;
			}
		}
		return result;
	}
	
	private static void bubbleSort(int[][] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j][2] > arr[j + 1][2]) {
                    // swap arr[j+1] and arr[j]
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
	
	public static void prnt2dArry(int[][] arry) {
		for(int[] x: arry) {
			String oneDArry = prntArry(x);
			System.out.print("[" + oneDArry + "], ");
		}
		System.out.println();
	}
	
	public static String prntArry(int[] k) {
		String result = "";
		for(int i: k) {
			result += i + ", ";
		}
		return result;
    }
	
	public static String prntArry(Integer[] k) {
		String result = "";
		for(int i: k) {
			result += i + ", ";
		}
		return result;
    }
}