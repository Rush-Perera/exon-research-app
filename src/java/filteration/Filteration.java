package filteration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Sudeera Perera
 */
public class Filteration {

    public static String getFilteredUsername(String uname) {
        //uname = uname.replaceAll("\\W+", "");
        uname = uname.replace("'", "");
        uname = uname.replace("<", "");
        uname = uname.replace(">", "");
        uname = uname.replace("(", "");
        uname = uname.replace(")", "");
        uname = uname.replace("\"", "");
//        uname = uname.replace(".", "");
        uname = uname.replace(",", "");
        uname = uname.replace("`", "");
        uname = uname.replace("\t", "");
        return uname;
    }

    public static String getFilteredFilename(String filename) {
        filename = filename.replace("\\", "");
        filename = filename.replace("/", "");
        filename = filename.replace(",", "");
        filename = filename.replace("..", "");
        filename = filename.replace("...", "");
        filename = filename.replace("$", "");
        filename = filename.replace("#", "");
        filename = filename.replace("%", "");
        filename = filename.replace("{", "");
        filename = filename.replace("}", "");
        filename = filename.replace("\"", "");
        filename = filename.replace("'", "");
        filename = filename.replace("<", "");
        filename = filename.replace(">", "");
        filename = filename.replace("</", "");
        filename = filename.replace("!", "");
        filename = filename.replace("&", "");
        filename = filename.replace("&&", "");
        filename = filename.replace("|", "");
        filename = filename.replace("||", "");
        filename = filename.replace(";", "");
        return filename;
    }

    public static String getFilteredUrl(String url) {
         url = url.replace("\\", "");
        url = url.replace("\"", "");
        url = url.replace(",", "");
        url = url.replace("..", "");
        url = url.replace("'", "");
        url = url.replace("#", "");
        url = url.replace("%", "");
        url = url.replace("<", "");
        url = url.replace(">", "");
        url = url.replace("</", "");
        return url;
    }

    public static String getFilteredNIC(String nic) {
        nic = nic.replaceAll("\\D+", "");
        if (nic.length() == 9) {
            nic = nic + "v";
        }
        return nic;
    }

    public static String getFilteredNumber(String num) {
        num = num.replaceAll("\\D+", "");
        return num;
    }

    public static int getFilteredNumberInt(String num) {
        num = num.replaceAll("\\D+", "");
        return Integer.parseInt(num);
    }

    public static String getFilteredPIN(String num) {
        num = num.replaceAll("\\D+", "");
        return num;
    }

    public static int getFilteredPINInt(String num) {
        num = num.replaceAll("\\D+", "");
        return Integer.parseInt(num);
    }

    public static String getFilteredString(String searchString) {
        searchString = searchString.replace("'", "");
        searchString = searchString.replace("<", "");
        searchString = searchString.replace(">", "");
        searchString = searchString.replace("(", "");
        searchString = searchString.replace(")", "");
        searchString = searchString.replace("\"", "");
        searchString = searchString.replace(".", "");
        searchString = searchString.replace(",", "");
        return searchString;
    }

    public static boolean validatePassword(String password) {
        int passlen = password.length();
        boolean validity;
//        System.out.println(passlen);
        int specialCharCount = password.replaceAll("\\w+", "").length();
        int DigitCount = password.replaceAll("\\D+", "").length();
        int UpperCount = password.replaceAll("[^A-Z]", "").length();
        int LowerCount = password.replaceAll("[^a-z]", "").length();

        if (passlen > 7 && specialCharCount > 0 && DigitCount > 0 && UpperCount > 0 && LowerCount > 0) {
            System.out.println("Valid Password");
            validity = true;
        } else {
            System.out.println("Invalid Password");
            validity = false;
        }

        return validity;
    }

//    getAutoGeneratedPassword()
   

    public static boolean validateEmail(String email) {

        String regex = "^[A-Za-z0-9+_.-]+@(.+)[.](.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static String getFilteredSHA1HashedPassword(String password) {
        String hashedPassword = null;
        String salt = password.replaceAll("\\W+", "");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hashedPassword;
    }

    public static String getFilteredSHA256HashedPassword(String password) {
        String hashedPassword = null;
        String salt = password.replaceAll("\\W+", "");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hashedPassword;
    }

    public static String getFilteredSHA512HashedPassword(String password) {
        String hashedPassword = null;
        String salt = password.replaceAll("\\W+", "");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hashedPassword;
    }

}
