package univ.Bordeaux.View;

import java.util.ArrayList;

public class BoardShell {

    private ArrayList<Character> lines;

    public BoardShell(){
        this.lines = new ArrayList<>();
        for (int i = 75; i > 64; i--)
            this.lines.add((char)i); // ascii table char (uppercased letter)
    }

    /**
     * do convolution when in range else return 0
     * formula : g(x)=((k-sqrt((x-k)^(2))+sqrt((k-sqrt((x-k)^(2)))^(2)))/(2))
     * <pre>
     *     /\
     * ___/  \____
     * </pre>
     * @param x
     * @param k
     * @return only positive values outside convolution
     */
    private int absolute_cone(int x, int k){
        return (int) ((k-Math.sqrt((x-k)^(2))+Math.sqrt(Math.pow(k-Math.sqrt(Math.pow(x-k,2)),2)))/(2));
    }

    /**
     * do convolution function with integers only int opposite direction (\/)
     * <pre>
     *   \  /
     *    \/
     * </pre>
     * (f(x,k) = |-|x-k-b|+k| = |(-|x-k-b|+k)| )
     * @param x the variable passed
     * @param a the height of the "cone"
     * @param b the horizontal translation of the curve
     * @return int the result
     */
    private int coneReversed(int x, int a, int b){
        return Math.abs(-Math.abs(x-a-b)+a);
    }

    /**
     * do convolution function with integers only (/\)
     * <pre>
     *   /\
     *  /  \
     * </pre>
     * @param x the variable passed
     * @param a the height of the "cone"
     * @param b the horizontal translation of the curve
     * @return int the result
     */
    private int cone(int x, int a, int b){
        return Math.abs(Math.abs(x+a+b)-a);
    }

    public void showBoard(){
        int indexContent, spaceCount;
        System.out.println();
        for (int lines = 0; lines < 11; lines++) {
            for (spaceCount = 0; spaceCount < this.coneReversed(lines,0,5); spaceCount++)
                System.out.print(' ');
            if (lines > 5)
                System.out.print(this.lines.get(lines)+" \\");
            else if (lines == 5)
                System.out.print("F |");
            else
                System.out.print(this.lines.get(lines)+" /");
            for (indexContent = 0; indexContent < 6 + this.cone(lines,5,-10); indexContent++){
                System.out.print('.');
                if (indexContent!=10-spaceCount) System.out.print(' ');
            }
            if (lines > 5)
                System.out.print("/ " + (17-lines));
            else if (lines == 5)
                System.out.print("| ");
            else
                System.out.print("\\");
            System.out.println();
        }
        System.out.println("        1 2 3 4 5 6");
    }


}
