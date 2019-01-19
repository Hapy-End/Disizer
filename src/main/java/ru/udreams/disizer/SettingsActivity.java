package ru.udreams.disizer;

import android.app.*;
import android.content.*;
import android.os.*;
import android.preference.*;
import android.view.*;

public class SettingsActivity extends PreferenceActivity {

	int color,theme;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// загружаем предпочтения из ресурсов
		addPreferencesFromResource(R.xml.prefs);
		SharedPreferences prefs = PreferenceManager
			.getDefaultSharedPreferences(this);
		String colors = prefs.getString(getString(R.string.pref_color), "");
		color = getResources().getColor(R.color.colorPrimaryDark2);
		if (colors.contains("Red"))
		{
			color = getResources().getColor(R.color.red7);
			theme = R.style.RTheme;}
		if (colors.contains("DeepOrange"))
		{
			color = getResources().getColor(R.color.dorange7);
			theme = R.style.DOTheme;}
		if (colors.contains("BlueGrey"))
		{
			color = getResources().getColor(R.color.bluegrey7);
			theme = R.style.BGTheme;}
		if (colors.contains("Yellow"))
		{
			color = getResources().getColor(R.color.yellow7);
			theme = R.style.YTheme;}
		if (colors.contains("Grey"))
		{
			color = getResources().getColor(R.color.grey7);
			theme = R.style.GTheme;}
		if (colors.contains("Pink"))
		{
			color = getResources().getColor(R.color.pink7);
			theme = R.style.PTheme;}
		if (colors.contains("Dark"))
		{
			color = getResources().getColor(R.color.black2);
			theme = R.style.DTheme;}
		if  (colors.contains("Blue"))
		{
			color = getResources().getColor(R.color.colorPrimaryDark2);
			theme = R.style.AppTheme;}
		Window window = getWindow();
		window.setStatusBarColor(color);
		setTheme(theme);
	}
}
