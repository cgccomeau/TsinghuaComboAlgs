import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        int lower = scan.nextInt();
        int upper = scan.nextInt();
        long[] p = new long[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
            sum += arr[i];
            p[i] = sum;
        }
        System.out.println(mergeAndCount(p, 0, p.length - 1, lower, upper));
    }

    public static int mergeAndCount(long[] nums, int left, int right, int lower, int upper){
        if(left>=right) return 0;
        int count = 0;
        int mid = left+(right-left)/2;
        count+=mergeAndCount(nums, left, mid, lower, upper)+mergeAndCount(nums, mid+1, right, lower, upper);
        for(int i=left, k=mid+1, j=mid+1; i<=mid; i++){
            while(k<=right && nums[k]-nums[i]<lower) k++;
            while(j<=right && nums[j]-nums[i]<=upper) j++;
            count+=(j-k);
        }
        merge(nums, left, mid, right);
        return count;
    }

    public static void merge(long[] nums, int left, int mid,  int right){
        long[] leftA = new long[mid+1-left];
        long[] rightA = new long[right-mid];
        for(int i=left; i<=mid; i++){
            leftA[i-left] = nums[i];
        }
        for(int i=mid+1; i<=right; i++){
            rightA[i-mid-1] = nums[i];
        }
        int p1 = 0;
        int p2 = 0;
        for(int i=left; i<=right; i++){
            if(p1<leftA.length && p2<rightA.length){
                if(leftA[p1]<=rightA[p2]){
                    nums[i] = leftA[p1++];
                }
                else{
                    nums[i] = rightA[p2++];
                }
            }
            else if(p1<leftA.length){
                nums[i] = leftA[p1++];
            }
            else{
                nums[i] = rightA[p2++];
            }
        }
    }
}