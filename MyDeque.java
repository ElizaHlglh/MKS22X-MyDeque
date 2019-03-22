public class MyDeque<E>{
  private E[] data;
  private int size, start, end;

//no space between the start and the end
  @SuppressWarnings("unchecked")
  public MyDeque(){
    data = (E[])new Object[10];
  }
  public MyDeque(int initialCapacity){
    data = (E[])new Object[initialCapacity];
  }
  public int size(){
    int ans = 0;
    for (int i = 0; i < data.length; i++){
      if (data[i] != null){
        ans++;
      }
    }
    return ans;
  }
  public String toString(){
    String ans = "";
    for (int i = 0; i < data.length; i++){
      if (data[i] != null){
        if (i == data.length-1){
          ans += data[i] + "}";
        }
        else{
          ans += data[i] + " ";
        }
      }
      else{
        if (i == data.length-1){
          ans += " }";
        }
        else{
          ans += "  ";
        }
      }
    }
    return ans;
  }
  public void addFirst(E element){ }
  public void addLast(E element){ }
  public E removeFirst(){ }
  public E removeLast(){ }
  public E getFirst(){ }
  public E getLast(){ }
}
