package zhangyi.refactoring.codegen.utility;

public class Strings {
    public static String capFirst(String str) {
        int firstIndex = 0;
        int strLength;
        for (strLength = str.length(); firstIndex < strLength && Character.isWhitespace(str.charAt(firstIndex)); ++firstIndex) {
        }
        if (firstIndex < strLength) {
            StringBuilder b = new StringBuilder(str);
            b.setCharAt(firstIndex, Character.toUpperCase(str.charAt(firstIndex)));
            str = b.toString();
        }
        return str;
    }

    public static String uncapFirst(String str) {
        int firstIndex = 0;
        int strLength;
        for (strLength = str.length(); firstIndex < strLength && Character.isWhitespace(str.charAt(firstIndex)); ++firstIndex) {
        }
        if (firstIndex < strLength) {
            StringBuilder b = new StringBuilder(str);
            b.setCharAt(firstIndex, Character.toLowerCase(str.charAt(firstIndex)));
            str = b.toString();
        }
        return str;
    }

    public static String toLowerCase(String string)
    {
        boolean changed = false;
        char[] chars = string.toCharArray();

        for (int i = 0; i != chars.length; i++)
        {
            char ch = chars[i];
            if ('A' <= ch && 'Z' >= ch)
            {
                changed = true;
                chars[i] = (char)(ch - 'A' + 'a');
            }
        }

        if (changed)
        {
            return new String(chars);
        }

        return string;
    }
}
