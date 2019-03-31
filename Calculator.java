public class Calculator{
    /*Evaluate a postfix expression stored in s.
     *Assume valid postfix notation, separated by spaces.
     */
    public static double eval(String s){
      String[] info = s.split(" ");
      MyDeque number = new MyDeque<Double>();
      double result;
      for (int i = 0; i < info.length; i++){
        if (info[i].equals("+")){
          //result = (number.removeLast() + 0.0) + (number.removeLast() + 0.0);
          /*double last1 = (double)number.removeLast();
          System.out.println("number get before Last == " + number.getLast());
          System.out.println("Last1 == " + last1);
          System.out.println("number currently == " + number);
          System.out.println("number get first == " + number.getFirst());
          System.out.println("number get Last == " + number.getLast());*/
          result = (double) number.removeLast() + (double) number.removeLast();
          number.addLast(result);
        }
        else if (info[i] == "-"){
          result = (double) number.removeLast() - (double) number.removeLast();
          number.addLast(result);
        }
        else if (info[i] == "*"){
          result = (double) number.removeLast() * (double) number.removeLast();
          number.addLast(result);
        }
        else if (info[i] == "/"){
          result = (double) number.removeLast() / (double) number.removeLast();
          number.addLast(result);
        }
        else{//the string contains a number
          number.addFirst(Double.parseDouble(info[i]));
        }
      }
      return (double) number.getLast();
    }
}
