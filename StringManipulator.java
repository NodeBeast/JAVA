public class StringManipulator{
  String[] strStore;


//Method that returns true if substring contained in original
  public static boolean strIsContained(String str, String search){
    return (str.contains(search));//Exact match only
  }

//Method that concatinates & returns two string parameters
  public static String strConcat(String a, String b){
    return new String(a + b);
  }

  public static void main(String[] args){
    String h = "Hello";
    String w = "World";
    String searchStr = "ain";
    String str = "My name is @NodeBeast and my email is abc@hotmail.com";
    String a = "ababa";
    String b = "bababa";
    String alphaNum = "p1xm32f101";

//Formatting Strings
    String c = String.format("I have %d dogs", 2);
    //%d ---> integers (variable or literal)
    //%s ---> strings
    //%f ---> floats
//SubStrings
  //Return substr
  String ss1 = str.substring(1,19); //[1-19]
  String ss2 = str.substring(19);//[0-19]
// charAt()
  //Returns ASCII table int representation of char
  int index = 0;
  int charAtIndex = str.charAt(index);
  //Replace All occurences of __ with __
  String strReplace = str.replaceAll("will", "WILL NOT");
  //Remove all numbers from strings
  String strNoNumbers = alphaNum.replaceAll("[a-zA-Z]", " ");
  //
  String strNoLetters = alphaNum.replaceAll("[^a-zA-Z]", " ");
  String strOnlySymbols = str.replaceAll("[a-zA-Z0-9]",".");
//====Testing===


    //System.out.println(str);
    System.out.println(alphaNum);
    System.out.println(strOnlySymbols);
  }
}
