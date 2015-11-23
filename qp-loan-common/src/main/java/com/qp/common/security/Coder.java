package com.qp.common.security;


import java.security.MessageDigest;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author haiping
 *
 */
@SuppressWarnings("restriction")
public abstract class Coder
{
  public static final String KEY_SHA = "SHA";
  public static final String KEY_MD5 = "MD5";

  public static byte[] decryptBASE64(String key)
    throws Exception
  {
    return new BASE64Decoder().decodeBuffer(key);
  }

  public static String encryptBASE64(byte[] key)
    throws Exception
  {
    return new BASE64Encoder().encode(key);
  }

  public static byte[] encryptMD5(byte[] data)
    throws Exception
  {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(data);

    return md5.digest();
  }

  public static byte[] encryptSHA(byte[] data)
    throws Exception
  {
    MessageDigest sha = MessageDigest.getInstance("SHA");
    sha.update(data);

    return sha.digest();
  }

  public static String asHex(byte[] buf)
  {
    StringBuffer strbuf = new StringBuffer(buf.length * 2);

    for (int i = 0; i < buf.length; i++) {
      if ((buf[i] & 0xFF) < 16) {
        strbuf.append("0");
      }
      strbuf.append(Long.toString(buf[i] & 0xFF, 16));
    }

    return strbuf.toString();
  }
}