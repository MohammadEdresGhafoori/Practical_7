package com.example.practical_7;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends ListActivity {
    ArrayList<String> xmlList = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            InputStream is=getResources().openRawResource(R.raw.employeedetails);
            DocumentBuilder builder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc=builder.parse(is, null);
            NodeList nodes=doc.getElementsByTagName("Details");
            if(nodes != null && nodes.getLength() > 0) {
                xmlList.clear();
                int len = nodes.getLength();
                for(int i = 0; i < len; ++i) {
                    // query value
                    Node node = nodes.item(i);
                    xmlList.add(node.getTextContent());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, xmlList);
            setListAdapter(adapter);
        }
        catch(Throwable t){
            Toast.makeText(this, "Exception :" + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
