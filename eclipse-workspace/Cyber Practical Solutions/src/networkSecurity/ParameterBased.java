package networkSecurity;

import java.util.ArrayList;

public class ParameterBased {

	public static void main(String[] args) {
		int[] nums = {132, 135, 120, 139, 19, 117, 112, 285, 135, 103}; 
		ArrayList<Integer> ans = statAnomalyIDS(nums, 0.006);
		prntArryLst(ans);
		
		System.out.println();
		ArrayList<Integer> ans2 = statAnomalyIDS(nums, 0.006, 61.0, 130.0);
		prntArryLst(ans2);
		
		System.out.println();
		ArrayList<Integer> ans4 = statAnomalyIDS(nums, 0.006, 61.0, 130.0, 4);
		prntArryLst(ans4);
		
		System.out.println();
		int[] nums2 = {190, 127, 48, 108, 122, 215, 75, 145, 54, 69}; 
		ArrayList<Integer> ans3 = statAnomalyIDS(nums2, 0.005, 56.0, 115.0);
		prntArryLst(ans3);
		
		// make a statAnomalyIDS which rounds up at every stage based on a parameter
	}

	private static ArrayList<Integer> statAnomalyIDS(int[] sizes, double threshold) {
		ArrayList<Integer> anomalies = new ArrayList<Integer>();
		double mean = getMean(sizes);
		double sd = getStandardDeviation(sizes);

		System.out.println("mean = " + mean);
		for(int i=0; i<sizes.length; i++) {
			double diff = mean - sizes[i];
			//System.out.println((i + 1) + " diff = " + diff);
			
			double diffSigma = diff/sd;
			//System.out.println((i + 1) + " afterSd = " + diffSigma);
			
			double sqrd = diffSigma * diffSigma;
			//System.out.println((i + 1) + " sqrd = " + sqrd);
			
			double beforeExp = sqrd * -0.5;
			
			double prob = Math.exp(beforeExp) * getNormalDist(sd);
			System.out.println((i + 1) + " = " + prob);
			if(prob < threshold) {
				anomalies.add(i + 1);
			}
			//System.out.println();
		}
		return anomalies;
	}
	
	private static ArrayList<Integer> statAnomalyIDS(int[] sizes, double threshold, double sd, double mean) {
		ArrayList<Integer> anomalies = new ArrayList<Integer>();

		for(int i=0; i<sizes.length; i++) {
			double diff = mean - sizes[i];
			//System.out.println((i + 1) + " diff = " + diff);
			
			double diffSigma = diff/sd;
			//System.out.println((i + 1) + " afterSd = " + diffSigma);
			
			double sqrd = diffSigma * diffSigma;
			//System.out.println((i + 1) + " sqrd = " + sqrd);
			
			double beforeExp = sqrd * -0.5;
			
			double prob = Math.exp(beforeExp) * getNormalDist(sd);
			System.out.println((i + 1) + " = " + prob);
			if(prob < threshold) {
				anomalies.add(i + 1);
			}
			//System.out.println();
		}
		return anomalies;
	}
	
	// the rounding may not be completely exact
	private static ArrayList<Integer> statAnomalyIDS(int[] sizes, double threshold, double sd, double mean, int dp) {
		ArrayList<Integer> anomalies = new ArrayList<Integer>();

		System.out.println("mean = " + mean);
		for(int i=0; i<sizes.length; i++) {
			double diff = mean - sizes[i];
			diff = Math.round(diff * Math.pow(10, dp)) / Math.pow(10, dp);
			System.out.println((i + 1) + " diff = " + diff);
			
			double diffSigma = diff/sd;
			diffSigma = Math.round(diffSigma * Math.pow(10, dp)) / Math.pow(10, dp);
			System.out.println((i + 1) + " afterSd = " + diffSigma);
			
			double sqrd = diffSigma * diffSigma;
			sqrd = Math.round(sqrd * Math.pow(10, dp)) / Math.pow(10, dp);
			System.out.println((i + 1) + " sqrd = " + sqrd);
			
			double beforeExp = sqrd * -0.5;
			beforeExp = Math.round(beforeExp * Math.pow(10, dp)) / Math.pow(10, dp);
			
			double prob = Math.exp(beforeExp) * getNormalDist(sd);
			prob = Math.round(prob * Math.pow(10, dp)) / Math.pow(10, dp);
			System.out.println((i + 1) + " = " + prob);
			if(prob < threshold) {
				anomalies.add(i + 1);
			}
			System.out.println();
		}
		return anomalies;
	}
	
	private static double getMean(int[] nums) {
		double sum = 0.0;
		
		for(int i=0; i<nums.length; i++) {
			sum += nums[i];
		}
		System.out.println(sum);
		System.out.println(nums.length);
		return (sum/nums.length);
	}
	
	private static double getNormalDist(double sd) {
		double pi = Math.PI;
		double beforeRoot = 2 * pi * (sd*sd);
		return 1/Math.sqrt(beforeRoot);
	}
	
	private static void prntArryLst(ArrayList<Integer> arryLst) {
		for(int x: arryLst) {
			System.out.print(x + ", ");
		}
		System.out.println();
	}
	
	private static double getStandardDeviation(int[] nums) {
		double sum = 0.0, standardDeviation = 0.0;
        int length = nums.length;

        for(double num : nums) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: nums) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation/length);
	}
}
