package bitset;

import java.util.Arrays;


public class BitSetImpl implements MyBitSet {
    /*
    * BitSets are packed into arrays of "words."  Currently a word is
    * a long, which consists of 64 bits, requiring 6 address bits.
    * The choice of word size is determined purely by performance concerns.
    */
    private final static int ADDRESS_BITS_PER_WORD = 6;
    private final static int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
    
    /**
     *  The internal field corresponding to the serialField "bits"
     */
    private long[] words;

    /**
     *  The Number of words in the logical size of this BitSet.
     */
    private transient int wordsInUse = 0;

    public static void main(String[] args) {
        MyBitSet bs = new BitSetImpl(10);

        bs.set(1);        
        bs.set(3);        
        bs.set(5);        
        bs.set(7);        
        bs.set(9);        
        bs.set(10);        
        bs.clear(10);

        System.out.println(bs.contains(1));
        System.out.println(bs.contains(3));
        System.out.println(bs.contains(5));
        System.out.println(bs.contains(7));
        System.out.println(bs.contains(9));
        System.out.println(bs.contains(10));
        System.out.println(bs.contains(2));
        System.out.println(bs.contains(11));
    }

    /**
     * (1000000, 64) 를 initWords 의 인자로 준다.
     *  words[] 는 크기가 1인 long array 가 됨.
     */
    public BitSetImpl() {
        initWords(BITS_PER_WORD);
    }

    /**
     * 
     * @param nbits nbits / 64(long 의 size) + 1 만큼의 words 배열이 초기화 된다.
     */
    public BitSetImpl(int nbits) {
        if (nbits < 0) {
            throw new NegativeArraySizeException("nbits < 0: " +nbits);
        }
        initWords(nbits);
    }

    private void initWords(int nbits) {
        words = new long[wordIndex(nbits - 1) +1];
    }

    private static int wordIndex(int bitIndex) {
        return bitIndex >> ADDRESS_BITS_PER_WORD;
    }

    @Override
    public void set(int bitIndex) {
        if (bitIndex < 0) {
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
        } 
        int wordIndex = wordIndex(bitIndex);
        expandTo(wordIndex);

        words[wordIndex] |= (1L << bitIndex); // Restore invariants

        checkInvariants();
    }

    @Override
    public void clear(int bitIndex) {
        if (bitIndex < 0) {
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
        } 
        int wordIndex = wordIndex(bitIndex);
        if (wordIndex >= wordsInUse) {
            return;
        }

        words[wordIndex] &= ~(1L << bitIndex); // Restore invariants
        
        recalculateWordsInUse();
        checkInvariants();
    }

    @Override
    public boolean contains(int bitIndex) {
        if (bitIndex < 0) {
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);
        } 
        int wordIndex = wordIndex(bitIndex);
        return (wordIndex < wordsInUse) 
            && ((words[wordIndex] & (1L << bitIndex)) != 0); 
    }

    private void recalculateWordsInUse() {
        int i;
        for (i = wordsInUse-1; i>=0; i--) {
            if (words[i] != 0) {
                break;
            }
        }

        wordsInUse = i+1; // The new logical size;
    }
    private void expandTo(int wordIndex) {
        int wordsRequired = wordIndex + 1;
        if (wordsInUse < wordsRequired) {
            ensureCapacity(wordsRequired);
            wordsInUse = wordsRequired;
        }
    }

    private void ensureCapacity(int wordsRequired) {
        if (words.length < wordsRequired) {
            int request = Math.max(2 * words.length, wordsRequired);
            words = Arrays.copyOf(words, request);
        }
    }

    private void checkInvariants() {
        assert(wordsInUse == 0 || words[wordsInUse - 1] != 0);
        assert(wordsInUse >= 0 || wordsInUse <= words.length);
        assert(wordsInUse == words.length || words[wordsInUse] == 0);
    }
}