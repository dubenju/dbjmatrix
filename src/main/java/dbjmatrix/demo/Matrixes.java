package dbjmatrix.demo;
import org.ujmp.core.Matrix;
import org.ujmp.jama.*;
import org.ujmp.core.MatrixFactory;
import org.ujmp.core.calculation.Calculation.Ret;
public class Matrixes {
    /**
    * 矩阵测试
    */
    public static void main(String[] args){
    /*
    * 初始化一个矩阵
    */
    double f[][]={{1,1},{2,3}};
    Matrix v=MatrixFactory.importFromArray(f) ;
    /*
    * 随机产生一个矩阵
    */
    Matrix test_1 = Matrix.factory.rand(4, 4);
    /*
    * 获取一个矩阵的数组
    */
    double gh[][]=test_1.toDoubleArray();
    System.out.println(test_1);
    /*
    * 转置矩阵
    */
    Matrix test_2=test_1.transpose();
    System.out.println(test_2);
    /*
    * 归一化一个矩阵
    */
    Matrix test_3=test_1.mtimes(test_2);
    System.out.println(test_3);
    int row=(int)test_1.getRowCount();
    for(int i=0;i<row;i++){
    double a=test_3.getAsDouble(i,i);
    for(int j=0;j<row;j++){
    double d=test_1.getAsDouble(i,j);
    test_1.setAsDouble(d/a,i,j);
    }
    }
    System.out.println(test_1);

    /*
    * 矩阵的行数与列数
    */
    int row1=(int)test_1.getRowCount();
    int col=(int)test_1.getColumnCount();
    System.out.println("row="+row1);
    System.out.println("col="+col);
    /*
    * 选取矩阵的某一行
    */
    Matrix res_3 = test_1.selectRows(Ret.NEW, 1);
    //矩阵所有数值求和
    double ds=res_3.getValueSum();
    // System.out.println("ds="+ds);
    //
    // Matrix test_2=test_1.copy();
    // for(int i=0;i<col;i++){
    // double d=test_2.getAsDouble(1,i);
    // test_2.setAsDouble(d/ds,1,i);
    // }
    // System.out.println(test_2);

    //

    System.out.println("OK!");
    }
}
