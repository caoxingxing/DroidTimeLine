package eu.giovannidefrancesco.DroidTimeline.view;

import java.util.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import eu.giovannidefrancesco.DroidTimeline.R;
import eu.giovannidefrancesco.DroidTimeline.widget.HorizontalListView;

public class TimeLineView extends HorizontalListView {

	private int mStartYear = 1850;
	private int mCenterYear = 1920;
	private int mEndYear;
	private int mStep = 10;
	private int mBackgroundColor = Color.LTGRAY;
	private int mYearColor = Color.DKGRAY;
	private int mIntervalWidth = 30;
	private boolean mUsesLines = true;
	private int mLinesColor = Color.DKGRAY;
	private int mLinesHeight = 10;
	private int mLinesCount = -1;
	private int mLinesWidth = 3;
	private int mYearSize = 50;

	public TimeLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.TimeLineView, 0, 0);
		try {
			mBackgroundColor = ta.getColor(
					R.styleable.TimeLineView_backgroundColor, mBackgroundColor);
			mYearColor = ta.getColor(R.styleable.TimeLineView_yearColor,
					mYearColor);
			mYearSize = ta.getDimensionPixelSize(
					R.styleable.TimeLineView_yearSize, mYearSize);
			mIntervalWidth = ta.getInteger(
					R.styleable.TimeLineView_intervalWidth, mIntervalWidth);
			mUsesLines = ta.getBoolean(R.styleable.TimeLineView_useLines,
					mUsesLines);
			mLinesColor = ta.getColor(R.styleable.TimeLineView_linesColor,
					mLinesColor);
			mLinesHeight = ta.getInteger(R.styleable.TimeLineView_linesHeight,
					mLinesHeight);
			mLinesWidth = ta.getInteger(R.styleable.TimeLineView_linesWidth,
					mLinesWidth);
			mLinesCount = ta.getInteger(R.styleable.TimeLineView_linesCount,
					mLinesCount);

			mStartYear = ta.getInteger(R.styleable.TimeLineView_startYear,
					mStartYear);
			mCenterYear = ta.getInteger(R.styleable.TimeLineView_centerYear,
					mCenterYear);
			mEndYear = ta.getInteger(R.styleable.TimeLineView_endYear, -1);
			mStep = ta.getInteger(R.styleable.TimeLineView_step, mStep);
		} finally {
			ta.recycle();
		}
		
		if (mEndYear == -1) {
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			mEndYear = year;
		}
		
		setAdapter(new TimelineAdapter(context, mStep, mStartYear, mEndYear,
				mBackgroundColor, mYearColor, mIntervalWidth, mUsesLines,
				mLinesColor, mLinesHeight, mLinesCount, mLinesWidth, mYearSize));
		
		//scrollTo((mCenterYear-mStartYear)*mStep);
	}

	public static class TimelineAdapter extends BaseAdapter {
		private Context mContext;
		private int mStep;
		private int mStartYear;

		private int mBackgroundColor = Color.LTGRAY;
		private int mYearColor = Color.DKGRAY;
		private int mIntervalWidth = 30;
		private boolean mUsesLines = true;
		private int mLinesColor = Color.DKGRAY;
		private int mLinesHeight = 10;
		private int mLinesCount = -1;
		private int mLinesWidth = 3;
		private int mYearSize = 50;
		private int mEndYear;

		public TimelineAdapter(Context ctx) {
			super();
			this.mContext = ctx;
		}

		public TimelineAdapter(Context mContext, int mStep, int mStartYear,
				int mEndYear, int mBackgroundColor, int mYearColor,
				int mIntervalWidth, boolean mUsesLines, int mLinesColor,
				int mLinesHeight, int mLinesCount, int mLinesWidth,
				int mYearSize) {
			super();
			this.mContext = mContext;
			this.mStep = mStep;
			this.mStartYear = mStartYear;
			this.mEndYear = mEndYear;
			this.mBackgroundColor = mBackgroundColor;
			this.mYearColor = mYearColor;
			this.mIntervalWidth = mIntervalWidth;
			this.mUsesLines = mUsesLines;
			this.mLinesColor = mLinesColor;
			this.mLinesHeight = mLinesHeight;
			this.mLinesCount = mLinesCount;
			this.mLinesWidth = mLinesWidth;
			this.mYearSize = mYearSize;
		}

		@Override
		public int getCount() {
			return (mEndYear - mStartYear) / mStep + 1;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			YearView v = null;
			if (position < getCount() - 1)
				v = new YearView(mContext, mStartYear + position * mStep,
						mBackgroundColor, mYearColor, mIntervalWidth,
						mUsesLines, mLinesColor, mLinesHeight, mLinesCount,
						mLinesWidth, mYearSize);
			else {
				v = new YearView(mContext, mEndYear, mBackgroundColor,
						mYearColor, mIntervalWidth, mUsesLines, mLinesColor,
						mLinesHeight, mLinesCount, mLinesWidth, mYearSize);
			}
			return v;
		}

	}

}
