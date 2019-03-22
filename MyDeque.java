public class MyDeque<E>{
  private E[] data;
  private int size, start, end;

//no space between the start and the end
  @SuppressWarnings("unchecked")
  public MyDeque(){
    data = (E[])new Object[10];
    size = 0;
    start = -1;
    end = -1;
  }
  public MyDeque(int initialCapacity){
    if (initialCapacity < 0){
      throw new IllegalArgumentException("array length < 0");
    }
    data = (E[])new Object[initialCapacity];
    size = 0;
    start = -1;
    end = -1;
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
    String ans = "{";
    for (int i = 0; i < data.length; i++){
      if (data[i] != null){
        ans += data[i] + " ";
      }
      else{
        ans += "  ";
      }
    }
    ans += "}";
    return ans;
  }
  public void addFirst(E element){
    if (size == data.length){
      resize();
    }
    //shift the array one side over
    if (start != -1){//if the array isn't empty

    }
  }
  public void addLast(E element){ }
  public E removeFirst(){
    E temp = data[start];
    data[start] = null;
    start++;
    return temp;
  }
  public E removeLast(){
    E temp = data[end];
    data[end] = null;
    end--;
    return temp;
  }
  public E getFirst(){
    return data[start];
  }
  public E getLast(){
    return data[end];
  }
  public void resize(){
    E[] copy = (E[])new Object[data.length*2 + 1];
    for (int i = 0; i < data.length; i++){
      copy[i] = data[i];
    }
    data = copy;
  }
}
