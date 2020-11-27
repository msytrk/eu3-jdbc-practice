import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            String dummy = "";
            if (!(i % 2 == 0) && !(i % 3 == 0) && !(i % 5 == 0))
                System.out.println(i);
            else {
                if (i % 2 == 0)
                    dummy += "Codility";
                if (i % 3 == 0)
                    dummy += "Test";
                if (i % 5 == 0)
                    dummy += "Coders";
                System.out.println(dummy);
            }
        }

        String phoneNumber="0 - 222 198-----";
        System.out.println("test1(phoneNumber) = " + test1(phoneNumber));

    }

    public static String  test1(String phoneNumber){
        String dummy="";
        char[] charArray=phoneNumber.toCharArray();
        String chars="";
        int counter=0;
        for (int i = 0; i < charArray.length; i++) {
            if(Character.isDigit(charArray[i])) {
                chars+=charArray[i];
            }
        }
        String a="";
        if(chars.length()%3==1) {
            a = chars.substring(chars.length() - 4);
            String b =chars.substring(0,chars.length() - 4);
            System.out.println(a);
            System.out.println(b);
            for (int i = 0; i < b.length(); i++) {
                if(counter!=3)
                    dummy+=b.charAt(i);
                else
                {
                    counter=-1;
                    dummy+="-";
                    i--;    }
                counter++;
            }
            dummy+="-";
            counter=0;
            for (int i = 0; i < a.length(); i++) {
                if(counter!=2)
                    dummy+=a.charAt(i);
                else
                {
                    counter=-1;
                    dummy+="-";
                    i--;    }
                counter++;
            }
        }
        else{
            for (int i = 0; i < chars.length(); i++) {
                if( counter!=3)
                    dummy+=chars.charAt(i);
                else
                {
                    counter=-1;
                    dummy+="-";
                    i--;    }
                counter++;
            }
        }
        return dummy;
    }


}
