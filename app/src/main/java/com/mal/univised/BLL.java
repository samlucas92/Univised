package com.mal.univised;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Business Logic Layer which handles the database commands
 * Created by samlucas on 15/08/2016.
 */
public class BLL extends SQLiteOpenHelper {


    private final Context myContext;
    private static BLL databaseHelper;
    public BLL(Context context){

        super(context, "application.db", null, 4);
        this.myContext = context;

    }
    public static synchronized BLL getInstance(Context c){
        if (databaseHelper == null){
            synchronized(BLL.class){
                databaseHelper = new BLL(c);
            }
        }
        return databaseHelper;

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //name phone email website image
        String queryUni = "CREATE TABLE universities ( id INTEGER PRIMARY KEY, name TEXT, location TEXT ,phone TEXT, " +
                "email TEXT, website TEXT,image BLOB)";
        database.execSQL(queryUni);
        insertToDB();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS universities");
        onCreate(database);

    }

    private List<String> updateUniversityData(){

        return null;
    }
    private List<university> getUniversityList(){
        return null;
    }
    public void insertToDB(){
        SQLiteDatabase database = this.getWritableDatabase();
        String[][] universities= new String[][]{
                {"Aberystwyth University", "Aberystwyth","www.aber.ac.uk"},
                {"Anglia Ruskin University", "Cambridge","www.anglia.ac.uk"},
                {"Arts University Bournemouth", "Poole","www.aub.ac.uk"},
                {"Aston University", "Birmingham","www.aston.ac.uk"},
                {"Bangor University", "Bangor","www.bangor.ac.uk"},
                {"Bath Spa University", "Bath","www.bathspa.ac.uk"},
                {"Birkbeck, University of London", "London","www.bbk.ac.uk"},
                {"Birmingham City University", "Birmingham", "www.bcu.ac.uk"},
                {"Bishop Grosseteste University", "Lincoln", "www.bishopg.ac.uk"},
                {"Bournemouth University", "Poole","www1.bournemouth.ac.uk"},
                {"BPP University", "Manchester","www.bpp.com"},
                {"Brunel University", "Uxbridge","www.brunel.ac.uk"},
                {"Buckinghamshire New University", "High Wycombe", "www.bucks.ac.uk"},
                {"Canterbury Christ Church University", "Canterbury","www.canterbury.ac.uk"},
                {"Cardiff Metropolitan University", "Cardiff","www.cardiffmet.ac.uk"},
                {"Cardiff University", "Cardiff","www.cardiff.ac.uk"},
                {"City University London", "London","www.city.ac.uk"},
                {"Coventry University", "Coventry","www.coventry.ac.uk"},
                {"Cranfield University", "Cranfield","www.cranfield.ac.uk"},
                {"De Montfort University", "Leicester","www.dmu.ac.uk"},
                {"Durham University", "Durham","www.dur.ac.uk"},
                {"Edge Hill University", "Ormskirk","www.edgehill.ac.uk"},
                {"Edinburgh Napier University", "Edinburgh","www.napier.ac.uk"},
                {"European School of Economics", "London","www.eselondon.ac.uk"},
                {"Falmouth University", "Falmouth","www.falmouth.ac.uk"},
                {"Glasgow Caledonian University", "Glasgow","www.gcu.ac.uk"},
                {"Glasgow School of Art", "Glasgow","www.gsa.ac.uk"},
                {"Glyndwr University", "Wrexham","www.glyndwr.ac.uk"},
                {"Goldsmiths, University of London", "London","www.gold.ac.uk"},
                {"Harper Adams University", "Newport","www.harper-adams.ac.uk"},
                {"Heriot-Watt University", "Edinburgh","www.hw.ac.uk"},
                {"Heythrop College, University of London", "London","www.heythrop.ac.uk"},
                {"Imperial College London", "London","www.imperial.ac.uk"},
                {"Keele University", "Newcastle under Lyme","www.keele.ac.uk"},
                {"King's College London", "London","www.kcl.ac.uk"},
                {"Kingston University", "Kingston upon Thames","www.kingston.ac.uk"},
                {"Lancaster University", "Lancaster","www.lancaster.ac.uk"},
                {"Leeds Beckett University", "Leeds","www.leedsbeckett.ac.uk"},
                {"Leeds College of Art", "Leeds","www.leeds-art.ac.uk"},
                {"Leeds Trinity University", "Leeds","www.leedstrinity.ac.uk"},
                {"Liverpool Hope University", "Liverpool","www.hope.ac.uk"},
                {"Liverpool John Moores University", "Liverpool","www.ljmu.ac.uk"},
                {"London Business School", "London","www.london.edu"},
                {"London Metropolitan University", "London","www.londonmet.ac.uk"},
                {"London School of Hygiene and Tropical Medicine, University of London", "London","www.lshtm.ac.uk"},
                {"London South Bank University", "London","www.lsbu.ac.uk"},
                {"Loughborough University", "Loughborough","www.lboro.ac.uk"},
                {"Manchester Metropolitan University", "Manchester","www2.mmu.ac.uk"},
                {"Middlesex University", "London","www.mdx.ac.uk"},
                {"Newcastle University", "Newcastle upon Tyne","www.ncl.ac.uk"},
                {"Newman University", "Birmingham","www.newman.ac.uk"},
                {"Northumbria University", "Newcastle upon Tyne","www.northumbria.ac.uk"},
                {"Norwich University of the Arts", "Norwich","www.nua.ac.uk"},
                {"Nottingham Trent University", "Nottingham","www.ntu.ac.uk"},
                {"Oxford Brookes University", "Oxford","www.brookes.ac.uk"},
                {"Plymouth College of Art", "Plymouth","www.plymouthart.ac.uk"},
                {"Plymouth University", "Plymouth","www.plymouth.ac.uk"},
                {"Queen Margaret University", "Edinburgh","www.qmu.ac.uk"},
                {"Queen Mary University of London", "London","www.qmul.ac.uk"},
                {"Queen's University Belfast", "Belfast","www.qub.ac.uk"},
                {"Regent's University London", "London","www.regents.ac.uk"},
                {"Richmond, The American International University in London", "London","www.richmond.ac.uk"},
                {"Robert Gordon University", "Aberdeen","www.rgu.ac.uk"},
                {"Royal Academy of Music, University of London", "London","www.ram.ac.uk"},
                {"Royal Agricultural University", "Cirencester","www.rau.ac.uk"},
                {"Royal College of Art", "London","www.rca.ac.uk"},
                {"Royal College of Music", "London","www.rcm.ac.uk"},
                {"Royal Conservatoire of Scotland", "Glasgow","www.rcs.ac.uk"},
                {"Royal Holloway, University of London", "Egham","www.royalholloway.ac.uk"},
                {"Royal Veterinary College University of London", "London","www.rvc.ac.uk"},
                {"School of Advanced Study, University of London", "London","www.sas.ac.uk"},
                {"Scotland’s Rural College", "Edinburgh","www.sruc.ac.uk"},
                {"Sheffield Hallam University", "Sheffield","www.shu.ac.uk"},
                {"SOAS, University of London", "London","www.soas.ac.uk"},
                {"Southampton Solent University", "Southampton","www.solent.ac.uk"},
                {"St George's, University of London", "London","www.sgul.ac.uk"},
                {"St Mary's University, Twickenham", "Twickenham","www.stmarys.ac.uk"},
                {"Staffordshire University", "Stoke-on-Trent","www.staffs.ac.uk"},
                {"Swansea University", "Swansea","www.swansea.ac.uk"},
                {"Teesside University", "Middlesbrough","www.tees.ac.uk"},
                {"The Courtauld Institute of Art, University of London", "London","www.courtauld.ac.uk"},
                {"The London School of Economics and Political Science", "London","www.lse.ac.uk"},
                {"The Royal Central School of Speech and Drama", "London","www.cssd.ac.uk"},
                {"The University of Buckingham", "Buckingham","www.buckingham.ac.uk"},
                {"The University of Edinburgh", "Edinburgh","www.ed.ac.uk"},
                {"The University of Hull", "Hull","www2.hull.ac.uk"},
                {"The University of Law", "Guildford","www.law.ac.uk"},
                {"The University of Manchester", "Manchester","www.manchester.ac.uk"},
                {"The University of Northampton", "Northampton","www.northampton.ac.uk"},
                {"The University of Nottingham", "Nottingham","www.nottingham.ac.uk"},
                {"The University of Sheffield", "Sheffield","www.sheffield.ac.uk"},
                {"The University of Warwick", "Coventry","www2.warwick.ac.uk"},
                {"The University of Winchester", "Winchester","www.winchester.ac.uk"},
                {"The University of York", "York","www.york.ac.uk"},
                {"Ulster University","Coleraine","www.ulster.ac.uk"},
                {"University College Birmingham","Birmingham","www.ucb.ac.uk"},
                {"University College London","London","www.ucl.ac.uk"},
                {"University for the Creative Arts","Canterbury","www.ucreative.ac.uk"},
                {"University of Aberdeen","Aberdeen","www.abdn.ac.uk"},
                {"University of Abertay Dundee","Dundee","www.abertay.ac.uk"},
                {"University of Bath","Bath","www.bath.ac.uk"},
                {"University of Bedfordshire","Luton","www.beds.ac.uk"},
                {"University of Birmingham","Birmingham","www.birmingham.ac.uk"},
                {"University of Bolton","Bolton","www.bolton.ac.uk"},
                {"University of Bradford","Bradford","www.bradford.ac.uk"},
                {"University of Brighton", "Brighton","www.brighton.ac.uk"},
                {"University of Bristol", "Bristol","www.bristol.ac.uk"},
                {"University of Cambridge", "Cambridge","www.cam.ac.uk"},
                {"University of Central Lancashire", "Preston","www.uclan.ac.uk"},
                {"University of Chester", "Chester","www.chester.ac.uk"},
                {"University of Chichester", "Chichester","www.chi.ac.uk"},
                {"University of Cumbria", "Carlisle","www.cumbria.ac.uk"},
                {"University of Derby", "Derby","www.derby.ac.uk"},
                {"University of Dundee", "Dundee","www.dundee.ac.uk"},
                {"University of East Anglia", "Norwich","www.uea.ac.uk"},
                {"University of East London", "London","www.uel.ac.uk"},
                {"University of Essex","Colchester","www.essex.ac.uk"},
                {"University of Exeter","Exeter","www.exeter.ac.uk"},
                {"University of Glasgow","Glasgow","www.gla.ac.uk"},
                {"University of Gloucestershire","Cheltenham","www.glos.ac.uk"},
                {"University of Greenwich","Greenwich","www.gre.ac.uk"},
                {"University of Hertfordshire","Hatfield","www.herts.ac.uk"},
                {"University of Huddersfield","Huddersfield","www.hud.ac.uk"},
                {"University of Kent","Canterbury","www.kent.ac.uk"},
                {"University of Leeds","Leeds","www.leeds.ac.uk"},
                {"University of Leicester","Leicester","www.le.ac.uk"},
                {"University of Lincoln","Lincoln","www.lincoln.ac.uk"},
                {"University of Liverpool","Liverpool","www.liverpool.ac.uk"},
                {"University of London","London","www.london.ac.uk"},
                {"University of Oxford","Oxford","www.ox.ac.uk"},
                {"University of Portsmouth","Portsmouth","www.port.ac.uk"},
                {"University of Reading","Reading","www.reading.ac.uk"},
                {"University of Roehampton","Roehampton","www.roehampton.ac.uk"},
                {"University of Salford","Salford","www.salford.ac.uk"},
                {"University of South Wales","Pontypridd","www.southwales.ac.uk"},
                {"University of Southampton","Southampton","www.southampton.ac.uk"},
                {"University of St Andrews","St Andrews","www.st-andrews.ac.uk"},
                {"University of St Mark and St John","Plymouth","www.marjon.ac.uk"},
                {"University of Stirling","Stirling","www.stir.ac.uk"},
                {"University of Strathclyde","Glasgow","www.strath.ac.uk"},
                {"University of Sunderland","Sunderland","www.sunderland.ac.uk"},
                {"University of Surrey","Guildford","www.surrey.ac.uk"},
                {"University of Sussex","Brighton","www.sussex.ac.uk"},
                {"University of the Arts London","London","www.arts.ac.uk"},
                {"University of the Highlands and Islands","Inverness","www.uhi.ac.uk"},
                {"University of the West of England","Bristol","www.uwe.ac.uk"},
                {"University of the West of Scotland","Paisley","www.uws.ac.uk"},
                {"University of Wales","Cardiff","www.wales.ac.uk"},
                {"University of Wales Trinity Saint David","Carmarthen","www.uwtsd.ac.uk"},
                {"University of West London","Ealing","www.uwl.ac.uk"},
                {"University of Westminster","London","www.westminster.ac.uk"},
                {"University of Wolverhampton","Wolverhampton","www.wlv.ac.uk"},
                {"University of Worcester","Worcester","www.worcester.ac.uk"},
                {"York St John University","York","www.yorksj.ac.uk"}
        };

        for (int i = 0; i <= universities.length-1; i++){

            String queryInsert = "INSERT INTO universities (name, location, website) " +
                    "VALUES ('" + universities[i][0].replace("'","''") +"', '" + universities[i][1].replace("'","''") + "', '"+ universities[i][2].replace("'","''")+"')";
            database.execSQL(queryInsert);
        }

    }
    public String getData(){
        String out ="";
        SQLiteDatabase database = this.getWritableDatabase();
        String queryOut = "SELECT name from universities";
        Cursor cursor = database.rawQuery(queryOut, null);
        System.out.println("records: "+cursor.getCount());
        try {
            if(cursor.moveToFirst()) {
                do {
                    //out = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        }catch(Exception e){
            Log.e("ERROR", e.toString());
        }
        int test = 0;
        test = cursor.getColumnCount();
        database.close();
        out = Integer.toString(test);
     return out;
    }
    public ArrayList<HashMap<String, String>> getSearch(){

        ArrayList<HashMap<String, String>> uniList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();

        String leaderQuery = "SELECT id, name, location FROM universities";
        try
        {


            Cursor cursor = database.rawQuery(leaderQuery, null);

            if(cursor.moveToFirst()){

                do{

                    HashMap<String, String> contactMap = new HashMap<String, String>();
                    contactMap.put("rankId", cursor.getString(0));
                    contactMap.put("name", cursor.getString(1));
                    contactMap.put("location", cursor.getString(2));
                    uniList.add(contactMap);

                } while(cursor.moveToNext());

            }
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        database.close();
        return uniList;
    }
    public ArrayList<HashMap<String,String>> searchResults(String searchQuery){
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();

        String leaderQuery = "SELECT id, name, location FROM universities Where name LIKE '%" + searchQuery + "%'";
        try
        {


            Cursor cursor = database.rawQuery(leaderQuery, null);

            if(cursor.moveToFirst()){

                do{

                    HashMap<String, String> contactMap = new HashMap<String, String>();
                    contactMap.put("rankId", cursor.getString(0));
                    contactMap.put("name", cursor.getString(1));
                    contactMap.put("location", cursor.getString(2));
                    results.add(contactMap);

                } while(cursor.moveToNext());

            }
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        database.close();
        return results;
    }
    public ArrayList<HashMap<String,String>> getUniByID(String searchQuery){
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();

        String leaderQuery = "SELECT id, name, location FROM universities WHERE id='" + searchQuery + "'";
        try
        {

            Cursor cursor = database.rawQuery(leaderQuery, null);

            if(cursor.moveToFirst()){
                do{

                    HashMap<String, String> contactMap = new HashMap<String, String>();
                    contactMap.put("rankId", cursor.getString(0));
                    contactMap.put("name", cursor.getString(1));
                    contactMap.put("location", cursor.getString(2));
                    results.add(contactMap);

                } while(cursor.moveToNext());

            }
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        database.close();
        return results;
    }
    public ArrayList<HashMap<String,String>> getReviews(String searchQuery){
        ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();

        return results;
    }
    public String testService(){
        String testString = "Did not work";

            String serverURL = "http://www.gamingcentury.com/univisedservice.php";

            // Use AsyncTask execute Method To Prevent ANR Problem


        System.out.println("result - " + testString);
        return testString;
    }

}



