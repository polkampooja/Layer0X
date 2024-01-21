class MaxSubarraySum{
    static long maxSubarraySum(int arr[]){
        long maxi = Long.MIN_VALUE;
        int n = arr.length;
        long sum = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
            if(sum > maxi){
                maxi = sum;
            }

            if(sum < 0){sum = 0;}
        }
     
        return maxi;
    }

    public static void main(String[] args) {
        int[] input = {1, -2, 3, 4, -1, 2, -3, 5, -4};

        long result = maxSubarraySum(input);
        System.out.println("Maximum sum of contiguous subarray: " + result);
    }
}

