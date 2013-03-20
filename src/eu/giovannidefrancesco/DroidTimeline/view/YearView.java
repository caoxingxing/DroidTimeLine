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
	private int mBackgroundColor = Color.LTGRAY;
	private int mYearColor = Color.DKGRAY;
	private int mIntervalWidth = 30;
	private boolean mUsesLines = true;
	private int mLinesColor = Color.DKGRAY;
	private int mLinesHeight = 10;
	private int mLinesCount = -1;
	private int mLinesWidth = 3;
	private int mYearSize = 50;

	public YearView(Context context) {
		super(context);
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

	public YearView(Context context, Integer year, Integer backgroundColor,
			Integer yearColor, Integer intervalWidth, boolean usesLines,
			Integer linesColor, Integer linesHeight, Integer linesCount,
			Integer linesWidth, Integer yearSize) {
		super(context);
		this.mYear = year;
		this.mBackgroundColor = backgroundColor;
		this.mYearColor = yearColor;
		this.mIntervalWidth = intervalWidth;
		this.mUsesLines = usesLines;
		this.mLinesColor = linesColor;
		this.mLinesHeight = linesHeight;
		this.mLinesCount = linesCount;
		this.mLinesWidth = linesWidth;
		this.mYearSize = yearSize;
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
	public void draw(Canvas canvas) {
		super.draw(canvas);

		Paint p = new Paint();
		p.setStyle(Style.FILL);
		p.setAntiAlias(true);

		// background
		p.setColor(mBackgroundColor);
		canvas.drawRect(getPaddingTop(), getPaddingLeft(), getMeasuredWidth()
				- getPaddingRight(), getMeasuredHeight() - getPaddingBottom(),
				p);

		// Year
		p.setColor(mYearColor);
		p.setTextAlign(Paint.Align.CENTER);
		p.setTextSize(mYearSize);
		canvas.drawText(mYear + "", mYearSize + getPaddingLeft() + 10,
				mYearSize + getPaddingTop() + 10, p);

		// lines
		if (mUsesLines) {
			p.setColor(mLinesColor);
			p.setStrokeWidth(mLinesWidth);
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
				canvas.drawLine(startX, startY, endX, endY, p);
			}
		}
		// end line
		canvas.drawLine(getMeasuredWidth() - mLinesWidth - getPaddingLeft(),
				getPaddingTop(), getMeasuredWidth() - mLinesWidth
						- getPaddingRight(), getMeasuredHeight()
						- getPaddingBottom(), p);
		p = null;
	}
}
