package com.tevinjeffrey.rutgersct.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tevinjeffrey.rutgersct.R;
import com.tevinjeffrey.rutgersct.model.Subject;

import org.apache.commons.lang3.text.WordUtils;

import java.util.List;

public class SubjectAdapter extends ArrayAdapter {

    private final Context context;
    private final List<Subject> item;

    public SubjectAdapter(Context context, List<Subject> item) {
        //noinspection unchecked
        super(context, -1, item);
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        ViewHolder viewHolder;

        if (rowView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            viewHolder = new ViewHolder();

            rowView = mInflater.inflate(R.layout.subject_name, null);
            viewHolder.text = (TextView) rowView.findViewById(R.id.course_list_title);

            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        String text = item.get(position).getCode() + ": " + item.get(position).getDescription();
        viewHolder.text.setText(WordUtils.capitalize(text.toLowerCase()));

        return rowView;

    }

    static class ViewHolder {
        public TextView text;
    }

}