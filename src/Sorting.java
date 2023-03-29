import java.util.HashMap;

public class Sorting {
    public int[] sortArray(int[] nums) {
        //mergeSort(nums, 0, nums.length-1);
        //heapSort(nums);
        //quickSort(nums, 0, nums.length-1);
        countingSort(nums);
        return nums;
    }

    //***************************************************************
    //counting sort
    //time: O(n+k), where k is the range of the input array
    //space: O(n), using hashmap to store the array element
    private void countingSort(int[] nums){
        HashMap<Integer, Integer> map = new HashMap<>();
        int min = nums[0], max = nums[0];

        for(int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int index = 0;
        for(int i = min; i <= max; i++){
            while(map.getOrDefault(i, 0) > 0){
                nums[index] = i;
                index++;
                map.put(i, map.get(i)-1);
            }
        }
    }

    //**************************************************************
    //quick sort
    //time: Average - O(nlgn), Worst: O(n^2), space: O(lgn)
    private void quickSort(int[] nums, int start, int end){
        if(start < end){
            int p = partition(nums, start, end);

            quickSort(nums, start, p - 1);
            quickSort(nums, p + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end){
        int pivot = nums[end]; //using the last element as pivot;

        int i = start - 1;

        for(int j = start; j <= end - 1; j++){
            if(nums[j] <= pivot){
                i++;
                swap(nums, j, i);
            }
        }
        swap(nums, i+1, end);
        return i+1;
    }

    //****************************************************************
    //heap soft
    //time:O(nlgn), space:O(lgn)
    private void heapSort(int[] nums){
        int n = nums.length;

        //build max heap
        for(int i = n / 2 - 1; i >= 0; i--){
            heapify(nums, n, i);
        }

        //sorting
        for(int i = n - 1; i >= 0; i--){
            swap(nums, 0, i); //swap the first num to the end
            heapify(nums, i, 0); //heapify the new first num
        }
    }
    private void heapify(int[] nums, int n, int i){
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < n && nums[left] > nums[largest]){
            largest = left;
        }
        if(right < n && nums[right] > nums[largest]){
            largest = right;
        }

        if(i != largest){
            swap(nums, i, largest);
            heapify(nums, n, largest);
        }
    }
    private void swap(int[] nums, int int1, int int2){
        int temp = nums[int1];
        nums[int1] = nums[int2];
        nums[int2] = temp;
    }

    //**************************************************************
    //merge sort
    //time: O(nlgn), space: O(n)
    private void mergeSort(int[] nums, int start, int end){
        if(start >= end)
            return;

        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int start, int mid, int end){

        int[] left = new int[mid - start + 1];
        int[] right = new int[end - mid];

        for(int i = 0; i < left.length; i++){
            left[i] = nums[start + i];
        }
        for(int j = 0; j < right.length; j++){
            right[j] = nums[mid + 1 + j];
        }

        int i = 0, j = 0, k = start;

        while(i < left.length && j < right.length){
            if(left[i] <= right[j]){
                nums[k] = left[i];
                i++;
            }
            else{
                nums[k] = right[j];
                j++;
            }
            k++;
        }

        while(i < left.length){
            nums[k] = left[i];
            k++;
            i++;
        }
        while(j < right.length){
            nums[k] = right[j];
            k++;
            j++;
        }
    }
}
