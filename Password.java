import java.util.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Password {
    static Scanner sc=new Scanner(System.in);
    static String CRED_FILE = "credentials.txt";
    static String SECRET= "thisis32charslongexactlyyay12345";
    static String Init="RandomRandomRand";

    public static void main(String args[])
    {
        while(true)
        {
        System.out.println("----Welcome to Forticheck-----");
        System.out.println("Enter your username: ");
        String user = sc.nextLine();
        System.out.println("Enter your password: ");
        String pass = sc.nextLine();
        if(authentication(user,pass)) {
            System.out.println("---Authentication Successful--- ");
            validuser(user);
        }
        else{
            System.out.println("---Authentication Failed--- ");
        }
    }
    }

    public static boolean authentication(String user, String pass)
    {
        try(BufferedReader br=new BufferedReader(new FileReader(CRED_FILE))) {
            String line;
            while((line=br.readLine())!=null)
            {
                if(!line.contains(":")) continue;

                String[] parts= line.split(":",2);
                String suser=parts[0].trim();
                String spass=parts[1].trim();

                if(user.equals(suser))
                {
                    String dpass = encryption(pass);
                    System.out.println("Encrypted input password: " + dpass);

                    if(dpass != null && dpass.equals(spass))
                    {
                        return true;
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void validuser(String user){
        System.out.println("----------");
        System.out.println("Welcome " + user);
        System.out.print("Select one from the below\n");
        System.out.print("1) Add user\n2) Remove User\n3) Password Strength Checker\n4) logout\n5)Encryption\n6) Decryption\n7) Exit\n->");
        int c=sc.nextInt();
        sc.nextLine();
        switch (c){
            case 1: adduser();
            validuser(user);
                break;
            case 2: removeuser();
            validuser(user);
                break;
            case 3: passwordchecker();
            validuser(user);
                break;
            case 4: System.out.println("Logging out --");
            return;
            case 5 :
                System.out.println("Enter a text to encrypt : ");
                String enc=sc.nextLine();
                String encResult = encryption(enc);
                if(encResult != null)
                    System.out.println("Encrypted text: " + encResult);
            validuser(user);
                break;
            case 6:
                System.out.println("Enter a text to decrypt : ");
                String dec=sc.nextLine();
                String decResult = decryption(dec);
                if(decResult != null)
                    System.out.println("Decrypted text: " + decResult);
            validuser(user);
                break;
            case 7:
            System.out.println("---Exiting the Forticheck---\nThank you");
            System.exit(0);
            break;

            default:
            System.out.println("Wrong input ");
            validuser(user);
                break;

        }


    }

    public static void adduser(){
        System.out.println("Only Admins can remove User\nEnter the password for admin:");
        String pad=sc.nextLine();
        if(pad.equals("admin"))
        {
        System.out.println("Enter new username: ");
        String newUser = sc.nextLine();
        System.out.println("Enter new password: ");
        String newPass = sc.nextLine();

        String encryptedPass = encryption(newPass);
        if(encryptedPass == null){
            System.out.println("Error encrypting password. User not added.");
            return;
        }

        try(FileWriter fw = new FileWriter(CRED_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(newUser + ":" + encryptedPass);
            System.out.println("User added successfully.");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Error writing to credentials file.");
        }
    }
    else{
        System.out.println("try again !");
        adduser();
    }
    }

    public static void removeuser(){
        System.out.println("Only Admins can remove User\nEnter the password for admin:");
        String pad=sc.nextLine();
        if(pad.equals("admin"))
        {
            System.out.println("Enter the Username to remove ");
            String user=sc.nextLine().trim();
            File inp=new File(CRED_FILE);
            File temp=new File("tmp.txt");
            boolean found=false;
            try(
            BufferedReader reader = new BufferedReader(new FileReader(inp));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp))
        ) 
                {
                    String line;
                    while((line=reader.readLine())!=null)
                    {
                        String[] parts=line.split(":",2);
                        String username=parts[0];
                        if(username.trim().equals(user))
                        {
                            found=true;
                            continue;
                        }
                        writer.write(line + System.lineSeparator());
                        writer.newLine();
                    }
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                    if(found)
                    {
                        inp.delete();
                        temp.renameTo(inp);
                        System.out.println("User removed : " + user);
                    }
                    else
                    {
                        temp.delete();
                        System.out.println("User not found ");
                    }
                }
                else{
            System.out.println("Try Again ! ");
            removeuser();
        }
                

        }
        


    public static void passwordchecker(){
        int check=0;
        System.out.println("Enter your Password");
        String pass =sc.nextLine();
        System.out.println("The password strength is checked bases on 5 factors");
        System.out.println("1- Password must be of 8 charcters\n2- One Upper case letter\n 3- One Lower case letter\n 4- One number\n5- One Special character\n");
        if(pass!=null)
        {
        boolean len=pass.length() >=8;
        boolean AZ=pass.matches(".*[A-Z].*") ;
        boolean az=pass.matches(".*[a-z].*");
        boolean num=pass.matches(".*[0-9].*");
        boolean sp=pass.matches(".*[!@#$%^&*()_+=\\\\-{}\\\\[\\\\]:;\\\"'<>,.?/~`|\\\\\\\\].*");
        if(len) check++;
        if(AZ) check++;
        if(az) check++;
        if(num) check++;
        if(sp) check++;
        int score=(int)(check/5.0) * 100;
        if(score==100)
        {
            System.out.println("Password is 100% Secure");
        }
        else if(score==60)
        {
            System.out.println("Password is moderately Secure");
        } 
        else {
            System.out.println("Password is not Secure");
        }
        System.out.println("Password Strength : " + score + "%");
        System.out.println("---Sugegstions---");
        if(!len) System.out.println("Password Must be 8 Characters Minimum");
        if(!AZ) check++; System.out.println("Use One Upper Case Letter A-Z");
        if(!az) check++;System.out.println("Use One Lower Case Letter a-z");
        if(!num) check++;System.out.println("Use one Number 0-9");
        if(!sp) check++;System.out.println("Use one Special Character !@#$%^&*()_+=:;}{[}]'<,>.?/'");
    }
    else {
        System.out.println("error");
    }
    }

    public static String encryption(String value){
        try {
            IvParameterSpec iv = new IvParameterSpec(Init.getBytes("UTF-8"));
            SecretKeySpec sp = new SecretKeySpec(SECRET.getBytes("UTF-8"), "AES");
            Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,sp,iv);
            byte[] encryptval=cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptval);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryption(String value){
        try{
            IvParameterSpec iv = new IvParameterSpec(Init.getBytes("UTF-8"));
            SecretKeySpec sp = new SecretKeySpec(SECRET.getBytes("UTF-8"), "AES");
            Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,sp,iv);
            byte[] decrypt=cipher.doFinal(Base64.getDecoder().decode(value));
            return new String(decrypt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
