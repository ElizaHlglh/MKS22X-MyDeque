import java.util.NoSuchElementException;
public class MyDeque<E>{
  private E[] data;
  private int size, start, end;

//no space between the start and the end
  public MyDeque(){
    @SuppressWarnings("unchecked")
    E[] d = (E[])new Object[10];
    data = d;
    size = 0;
    start = -1;
    end = -1;
  }

  public MyDeque(int initialCapacity){
    if (initialCapacity < 0){
      throw new IllegalArgumentException("array length < 0");
    }
    @SuppressWarnings("unchecked")
    E[] d = (E[])new Object[initialCapacity];
    data = d;
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
    //System.out.println("starting toString");
    if (size == 0){
      return "{}";
    }
    String ans = "{";
    /*for (int i = 0; i < data.length; i++){
      if (data[i] != null){
        ans += data[i] + " ";
      }
      else{
        ans += "  ";
      }
    }*/
    int i = start;
    while (i != end){
      //System.out.println("i = " + i + "      end = " + end);
      ans += data[i] + " ";
      i++;
      if (i == data.length){
        i = 0; //PROBLEM HERE!!!!!!!!!!!
      }
    }
    ans += data[end];
    ans += "}";
    return ans;
  }

  public void addFirst(E element){
    //System.out.println("array length is: " + data.length);
    if (!(element != null)){
      throw new NullPointerException("Can't add null to deque!!!");
    }
    if (start != -1){//if the array isn't empty
    //System.out.println("size is = " + size);
    //System.out.println("array length is: " + data.length);
      if (size == data.length){
        //System.out.println("before resize(), length is: " + data.length);
        resize();
        //System.out.println("after resize(), length is: " + data.length);
      }
      if (start == 0){//if current start is at 0, add the new element/start to the back of the array
        //System.out.println("is the problem here?");
        data[data.length-1] = element;
        start = data.length-1;
        //System.out.println("start is : " + start + "     end is : "+ end);
        size++;
        //System.out.println(toString());
        //System.out.println("current size =?" + size);
        //System.out.println("array length is: " + data.length);
      }
      else{
        data[start-1] = element;
        start--;
        size++;
      }
    }
    else{//if the array is empty
      //System.out.println(toString());
      start = 0;
      end = 0;
      if (data.length == 0 || data.length == size){
        resize();
      }
      data[0] = element;
      size++;
      //System.out.println(toString());
    }
  }

  public void addLast(E element){
    if (!(element != null)){
      throw new NullPointerException("Can't add null to deque!!!");
    }
    if (end != -1){
      if (size == data.length){//if full
        resize();
      }
      data[end+1] = element;
      end++;
      size++;
    }
    else{//if array is empty
      start = 0;
      end = 0;
      if (data.length == 0 || data.length == size){
        resize();
      }
      data[0] = element;
      size++;
    }
  }

  public E removeFirst(){
    if (size == 0){
      throw new NoSuchElementException("Can't remove from empty deque!!!");
    }
    E temp = data[start];
    data[start] = null;
    if (start == data.length-1){
      start = 0;
    }
    else{
      start++;
    }
    size--;
    if (size == 0){ //restart if everything is completely removed
      end = -1;
      start = -1;
    }
    return temp;
  }

  public E removeLast(){
    if (size == 0){
      throw new NoSuchElementException("Can't remove from empty deque!!!");
    }
    E temp = data[end];
    data[end] = null;
    if (end == 0){
      end = data.length-1;
    }
    end--;
    size--;
    if (size == 0){ //restart if everything is completely removed
      end = -1;
      start = -1;
    }
    return temp;
  }

  public E getFirst(){
    if (size == 0){
      throw new NoSuchElementException("Can't get anything from empty deque!!!");
    }
    return data[start];
  }

  public E getLast(){
    if (size == 0){
      throw new NoSuchElementException("Can't get anything from empty deque!!!");
    }
    return data[end];
  }

  public void resize(){
    E[] copy = (E[])new Object[data.length*2 + 1];
    //System.out.println("Copy's length" + copy.length);
    if (start == 0){//if there is no need for the array to put the part after start into the back of new array
      for (int i = 0; i < data.length; i++){
        copy[i] = data[i];
      }
    }
    else{
      int OldStart = start;
      int NewStart = copy.length - (data.length - start);
      start = NewStart;
      while (OldStart != end){
        copy[NewStart] = data[OldStart];
        NewStart++;
        OldStart++;
        if (OldStart == data.length){
          OldStart = 0;
        }
        if (NewStart == copy.length){
          NewStart = 0;
        }
      }
      //after the while loop, the OldStart should be == to end and I need to add that end value to the copy array
      copy[NewStart] = data[OldStart];
    }
    data = copy;
  }
}
