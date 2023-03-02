public class Problem2266 {
    private final static int MOD = 1000000007;

    public int countTexts(String pressedKeys) {
        double cnt = 1;
        char reservedChar = pressedKeys.charAt(0);
        int reservedCnt = 1;

        for (int i=1; i<pressedKeys.length(); i++) {
            char pressed = pressedKeys.charAt(i);
            if (pressed == reservedChar) {
                reservedCnt++;
            } else {
                cnt *= calculate(reservedChar, reservedCnt);
                
                System.out.println(cnt);
                cnt %= MOD;
                reservedCnt = 1;
                reservedChar = pressed;
            }
        }

        cnt *= calculate(reservedChar, reservedCnt);
        cnt %= MOD;
        return (int)cnt;
    }

    private int calculate(char key, int cnt) {
        int v = 1;
        if (key == '7' || key == '9') {
            v = fourFibo(cnt);
        } else {
            v = threeFibo(cnt);
            
        }
        System.out.println(v);
        return v;
    }

    int[] cache3 = new int[100_001];
    private int threeFibo(int n) {
        if (cache3[n] != 0) return cache3[n];
        if (n == 1) return 1;
        if (n ==2) return 2;
        if (n == 3) return 4;
        
        double v = threeFibo(n-1);
        v += threeFibo(n-2);
        v += threeFibo(n-3);
        cache3[n] = (int)(v % MOD);
        return cache3[n];
    }

    int[] cache4 = new int[100_001];
    private int fourFibo(int n) {
        if (cache4[n] != 0) return cache4[n];
        if (n == 1) return 1;
        if (n ==2) return 2;
        if (n == 3) return 4;
        if (n == 4) return 8;
       
        double v = fourFibo(n-1);
        v += fourFibo(n-2); 
        v += fourFibo(n-3); 
        v += fourFibo(n-4); 
        cache4[n] = (int)(v % MOD);
        return cache4[n];
    }
}