package feladat;

import java.util.Stack;

public class LengyelForma {

    static String lengyelForma;
    public static void main(String[] args) {
        
        lengyelForma = ""; /*"9 2 - 4 * 7 3 2 - / - 8 -";*/
        LengyelFormaba("2*4");
        //System.out.println(lengyelForma);
        eredmeny(lengyelForma);

    }

    private static void eredmeny(String lengy) {
        Stack<String> verem = new Stack<>();
        String elem;
        String matKif;
        Stack<String> lengyKif = new Stack<>();
        int eredmeny = 0;
        int ertek1 = 0;
        int ertek2 = 0;
        int i = 0;

        String[] s = lengy.split("");
        for (int j = s.length - 1; j >= 0; j--) {
            lengyKif.push(s[j]);
        }
        
        verem.clear();
        while (!lengyKif.isEmpty()) {
            elem = lengyKif.pop();
            if (isAdat(elem)) {
                verem.push(elem);
            } else {
                ertek1 = Integer.parseInt(verem.pop());
                ertek2 = Integer.parseInt(verem.pop());
                eredmeny = kiszamol(ertek2, elem, ertek1);
                verem.push(String.valueOf(eredmeny));
            }
            i++;
        }
        System.out.println("Eredmény: " + verem.pop());
    }

    private static boolean isAdat(String elem) {

        try {
            Integer.parseInt(elem);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static int kiszamol(int ertek2, String muvelet, int ertek1) {
        int eredmeny = 0;
        switch (muvelet) {
            case "+":
                eredmeny = ertek2 + ertek1;
                break;
            case "-":
                eredmeny = ertek2 - ertek1;
                break;
            case "/":
                eredmeny = ertek2 / ertek1;
                break;
            case "*":
                eredmeny = ertek2 * ertek1;
                break;
        }

        return eredmeny;
    }
    
    private static void LengyelFormaba(String kifejezes) {
        Stack<Character> verem = new Stack<>();
        char kivettElem = 0;
        int i = 0;
        while(i < kifejezes.length()){
            char akt = kifejezes.charAt(i);
            if (akt == '(') {
                verem.push(akt);
            }else if (Character.isDigit(akt)) {
                lengyelForma += akt;
            }else if (akt == '*' || akt == '/' || akt == '-' || akt == '+') {
                if (verem.isEmpty()) {
                    verem.push(akt);
                }
            }else{
                while(!verem.isEmpty()){
                    //System.out.println("szia");
                    char legFelso = verem.peek();
                    if (Precedencia(legFelso) >= Precedencia(akt)) {
                        kivettElem = verem.pop();
                        lengyelForma += kivettElem;
                    }else{
                        break;
                    }
                }
                verem.push(akt);
            }
            if (akt == ')') {
                while(verem.pop() != '('){
                    lengyelForma += verem.pop();
                    //lengyelForma += kivettElem;
                }
            }
            i++;
        }
        while(!verem.isEmpty()){
            lengyelForma += verem.pop();
            //lengyelForma += kivettElem;
        }
        System.out.println(lengyelForma);
        //return lengyelForma;
    }

    private static int Precedencia(char jel){
        int ertek = 0;
        switch (jel) {
            case '+':
            case '-':
                ertek = 0;
                break;
            case '*':
            case '/':
                ertek = 1;
                break;
        }
        return ertek;
    }
}
