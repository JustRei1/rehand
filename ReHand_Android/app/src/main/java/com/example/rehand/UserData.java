package com.example.rehand;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData extends Fragment {

    private LineChart thumb_lineChart, index_lineChart, middle_lineChart,
    ring_lineChart, little_lineChart, X_lineChart, Y_lineChart,
    EMG_lineChart;

    private List<String> xValues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View actView = inflater.inflate(R.layout.fragment_user_data, container, false);

        xValues = Arrays.asList("","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","",
                                "","","","","","");

        // Get all linechart views
        thumb_lineChart = actView.findViewById(R.id.thumb_chart_data);
        index_lineChart = actView.findViewById(R.id.index_chart_data);
        middle_lineChart = actView.findViewById(R.id.middle_chart_data);
        ring_lineChart = actView.findViewById(R.id.ring_chart_data);
        little_lineChart = actView.findViewById(R.id.little_chart_data);
        X_lineChart = actView.findViewById(R.id.x_chart_data);
        Y_lineChart = actView.findViewById(R.id.y_chart_data);
        EMG_lineChart = actView.findViewById(R.id.emg_chart_data);

        Description desc = new Description();
        desc.setText("");
        desc.setPosition(150f, 15f);
        thumb_lineChart.setDescription(desc);
        thumb_lineChart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = thumb_lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(8);
        xAxis.setGranularity(1f);

        YAxis yAxis = thumb_lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(0f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(60);

        List<Entry> thumb = new ArrayList<>();
        thumb.add(new Entry(0, 10f));
        thumb.add(new Entry(1, 10f));
        thumb.add(new Entry(2, 5f));
        thumb.add(new Entry(3, 25f));
        thumb.add(new Entry(4, 10f));
        thumb.add(new Entry(52, 10f));
        thumb.add(new Entry(53, 5f));
        thumb.add(new Entry(54, 25f));
        thumb.add(new Entry(55, 10f));
        thumb.add(new Entry(56, 5f));
        thumb.add(new Entry(57, 25f));
        thumb.add(new Entry(58, 10f));
        thumb.add(new Entry(59, 10f));

        LineDataSet thumbdataSet1 = new LineDataSet(thumb, "Thumb");
        thumbdataSet1.setColor(Color.BLUE);

        LineData thumblineData = new LineData(thumbdataSet1);
        thumb_lineChart.setData(thumblineData);
        thumb_lineChart.invalidate();


        index_lineChart.setDescription(desc);
        index_lineChart.getAxisRight().setDrawLabels(false);

        XAxis index_xAxis = index_lineChart.getXAxis();
        index_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        index_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        index_xAxis.setLabelCount(8);
        index_xAxis.setGranularity(1f);

        YAxis index_yAxis = index_lineChart.getAxisLeft();
        index_yAxis.setAxisMinimum(0f);
        index_yAxis.setAxisMaximum(100f);
        index_yAxis.setAxisLineWidth(0f);
        index_yAxis.setAxisLineColor(Color.BLACK);
        index_yAxis.setLabelCount(60);

        List<Entry> index = new ArrayList<>();
        index.add(new Entry(0, 10f));
        index.add(new Entry(1, 10f));
        index.add(new Entry(2, 5f));
        index.add(new Entry(3, 25f));
        index.add(new Entry(4, 10f));
        index.add(new Entry(52, 10f));
        index.add(new Entry(53, 5f));
        index.add(new Entry(54, 25f));
        index.add(new Entry(55, 10f));
        index.add(new Entry(56, 5f));
        index.add(new Entry(57, 25f));
        index.add(new Entry(58, 10f));
        index.add(new Entry(59, 10f));

        LineDataSet indexdataSet1 = new LineDataSet(index, "Index");
        thumbdataSet1.setColor(Color.BLUE);

        LineData indexlineData = new LineData(indexdataSet1);
        index_lineChart.setData(indexlineData);
        index_lineChart.invalidate();


        middle_lineChart.setDescription(desc);
        middle_lineChart.getAxisRight().setDrawLabels(false);

        XAxis middle_xAxis = middle_lineChart.getXAxis();
        middle_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        middle_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        middle_xAxis.setLabelCount(8);
        middle_xAxis.setGranularity(1f);

        YAxis middle_yAxis = middle_lineChart.getAxisLeft();
        middle_yAxis.setAxisMinimum(0f);
        middle_yAxis.setAxisMaximum(100f);
        middle_yAxis.setAxisLineWidth(0f);
        middle_yAxis.setAxisLineColor(Color.BLACK);
        middle_yAxis.setLabelCount(60);

        List<Entry> middle = new ArrayList<>();
        middle.add(new Entry(0, 10f));
        middle.add(new Entry(1, 10f));
        middle.add(new Entry(2, 5f));
        middle.add(new Entry(3, 25f));
        middle.add(new Entry(4, 10f));
        middle.add(new Entry(52, 10f));
        middle.add(new Entry(53, 5f));
        middle.add(new Entry(54, 25f));
        middle.add(new Entry(55, 10f));
        middle.add(new Entry(56, 5f));
        middle.add(new Entry(57, 25f));
        middle.add(new Entry(58, 10f));
        middle.add(new Entry(59, 10f));

        LineDataSet middledataSet1 = new LineDataSet(middle, "Middle");
        middledataSet1.setColor(Color.BLUE);

        LineData middlelineData = new LineData(middledataSet1);
        middle_lineChart.setData(middlelineData);
        middle_lineChart.invalidate();


        ring_lineChart.setDescription(desc);
        ring_lineChart.getAxisRight().setDrawLabels(false);

        XAxis ring_xAxis = ring_lineChart.getXAxis();
        ring_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        ring_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        ring_xAxis.setLabelCount(8);
        ring_xAxis.setGranularity(1f);

        YAxis ring_yAxis = ring_lineChart.getAxisLeft();
        ring_yAxis.setAxisMinimum(0f);
        ring_yAxis.setAxisMaximum(100f);
        ring_yAxis.setAxisLineWidth(0f);
        ring_yAxis.setAxisLineColor(Color.BLACK);
        ring_yAxis.setLabelCount(60);

        List<Entry> ring = new ArrayList<>();
        ring.add(new Entry(0, 10f));
        ring.add(new Entry(1, 10f));
        ring.add(new Entry(2, 5f));
        ring.add(new Entry(3, 25f));
        ring.add(new Entry(4, 10f));
        ring.add(new Entry(52, 10f));
        ring.add(new Entry(53, 5f));
        ring.add(new Entry(54, 25f));
        ring.add(new Entry(55, 10f));
        ring.add(new Entry(56, 5f));
        ring.add(new Entry(57, 25f));
        ring.add(new Entry(58, 10f));
        ring.add(new Entry(59, 10f));

        LineDataSet ringdataSet1 = new LineDataSet(ring, "Ring");
        ringdataSet1.setColor(Color.BLUE);

        LineData ringlineData = new LineData(ringdataSet1);
        ring_lineChart.setData(ringlineData);
        ring_lineChart.invalidate();


        little_lineChart.setDescription(desc);
        little_lineChart.getAxisRight().setDrawLabels(false);

        XAxis little_xAxis = little_lineChart.getXAxis();
        little_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        little_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        little_xAxis.setLabelCount(8);
        little_xAxis.setGranularity(1f);

        YAxis little_yAxis = thumb_lineChart.getAxisLeft();
        little_yAxis.setAxisMinimum(0f);
        little_yAxis.setAxisMaximum(100f);
        little_yAxis.setAxisLineWidth(0f);
        little_yAxis.setAxisLineColor(Color.BLACK);
        little_yAxis.setLabelCount(60);

        List<Entry> little = new ArrayList<>();
        little.add(new Entry(0, 10f));
        little.add(new Entry(1, 10f));
        little.add(new Entry(2, 5f));
        little.add(new Entry(3, 25f));
        little.add(new Entry(4, 10f));
        little.add(new Entry(52, 10f));
        little.add(new Entry(53, 5f));
        little.add(new Entry(54, 25f));
        little.add(new Entry(55, 10f));
        little.add(new Entry(56, 5f));
        little.add(new Entry(57, 25f));
        little.add(new Entry(58, 10f));
        little.add(new Entry(59, 10f));

        LineDataSet littledataSet1 = new LineDataSet(little, "Little");
        littledataSet1.setColor(Color.BLUE);

        LineData littlelineData = new LineData(littledataSet1);
        little_lineChart.setData(littlelineData);
        little_lineChart.invalidate();


        X_lineChart.setDescription(desc);
        X_lineChart.getAxisRight().setDrawLabels(false);

        XAxis x_xAxis = X_lineChart.getXAxis();
        x_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        x_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        x_xAxis.setLabelCount(8);
        x_xAxis.setGranularity(1f);

        YAxis x_yAxis = X_lineChart.getAxisLeft();
        x_yAxis.setAxisMinimum(0f);
        x_yAxis.setAxisMaximum(100f);
        x_yAxis.setAxisLineWidth(0f);
        x_yAxis.setAxisLineColor(Color.BLACK);
        x_yAxis.setLabelCount(60);

        List<Entry> x = new ArrayList<>();
        x.add(new Entry(0, 10f));
        x.add(new Entry(1, 10f));
        x.add(new Entry(2, 5f));
        x.add(new Entry(3, 25f));
        x.add(new Entry(4, 10f));
        x.add(new Entry(52, 10f));
        x.add(new Entry(53, 5f));
        x.add(new Entry(54, 25f));
        x.add(new Entry(55, 10f));
        x.add(new Entry(56, 5f));
        x.add(new Entry(57, 25f));
        x.add(new Entry(58, 10f));
        x.add(new Entry(59, 10f));

        LineDataSet xdataSet1 = new LineDataSet(x, "X");
        xdataSet1.setColor(Color.BLUE);

        LineData xlineData = new LineData(xdataSet1);
        X_lineChart.setData(xlineData);
        X_lineChart.invalidate();


        Y_lineChart.setDescription(desc);
        Y_lineChart.getAxisRight().setDrawLabels(false);

        XAxis y_xAxis = Y_lineChart.getXAxis();
        y_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        y_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        y_xAxis.setLabelCount(8);
        y_xAxis.setGranularity(1f);

        YAxis y_yAxis = Y_lineChart.getAxisLeft();
        y_yAxis.setAxisMinimum(0f);
        y_yAxis.setAxisMaximum(100f);
        y_yAxis.setAxisLineWidth(0f);
        y_yAxis.setAxisLineColor(Color.BLACK);
        y_yAxis.setLabelCount(60);

        List<Entry> y = new ArrayList<>();
        y.add(new Entry(0, 10f));
        y.add(new Entry(1, 10f));
        y.add(new Entry(2, 5f));
        y.add(new Entry(3, 25f));
        y.add(new Entry(4, 10f));
        y.add(new Entry(52, 10f));
        y.add(new Entry(53, 5f));
        y.add(new Entry(54, 25f));
        y.add(new Entry(55, 10f));
        y.add(new Entry(56, 5f));
        y.add(new Entry(57, 25f));
        y.add(new Entry(58, 10f));
        y.add(new Entry(59, 10f));

        LineDataSet ydataSet1 = new LineDataSet(y, "Y");
        ydataSet1.setColor(Color.BLUE);

        LineData ylineData = new LineData(ydataSet1);
        Y_lineChart.setData(ylineData);
        Y_lineChart.invalidate();

        EMG_lineChart.setDescription(desc);
        EMG_lineChart.getAxisRight().setDrawLabels(false);

        XAxis emg_xAxis = EMG_lineChart.getXAxis();
        emg_xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        emg_xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        emg_xAxis.setLabelCount(8);
        emg_xAxis.setGranularity(1f);

        YAxis emg_yAxis = EMG_lineChart.getAxisLeft();
        emg_yAxis.setAxisMinimum(0f);
        emg_yAxis.setAxisMaximum(100f);
        emg_yAxis.setAxisLineWidth(0f);
        emg_yAxis.setAxisLineColor(Color.BLACK);
        emg_yAxis.setLabelCount(60);

        List<Entry> emg = new ArrayList<>();
        emg.add(new Entry(0, 10f));
        emg.add(new Entry(1, 10f));
        emg.add(new Entry(2, 5f));
        emg.add(new Entry(3, 25f));
        emg.add(new Entry(4, 10f));
        emg.add(new Entry(52, 10f));
        emg.add(new Entry(53, 5f));
        emg.add(new Entry(54, 25f));
        emg.add(new Entry(55, 10f));
        emg.add(new Entry(56, 5f));
        emg.add(new Entry(57, 25f));
        emg.add(new Entry(58, 10f));
        emg.add(new Entry(59, 10f));

        LineDataSet emgdataSet1 = new LineDataSet(emg, "EMG");
        emgdataSet1.setColor(Color.BLUE);

        LineData emglineData = new LineData(emgdataSet1);
        EMG_lineChart.setData(emglineData);
        EMG_lineChart.invalidate();

        return actView;
    }
}