package com.jsyh.common.server.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j 
@Data
public class AESUtil {
   
    private String content;		
   
    private String secret;	

    public AESUtil(String content) {
        this.content = content;
        this.secret = "iwofnoadnsa922342mnjaolkdsao9423242niosadopa_a02402sad";
    }

    public String encrypt () {
    	
        Key key = getKey();
        byte[] result = null;
        
        try{
          
            Cipher cipher = Cipher.getInstance("AES");		
            
            cipher.init(Cipher.ENCRYPT_MODE,key);		
            
            result = cipher.doFinal(content.getBytes("UTF-8"));		
            
        } catch (Exception e) {
            log.info("加密失败：" + e);
        }
        
        StringBuffer sb = new StringBuffer();		
        
        for (int i =0; i < result.length; i++) {
            String hex = Integer.toHexString(result[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        
        return  sb.toString();
    }

    public String decrypt () {
        
        if (content.length() < 1)		
            return null;
        
        byte[] result = new byte[content.length()/2];
        
        for (int i = 0;i< content.length()/2; i++) {
            int high = Integer.parseInt(content.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(content.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        Key key = getKey();
        byte[] decrypt = null;
        
        try{ 
            Cipher cipher = Cipher.getInstance("AES");		
            
            cipher.init(Cipher.DECRYPT_MODE,key);		
            
            decrypt = cipher.doFinal(result);		
        } catch (Exception e) {
            log.info("aes解密失败：" + e);
        }
        
        assert decrypt != null;
        return new String(decrypt);
    }

    private Key getKey () {
        SecretKey key = null;
        try {
        	
            KeyGenerator generator = KeyGenerator.getInstance("AES");
        
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(secret.getBytes());
            generator.init(128,random);
            
            key = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            log.info("加密key生成失败：" + e);
        }
        return key;
    }

}

