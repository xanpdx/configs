class Subtract{
  public boolean subTwo(int i){
    if(i == 0){
      return true;
    }else if(i == 1){
      return false;
    }else{
      return subTwo(i - 2);
    }
  }
}
public class isEven{
  public static void main(String[] args){
    Subtract sub = new Subtract();
    boolean b = sub.subTwo(3); 
    System.out.println(b);
  }
}
