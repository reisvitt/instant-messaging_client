package utils;

public class GetData {

  public static String getUtil(String data) {
    String remove = data.split(" ")[0];
    return data.replaceAll(remove, "").trim();
  }

}
