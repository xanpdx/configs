public class recursive{
  public static int x = 0;
  public static int[] y = new int[9];
  public static void setX(int i){
    x = i;
  }
  public static void main(String[] args){
    System.out.println(x);
    x++;
    setX(9);
    y[2] = x;
    System.out.println(y[2]);
    System.out.println(x);
  }
}
