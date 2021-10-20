import java.io.*;
import java.util.Vector;


public class Q1
{
    public static Vector<String> LZW_dictionary(String str)
    {
        Vector<String> dictionary = new Vector<>();
        dictionary.add("A");
        dictionary.add("B");
        dictionary.add("C");
        for (int i=0; i<str.length(); i++)
        {
            StringBuilder temp = new StringBuilder(Character.toString(str.charAt(i)));
            int j = 1;
            boolean Find = false;
            while (i+j<str.length() && !Find)
            {
                temp.append(str.charAt(i + j));
                for (String s : dictionary)
                {
                    if (temp.toString().equals(s))
                    {
                        i += j;
                        Find = true;
                        break;
                    }
                }
                if (!Find)
                {
                    dictionary.add(temp.toString());
                    break;
                }
                Find = false;
            }
        }
        return dictionary;
    }

    public static Vector<String> LZW_output(String str)
    {
        Vector<String> dictionary = new Vector<>();
        Vector<String> code = new Vector<>();
        dictionary.add("A");
        dictionary.add("B");
        dictionary.add("C");
        int index = 0;
        boolean Find = false;
        String temp = Character.toString(str.charAt(index));
        while (index < str.length())
        {
            if (Find)
            {
                if (index + 1 >= str.length())
                {
                    Find = false;
                }
                else
                {
                    temp += Character.toString(str.charAt(index+1));
                    Find = false;
                }
            }
            else
            {
                if (index+1>=str.length())
                {
                    temp = Character.toString(str.charAt(index));
                }
                else
                {
                    temp = Character.toString(str.charAt(index));
                    temp += Character.toString(str.charAt(index+1));
                }
            }
            for (String s : dictionary) {
                if (index + 1 >= str.length()) {
                    break;
                }
                if (temp.equals(s)) {
                    Find = true;
                    index++;
                    break;
                }
            }
            if (!Find)
            {
                boolean find = false;
                for (int i=0;i<dictionary.size();i++)
                {
                    if (temp.equals(dictionary.get(i)))
                    {
                        find = true;
                        index ++;
                        code.add(Integer.toString(dictionary.indexOf(dictionary.get(i))));
                        break;
                    }
                }
                if (!find)
                {
                    dictionary.add(temp);
                    String copy = temp;
                    temp = "";
                    for (int i=0;i<copy.length()-1;i++)
                    {
                        temp += Character.toString(copy.charAt(i));
                    }
                    for (int i=0;i<dictionary.size();i++)
                    {
                        if (temp.equals(dictionary.get(i)))
                        {
                            find = true;
                            index ++;
                            code.add(Integer.toString(dictionary.indexOf(dictionary.get(i))));
                            break;
                        }
                    }
                    if (!find)
                    {
                        System.out.println("Error");
                        System.exit(-1);
                    }
                }
            }
        }
        return code;
    }

    public static void main(String[] args) throws IOException
    {
        // Read file
        InputStream inFile = new FileInputStream("Q1.in");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inFile));
        Vector<String> input = new Vector<>();
        String str;
        while((str = bufferedReader.readLine()) != null)
        {
            input.add(str);
        }
        inFile.close();
        bufferedReader.close();

        // Output file
        OutputStream outFile = new FileOutputStream("Q1.out");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outFile));
        for (int i=0; i<input.size(); i++)
        {
            // Build dictionary
            Vector<String> dictionary = LZW_dictionary(input.get(i));
            // Build code sequence
            Vector<String> code = LZW_output(input.get(i));
            bufferedWriter.write("Input sequence: " + input.get(i) + "\n");
            bufferedWriter.write("Output sequence: ");
            for (int j=0; j<code.size(); j++)
            {
                bufferedWriter.write(code.get(j) +" ");
            }
            bufferedWriter.write("\n"+"Dictionary:"+"\n");
            for (int j=0; j<dictionary.size(); j++)
            {
                bufferedWriter.write(j + " " + dictionary.get(j) + "\n");
            }
            bufferedWriter.write("\n");
            // Print
            System.out.println("Input sequence: " + input.get(i));
            System.out.println("Output sequence: ");
            for (int j=0; j<code.size(); j++)
            {
                System.out.println(code.get(j) +" ");
            }
            System.out.println("Dictionary:");
            for (int j=0; j<dictionary.size(); j++)
            {
                System.out.println(j + " " + dictionary.get(j));
            }
            System.out.print("\n");
        }
        bufferedWriter.close();
    }
}