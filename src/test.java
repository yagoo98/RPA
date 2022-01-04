public class test {
    public static void main(String[] args)
    {
        int[] num = new int[10];
        System.out.print("數字: ");

        for(int i = 0; i < num.length; i++)
        {
            num[i] = i + 1;
            System.out.print(num[i]+" ");
        }

        System.out.print("\n隨機不重複產生5個亂數: ");

        int[] Array;

        Array = getRandom(num);

        for(int i = 0; i < 5; i++)

        System.out.print(Array[i] + "" );
    }

    public static int[] getRandom(int[] num)
    {
        int[] arr = new int[5];
        int n;
        for(int i = 0; i < arr.length; i++)
        {
            n = (int)(Math.random()*(10-i));
            arr[i] = num[n];
            for(int j = n; j < num.length - 1; j++)
            {
                num[j] = num[j+1];
            }
        }
        return arr;
    }
}
