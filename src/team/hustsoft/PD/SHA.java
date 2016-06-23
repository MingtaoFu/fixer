/**
 * SHA加密算法
 * SHA.encode(str)即可返回加密后的字符串
 */
package team.hustsoft.PD;
import java.security.MessageDigest;

public class SHA{
  private static String byte2String(byte[] bt) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < bt.length; i++) {
			String shaHex = Integer.toHexString(bt[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
    }
    return hexString.toString();
  }
  public static String encode(String str) {
    try {
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      sha.update(str.getBytes());
      byte[] bt = sha.digest();
      return byte2String(bt);
    } catch (Exception err) {
      System.out.println(err);
    }
    return null;
  }
}
