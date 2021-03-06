package com.example.logpas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SucActivity extends AppCompatActivity  {
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private Adapter adapter;
    private List<ListItemCl> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suc);
        init();
    }
    private void init()
    {
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new Adapter(this,R.layout.list_item,arrayList,getLayoutInflater());
        listView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();

            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb()
    {
        try {
            doc = Jsoup.connect("https://cbr.ru/currency_base/daily/").get();
            Elements tables = doc.getElementsByTag("tbody");
            Element our_table = tables.get(0);
            Elements elements_from_table = our_table.children();
            Element dollar = elements_from_table.get(1);

            ListItemCl items = new ListItemCl();
            items.setData_1(our_table.children().get(0).child(1).text());
            items.setData_2(our_table.children().get(0).child(3).text());
            items.setData_3(our_table.children().get(0).child(4).text());
            arrayList.add(items);
            items = new ListItemCl();
            items.setData_1(our_table.children().get(11).child(1).text());
            items.setData_2(our_table.children().get(11).child(3).text());
            items.setData_3(our_table.children().get(11).child(4).text());
            arrayList.add(items);
            items = new ListItemCl();
            items.setData_1(our_table.children().get(12).child(1).text());
            items.setData_2(our_table.children().get(12).child(3).text());
            items.setData_3(our_table.children().get(12).child(4).text());
            arrayList.add(items);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
