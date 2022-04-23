package project;

import java.util.regex.Pattern;

public class count {static int countOccurrences(String str, String word)
{
    // split the string by spaces in a
    String a[] = str.split(" ");
 
    // search for pattern in a
    int count = 0;
    for (int i = 0; i < a.length; i++)
    {
    // if match found increase count
    if (word.equalsIgnoreCase(a[i]))
        count++;
    }
 
    return count;
}
 
// Driver code
public static void main(String args[])
{
    String str = "This is Praveen. Praveen is a good automation expert. Praveen rocks. ";
    String word = "is";
    System.out.println(countOccurrences(str, word));
}
}
 

	