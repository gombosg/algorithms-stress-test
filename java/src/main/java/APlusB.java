import java.util.Random;
import java.util.Scanner;

class APlusB
{
  public static int naiveAlgorithm(APlusBData data)
  {
    return data.a + data.b;
  }

  public static int implementedAlgorithm(APlusBData data)
  {
    if (new Random().nextInt(100) > 95)
    {
      // sometimes fails
      return data.a + data.b + 1;
    }
    return data.a + data.b;
  }

  public static void main(String[] args)
  {
    Scanner s = new Scanner(System.in);
    int a = s.nextInt();
    int b = s.nextInt();
    System.out.println(naiveAlgorithm(new APlusBData(a, b)));
  }
}

class APlusBData
{
  int a;
  int b;

  public APlusBData(final int a, final int b)
  {
    this.a = a;
    this.b = b;
  }
}

class APlusBDataGenerate
{
  static int min = -50000;
  static int max = 50000;
  static Random random = new Random();

  public static APlusBData generate()
  {
    return new APlusBData(randomInt(min, max), randomInt(min, max));
  }

  // utility methods like this can be copied to later assignments
  private static int randomInt(int min, int max)
  {
    return random.nextInt(max - min) + min;
  }
}