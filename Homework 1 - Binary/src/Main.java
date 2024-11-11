
public class Main {
    public static void main(String[] args) throws Exception {
      /* BinaryNumber str = new BinaryNumber("1111");
       BinaryNumber str2 = new BinaryNumber("1001001");
       BinaryNumber str3 = new BinaryNumber(5);
       System.out.println(str.getDigit(3));
       System.out.println(str.getLength());
       System.out.println(str2.getLength());
       System.out.println(str.getLength());
       System.out.println(str.toDecimal());
       System.out.println(str2.toDecimal());
        BinaryNumber t1 = new BinaryNumber("1010");
        BinaryNumber t2 = new BinaryNumber("1100");
       // System.out.println(t1);
       // System.out.println(t2);
        int[] t3 = BinaryNumber.bwor(t1, t2);
        int[] t4 = BinaryNumber.bwand(t1,t2);
         //Testing bwor
        for (int i = 0; i < t1.getLength(); i++) {
            System.out.print(t3[i]);

        }
        //Testing bwand
        for (int i = 0; i < t1.getLength(); i++) {
            System.out.print(t4[i]);
        }
        BinaryNumber t5= new BinaryNumber("1011");
        t5.bitShift(1,2);
        System.out.println(t5);*/
        BinaryNumber t1 = new BinaryNumber("1010");
        BinaryNumber t2 = new BinaryNumber("1100");
        t1.add(t2);
        System.out.println(t1);
        BinaryNumber t3 = new BinaryNumber("011");
        BinaryNumber t4 = new BinaryNumber("1000");
        t3.add(t4);
        System.out.println(t3);



    }
}