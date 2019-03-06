import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ReadFile readfile = new ReadFile();
        Split split = new Split();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入文件地址：");
        String path = sc.nextLine();
        // String path = "C:\\Users\\azu\\OneDrive\\Course\\web安全技术\\MD5\\M.txt";
        String strB = readfile.readToString(path);
        String pad = split.padding(strB);
        String[] Y = split.split(pad);
        long[] result = new long[4];
        MDRegister CV0 = new MDRegister(0x67452301L, 0xefcdab89L, 0x98badcfeL, 0x10325476L);

        for (String s : Y) {
            MD5 md5 = new MD5(CV0.CV[0], CV0.CV[1], CV0.CV[2], CV0.CV[3], s);
            md5.md5();
            CV0 = md5.CV;
        }
        for (int i = 0; i < 4; i ++) {
            long[] b = new long[4];
            result[i] = 0xFFFFFFFFL;
            b[0] = CV0.CV[i] & 0xFFL;
            b[1] = CV0.CV[i] & 0xFF00L;
            b[2] = CV0.CV[i] & 0xFF0000L;
            b[3] = CV0.CV[i] & 0xFF000000L;
            result[i] = (b[0] << 24) | (b[1] << 8) | (b[2] >>> 8) | (b[3] >>> 24);
            System.out.print(Long.toString(result[i] & 0xFFFFFFFFL, 16));
        }
    }
}
