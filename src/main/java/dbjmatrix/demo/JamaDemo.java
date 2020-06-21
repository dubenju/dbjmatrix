package dbjmatrix.demo;

import Jama.LUDecomposition;
import Jama.Matrix;

/*
 * 计算逆矩阵。
 * 建议使用Apache Commons Math 2.0。 JAMA是一个死的项目。
 * ACM 2.0实际上采用了JAMA的线性代数并进一步发展了它。
 */
public class JamaDemo {
    public static void main(String[] args)
    {
        double [][] values = {{1, 1, 2}, {2, 4, -3}, {3, 6, -5}};  // each array is a row in the matrix
        double [] rhs = { 9, 1, 0 }; // rhs vector
        double [] answer = { 1, 2, 3 }; // this is the answer that you should get.

        Matrix a = new Matrix(values);
        a.print(10, 2);
        LUDecomposition luDecomposition = new LUDecomposition(a);
        luDecomposition.getL().print(10, 2); // lower matrix
        luDecomposition.getU().print(10, 2); // upper matrix

        Matrix b = new Matrix(rhs, rhs.length);
        Matrix x = luDecomposition.solve(b); // solve Ax = b for the unknown vector x
        x.print(10, 2); // print the solution
        Matrix residual = a.times(x).minus(b); // calculate the residual error
        double rnorm = residual.normInf(); // get the max error (yes, it's very small)
        System.out.println("residual: " + rnorm);
    }
}
