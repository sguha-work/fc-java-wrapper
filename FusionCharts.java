/**
 *
 * @Contributors Sahasrangshu Guha, Subhasis Ghosal, Suvradip Saha
 */
public class FusionCharts {
    private String constructorTemplate = "<script type=\"text/javascript\">FusionCharts.ready(function () {new FusionCharts(__constructorOptions__);});</script>";
    private String renderTemplate = "<script type=\"text/javascript\">FusionCharts.ready(function () {                FusionCharts(\"__chartId__\").render();});</script>";
    private String[] chartOptions = new String[10];
    private String chartDataSource = "";
    public FusionCharts(String type, String id, String width, String height, String renderAt, String dataFormat, String dataSource) {
        this.chartOptions[0] = id;
        this.chartOptions[1] = width;
        this.chartOptions[2] = height;
        this.chartOptions[3] = renderAt;
        this.chartOptions[4] = type;
        this.chartOptions[5] = dataFormat;
        if(this.chartOptions[5].contains("url")) {
            this.chartOptions[6] = "\""+dataSource+"\"";
        } else {
            this.chartOptions[6] = "__dataSource__";
            this.chartDataSource = this.addSlashes(dataSource.replaceAll("\n", ""));
        }
        this.chartOptions[7] = null;
    }
    
    public FusionCharts(String type, String id, String width, String height, String renderAt, String dataFormat, String dataSource, String extra) {
        this(type, id, width, height, renderAt, dataFormat, dataSource);
        this.chartOptions[7] = extra;
    }
    
    
    
    private String addSlashes(String str) {
        str = str.replaceAll("\\\\", "\\\\\\\\");
        str = str.replaceAll("\\n", "\\\\n");
        str = str.replaceAll("\\r", "\\\\r");
        str = str.replaceAll("\\00", "\\\\0");
        str = str.replaceAll("u003d", "=");
        str = str.replaceAll("'", "\\\\'");
        str = str.replaceAll("\\\\", "");
        str = str.replaceAll("\"\\{", "{");
        str = str.replaceAll("\"\\[", "[");
        str = str.replaceAll("\\}\\]\"", "}]");
        str = str.replaceAll("\"\\}\"", "\"}");
        return str;
    }
    private String jsonEncode(){
        String json;
        if(this.chartOptions[7]==null) {
            json = "{type: \""+this.chartOptions[4]+"\",renderAt: \""+this.chartOptions[3]+"\",width: \""+this.chartOptions[1]+"\",height: \""+this.chartOptions[2]+"\",dataFormat: \""+this.chartOptions[5]+"\",id: \""+this.chartOptions[0]+"\",dataSource: "+this.chartOptions[6]+"}";
        } else {
            json = "{type: \""+this.chartOptions[4]+"\",renderAt: \""+this.chartOptions[3]+"\",width: \""+this.chartOptions[1]+"\",height: \""+this.chartOptions[2]+"\",dataFormat: \""+this.chartOptions[5]+"\",id: \""+this.chartOptions[0]+"\",dataSource: "+this.chartOptions[6]+","+this.chartOptions[7].substring(1);
        }
        return json;
    }
    public String render() {
        String outputHTML;
        if(this.chartOptions[5].contains("url")) {
            outputHTML = this.constructorTemplate.replace("__constructorOptions__", this.jsonEncode())+this.renderTemplate.replace("__chartId__", this.chartOptions[0]);
        } else {
            if("json".equals(this.chartOptions[5])) {
                outputHTML = this.constructorTemplate.replace("__constructorOptions__", this.jsonEncode().replace("__dataSource__",this.chartDataSource))+this.renderTemplate.replace("__chartId__", this.chartOptions[0]);
            } else {
                outputHTML = this.constructorTemplate.replace("__constructorOptions__", this.jsonEncode().replace("__dataSource__","\'"+this.chartDataSource+"\'"))+this.renderTemplate.replace("__chartId__", this.chartOptions[0]);
            }
        }
        return outputHTML;
    }
}
