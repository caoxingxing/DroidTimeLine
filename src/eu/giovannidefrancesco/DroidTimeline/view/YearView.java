package eu.giovannidefrancesco.DroidTimeline.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import eu.giovannidefrancesco.DroidTimeline.R;

public class YearView extends View {

	private int mYear = 1850;
	private int mBackgroundColor = -1;
	private Integer mBackgroundResource = R.drawable.timeline_default_gradient;
	private int mYearColor = Color.DKGRAY;
	private int mIntervalWidth = 30;
	private boolean mUsesLines = true;
	private int mLinesColor = Color.DKGRAY;
	private int mLinesHeight = 10;
	private int mLinesCount = -1;
	private int mLinesWidth = 3;
	private int mYearSize = 50;
	private Paint paint;

	public YearView(Context context) {
		super(context);
		
		paint = new Paint();
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
	}

	public YearView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setAttributes(context, attrs);
	}

	public YearView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAttributes(context, attrs);
	}

	private void setAttributes(Context context, AttributeSet attrs) {
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.YearView, 0, 0);
		try {
			mYear = ta.getInteger(R.styleable.YearView_year, mYear);
		} finally {
			ta.recycle();
		}
	}

	public int getYear() {
		return mYear;
	}

	public void setYear(int mYear) {
		this.mYear = mYear;
		this.invalidate();
	}

	public int getBackgroundColor() {
		return mBackgroundColor;
	}

	public void setBackgroundColor(int mBackgroundColor) {
		this.mBackgroundColor = mBackgroundColor;
		this.invalidate();
	}

	public int getYearColor() {
		return mYearColor;
	}

	public void setYearColor(int mYearColor) {
		this.mYearColor = mYearColor;
		this.invalidate();
	}

	public int getIntervalWidth() {
		return mIntervalWidth;
	}

	public void setIntervalWidth(int mIntervalWidth) {
		this.mIntervalWidth = mIntervalWidth;
		this.invalidate();
	}
	

	public Integer getBackgroundResource() {
		return mBackgroundResource;
	}
	public void setUseLines(boolean use){
		this.mUsesLines=use;
		this.invalidate();
	}

	public void setBackgroundResource(Integer mBackgroundResource) {
		this.mBackgroundResource = mBackgroundResource;
		super.setBackgroundResource(mBackgroundResource);
		this.invalidate();
	}

	public int getLinesColor() {
		return mLinesColor;
	}

	public void setLinesColor(int mLinesColor) {
		this.mLinesColor = mLinesColor;
		this.invalidate();
	}

	public int getLinesHeight() {
		return mLinesHeight;
	}

	public void setLinesHeight(int mLinesHeight) {
		this.mLinesHeight = mLinesHeight;
		this.invalidate();
	}

	public int getLinesWidth() {
		return mLinesWidth;
	}

	public void setLinesWidth(int mLinesWidth) {
		this.mLinesWidth = mLinesWidth;
		this.invalidate();
	}

	public int getYearSize() {
		return mYearSize;
	}

	public void setYearSize(int mYearSize) {
		this.mYearSize = mYearSize;
		paint.setTextSize(mYearSize);
		this.invalidate();
	}

	public int getLinesCount() {
		return mLinesCount;
	}

	public void setLinesCount(int mLinesCount) {
		this.mLinesCount = mLinesCount;
	}

	public boolean isUsesLines() {
		return mUsesLines;
	}

	public void setmUsesLines(boolean mUsesLines) {
		this.mUsesLines = mUsesLines;
		this.invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int desiredWidth = 200;
		int desiredHeight = 100;

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width;
		int height;

		// Measure Width
		if (widthMode == MeasureSpec.EXACTLY) {
			// Must be this size
			width = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			// Can't be bigger than...
			width = Math.min(desiredWidth, widthSize);
		} else {
			// Be whatever you want
			width = desiredWidth;
		}

		// Measure Height
		if (heightMode == MeasureSpec.EXACTLY) {
			// Must be this size
			height = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			// Can't be bigger than...
			height = Math.min(desiredHeight, heightSize);
		} else {
			// Be whatever you want
			height = desiredHeight;
		}

		setMeasuredDimension(width, height);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		// background
		if (mBackgroundColor!= -1) {
			paint.setColor(mBackgroundColor);
			canvas.drawRect(getPaddingTop(), getPaddingLeft(),
					getMeasuredWidth() - getPaddingRight(), getMeasuredHeight()
							- getPaddingBottom(), paint);
		}

		// Year
		paint.setColor(mYearColor);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(mYearSize);
		canvas.drawText(mYear + "", mYearSize + getPaddingLeft() + 10,
				mYearSize + getPaddingTop() + 5, paint);

		// lines
		if (mUsesLines) {
			paint.setColor(mLinesColor);
			paint.setStrokeWidth(mLinesWidth);
			// middle lines
			if (mLinesCount != -1)
				mIntervalWidth = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight())
						/ mLinesCount;
			for (int i = 1; i < (getMeasuredWidth() - getPaddingLeft() - getPaddingRight())
					/ mIntervalWidth; i++) {
				float startX = mIntervalWidth * i + getPaddingLeft();
				float startY = getMeasuredHeight()
						- (getMeasuredHeight() / mLinesHeight + getPaddingTop());
				float endX = mIntervalWidth * i + getPaddingLeft();
				float endY = getMeasuredHeight() - getPaddingBottom();
				canvas.drawLine(startX, startY, endX, endY, paint);
			}
		}
		// end line
		canvas.drawLine(getMeasuredWidth() - mLinesWidth - getPaddingLeft(),
				getPaddingTop(), getMeasuredWidth() - mLinesWidth
						- getPaddingRight(), getMeasuredHeight()
						- getPaddingBottom(), paint);
	}
}
