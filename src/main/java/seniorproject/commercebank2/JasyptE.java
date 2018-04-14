package seniorproject.commercebank2;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptE{

    public String encrypt(String plainText){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        String encryptedText = textEncryptor.encrypt(plainText);
        return encryptedText;
    }

    public String decrypt(String encryptedText){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        String decryptedText = textEncryptor.decrypt(encryptedText);
        return decryptedText;
    }

}