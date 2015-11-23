package com.qp.common.security;


import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author haiping
 *
 */
public abstract class DESCoder extends Coder
{
  public static final String ALGORITHM = "TripleDES";

  private static Key toKey(byte[] key)
    throws Exception
  {
    SecretKey secretKey = new SecretKeySpec(key, "TripleDES");

    return secretKey;
  }

  public static byte[] decrypt(byte[] data, String key)
    throws Exception
  {
    Key k = toKey(decryptBASE64(key));

    Cipher cipher = Cipher.getInstance("TripleDES");
    cipher.init(2, k);

    return cipher.doFinal(data);
  }

  public static byte[] encrypt(byte[] data, String key)
    throws Exception
  {
    Key k = toKey(decryptBASE64(key));
    Cipher cipher = Cipher.getInstance("TripleDES");
    cipher.init(1, k);

    return cipher.doFinal(data);
  }

  public static String initKey()
    throws Exception
  {
    return initKey(null);
  }

  public static String initKey(String seed)
    throws Exception
  {
    SecureRandom secureRandom = null;

    if (seed != null)
      secureRandom = new SecureRandom(decryptBASE64(seed));
    else {
      secureRandom = new SecureRandom();
    }

    KeyGenerator kg = KeyGenerator.getInstance("TripleDES");
    kg.init(secureRandom);

    SecretKey secretKey = kg.generateKey();

    return encryptBASE64(secretKey.getEncoded());
  }
}