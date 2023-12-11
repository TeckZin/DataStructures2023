package Stack;


public class Lisp {


    int len = 0;
    String strList;
    ArrayStack<String> arrayStack;
    LinkedStack<Double> arrayStackDigits;

    /**
     * when first call it will split the string up, and remove all spaces
     * then it will add the numbers into the arrayStackDigits, and repsent it in arrayStack with a space " "
     * if not all will be added into the arrayStack
     *
     * mesuare out the len and call the main func operations to find the result.
     * @param strList
     */

    public Lisp(String strList) {

        this.strList = strList;
        this.arrayStack = new ArrayStack<>(strList.length());
        this.arrayStackDigits = new LinkedStack<>();

        String[] arrStr = strList.split("");


        for (String x : arrStr) {
//            System.out.println(x);
            if (!x.equals(" ")) {
                try {

                    double digit = Double.parseDouble(x);
//                    System.out.println(digit);

                    arrayStackDigits.push(digit);

                    arrayStack.push(" ");


                } catch (Exception e) {
                    arrayStack.push(x);

//                System.out.println("Not Digit");
                }
                this.len++;

            }


        }



        operations(arrayStack, arrayStackDigits);


    }

    /**
     * it will do all the direct math first for exmaple eg (- 1 2 3)
     * for complicated tuples like (+ 2 (-1 2 3)) it will slove to -> (+ 2 -4)
     * then recur it self to slove until one digit left and print
     *
     * method it will be using are
     * 1) mathOperation which will conduct all the math related problems
     * 2) reverse the both of the list
     * 3) simplfied list with is for the unlink stack and it will also take out any extra tuples
     * @param arrayStack to store the tuples and opeations using space eg " " to repsent digits
     * @param arrayStackDigits to store all the digits so we could use digits that are negative and greater than 9
     */


    public void operations(ArrayStack<String> arrayStack, LinkedStack<Double> arrayStackDigits) {

        // due to recursion the arrayStack will be call back
        this.arrayStack = arrayStack;
        this.arrayStackDigits = arrayStackDigits;


        ArrayStack<Double> digitsStack = new ArrayStack<>(len); // store the digits when caluclation is valid


        ArrayStack<String> finalArr = new ArrayStack<>(len); // the array will be build upon

        LinkedStack<Double> finalDigitArr = new LinkedStack<>(); // the digit array will be build upon as teh arrayStack gets remove

        LinkedStack<Double> passThroughDigitArr = new LinkedStack<>(); // for digits that are out side an on going
        // operations (+ 3 3 5( -...)), 3 3 and 5 will be here.

        boolean hasOp = false; //is there a operator ready to be use
        boolean flagTuple; // is there a tuple pair
        boolean twoClose = false; // check if there is an open tuples with digits in it  eg (+ 12 12 (...
        boolean open = true; // is there a opening tuples ... ) ...
        char last = ' '; // get the last tuples if its a ) or (
        char current = ')'; // the current tuples eg value could be 2  but { 2 3 4 ) ( }, the current wil be ) and last would
        // be (
        char op = '.'; // to other the operator /, +, -, *


        while (!arrayStack.isEmpty()) {
//            arrayStack.printArr();
            // get current value
            char value = arrayStack.pop().charAt(0);

//            System.out.println(value);
            // use a switch statment to check the value
            switch (value) {
                case ' ' -> { // means its a digit
                    Double digit = arrayStackDigits.pop();
                    if (current == ')') {


                        digitsStack.push(digit);
                    } else {
                        passThroughDigitArr.push(digit);
                    }
                }
//                    System.out.println(digit);
                case ')' -> {

                    if (open) { // since we are always reading right to left it being a stack
                        // when its open we can add the ( and if not open means close we add the (
                        finalArr.push("("); // due to it being a stack the top will got the bottom
                        // so we print ( to make it simpfied
                    }
                    last = current; // set the last to curent, due to current will be change
                    current = value;
                    twoClose = false;
                    open = true;
                }
                case '(' -> {

                    if (!open) {
                        finalArr.push(")");

                    }
                    twoClose = last == '(';

                    last = current;
                    current = value;
                    open = false;
                }
                default -> {


                    /*
                    due to it being defualt and all the other value have been account for,
                    the rest or defualt could only be oerators

                     */

                    if ((current == '(' && arrayStack.peek().charAt(0) == '(') || twoClose) {


                        while (!passThroughDigitArr.isEmpty()) {
                            finalDigitArr.push(passThroughDigitArr.pop());
                            finalArr.push(" ");


                        }
                        finalArr.push(String.valueOf(value));
                    } else {
//                        System.out.println(value);
                        hasOp = true;
                        op = value;
                    }


                }
            }

            // import the boolean of ( ... ) so if current is ( and last is ), thats a close and open tuples
            flagTuple = last == ')' && current == '(';

            if (hasOp && flagTuple && !digitsStack.isEmpty()) {

                double ans = operationMath(digitsStack, op);
                finalDigitArr.push(ans);

                finalArr.push(" ");
                hasOp = false;
            }
            if (flagTuple && hasOp && digitsStack.isEmpty()) {
                finalDigitArr.push(1.0);
                finalArr.push(" ");
//                System.out.println(op);
            }


        }


        this.arrayStack = simplfiedArrayStack(finalArr);
//        this.arrayStack.printArr();
        this.arrayStackDigits = reverseSortLink(finalDigitArr);

        /* due to all value will be in (), and only when the final operation is conculde the tuples will dispear
            this is because before when there is value and operation in tuples eg (- 1 2 3)
            it will push the result on to the finalDigitArr, and pusha " " without the tuples to the
            finalArr to repsetn a digit

            so if there are no tuples and the top value is a " " it meansthe operation is finshed else recur

         */

        if (this.arrayStack.peek().equals(" ")) {
            double digits = this.arrayStackDigits.pop();
            System.out.printf("The answer is: %.2f ", digits);
        } else {
            operations(this.arrayStack, this.arrayStackDigits);
        }


    }

