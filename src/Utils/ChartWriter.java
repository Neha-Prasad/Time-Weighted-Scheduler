package Utils;

import TestRunner.TestResult;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.util.List;

public class ChartWriter {

    public static void Write(String name, List<TestResult> results) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TestResult result: results) {
            dataset.addValue(result.getRunTimeInSeconds(), result.getAlgorithmName(), "RunTime");
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                name,
                "AlgorithmName",
                "RunTime",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        File file = new File("TestResultsCharts\\" + name+ ".jpeg");
        try {
            ChartUtils.saveChartAsJPEG(file, barChart, 640, 480);
            System.out.println("Chart saved at " + name+ ".jpeg");
        } catch (Exception ex) {
            System.out.println("Failed to write chart");
        }
    }
}
