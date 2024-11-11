
public class BinaryNumber {
    /*Marc Sulsenti
    CS284-RF CS284-C
    I pledge my honor that I have abided by the Stevens Honor System
     */

    //VARIABLES----------------------------------------------
    private int[] data;
    private int length;

    //CONSTRUCTORS-------------------------------------------
    public BinaryNumber(int length) {
        //Constructor that creates an array with given length
        int[] setup = new int[length];
        this.length = length;
        this.data = setup;
        //Array is set up with given length
    }

    public BinaryNumber(String input) throws Exception {
        //Turns a string input into an array of 1s and 0s to represent a binary number.
        int measure = input.length();
        int[] setup = new int[measure];
        if(input.equals("")){
            throw new Exception("Entered string is empty");
        }
        if (measure <= 0) {
            throw new Exception("The binary strength length must be greater than 0. ");
        }
        for (int i = 0; i < measure; i++) {
            char c = input.charAt(i);
            int n = Character.getNumericValue(c);            //System.out.println(bit);
            if (n != 1 && n != 0) {
                throw new Exception("string must contain only 1s or 0s");
            }
            setup[i] = n;
        }
        this.length = measure;
        this.data = setup;

    }

    //METHODS-------------------------------------

    public int getLength() {
        //Returns the length of the data variable
        return this.length;
    }

    public int getDigit(int index) throws Exception {
        //gets the digit of the array at a specified index. Throws exception if given index is out of bounds.
        try {
            return this.data[index];
            //Returns value at given index
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new Exception("Index is out of bounds, error");
        }
    }

    public int[] getInnerArray() {
        //returns the integer array representing the binary number
        return this.data;
    }

    public int toDecimal() {
        //Converts a binary array to a decimal based integer
        int fin = 0;
        for (int i = 0; i < (this.data).length; i++) {
            if (data[i] == 1) {
                int temp = (int) Math.pow(2, i);
                fin = fin + temp;
            }
        }

        return fin;
    }

    public static int[] bwor(BinaryNumber b1, BinaryNumber b2) throws Exception {
        //bwor creates a new binary number, where if ONE (or) the two binary nums have a 1 in that index, than the bwor b3 does
        int length_b3 = b1.getLength();
        int[] b3 = new int[length_b3];
        //Lengths of b1 and b2 must be the same (stated in directions)
        if (b1.getLength() != b2.getLength()) {
            throw new Exception("Lengths are not the same!");
        }
        //Return a new array (the bwor of the two binary numbers)
        for (int i = 0; i < b1.length; i++) {
            if (b1.getDigit(i) == 1 || b2.getDigit(i) == 1) {
                b3[i] = 1;
            } else {
                b3[i] = 0;
            }

        }
        return b3;
    }

    public static int[] bwand(BinaryNumber b1, BinaryNumber b2) throws Exception {
        //Same as bwor, but instead uses && instead of ||
        //bwand creates a new binary number, where if BOTH (and) the two binary nums have a 1 in that index, than the bwand b3 does
        int length_b3 = b1.getLength();
        int[] b3 = new int[length_b3];
        //Lengths of b1 and b2 must be the same (stated in directions)
        if (b1.getLength() != b2.getLength()) {
            throw new Exception("Lengths are not the same!");
        }
        //Return a new array (the bwand of the two binary numbers)
        for (int i = 0; i < b1.length; i++) {
            if (b1.getDigit(i) == 1 && b2.getDigit(i) == 1) {
                b3[i] = 1;
            } else {
                b3[i] = 0;
            }

        }
        return b3;
    }

    public void bitShift(int direction, int amount) throws Exception {
        /* Bitshift method shifts all digits in a binary number a given number of places (amount)
        to the left (-1) and to the right (1).
         */
        //-1 is to the left and 1 is to the right. No others integers valid
        if (direction != 1 && direction != -1) {
            throw new Exception("Invalid direction. 1 or -1 only.");
        }
        if (amount <= 0) {
            throw new Exception("Invalid amount, must be non-negative");
        }
        if (direction == 1) {
            //Right shift (Smaller Binary Numbers)
            int new_length = this.getLength() - amount;
            int[] bs = new int[new_length];
            for (int i = 0; i < new_length; i++) {
                System.out.println("HERE:  " + i);
                bs[i] = this.getDigit(i);
            }
            this.data = bs;
        }
        if (direction == -1) {
            //Left shift (Bigger Binary Number)

            int new_length = this.getLength() + amount;
            int[] bs = new int[new_length];
            for (int i = 0; i < this.getLength(); i++) {
                bs[i] = this.getDigit(i);
            }
            for (int i = 0; i < amount; i++) {
                bs[i] = 0;
            }

            this.data = bs;
        }
    }

    public void prepend(int num) throws Exception {
        /*This method adds zeros to the end in order to help with the cary,
        and making the two arrays the same size if they are not.

       DOES NOT HAVE TO BE STATIC (directions)
         */
        if(num<0){
            throw new Exception("Entered amount must be >= 0");
        }
        int new_length = this.length+num;
        int[] prep_array   = new int[new_length];
        //new array created with the proper length
        for(int i = num; i<new_length; i++){
            //The old array is copied onto the new array
                prep_array[i]=this.getDigit(i-num);

            }
        //New array turns back to old array
        //Length of this array is also updated
        this.length=this.length+num;
        this.data=prep_array;
        }


    public void add(BinaryNumber bin_num) throws Exception {
    /* This method will add two binary numbers together. If the two binary numbers are
    not the same length, they will be made the same length by adding on 0s to the shorter binary num.
    This method will utilize the helper method "prepend" to keep track of carry and use it for the next digit addition
     */
        //First determine which of the two binary numbers, if either are shorter
        if(this.length<bin_num.getLength()){
            //prepend the difference of the two binary num lengths (bin_num bigger) to make the binary numbers  equal length
            this.prepend(bin_num.getLength()-this.length);
        }
         if(bin_num.getLength()<this.length){
            //prepend the difference of the two binary num lengths (this.length bigger) to make the binary numbers equal laengths
            bin_num.prepend(this.length-bin_num.getLength());
        }
        int carry_num = 0;
        int[]added_array= new int[this.length];
        for(int i=bin_num.getLength()-1; i>-1; i--){
            //you add from the back to the front of a binary #
            int sum = bin_num.getDigit(i)+this.data[i]+carry_num;
            //sum represents all the adding that will be done between bits. if there is  still a cary by the end of the string prepend will be used to add that final carry
            ///sum will either be 0,1,2,3 in a binary, we will have four special cases for each.
             if(sum == 0){
                //case where both bits are 0 carry 0.
                added_array[i]=0;
                carry_num=0;
            }
          else if(sum== 1){
                //case where one of the bits is 1. others are 0
                added_array[i]=1;
                carry_num=0;
            }
            else if(sum==2){
                //case where 2/3 bits are 1.
                added_array[i]=0;
                carry_num=1;
            }
            else if(sum==3){
                //case where 3/3 bits are 1;
                added_array[i]=1;
                carry_num=1;
            }
        }
        //update arrays to objects so they can be used with prepend for carry, because prepend is not static.
        this.data=added_array;
        this.length=added_array.length;
        //deal with the carry
        if(carry_num==1){
            //If a number is to be carried, prepend adds a zero to the front, where the carry digit is added.
            this.prepend(1);
            this.data[0]=1;
        }



    }

    @Override
    public String toString() {
        //How the binary num array will print out
        //Overrides print
        String bin_num = new String();
        for (int i = 0; i < this.getLength(); i++) {
            try {
                bin_num = bin_num + this.getDigit(i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return bin_num;
    }

//end
}
