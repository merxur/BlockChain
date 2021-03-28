//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.zhiyun.blockchain.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {
    public static final String ALGORITHM = "RSA";
    public static final int KEY_SIZE = 1024;
    public static Decoder decoder = Base64.getDecoder();
    public static Encoder encoder = Base64.getEncoder();

    public RSAUtil() {
    }

    public static HashMap<String, String> generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        KeyPair keyPair;
        try {
            keyPairGenerator.initialize(1024, new SecureRandom(UUID.randomUUID().toString().replaceAll("-", "").getBytes()));
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (InvalidParameterException var5) {
            throw var5;
        } catch (NullPointerException var6) {
            throw var6;
        }

        RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
        HashMap<String, String> map = new HashMap();
        map.put("private", encoder.encodeToString(rsaPrivateKey.getEncoded()));
        map.put("public", encoder.encodeToString(rsaPublicKey.getEncoded()));
        return map;
    }

    public static PublicKey getPublicKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(decoder.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        return publicKey;
    }

    public static PrivateKey getPrivateKeyFromString(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec x509KeySpec = new PKCS8EncodedKeySpec(decoder.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(x509KeySpec);
        return privateKey;
    }

    public static String encryptByPublic(byte[] content, PublicKey publicKey) {
        if (publicKey == null) {
        }

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, publicKey);
            int splitLength = ((RSAPublicKey)publicKey).getModulus().bitLength() / 8 - 11;
            byte[][] arrays = splitBytes(content, splitLength);
            StringBuffer stringBuffer = new StringBuffer();
            byte[][] var6 = arrays;
            int var7 = arrays.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                byte[] array = var6[var8];
                stringBuffer.append(bytesToHexString(cipher.doFinal(array)));
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException var10) {
        } catch (NoSuchPaddingException var11) {
        } catch (InvalidKeyException var12) {
        } catch (BadPaddingException var13) {
        } catch (IllegalBlockSizeException var14) {
        }

        return null;
    }

    public static String encryptByPrivate(byte[] content, PrivateKey privateKey) {
        if (privateKey == null) {
        }

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, privateKey);
            int splitLength = ((RSAPrivateKey)privateKey).getModulus().bitLength() / 8 - 11;
            byte[][] arrays = splitBytes(content, splitLength);
            StringBuffer stringBuffer = new StringBuffer();
            byte[][] var6 = arrays;
            int var7 = arrays.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                byte[] array = var6[var8];
                stringBuffer.append(bytesToHexString(cipher.doFinal(array)));
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException var10) {
        } catch (NoSuchPaddingException var11) {
        } catch (InvalidKeyException var12) {
        } catch (BadPaddingException var13) {
        } catch (IllegalBlockSizeException var14) {
        }

        return null;
    }

    public static String decryptByPrivate(String content, PrivateKey privateKey) {
        if (privateKey == null) {
        }

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, privateKey);
            int splitLength = ((RSAPrivateKey)privateKey).getModulus().bitLength() / 8;
            byte[] contentBytes = hexStringToBytes(content);
            byte[][] arrays = splitBytes(contentBytes, splitLength);
            StringBuffer stringBuffer = new StringBuffer();
            byte[][] var7 = arrays;
            int var8 = arrays.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                byte[] array = var7[var9];
                stringBuffer.append(new String(cipher.doFinal(array)));
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException var11) {
        } catch (NoSuchPaddingException var12) {
        } catch (InvalidKeyException var13) {
        } catch (BadPaddingException var14) {
        } catch (IllegalBlockSizeException var15) {
        }

        return null;
    }

    public static String decryptByPublic(String content, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, publicKey);
            int splitLength = ((RSAPublicKey)publicKey).getModulus().bitLength() / 8;
            byte[] contentBytes = hexStringToBytes(content);
            byte[][] arrays = splitBytes(contentBytes, splitLength);
            StringBuffer stringBuffer = new StringBuffer();
            byte[][] var7 = arrays;
            int var8 = arrays.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                byte[] array = var7[var9];
                stringBuffer.append(new String(cipher.doFinal(array)));
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException var11) {
            var11.printStackTrace();
        } catch (NoSuchPaddingException var12) {
            var12.printStackTrace();
        } catch (InvalidKeyException var13) {
            var13.printStackTrace();
        } catch (BadPaddingException var14) {
            var14.printStackTrace();
        } catch (IllegalBlockSizeException var15) {
            var15.printStackTrace();
        }

        return null;
    }

    public static byte[][] splitBytes(byte[] bytes, int splitLength) {
        int remainder = bytes.length % splitLength;
        int quotient = remainder != 0 ? bytes.length / splitLength + 1 : bytes.length / splitLength;
        byte[][] arrays = new byte[quotient][];
        byte[] array = null;

        for(int i = 0; i < quotient; ++i) {
            if (i == quotient - 1 && remainder != 0) {
                array = new byte[remainder];
                System.arraycopy(bytes, i * splitLength, array, 0, remainder);
            } else {
                array = new byte[splitLength];
                System.arraycopy(bytes, i * splitLength, array, 0, splitLength);
            }

            arrays[i] = array;
        }

        return arrays;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String temp = null;

        for(int i = 0; i < bytes.length; ++i) {
            temp = Integer.toHexString(255 & bytes[i]);
            if (temp.length() < 2) {
                sb.append(0);
            }

            sb.append(temp);
        }

        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hex) {
        int len = hex.length() / 2;
        hex = hex.toUpperCase();
        byte[] result = new byte[len];
        char[] chars = hex.toCharArray();

        for(int i = 0; i < len; ++i) {
            int pos = i * 2;
            result[i] = (byte)(toByte(chars[pos]) << 4 | toByte(chars[pos + 1]));
        }

        return result;
    }

    private static byte toByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    public static String getSHA256(String str) {
        String encodestr = "";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
        }

        return encodestr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;

        for(int i = 0; i < bytes.length; ++i) {
            temp = Integer.toHexString(bytes[i] & 255);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }

            stringBuffer.append(temp);
        }

        return stringBuffer.toString();
    }

    public static PrivateKey readPemRsaPrivateKey(String pemFilename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String pemString = File2String(pemFilename);
        System.out.println(pemString);
        byte[] decoded = Base64.getMimeDecoder().decode(pemString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static String File2String(String fileName) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        char[] buffer = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        buffer = new char[(int)file.length()];
        int i = 0;

        for(int c = bufferedReader.read(); c != -1; c = bufferedReader.read()) {
            buffer[i++] = (char)c;
        }

        return new String(buffer);
    }

    public static String private2public(String privateKeyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(privateKeyString);
        PrivateKey privateKey = getPrivateKeyFromString(privateKeyString);
        RSAPrivateCrtKey privateCrtKey = (RSAPrivateCrtKey)privateKey;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privateCrtKey.getModulus(), privateCrtKey.getPublicExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
        return encoder.encodeToString(publicKey.getEncoded());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String myPrivateKeyPemFileName = "d:/2.pem";
        PrivateKey privateKey = getPrivateKeyFromString("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCA9i/KuuyRpGAkUF2WMG4om4NPn0W90JjSE4+HHKelDxGgu7VdZ+mV7rIo+XnOlK/IagmhjQvztTOISVlhosydo8YpFjZ1T0GjHh4WLvigQka4XBzGuHgHx17Fx2tfu89jEWg4r90voqfJL/3m1wBA5zCdsZUXdIAm+u+Zc++JKA85ZuZ6zBg3RxIfI2grMQoGw4T25hNzMYbiyF8v2RaJLw1nWe16bE9GIRIvU8hURyh1NLqz4D8D3ZPPK/n9PH1UPXJL0dEkO38y92yOVsNexjkAWa+9LyGo5mKybox6pnPQBLIDSd/P1xgS/oCTwpvcjomJUxHg4VaYMQPPPd4TAgMBAAECggEAdx7wVlsDWiRziheR2sExf+DmEg4U5ZhhEOtwkGUvlY+twMKibH0njo7FkoOet+sv0/fenZMjjyG5UBIwtbcjOhmlWR5jf0iOYJXZRLRug9m0aWbsTcLnXIgUSqxBylQ45FGX64TG0tF0Gqpx5WCDo2/pwD/gb6H1Hgx8dUpZ4F4Iugn2a0wIehTkYCm5/36Av8GS8y65vgIvCfMVFIkKJx0v965140W5CFIUCWROwju1MDuRj2cQOAW46MTyS/Hjk4TFhafKP6ouSG34sEEgyY8fGmFMafm0RSKIduPBkoTFavBtA9hb2KgcMYSeIXNQUnIy8MbedTym7jGmQ7M5wQKBgQC4x5U/S0LeIUkMcPenx+s+RR946Mxl1v5kWtQPrjD5tDwBMRoTmiVt8Vm9OzjEAgES0wzmebSlnujMdhgZsYAQxAvl7E3umV2vDLPaK1tA1Dw1u8C4LOJDqzXkMNdLhUscTNzljukNHwe9LBSWyObgnAxDThdbHBuB7kZOYTtEPQKBgQCyqvtuu7Ad4PTK9kXk0ZgBJjX7qSQlHY2lRVT4W/Fld3FpKMHoe9nTB2TMF48f5mA9B632NwV5H5PH7fGO9kt2yoz9SoR14RVOBiPms4bScFquJH13qxGRkkfdXNUYVaWrR4U034hOItaBu7U9XQlUCvGOUBlyPkftcyz3ChnAjwKBgQCMcBR9AKSX01CDxjBUMtwWfc7HxJflJxw+BdJ7AHd0sYmRllItZ9De2LrN2f7w6l0Kg6GgYgvsfPSOwYrbrE6bb4VjgNBScfoEG3FuddlrUmNYq5Rz5pkpuSzCfGSFn49QuRKC3rU+JcFqA+C6IYNoXvHrDaUuuBQoEoMY+0liOQKBgFriQN32GUNJoBvyJGGeAD1ta35lbp52pwvP9cYUNvoPRSBh/Ck/fvEqzKpIvaRTctb4phQdruoLFwhXUE4RmReHNyrNEg5Ifabf1rlOzyESx/3XT/9khJ5RE79VrowfXROAYREhN9vPOuBpVGsvfUREANYMhrUnWz09Hj1X5PufAoGAByM4xbqIa2d+lDjTuRtyXNzo+stda+KcDEQ+NksIRG17tkBjh7KZnBy2KU0MeCphurUok1JnMg2uZjD+ZWmWy8QF0FDmrPI75vdvT+BKdsjDkNe4S74DLP0gng5t2Y3v6Z3ltjUOF4FPht14aANh5znOA88ip9v3+XEwrnZFT1s=");
        RSAPrivateCrtKey privateCrtKey = (RSAPrivateCrtKey)privateKey;
        PrivateKey myPrivateKey = readPemRsaPrivateKey(myPrivateKeyPemFileName);
        RSAPrivateCrtKey privk = (RSAPrivateCrtKey)myPrivateKey;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
        RSAPublicKeySpec publicKeySpec1 = new RSAPublicKeySpec(privateCrtKey.getModulus(), privateCrtKey.getPublicExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey myPublicKey = keyFactory.generatePublic(publicKeySpec);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec1);
        System.out.println(encoder.encodeToString(myPublicKey.getEncoded()));
        System.out.println(encoder.encodeToString(publicKey.getEncoded()));
    }
}
