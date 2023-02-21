public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String languages[] ={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n*=3;
        n+=0b10101;
        n+=0xFF;
        n*=6;
        System.out.println(n);
        int result =0;
        while(n>9)
        {
            result=0;
            while(n>0)
            {
                result+=n%10;
                n/=10;
            }
          n=result;
        }
        System.out.println(result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}