package Monitor;

/*************************************************************************
 *  Compilation:  javac Matrix.java
 *  Execution:    java Matrix
 *
 *  A bare-bones immutable data type for M-by-N matrices.
 *
 *************************************************************************/

final public class Matrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }

    /* Este funcion es para rotar el matirz de 1X9 que define la marcacion inicial
     * Recibe un entero entre 0 y 3, dependiendo de donde venga el misil, acomoda
     * dicha matriz. En resumen rota a izquierda los elementos desde 1 2 3 4,
     * y por separado rota 5 6 7 8, n lugares*/
    public void rotarIzquierda(int n){
    	double[] auxDisponibles=new double[4];
    	double[] auxOcupados=new double[4];
    	for(int i=0;i<4;i++){
    		{
    			auxDisponibles[i]=data[0][i+1];
    			auxOcupados[i]=data[0][i+5];}
    	}   	
    	for(int i=0;i<4;i++){
    	data[0][i+1]=auxDisponibles[(i+n)%4];
    	data[0][i+5]=auxOcupados[(i+n)%4];
    	}
    }
    
    /*Rora a derecha para deshacer la operacion rotar a derecha*/
    public void rotarDerecha(int n){
    	rotarIzquierda(4-n);
    	
    }
    
    public boolean contieneNeg(){
    	
    	for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
            	if (data[i][j]<0){
            		return true;
            	}
            }
          }
    return false;
    }
    
    public double getValor(int fila, int columna) {
    	return data[fila][columna];	
    }
}
