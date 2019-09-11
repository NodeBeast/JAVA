import java.util.Random;


public class QuickSort{
        //Find pivot (ideally at center)
        //        Median of three - randomly select 3 indices in array and choose the middle value
        
        //set left/right pointers
		
		//LeftItem is first item smaller than piv
		//rightItem is first item larger than piv
		//Switch left/right
        
		//When left < right index  ---> switch pivot
        //recursive call to sub array
        //Continue until array size is 1
        
        public static int medianOfThree(int[] arr){
                int MAX_VALUE = arr.length;
                Random ran = new Random();
                int random = ran.nextInteger(MAX_VALUE);
                return = (arr.length + random)/2;
        }
        public static int partition(int[] arr, int lo, int hi){ //
                        
                        
                
                        return pivIndex;
        }
                
        public static void quickSort(int[] data, int , int piv){
            piv = arr[lo];
            i=lo + 1;
            j = hi+1;
                        
                        quickSort(int[] arr, i, piv);
                        quickSort(int[] arr, j, piv);    
                
                
        }
        
        
}
//==============================