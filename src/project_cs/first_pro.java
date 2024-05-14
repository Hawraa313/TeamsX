
package project_cs;
import java.util.Scanner;


public class first_pro {

    private String KeyWord = new String();
    private String Key  = new String();
    private char   matrix[][] = new char[5][5];
 
    public void processKey(String p)
    {
        String Mod_KeyWord = new String();
        boolean flag = false;
        Mod_KeyWord  = Mod_KeyWord + p.charAt(0);
        for (int i = 1; i < p.length(); i++)
        {
            for (int j = 0; j < Mod_KeyWord .length(); j++)
            {
                if (p.charAt(i) == Mod_KeyWord .charAt(j))
                {
                    flag = true;
                }
            }
            if (flag == false)
                Mod_KeyWord  = Mod_KeyWord + p.charAt(i);
            flag = false;
        }
        KeyWord = Mod_KeyWord ;
    }
 
    public void GenerateKey()
    {
        boolean flag = true;
        char letter;
        Key = KeyWord;
        for (int i = 0; i < 26; i++)
        {
            letter = (char) (i + 97);
            if (letter == 'j')
                continue;
            for (int j = 0; j < KeyWord.length(); j++)
            {
                if (letter == KeyWord.charAt(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
                Key = Key + letter;
            flag = true;
        }
        System.out.println(Key);
        matrix();
    }
 
    private void matrix()
    {
        int pointer = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                matrix[i][j] = Key.charAt(pointer);
                System.out.print(matrix[i][j] + " ");
                pointer++;
            }
            System.out.println();
        }
    }
 
    private String TextFormat(String PlainText)
    {
        int i = 0;
        int lenght = 0;
        String textformat = new String();
        lenght = PlainText.length();
        for (int x = 0; x < lenght; x++)
        {
            if (PlainText.charAt(x) == 'j')
            {
                 textformat=  textformat + 'i';
            }
            else
                 textformat =  textformat+ PlainText.charAt(x);
        }
        lenght =  textformat.length();
        for (i = 0; i < lenght-1; i = i + 2)
        {
            if ( textformat.charAt(i + 1) ==  textformat.charAt(i))
            {
                 textformat =  textformat.substring(0, i + 1) + 'x' +  textformat.substring(i + 1);
            }
        }
        return  textformat;
    }
 
    private String[] TextDivid(String text)
    {
        String RealText = TextFormat(text);
        int size = RealText.length();
        if (size % 2 != 0)
        {
            size++;
            RealText = RealText+ 'x';
        }
        String x[] = new String[size / 2];
        int count = 0;
        for (int i = 0; i < size / 2; i++)
        {
            x[i] = RealText.substring(count, count + 2);
            count = count + 2;
        }
        return x;
    }
 
    public int[] LetterPosition(char letter)
    {
        int[] key = new int[2];
        if (letter == 'j')
            letter = 'i';
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (matrix[i][j] == letter)
                {
                    key[0] = i;
                    key[1] = j;
                    break;
                }
            }
        }
        return key;
    }
 
    public String encryption(String T)
    {
        String Dividtext[] = TextDivid(T);
        String CipherText = new String();
        char Letter1;
        char Letter2;
        int postion1[] = new int[2];
        int postion2[] = new int[2];
        for (int i = 0; i < Dividtext.length; i++)
        {
            Letter1 = Dividtext[i].charAt(0);
            Letter2= Dividtext[i].charAt(1);
            postion1 = LetterPosition(Letter1);
            postion2 = LetterPosition(Letter2);
            if (postion1[0] == postion2[0])
            {
                if (postion1[1] < 4)
                    postion1[1]++;
                else
                    postion1[1] = 0;
                if (postion2[1] < 4)
                    postion2[1]++;
                else
                    postion2[1] = 0;
            }
            else if (postion1[1] == postion2[1])
            {
                if (postion1[0] < 4)
                    postion1[0]++;
                else
                    postion1[0] = 0;
                if (postion2[0] < 4)
                    postion2[0]++;
                else
                    postion2[0] = 0;
            }
            else
            {
                int temp = postion1[1];
                postion1[1] = postion2[1];
                postion2[1] = temp;
            }
            CipherText= CipherText+ matrix[postion1[0]][postion1[1]]
                    + matrix[postion2[0]][postion2[1]];
        }
        return CipherText;
    }
    public String decryption(String Cipher)
            
    {
        String text = new String();
        String array[] = TextDivid(Cipher);
        char Letter1;
        char Letter2;
        int postion1[] = new int[2];
        int postion2[] = new int[2];
        for (int i = 0; i < array.length; i++)
        {
            Letter1= array[i].charAt(0);
            Letter2 = array[i].charAt(1);
            postion1 = LetterPosition(Letter1);
            postion2 = LetterPosition(Letter2);
            if (postion1[0] == postion2[0])
            {
                if (postion1[1] > 0)
                    postion1[1]--;
                else
                    postion1[1] = 4;
                if (postion2[1] > 0)
                    postion2[1]--;
                else
                    postion2[1] = 4;
            }
            else if (postion1[1] == postion2[1])
            {
                if (postion1[0] > 0)
                    postion1[0]--;
                else
                    postion1[0] = 4;
                if (postion2[0] > 0)
                    postion2[0]--;
                else
                    postion2[0] = 4;
            }
            else
            {
                int p = postion1[1];
                postion1[1] = postion2[1];
                postion2[1] = p;
            }
            text = text + matrix[postion1[0]][postion1[1]]
                    + matrix[postion2[0]][postion2[1]];
        }
        return text;
    }

    public static void main(String[] args) {
     playfair x = new playfair();
     x.setVisible(true);
     x.setDefaultCloseOperation(0);
     x.pack();
     }}
