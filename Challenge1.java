
public class MaxSubarraySum {

    public static int maxSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] input = {1, -2, 3, 4, -1, 2, -3, 5, -4};
        int result = maxSubarraySum(input);
        System.out.println("Maximum sum of contiguous subarray: " + result);
    }
}

