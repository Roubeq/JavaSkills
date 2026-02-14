package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {
    public static void main(String[] args) {
            new Main().run();
    }

    public void run() {
        Data data = null;
        try {
            data = readValuesFromFile("data.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        double[] coefficients = calculatePolynomialApproximation(
                data.xCoords,data.yCoords,data.polyDegree
        );

        printResults(coefficients,data.xCoords,data.yCoords, data.polyDegree);

        createChart(data.xCoords,data.yCoords,coefficients);
    }


    public static Data readValuesFromFile(String filename) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] firstLine = reader.readLine().split("\\s");
            int pointCount = Integer.parseInt(firstLine[0]);
            int polyDegree = Integer.parseInt(firstLine[1]);

            List<Double> xCoord = new ArrayList<>();
            List<Double> yCoord = new ArrayList<>();

            for (int i = 0; i < pointCount; i++) {
                String[] line = reader.readLine().split("\\s");
                Double xCoordinate = Double.parseDouble(line[0]);
                Double yCoordinate = Double.parseDouble(line[1]);
                xCoord.add(xCoordinate);
                yCoord.add(yCoordinate);
            }

            return new Data(
                    xCoord,
                    yCoord,
                    polyDegree,
                    pointCount
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double[] calculatePolynomialApproximation(List<Double> xCoords,
                                                      List<Double> yCoords,
                                                      int polyDegree) {
        int pointsCount = xCoords.size();
        int maxExp = 2 * polyDegree;
        double[] powerSums = new double[maxExp + 1];

        for (int i = 0; i < pointsCount; i++) {
            double currentPower = 1.0;
            for (int exp = 0; exp <= maxExp; exp++) {
                powerSums[exp] += currentPower;
                currentPower *= xCoords.get(i);
            }
        }

        int matrixSize = polyDegree + 1;
        double[][] coefficientMatrix = new double[matrixSize][matrixSize];
        double[] rightSide = new double[matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                coefficientMatrix[row][col] = powerSums[row + col];
            }
        }

        for (int eqNum = 0; eqNum < matrixSize; eqNum++) {
            for (int pointIdx = 0; pointIdx < pointsCount; pointIdx++) {
                rightSide[eqNum] += yCoords.get(pointIdx) *
                        Math.pow(xCoords.get(pointIdx), eqNum);
            }
        }

        return solveLinearSystem(coefficientMatrix, rightSide);
    }

    private double[] solveLinearSystem(double[][] matrix, double[] rightSide) {
        int n = rightSide.length;

        for (int pivot = 0; pivot < n; pivot++) {
            for (int targetRow = pivot + 1; targetRow < n; targetRow++) {
                double multiplier = matrix[targetRow][pivot] / matrix[pivot][pivot];
                for (int colIdx = pivot; colIdx < n; colIdx++) {
                    matrix[targetRow][colIdx] -= multiplier * matrix[pivot][colIdx];
                }
                rightSide[targetRow] -= multiplier * rightSide[pivot];
            }
        }

        double[] solution = new double[n];
        for (int row = n - 1; row >= 0; row--) {
            solution[row] = rightSide[row];
            for (int col = row + 1; col < n; col++) {
                solution[row] -= matrix[row][col] * solution[col];
            }
            solution[row] /= matrix[row][row];
        }

        return solution;
    }

    private void printResults(double[] coefficients,
                              List<Double> xCoords,
                              List<Double> yCoords,
                              int polyDegree) {
        System.out.println("Найденные коэффициенты полинома:");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.printf("a%d = %.2f%n", i, coefficients[i]);
        }

        double errorSum = 0.0;
        int pointsCount = xCoords.size();

        for (int i = 0; i < pointsCount; i++) {
            double approxValue = calculatePolynomialValue(coefficients, xCoords.get(i));
            errorSum += Math.pow(yCoords.get(i) - approxValue, 2);
        }

        double variance = errorSum / (pointsCount - polyDegree - 1);
        double stdDeviation = Math.sqrt(variance);

        System.out.printf("%nОстаточная дисперсия: %.2f%n", variance);
        System.out.printf("Стандартное отклонение: %.2f%n", stdDeviation);
    }

    private double calculatePolynomialValue(double[] coefficients, double x) {
        double result = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    private void createChart(List<Double> xCoords, List<Double> yCoords, double[] coefficients) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries points = new XYSeries("Точки");
        for (int i = 0; i < xCoords.size(); i++) {
            points.add(xCoords.get(i),yCoords.get(i));
        }
        dataset.addSeries(points);

        XYSeries curveSeries = new XYSeries("Аппроксимирующая функция");
        double minX = xCoords.stream().min(Double::compareTo).get();
        double maxX = xCoords.stream().max(Double::compareTo).get();

        for (int i = 0; i< 200; i++) {
            double x = minX + i  * (maxX - minX) / 199;
            double y = calculatePolynomialValue(coefficients,x);
            curveSeries.add(x,y);
        }
        dataset.addSeries(curveSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Аппроксимация зависимости скорости от расстояния",
                "Расстояние, Мпк",
                "Скорость, км/с",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(0, java.awt.Color.BLUE);
        renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6));

        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesPaint(1, java.awt.Color.RED);
        renderer.setSeriesStroke(1, new java.awt.BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(java.awt.Color.WHITE);
        plot.setDomainGridlinePaint(java.awt.Color.GRAY);
        plot.setRangeGridlinePaint(java.awt.Color.GRAY);

        ChartFrame frame = new ChartFrame("График аппроксимации", chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static class Data {
        List<Double> xCoords;
        List<Double> yCoords;

        int pointsCount;
        int polyDegree;

        Data(List<Double> xCoords, List<Double> yCoords, int polyDegree, int pointCount) {
            this.xCoords = xCoords;
            this.yCoords = yCoords;
            this.pointsCount = pointCount;
            this.polyDegree = polyDegree;
        }
    }
}