package univ.Bordeaux.Model;


class BitBoard {

    private long[] bitboard;
    private long[] valid_mask;

    public BitBoard(long[] board) {
        this.bitboard = board;
    }

    public BitBoard() {
        this.bitboard = new long[3];
        this.valid_mask = new long[3];
        int diameter = 11;
        int limit = diameter / 2;
        for (int letter = 0; letter <= 10; letter++) {
            for (int i = 0; i <= 10; i++) {
                if (letter - i >= -limit && letter - i <= limit) {
                    int index_bitboard = (letter * diameter + i) / 64;
                    int index_long = ((letter * diameter) + i) % 64;
                    valid_mask[index_bitboard] = valid_mask[index_bitboard] | (1L << index_long);
                }
            }
        }
        for (int i = 0; i <= 2; i++) {
            bitboard[i] = bitboard[i] & valid_mask[i];
        }
    }

    public void print() {
        int diameter = 11;
        for (int letter = 10; letter >= 0; letter--) {
            for (int s = 0; s < (10 - letter); s++) {
                System.out.print(" ");
            }
            for (int i = 0; i < diameter; i++) {
                int index_bitboard = ((letter * diameter) + i) / 64;
                int index_long = ((letter * diameter) + i) % 64;
                long bit = (valid_mask[index_bitboard] >> index_long) & 1L;
                System.out.print(bit == 1 ? "X " : ". ");
            }
            System.out.println();
        }
    }
}
