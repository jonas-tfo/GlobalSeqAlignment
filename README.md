# GlobalSeqAlignment
An implementation of global sequence alignment using the Needleman-Wunsch algorithm.

Main functionabilities:

MatricesSetup: 
define two sequences to align using the constructor

fillMatrix: 
fills the matrix and the traceback matrix according to the Needleman-Wunsch 
algorithm, using a gap penalty, match score and mismatch score of choice

getAlignment:
retrieves the optimal global alignment using the traceback matrix and returns a list containing the two aligned sequences 
