package com.lecandroid_2024_1.week10_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Document doc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView1);
    }

    public void onClick(View view) {
        Thread downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doc = getXML("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            postproc(doc);
                            Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Background Task", e.toString());
                }
            }
        });
        downloadThread.start();
    }

    private Document getXML(String urla) {
        try {
            URL url = new URL(urla);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {

        }
        return  doc;
    }

    protected void postproc(Document doc) {
        String s = "";
        NodeList nodeList = doc.getElementsByTagName("data");

        for (int i = 0; i < nodeList.getLength(); i++) {
            s += "" + i + ": 날씨 정보: ";
            Node node = nodeList.item(i);
            Element fstllmnt = (Element) node;

            NodeList nameList = fstllmnt.getElementsByTagName("temp");
            Element nameElement = (Element) nameList.item(0);
            nameList = nameElement.getChildNodes();

            s += "온도 = " + ((Node) nameList.item(0)).getNodeValue() + "도, ";

            NodeList websiteList = fstllmnt.getElementsByTagName("wfKor");
            Element websiteElement = (Element) websiteList.item(0);
            websiteList = websiteElement.getChildNodes();

            s += "날씨 = " + ((Node) websiteList.item(0)).getNodeValue() + ", ";

            NodeList wdList = fstllmnt.getElementsByTagName("wdKor");
            Element wdElement = (Element) wdList.item(0);
            wdList = wdElement.getChildNodes();

            s += "바람방향 = " + ((Node) wdList.item(0)).getNodeValue() + "\n";
        }
        textView.setText(s);
    }
}