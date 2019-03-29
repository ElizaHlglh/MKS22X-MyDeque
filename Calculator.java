public class Calculator{
    /*Evaluate a postfix expression stored in s.
     *Assume valid postfix notation, separated by spaces.
     */
    public static double eval(String s){
      /*String info = "";
      int previousSpace = 0;
      for (int i = 0; i < s.length(); i++){
        if (s.charAt(i) == ' '){
          if (previousSpace == 0){
            info = s.substring(previousSpace, i);
            //ans.add(Integer.parseInt(info.substring(previousSpace, i)));
            previousSpace = i;
          }
          else{
            info = s.substring(previousSpace+1, i);
            //ans.add(Integer.parseInt(info.substring(previousSpace+1, i)));
            previousSpace = i;
          }
        }
      }
      ans.add(Integer.parseInt(info.substring(previousSpace+1,info.length())));
      return ans;*/
      String[] info = s.split(" ");
      for (int i = 0; i< info.length; i++){
        System.out.println(info[i]);
      }
      return 1.0;
    }
}
