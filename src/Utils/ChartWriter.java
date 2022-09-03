package Utils;

import TestRunner.TestResult;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartWriter {
    public static void Write(String filePath) {
        List<TestResult> results = new ArrayList<>();
        boolean headerRead = false;
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                if (headerRead) {
                    TestResult result = new TestResult(values[0], Long.parseLong(values[1]), Integer.parseInt(values[2]), values[3]);
                    results.add(result);
                } else {
                    headerRead = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        Map<Integer, List<TestResult>> groupedByNumberOfHils = results.stream().collect(Collectors.groupingBy(TestResult::getNumberOfHilsUsed));
        for (Integer numOfHils : groupedByNumberOfHils.keySet()) {
            DefaultCategoryDataset datasetGroupedByNumOfHils = new DefaultCategoryDataset();
            for (TestResult result: groupedByNumberOfHils.get(numOfHils)) {
                datasetGroupedByNumOfHils.addValue(result.getRunTimeInSeconds(), result.getAlgorithmName(), result.getTestDistribution());
            }
            Write("NumOfHils_" + numOfHils, datasetGroupedByNumOfHils);
        }

        Map<String, List<TestResult>> groupedByTestDistribution = results.stream().collect(Collectors.groupingBy(TestResult::getTestDistribution));
        for (String testDistribution : groupedByTestDistribution.keySet()) {
            DefaultCategoryDataset datasetGroupedByTestDistribution = new DefaultCategoryDataset();
            for (TestResult result: groupedByTestDistribution.get(testDistribution)) {
                datasetGroupedByTestDistribution.addValue(result.getRunTimeInSeconds(), result.getAlgorithmName(), String.valueOf(result.getNumberOfHilsUsed()));
            }
            Write("TestDistribution_" + testDistribution, datasetGroupedByTestDistribution);
        }
    }

    private static void Write(String name, DefaultCategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                name,
                "TestDistribution",
                "RunTime",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        CategoryItemRenderer renderer = ((CategoryPlot)barChart.getPlot()).getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
        renderer.setDefaultPositiveItemLabelPosition(position);

        File file = new File("TestResultsCharts\\" + name+ ".jpeg");
        try {
            ChartUtils.saveChartAsJPEG(file, barChart, 1280, 480);
            System.out.println("Chart saved at " + name+ ".jpeg");
        } catch (Exception ex) {
            System.out.println("Failed to write chart");
        }
    }
}
