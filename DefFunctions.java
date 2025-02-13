public interface DefFunctions {

    /**
     * returns the matrix of type int[][] a MatricesSetup object
     * **/
    public int[][] getMatrix();

    /**
     * returns the traceback matrix of type char[][] of a MatricesSetup object
     * **/
    public char[][] getTraceback();

    /**
     * fills the matrix of a MatricesSetup object according to the Needleman-Wunsch algorithm
     * simultaneously fills in the traceback matrix of the MatricesSetup object.
     * **/
    public void fillMatrix(int gapPenalty, int matchScore, int mismatchScore);

    public void printMatrix();

    public void printTraceback();

    /**
     * returns a String[] object containing the two aligned sequences, i.e. with gaps inserted according to the traceback matrix
     * **/
    public String[] getAlignment();


}
