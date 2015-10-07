/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sguha
 */
public class Fusioncharts {
    private String constructorTemplate = "<script type=\"text/javascript\">FusionCharts.ready(function () {new FusionCharts(__constructorOptions__);});</script>";
    private String renderTemplate = "<script type=\"text/javascript\">FusionCharts.ready(function () {                FusionCharts(\"__chartId__\").render();});</script>";
    private String[] chartOptions = new String[10];
    private String chartDataSource = "";
    public Fusioncharts(String type, String id, int width, int height, String renderAt, String dataFormat, String dataSource) {
        this.chartOptions[0] = id;
        this.chartOptions[1] = Integer.toString(width);
        this.chartOptions[2] = Integer.toString(height);
        this.chartOptions[3] = renderAt;
        this.chartOptions[4] = type;
        this.chartOptions[5] = dataFormat;
        if(this.chartOptions[5].contains("url")) {
            this.chartOptions[6] = chartDataSource;
        } else {
            this.chartOptions[6] = "__dataSource__";
            if(this.chartOptions[5] == "json") {
                this.chartDataSource = chartDataSource.replaceAll("\\", "").replaceAll("\n", "");//stlr_replace("\n", "", stripslashes(chartDataSource));
            } else {
                this.chartDataSource = chartDataSource.replaceAll("\n", "");//str_replace("\n", "", chartDataSource);
            }
        }
    }
    public String render() {//strpos($this->chartOptions["dataFormat"], "url")
        String outputHTML;
        if(this.chartOptions[5].contains("url")) {
            outputHTML = str_replace("__constructorOptions__", json_encode($this->chartOptions), $this->constructorTemplate).str_replace("__chartId__", $this->chartOptions["id"], $this->renderTemplate);    
        } else {
            if(this.chartOptions[5] == "json") {
                outputHTML = str_replace("__constructorOptions__", str_replace("\"__dataSource__\"",$this->chartDataSource,json_encode($this->chartOptions)), $this->constructorTemplate).str_replace("__chartId__", $this->chartOptions["id"], $this->renderTemplate);                    
            } else {
                outputHTML = str_replace("__constructorOptions__", str_replace("__dataSource__",$this->chartDataSource,json_encode($this->chartOptions)), $this->constructorTemplate).str_replace("__chartId__", $this->chartOptions["id"], $this->renderTemplate);                    
            }
        }
        return outputHTML;
    }
}
