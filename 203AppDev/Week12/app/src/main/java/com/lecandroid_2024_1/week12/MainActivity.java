package com.lecandroid_2024_1.week12;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
        TextView textview;
        Document doc = null;
        LinearLayout layout = null;

        String keyStr = "";


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            textview = (TextView) findViewById(R.id.textView1);
            keyStr = getString(R.string.API_KEY);

        }

        public void onClick(View view) {
            GetXMLTask task = new GetXMLTask(this);
            task.execute("https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=%EC%A0%84%EB%B6%81&pageNo=1&numOfRows=100&returnType=xml&serviceKey=" +
                    keyStr +
                    "&ver=1.0");
        }

        // private inner class extending AsyncTask
        @SuppressLint("NewApi")
        private class GetXMLTask extends AsyncTask<String, Void, Document> {
            private Activity context;
            public GetXMLTask(Activity context) {
                this.context = context;
            }
            @Override
            protected Document doInBackground(String... urls) {

                URL url;
                try {
                    url = new URL(urls[0]);
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db;

                    db = dbf.newDocumentBuilder();//xml 문서 빌더 객체 생성
                    doc = db.parse(new InputSource(url.openStream())); //xml 문서 파싱
                    doc.getDocumentElement().normalize();

                } catch (Exception e) {
                    // 예외 메시지를 로그로 출력
                    Log.e("GetXMLTask", "Parsing Error: " + e.getMessage());
                }
                return doc;
            }

            @Override
            protected void onPostExecute(Document doc) {

                if (doc == null) {
                    // document가 null인 경우 처리
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String s = "";
                NodeList nodeList = doc.getElementsByTagName("item");
                //item 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    Element fstElmnt = (Element) node;

                    NodeList sidoNameList = fstElmnt.getElementsByTagName("sidoName");
                    Element sidoNameElement = (Element) sidoNameList.item(0);
                    sidoNameList = sidoNameElement.getChildNodes();
                    s += ((Node) sidoNameList.item(0)).getNodeValue() +"  ";

                    NodeList stationNameList = fstElmnt.getElementsByTagName("stationName");
                    Element stationNameElement = (Element) stationNameList.item(0);
                    stationNameList = stationNameElement.getChildNodes();
                    s += ((Node) stationNameList.item(0)).getNodeValue()+ "\n";

                    NodeList pm10ValueList = fstElmnt.getElementsByTagName("pm10Value");
                    Element pm10ValueElement = (Element) pm10ValueList.item(0);
                    pm10ValueList = pm10ValueElement.getChildNodes();
                    s += "미세먼지(PM10) 농도=>" + ((Node) pm10ValueList.item(0)).getNodeValue() + "㎍/㎥\n";

                    NodeList pm25ValueList = fstElmnt.getElementsByTagName("pm25Value");
                    Element pm25ValueElement = (Element) pm25ValueList.item(0);
                    pm25ValueList = pm25ValueElement.getChildNodes();
                    s += "미세먼지(PM2.5) 농도=>" + ((Node) pm25ValueList.item(0)).getNodeValue() + "㎍/㎥\n";

                    NodeList o3ValueList = fstElmnt.getElementsByTagName("o3Value");
                    Element so2GradeElement = (Element) o3ValueList.item(0);
                    o3ValueList = so2GradeElement.getChildNodes();
                    s += "오존 농도=>" + ((Node) o3ValueList.item(0)).getNodeValue() + "ppm\n";

                    NodeList coValueList = fstElmnt.getElementsByTagName("coValue");
                    Element coValueElement = (Element) coValueList.item(0);
                    coValueList = coValueElement.getChildNodes();
                    s += "일산화탄소 농도=>" + ((Node) coValueList.item(0)).getNodeValue() + "ppm\n\n\n";

                }
                textview.setText(s);
            }

        }
    }