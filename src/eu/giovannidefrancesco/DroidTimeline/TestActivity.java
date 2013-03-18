package eu.giovannidefrancesco.DroidTimeline;


import eu.giovannidefrancesco.DroidTimeline.R;
import eu.giovannidefrancesco.DroidTimeline.widget.HorizontalListView;
import eu.giovannidefrancesco.DroidTimeline.widget.TimeLineView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class TestActivity extends Activity{

	private HAdapter adapt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test);
		HorizontalListView hListView = (HorizontalListView) findViewById(R.id.hlistview);
		adapt = new HAdapter(this);
		hListView.setAdapter(adapt);
		hListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(TestActivity.this,
						"Yuppi! Siamo nel " + ((TimeLineView) arg1).getYear(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}

class HAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater inflater;
	private int count;

	public HAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		ctx = context;
		count = 10;
	}

	public void addToCount(int i) {
		count += i;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TimeLineView v = new TimeLineView(ctx);
		v.setYear(v.getYear() + position * 10);
		v.setYearColor(Color.GREEN);
		v.setLinesCount(9);
		if(position==getCount()-1){
			addToCount(5);
			this.notifyDataSetChanged();
		}
		return v;
	}
}
