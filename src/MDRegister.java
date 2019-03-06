public class MDRegister {
    private long A;
    private long B;
    private long C;
    private long D;
    public long CV[];

    public MDRegister(long A, long B, long C, long D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        CV = new long[4];
        CV[0] = A;
        CV[1] = B;
        CV[2] = C;
        CV[3] = D;
    }

    public long[] CV0() {
        long result[] = new long[4];
        result[0] = A;
        result[1] = B;
        result[2] = C;
        result[3] = D;
        return result;
    }
}
