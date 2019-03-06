import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class ReadFile {
    public String readToString(String fileName) {
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer result = new StringBuffer();
        for(int i = 0; i < filecontent.length; i ++)
        {
            StringBuilder temp = new StringBuilder(Long.toString(filecontent[i] & 0xff, 2));
            for(int j = temp.length(); j % 8 != 0; j ++) {
                temp.insert(0, "0");
            }
            result.append(temp);
        }
        return result.toString();
    }

    /*
    public static void main(String args[]){
        ReadFile test = new ReadFile();
        String str = test.readToString("C:\\Users\\azu\\OneDrive\\Course\\web安全技术\\MD5\\M.txt");
        System.out.println(str);
        System.out.println(str.length());
    }
    */
}
