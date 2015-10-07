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
    public Fusioncharts(String type, String id, int width, int height, String renderAt, String dataFormat, String dataSource) {
        
    }
}
