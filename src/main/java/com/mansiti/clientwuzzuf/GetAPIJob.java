package com.mansiti.clientwuzzuf;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
//import scala.util.parsing.json.JSONObject;

/**
 *
 * @author Yasser
 */
public class GetAPIJob {
    
    public static void main(String[] args) {
        GetAPIJob job = new GetAPIJob();
        job.printSummary();
        
        job.getCompanies();
        job.getCountries();
        job.getTitles();
        job.getSkills();
//        JSONObject json;
//        try {
//            json = readJsonFromUrl("http://localhost:8080/wuzzufJobs/resources/javaee8/getCompany");
////            System.out.println(json.toString());
//            Map<String,Long> lmap =  new ObjectMapper().readValue(json.toString(), HashMap.class);
//            Map<String,Long> map =  lmap.entrySet().stream().
//                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
//                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//            
//            String[] breaks = (String[]) map.keySet().toArray(new String[map.size()]);
//            Integer[] valuesInt = ( map.values ()).toArray (new Integer[map.size()]);
//            
//            StringBuilder st = new StringBuilder();
//            
//            for (int i = 0; i < 10; i++) {
//                st.append(breaks[i]+" , "+ valuesInt[i].toString()+"\n");
//            }
//            
//            System.out.println(st.toString());
//        } catch (IOException ex) {
//            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (JSONException ex) {
//            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    
    }
    
    
    public void printSummary(){
        
        try {
          InputStream is = new URL("http://localhost:8080/wuzzufJobs/resources/javaee8/summary").openStream();
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
            System.err.println(jsonText);
        } catch (IOException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public void getCompanies(){
        JSONObject json;
        try {
            json = readJsonFromUrl("http://localhost:8080/wuzzufJobs/resources/javaee8/getCompany");
//            System.out.println(json.toString());
            Map<String,Long> lmap =  new ObjectMapper().readValue(json.toString(), HashMap.class);
            Map<String,Long> map =  lmap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            
            String[] breaks = (String[]) map.keySet().toArray(new String[map.size()]);
            Integer[] valuesInt = ( map.values ()).toArray (new Integer[map.size()]);
            
            List<statCount> stats=new ArrayList<statCount>();
            StringBuilder st = new StringBuilder();
            int count = 0;
            for (int i = 0; i < valuesInt.length; i++) {
                if(i<20){
                    st.append(breaks[i]+" , "+ valuesInt[i].toString()+"\n");
                    stats.add( new statCount(breaks[i], valuesInt[i]));
                }else{
                    count+=valuesInt[i];
                }
            }
            
            st.append("Other , "+ count+"\n");
//            stats.add( new statCount("Other", count));
            graphPieCompany(stats,"Companies");
            
            System.out.println(st.toString());
        } catch (IOException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void getCountries(){
        JSONObject json;
        try {
            json = readJsonFromUrl("http://localhost:8080/wuzzufJobs/resources/javaee8/getCountry");
//            System.out.println(json.toString());
            Map<String,Long> lmap =  new ObjectMapper().readValue(json.toString(), HashMap.class);
            Map<String,Long> map =  lmap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            
            String[] breaks = (String[]) map.keySet().toArray(new String[map.size()]);
            Integer[] valuesInt = ( map.values ()).toArray (new Integer[map.size()]);
            
            List<statCount> stats=new ArrayList<statCount>();
            StringBuilder st = new StringBuilder();
            int count = 0;
            for (int i = 0; i < valuesInt.length; i++) {
                if(i<9){
                    st.append(breaks[i]+" , "+ valuesInt[i].toString()+"\n");
                    stats.add( new statCount(breaks[i], valuesInt[i]));
                }else{
                    count+=valuesInt[i];
                }
            }
            
            st.append("Other , "+ count+"\n");
            stats.add( new statCount("Other", count));
             graphBarJobs(stats,"Countries Count","Country's Name", "Number of Jobs", "Country", 10);
             
             
            System.out.println(st.toString());
            
           
        } catch (IOException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getTitles(){
        JSONObject json;
        try {
            json = readJsonFromUrl("http://localhost:8080/wuzzufJobs/resources/javaee8/getTitle");
//            System.out.println(json.toString());
            Map<String,Long> lmap =  new ObjectMapper().readValue(json.toString(), HashMap.class);
            Map<String,Long> map =  lmap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            
            String[] breaks = (String[]) map.keySet().toArray(new String[map.size()]);
            Integer[] valuesInt = ( map.values ()).toArray (new Integer[map.size()]);
            
            List<statCount> stats=new ArrayList<statCount>();
            StringBuilder st = new StringBuilder();
            int count = 0;
            for (int i = 0; i < valuesInt.length; i++) {
                if(i<50){
                    st.append(breaks[i]+" , "+ valuesInt[i].toString()+"\n");
                    stats.add( new statCount(breaks[i], valuesInt[i]));
                }else{
                    count+=valuesInt[i];
                }
            }
            
            st.append("Other , "+ count+"\n");
            stats.add( new statCount("Other", count));
            graphBarJobs(stats,"Jobs Count","Job's Name", "Number of Jobs", "Job", 50);
             
            
            System.out.println(st.toString());
        } catch (IOException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void getSkills(){
        JSONObject json;
        try {
            json = readJsonFromUrl("http://localhost:8080/wuzzufJobs/resources/javaee8/getskill");
//            System.out.println(json.toString());
            Map<String,Long> lmap =  new ObjectMapper().readValue(json.toString(), HashMap.class);
            Map<String,Long> map =  lmap.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            
            String[] breaks = (String[]) map.keySet().toArray(new String[map.size()]);
            Integer[] valuesInt = ( map.values ()).toArray (new Integer[map.size()]);
            
            StringBuilder st = new StringBuilder();
            
            for (int i = 0; i < 10; i++) {
                st.append(breaks[i]+" , "+ valuesInt[i].toString()+"\n");
            }
            
            System.out.println(st.toString());
        } catch (IOException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetAPIJob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
//    JSONObject json = new JSONObject(IOUtils.toString(new URL("https://graph.facebook.com/me"), Charset.forName("UTF-8")));

    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
    
    
    public void graphPieCompany(List<statCount> statistictsCount, String title) {
        //filter to get a map of passenger class and total number of passengers in each class
       
        // Create Chart
        PieChart chart = new PieChartBuilder ().width (800).height (600).title (title).build ();
        // Customize Chart
        Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120),
            new Color (80, 143, 100), new Color (100, 68, 70), new Color (150, 105, 20),
            new Color (100, 143, 120), new Color (120, 68, 90), new Color (170, 105, 40),
            new Color (120, 143, 140), new Color (140, 68, 110), new Color (190, 105, 60),
            new Color (140, 143, 160), new Color (160, 68, 130), new Color (210, 105, 80),
            new Color (160, 143, 180), new Color (180, 68, 150), new Color (230, 105, 100),
            new Color (180, 143, 200), new Color (200, 68, 170), new Color (250, 105, 120)};
        chart.getStyler ().setSeriesColors (sliceColors);
        // Series
        for (statCount count : statistictsCount) {
            chart.addSeries (count.getLabel(), count.getCount());
        }
        // Show it
        new SwingWrapper (chart).displayChart ();
    }
    
    public void graphBarJobs(List<statCount> statistictsCount,String title, String xAxis,String yAxis,String legend, int bars) {
        //filter to get an array of passenger ages
        List<Long> counts = statistictsCount.stream ().map (statCount::getCount).limit (bars).collect (Collectors.toList ());
        List<String> titles = statistictsCount.stream ().map (statCount::getLabel).limit (bars).collect (Collectors.toList ());

        //Using XCart to graph the Ages
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder ().width (1024).height (768).title (title).xAxisTitle (xAxis).yAxisTitle (yAxis).build ();
        // Customize Chart
        chart.getStyler ().setLegendPosition (Styler.LegendPosition.InsideNW);
        chart.getStyler ().setHasAnnotations (true);
        chart.getStyler().setXAxisLabelRotation(90);
        chart.getStyler ().setStacked (true);
        // Series
        chart.addSeries (legend,titles, counts);
        
        // Show it
        new SwingWrapper (chart).displayChart ();
    }
    
}
