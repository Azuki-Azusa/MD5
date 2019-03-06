public class MD5 {
    public MDRegister CV;
    private long X[];
    private int s[] = {
            7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21
    };
    private long T[] = {
            0xd76aa478L, 0xe8c7b756L, 0x242070dbL, 0xc1bdceeeL,
            0xf57c0fafL, 0x4787c62aL, 0xa8304613L, 0xfd469501L,
            0x698098d8L, 0x8b44f7afL, 0xffff5bb1L, 0x895cd7beL,
            0x6b901122L, 0xfd987193L, 0xa679438eL, 0x49b40821L,
            0xf61e2562L, 0xc040b340L, 0x265e5a51L, 0xe9b6c7aaL,
            0xd62f105dL, 0x02441453L, 0xd8a1e681L, 0xe7d3fbc8L,
            0x21e1cde6L, 0xc33707d6L, 0xf4d50d87L, 0x455a14edL,
            0xa9e3e905L, 0xfcefa3f8L, 0x676f02d9L, 0x8d2a4c8aL,

            0xfffa3942L, 0x8771f681L, 0x6d9d6122L, 0xfde5380cL,
            0xa4beea44L, 0x4bdecfa9L, 0xf6bb4b60L, 0xbebfbc70L,
            0x289b7ec6L, 0xeaa127faL, 0xd4ef3085L, 0x04881d05L,
            0xd9d4d039L, 0xe6db99e5L, 0x1fa27cf8L, 0xc4ac5665L,
            0xf4292244L, 0x432aff97L, 0xab9423a7L, 0xfc93a039L,
            0x655b59c3L, 0x8f0ccc92L, 0xffeff47dL, 0x85845dd1L,
            0x6fa87e4fL, 0xfe2ce6e0L, 0xa3014314L, 0x4e0811a1L,
            0xf7537e82L, 0xbd3af235L, 0x2ad7d2bbL, 0xeb86d391L
    };
    public MD5(long A, long B, long C, long D, String str) {
        CV = new MDRegister(A, B, C, D);
        X = new long[16];
        for (int i = 0; i < 16; i ++) {
            String substr = str.substring(i * 32, i * 32 + 32);
            X[i] = 0x00000000L;
            for (int j = 1; j <= 4; j ++) {
                for (int k = 32 - 8 * j; k < 40 - 8 * j; k ++) {
                    X[i] = X[i] << 1;
                    if (substr.charAt(k) == '1') X[i] = X[i] | 0x00000001L;
                }
            }
        }
    }

    public void md5() {
        for (int i = 0; i < 16; i ++) {
            this.Fstep(CV.CV[0], CV.CV[1], CV.CV[2], CV.CV[3], i);
        }

        for (int i = 16; i < 32; i ++) {
            this.Gstep(CV.CV[0], CV.CV[1], CV.CV[2], CV.CV[3], i);
        }

        for (int i = 32; i < 48; i ++) {
            this.Hstep(CV.CV[0], CV.CV[1], CV.CV[2], CV.CV[3], i);
        }

        for (int i = 48; i < 64; i ++) {
            this.Istep(CV.CV[0], CV.CV[1], CV.CV[2], CV.CV[3], i);
        }


        for (int i = 0; i < 4; i ++) {
            CV.CV[i] = CV.CV[i] + CV.CV0()[i];
            CV.CV[i] = CV.CV[i] & 0xFFFFFFFFL;
        }
    }

    private long F(long b, long c, long d) {
        return ((b & c) | ((~b) & d));
    }

    private long G(long b, long c, long d) {
        return ((b & d) | (c & (~d)));
    }

    private long H(long b, long c, long d) {
        return (b ^ c ^ d);
    }

    private long I(long b, long c, long d) {
        return (c ^ (b | (~d)));
    }

    private void Fstep(long A, long B, long C, long D, int i) {
        long temp1 = A + (F(B, C, D)&0xFFFFFFFFL) + X[i] + T[i];
        temp1 = temp1 & 0xFFFFFFFFL;
        long left = temp1 << s[i];
        long right = temp1 >>> (32 - s[i]);
        long temp2 = left | right;
        A = temp2 + B;
        A = A & 0xFFFFFFFFL;
        this.CV.CV[0] = D;
        this.CV.CV[1] = A;
        this.CV.CV[2] = B;
        this.CV.CV[3] = C;
    }

    private void Gstep(long A, long B, long C, long D, int i) {
        long temp1 = A + (G(B, C, D)&0xFFFFFFFFL) + X[(1 + 5 * i) % 16] + T[i];
        temp1 = temp1 & 0xFFFFFFFFL;
        long left = temp1 << s[i];
        long right = temp1 >>> (32 - s[i]);
        long temp2 = left | right;
        A = temp2 + B;
        A = A & 0xFFFFFFFFL;
        this.CV.CV[0] = D;
        this.CV.CV[1] = A;
        this.CV.CV[2] = B;
        this.CV.CV[3] = C;
    }

    private void Hstep(long A, long B, long C, long D, int i) {
        long temp1 = A + (H(B, C, D)&0xFFFFFFFFL) + X[(5 + 3 * i) % 16] + T[i];
        temp1 = temp1 & 0xFFFFFFFFL;
        long left = temp1 << s[i];
        long right = temp1 >>> (32 - s[i]);
        long temp2 = left | right;
        A = temp2 + B;
        A = A & 0xFFFFFFFFL;
        this.CV.CV[0] = D;
        this.CV.CV[1] = A;
        this.CV.CV[2] = B;
        this.CV.CV[3] = C;
    }

    private void Istep(long A, long B, long C, long D, int i) {
        long temp1 = A + (I(B, C, D)&0xFFFFFFFFL) + X[(7 * i) % 16] + T[i];
        temp1 = temp1 & 0xFFFFFFFFL;
        long left = temp1 << s[i];
        long right = temp1 >>> (32 - s[i]);
        long temp2 = left | right;
        A = temp2 + B;
        A = A & 0xFFFFFFFFL;
        this.CV.CV[0] = D;
        this.CV.CV[1] = A;
        this.CV.CV[2] = B;
        this.CV.CV[3] = C;
    }

}
