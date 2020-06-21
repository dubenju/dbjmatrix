package dbjmatrix.demo;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.ArrayRealVector;
import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealVector;

/*
 * 计算逆矩阵。
 */
public class LinearAlgebraDemo
{
    public static void main(String[] args)
    {
        double [][] values = {{1, 1, 2}, {2, 4, -3}, {3, 6, -5}};
        double [] rhs = { 9, 1, 0 };

        RealMatrix a = new Array2DRowRealMatrix(values);
        System.out.println("a matrix: " + a);
        DecompositionSolver solver = new LUDecompositionImpl(a).getSolver();

        RealVector b = new ArrayRealVector(rhs);
        RealVector x = solver.solve(b);
        System.out.println("solution x: " + x);;
        RealVector residual = a.operate(x).subtract(b);
        double rnorm = residual.getLInfNorm();
        System.out.println("residual: " + rnorm);
    }
}
