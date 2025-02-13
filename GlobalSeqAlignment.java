public class GlobalSeqAlignment {

    public static class MatricesSetup {

        private String sequence1;
        private String sequence2;
        private int[][] matrix;
        private char[][] traceback;
        String[] alignment;

        public MatricesSetup(String sequence1, String sequence2){
            this.sequence1 = sequence1;
            this.sequence2 = sequence2;
            this.matrix = new int[sequence1.length() + 1][sequence2.length() + 1];
            this.traceback = new char[sequence1.length() + 1][sequence2.length() + 1];
        }

        public int[][] getMatrix() {
            return this.matrix;
        }

        public char[][] getTraceback() {
            return this.traceback;
        }

        public void fillMatrix(int gapPenalty, int matchScore, int mismatchScore) {
            int[][] matrix = this.getMatrix();

            matrix[0][0] = 0;
            for (int i = 1; i <= sequence1.length() ; i++) {
                matrix[i][0] = matrix[i - 1][0] + gapPenalty;
                traceback[i][0] = 't';
            }
            for (int j = 1; j <= sequence2.length(); j++) {
                matrix[0][j] = matrix[0][j - 1] + gapPenalty;
                traceback[0][j] = 'l';
            }

            for (int i = 1; i <= sequence1.length(); i++) {
                traceback[i][0] = 't';
            }
            for (int j = 1; j <= sequence2.length(); j++) {
                traceback[0][j] = 'l';
            }

            for (int i = 1; i <= sequence1.length(); i++) {
                for (int j = 1; j <= sequence2.length(); j++) {

                    int topScore = matrix[i - 1][j] + gapPenalty;
                    int leftScore = matrix[i][j - 1] + gapPenalty;
                    int diagScore;
                    if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
                        diagScore = matrix[i - 1][j - 1] + matchScore;
                    } else {
                        diagScore = matrix[i - 1][j - 1] + mismatchScore;
                    }

                   if (diagScore >= Math.max(topScore, leftScore)) {
                       matrix[i][j] = diagScore;
                       traceback[i][j] = 'd';
                   } else if (topScore >= Math.max(leftScore, diagScore)) {
                       matrix[i][j] = topScore;
                       traceback[i][j] = 't';
                   } else if (leftScore >= Math.max(topScore, diagScore)) {
                       matrix[i][j] = leftScore;
                       traceback[i][j] = 'l';
                   }
                }
            }


        }

        public void printMatrix() {
            for (int i = 0; i <= sequence1.length(); i++) {
                for (int j = 0; j <= sequence2.length(); j++) {
                    System.out.print(" [" + matrix[i][j] + "] ");
                }
                System.out.println();
            }
        }

        public void printTraceback() {
            for (int i = 0; i < this.getTraceback().length; i++) {
                for (int j = 0; j < this.getTraceback()[i].length; j++) {
                    System.out.print( " [ " + this.getTraceback()[i][j] + " ] ");
                }
                System.out.println();
            }
        }

        public String[] getAlignment() {

           StringBuilder line1 = new StringBuilder();
           StringBuilder line2 = new StringBuilder();

           String[] result = new String[2];

           int i = sequence1.length();
           int j = sequence2.length();

           while (i > 0 || j > 0) {

               if (i > 0 && j > 0 && traceback[i][j] == 'd') {
                   line1.append(sequence1.charAt(i - 1));
                   line2.append(sequence2.charAt(j - 1));
                   i--;
                   j--;
               } else if (i > 0 && traceback[i][j] == 't') {
                   line1.append(sequence1.charAt(i - 1));
                   line2.append("-");
                   i--;
               } else if (j > 0 && traceback[i][j] == 'l') {
                   line1.append("-");
                   line2.append(sequence2.charAt(j - 1));
                   j--;
               }

           }
           result[0] = line1.toString();
           result[1] = line2.toString();
           return result;
        }


    }


}
