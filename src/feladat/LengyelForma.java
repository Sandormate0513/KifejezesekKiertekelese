package lengyelforma;

import java.util.Stack;

public class LengyelForma {

    Stack<String> verem;

    public static void main(String[] args) {
        String lengy = "9 2 - 4 * 7 3 2 - / - 8 -";
        eredmeny(lengy);

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

        String[] s = lengy.split(" ");
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
        System.out.println(verem.pop());
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
}
