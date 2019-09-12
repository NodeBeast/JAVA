public class StringSearcher{
//
//Take input string and convert to String[] words
  static String[] strToWordArr(String s){
    String[] wordArr = new String[s.length()];
    int div = 0;
    int end = 0;
    int wordLen;
    int wordNum = 0;
    String tmp;
    for(int i=0; i<s.length();i++){
      //System.out.println(s.charAt(i));
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

  void matchingStrings(String[] wordArr, String sChars){
//sChars is a string of the 3 search characters expected
    boolean a = false;
    boolean b = false;
    boolean c = false;
    for(int i=0; i<wordArr.length;i++){
      //Iterate through wordArr[]
      for(int letter=0;letter<wordArr[i].length();letter++){
        //Iterate through words, letter by letter
        if(wordArr[i].contains(sChars.charAt(0)){
          a = true;
        }
        if(wordArr[i].contains(sChars.charAt(0)){
          b = true;
        }
        if(wordArr[i].contains(sChars.charAt(0)){
          c = true;
        }
        System.out.println(wordArr[i]+", at i="+i+" a,b,c: "+
        a + ", "+b+", "+c);
      }
    }
  return 0;
  }

  public static void main(String[] args){
    //Output string representation of shortest word with search chars
    String str = "these are words that I am typing because I must have many words to compare with bla hello buddy power monkey! 123$";

    System.out.println("---Hello---");
    String[] words = strToWordArr(str);
  }
};
