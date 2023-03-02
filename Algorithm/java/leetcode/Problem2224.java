public class Problem2224 {
        public int convertTime(String current, String correct) {
            int diff = (Integer.parseInt(correct.substring(0, 2)) - Integer.parseInt(current.substring(0, 2))) * 60 +
                (Integer.parseInt(correct.substring(3)) - Integer.parseInt(current.substring(3)));
    
            int ops = 0;
    
            while (diff > 0) {
                if (diff >= 60) {
                    diff -= 60;
                } else if (diff >= 15) {
                    diff -= 15;
                } else if (diff >= 5) {
                    diff -= 5;
                } else {
                    diff -= 1;
                }
                ops++;
            }
    
            return ops;
        }
}