public class StringSearcher{

//Take input string and convert to String[] words
  static String[] strToWordArr(String s){
    String[] wordArr = new String[s.length()];
    int div = 0;
    int end = 0;
    int wordLen;
    int wordNum = 0;
    String tmp;
    for(int i=0; i<s.length();i++){
      System.out.println(s.charAt(i));
      if(s.charAt(i)==' '){
        end = i;
        wordLen = end-div;
        wordArr[wordNum++] = s.substring(div,end);
        System.out.println("wordArr: "+wordArr[wordNum-1]);
        i = end;
        div = end+1;
      }
    }
    return wordArr;
  }

  int shortestMatch(String[] wordArr, boolean[] flags){
    //
  return 0;
  }

  public static void main(String[] args){
    //Output string representation of shortest word with search chars
    String str = "these are words that I am typing because I must have many words to compare with bla hello buddy power monkey! 123$";

    System.out.println("---Hello---");
    String[] words = strToWordArr(str);
  }
};
