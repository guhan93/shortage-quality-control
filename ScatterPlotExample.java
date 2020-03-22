
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlotExample extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;

  public ScatterPlotExample(String title) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset();

    // Create chart
    JFreeChart chart = ChartFactory.createScatterPlot(
        "week VS shortage weight comparison chart", 
        "X-Axis", "Y-Axis", dataset);

    
    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  private XYDataset createDataset() {
    XYSeriesCollection dataset = new XYSeriesCollection();
    

    XYSeries series2 = new XYSeries("shortage");
    series2.add(1, 11.3);
    series2.add(2, 12.1);
    series2.add(3, 12.5);
    series2.add(4, 12.8);
    series2.add(5, 12.2);
    series2.add(6, 12);
    series2.add(7, 10.2);
    series2.add(8, 12.1);
    series2.add(9, 11.6);
    series2.add(10, 11.3);
    series2.add(11, 11.9);
    series2.add(12, 11.8);
    series2.add(13, 12.7);
    series2.add(14, 11.9);
    series2.add(15, 11.5);
    series2.add(16, 9.5);
    series2.add(17, 13.2);
    series2.add(18, 11.3);
    series2.add(19, 10.9);
    series2.add(20, 11.9);
    series2.add(21, 12);
    series2.add(22, 11.8);
    series2.add(23, 10.9);
    series2.add(24, 11.1);
    series2.add(25, 12.4);
    series2.add(26, 12.3);
    series2.add(27, 11.8);
    series2.add(28, 12.1);
    series2.add(29, 12);
    series2.add(30, 12.2 );
    series2.add(31, 11.7);
    series2.add(32, 10.8);
    series2.add(33, 12);
    series2.add(34, 12.2);
    series2.add(35, 12.1);
    series2.add(36, 11.9);
    series2.add(37, 11.8);
    series2.add(38, 12.1);
    series2.add(39, 12.2);
    series2.add(40, 11.5);
    series2.add(41, 11.9);
    series2.add(42, 11.6);
    series2.add(43, 11.8);
    series2.add(44, 11.5);
    series2.add(45, 11);
    series2.add(46, 11.1);
    series2.add(47, 10.9);
     dataset.addSeries(series2);
    
    XYSeries series1 = new XYSeries("forecast");
    series1.add(48, 4);
    series1.add(49, 8);
    series1.add(50, 6.5);
    series1.add(51, 4.5);
    series1.add(52, 5);
     dataset.addSeries(series1);
    
    

   

    return dataset;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      ScatterPlotExample example = new ScatterPlotExample("Scatter Chart");
      example.setSize(800, 400);
      example.setLocationRelativeTo(null);
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      example.setVisible(true);
    });
  }
}