    /**
     * it will count the aount of tuples imported and balance it out on to
     * a new ArrayStack and due to it being an object
     * we have to set the arr back the tempArr (the tempory array )
     *
     *
     * @param arr // the object arr
     * @return finalArr // the simpfied arr with out the extra tuples
     */

    public ArrayStack<String> simplfiedArrayStack(ArrayStack<String> arr) {
        ArrayStack<String> tempArr = new ArrayStack<>(len);
        int status = 0;
        while (!arr.isEmpty()) {
            char value = arr.pop().charAt(0);
            switch (value) {

                case ')' -> {
                    tempArr.push("(");
                    status++;

                }

                case '(' -> {
                    if (status > 0) {
                        tempArr.push(")");
                        status--;
                    }

                }

                default -> tempArr.push(String.valueOf(value));


            }

        }


        return tempArr;
    }

    /**
     *
     * reverse the a LinkStack Double due to when push at the beigning the top will
     * go to the bootom and return the revser linkStack back
     *
     * @param arr
     * @return tempArr;
     */

    public LinkedStack<Double> reverseSortLink(LinkedStack<Double> arr) {
        LinkedStack<Double> tempArr = new LinkedStack<>();
        while (!arr.isEmpty()) {
            double d = arr.pop();
            tempArr.push(d);
        }
        return tempArr;


    }

    /**
     *  it wil check what is the op due theeres no case and only defualt in the main funciton
     *  and conduct the calculations and return it
     *
     * @param tempArr
     * @param op
     * @return answer to the operation
     */

    public double operationMath(ArrayStack<Double> tempArr, char op) {

        double sum = 0;


        boolean first = true;

        while (!tempArr.isEmpty()) {
            double value = tempArr.pop();


//            System.out.printf("value: %.2f\n", value);


            switch (op) {
                case '*' -> {
                    if (first) {
                        sum = value;
                        break;
                    }
                    sum = sum * value;
                }
                //                    System.out.println(op);
                case '/' -> {
                    if (first) {
                        sum = value;
                        break;
                    }
                    sum = sum / value;
                }


                //                    System.out.println(op);
                case '+' -> {
                    if (first) {
                        sum = value;
                        break;
                    }
                    sum += value;
                }
                //                    System.out.println(op);
                default -> {
                    if (first && tempArr.isEmpty()) {
                        sum = 0 - value;
                        break;
                    } else if (first) {
                        sum = value;
                        break;
                    }
                    sum -= value;
                }


                //                    System.out.println(op);
            }

            first = false;

        }
//        System.out.printf("Sum: %.2f\n", sum);
        return sum;


    }


}
