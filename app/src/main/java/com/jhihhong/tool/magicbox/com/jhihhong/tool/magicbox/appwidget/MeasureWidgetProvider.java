package com.jhihhong.tool.magicbox.com.jhihhong.tool.magicbox.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;

import com.jhihhong.tool.magicbox.R;

/**
 * Implementation of App Widget functionality.
 */
public class MeasureWidgetProvider extends AppWidgetProvider {

	private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
								 int appWidgetId) {
		updateAppWidget(context, appWidgetManager, appWidgetId, getDisplayInfo(context).toString());
	}

	private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
								 int appWidgetId, String info) {
		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.measure_widget_provider);
		views.setTextViewText(R.id.appwidget_text, info);

		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		for (int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}

	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first widget is created
	}

	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions){
		String msg =
				String.format("w(%d-%d)\r\nh(%d-%d)\r\n",
						newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH),
						newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH),
						newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT),
						newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT));
		updateAppWidget(context, appWidgetManager, appWidgetId, msg + getDisplayInfo(context).toString());
	}

	private StringBuilder getDisplayInfo(Context context) {
		StringBuilder info = new StringBuilder();
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		info.append("dpi " + metrics.densityDpi + " sw " + (int) (Math.min(metrics.widthPixels, metrics.heightPixels) / metrics.density) + "\r\n");
		info.append("px" + metrics.widthPixels + "x" + metrics.heightPixels + "\r\n");
		int dpWidth = (int) (metrics.widthPixels / metrics.density);
		int dpHeight = (int) (metrics.heightPixels / metrics.density);
		info.append("dp" + dpWidth + "x" + dpHeight + "\r\n");
		return info;
	}
}

