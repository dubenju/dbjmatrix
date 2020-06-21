package dbjmatrix.demo;
import org.ujmp.core.Matrix;
import org.ujmp.core.MatrixFactory;
import org.ujmp.core.calculation.Calculation.Ret;

public class PerfomaceTest {
    public static void main(String[] args) {
        long begin, end;
        /**
         * test变test2才变 *********test2不能被改变
         */

        long m = 725, n = 20;
        // Matrix test_1 = Matrix.factory.rand(5, 5);
        // test_1.showGUI();
        // Matrix test_2 = test_1.transpose(Ret.ORIG);
        // test_2.showGUI();
        // Matrix test_3 = test_2.mtimes(Matrix.factory.ones(5, 5).times(2));
        // test_3.showGUI();
        begin = System.currentTimeMillis();
        Matrix res = Matrix.factory.rand(m, n);
        Matrix res0 = Matrix.factory.rand(m, n);
        end = System.currentTimeMillis();
        System.out.println("构建矩阵耗时" + (end - begin) + "ms");
        // res.setLabel("res");
        // res.showGUI();

        begin = System.currentTimeMillis();
        Matrix res_1_trannull = res.transpose();
        end = System.currentTimeMillis();
        System.out.println("res_1_trannull-耗时" + (end - begin) + "ms");

        begin = System.currentTimeMillis();
        Matrix res_2_tranlink = res.transpose(Ret.LINK);
        end = System.currentTimeMillis();
        System.out.println("res_2_tranlink-耗时" + (end - begin) + "ms");
        // res_2_tranlink.setLabel("res_2_tranlink");
        // res_2_tranlink.setAsDouble(10, 0, 0);
        // res_2_tranlink.showGUI();

        /**
         * 进行矩阵赋值，两个矩阵式同一个矩阵，除非用copy()
         */
        Matrix xxxMatrix = res_2_tranlink;
        xxxMatrix.setAsDouble(10, 0, 0);
        xxxMatrix.showGUI();
        /**
         * 对LINK的矩阵进行赋值
         */
        res_2_tranlink = MatrixFactory.ones(1, 1);
        res_2_tranlink.setAsDouble(110, 0, 0);
        res_2_tranlink.showGUI();

        /**
         * 选取特定行与列
         */
        begin = System.currentTimeMillis();
        Matrix res_3 = res_2_tranlink.selectColumns(Ret.NEW, 10);
        end = System.currentTimeMillis();
        res_3.showGUI();
        System.out.println("选取列-NEW-耗时" + (end - begin) + "ms");

        begin = System.currentTimeMillis();
        Matrix res_4 = res_2_tranlink.selectColumns(Ret.LINK, 0);
        end = System.currentTimeMillis();
        res_4.setAsDouble(10, 0, 0);
        res_4.showGUI();
        System.out.println("选取列-link-耗时" + (end - begin) + "ms");

        /**
         * 求逆耗时较长，但是inv和invSymm相差无几
         */
        for (int i = 0; i < 1; ++i) {
            begin = System.currentTimeMillis();
            Matrix res_5 = res_2_tranlink.inv();
            end = System.currentTimeMillis();
            System.out.println("inv-耗时" + (end - begin) + "ms");
        }

        /**
         * 获取行数，列数
         */
        begin = System.currentTimeMillis();
        long res_rowcount = res_2_tranlink.getRowCount();
        end = System.currentTimeMillis();
        System.out.println("getRowCount-耗时" + (end - begin) + "ms");

        /**
         * 矩阵相乘的检测
         */

        begin = System.currentTimeMillis();
        Matrix res_muti_link = res_2_tranlink.mtimes(Ret.LINK, false, res0);
        end = System.currentTimeMillis();
        res_muti_link.setAsDouble(100, 0, 0);
        // res_muti_link.showGUI();
        System.out.println("res_muti_link-耗时" + (end - begin) + "ms");

        // 这里是LINK后和LINK后的矩阵相乘，但是返回的是NEW，所以可以改变值
        Matrix afterlinklink = res_muti_link.mtimes(res_2_tranlink);
        afterlinklink.setAsDouble(100, 0, 0);
        afterlinklink.showGUI();
        begin = System.currentTimeMillis();
        Matrix res_muti_new = res_2_tranlink.mtimes(Ret.NEW, false, res0);
        end = System.currentTimeMillis();
        res_muti_new.showGUI();
        System.out.println("res_muti_new-耗时" + (end - begin) + "ms");

        /**
         * 对不是LINK的矩阵选取行或列再改变变量值，使用LINK的话都会受到影响
         */
        Matrix beforeMatrix = Matrix.factory.rand(5, 5);
        beforeMatrix.setLabel("beforeMatrix");
        beforeMatrix.showGUI();

        Matrix nowMatrix = beforeMatrix.selectRows(Ret.NEW, 0);
        nowMatrix.setAsDouble(10, 0, 0);
        nowMatrix.setLabel("nowMatrix");
        nowMatrix.showGUI();

        Matrix laterMatrix = beforeMatrix.transpose(Ret.LINK);
        laterMatrix.setLabel("laterMatrix");
        // laterMatrix.showGUI();
        Matrix xx = laterMatrix.minus(Ret.LINK, false, 10);
        double xxd = xx.getAsDouble(0, 0);
        System.out.println(xxd);
        // xx.showGUI();

    }
}
