package com.example.p3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

//public final class list8adapter {

//}
//set   list.setAdapter(new EfficientAdapter(getApplicationContext()));

public  class list8adapter extends BaseAdapter  {


        ViewHolder holder; 
        private LayoutInflater mInflater;
        Activity context = null;
		private Object one;
		private Object two;

        public static final int DIALOG_DOWNLOAD_PROGRESS = 0;












        public list8adapter(Context context) {
            mInflater = LayoutInflater.from(context);


        }


        public int getCount() {
            return 3;  //one.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list8_item, null);



                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.TextView01);
                holder.text1 = (TextView) convertView.findViewById(R.id.textView2);
                //holder.image = (ImageView) convertView.findViewById(R.id.ImageView01);
                holder.btn = (Button) convertView.findViewById(R.id.button);
                holder.btn.setFocusable(false);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }







           // holder.text.setText(one.get(position));
           // holder.text1.setText("Stall No: "+two.get(position));







            return convertView;

        }



        class ViewHolder {
            TextView text;
            TextView text1;
            //ImageView image;
            Button btn;


        }
        }