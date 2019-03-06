public class Split {
    public String padding(String s1) {
        int lengthK = s1.length();
        int rest = 512 - lengthK % 512;
        int lengthP = rest >= 64 ? (rest - 64) : (rest + 448);
        int mod = (new Double(Math.pow(2, 64))).intValue();
        int tail = lengthK % mod;
        StringBuilder K64 = new StringBuilder();
        for(int i = 0; i < 8; i ++) {
            long temp = tail & 0xFFL;
            StringBuilder s = new StringBuilder(Long.toString(temp,2));
            while (s.length() != 8) s.insert(0, '0');
            K64.append(s);
            tail = tail >> 8;
        }
        StringBuilder Pstr = new StringBuilder("1");
        for(int i = 1; i < lengthP; i ++) {
            Pstr.append("0");
        }
        return s1 + Pstr + K64;
    }

    public String[] split(String str) {
        int num = str.length() / 512;
        String[] result = new String[num];
        for(int i = 0; i < num; i ++) {
            result[i] = str.substring(i * 512, i * 512 + 512);
        }
        return result;
    }

    /*
    public static void main(String args[]) {
        ReadFile test = new ReadFile();
        String s1 = test.readToString("C:\\Users\\azu\\OneDrive\\Course\\web安全技术\\MD5\\M.txt");
        Split test2 = new Split();
        String s2 = test2.padding(s1);
        System.out.println(s2);
        System.out.println(s2.length());
        String[] s3 = test2.split(s2);
        for(int i = 0; i < s3.length; i ++) {
            System.out.println(s3[i].length() + " " + s3[i]);
        }
    }
    */
}
