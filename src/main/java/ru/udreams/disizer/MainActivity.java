package ru.udreams.disizer;

import android.*;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.preference.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity 
{
	EditText hed,wed,dpied,tos,los,ros,bos;
	RelativeLayout ml;
	int color,theme;
	TextView title,text,subtext;
	LinearLayout ll,pxe,de,ose;
	String Path = Environment.getExternalStorageDirectory().getAbsolutePath();
	String command = "wm size",dp="",output,cache = null,apath = "/Android/data/ru.udreams.disizer/",osz = "osz";

	@Override
	protected void onStart()
	{
		super.onStart();
	}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
		setContentView(R.layout.main);
		hed = findViewById(R.id.h);
		ll = findViewById(R.id.ll);
		pxe = findViewById(R.id.pxe);
		de = findViewById(R.id.de);
		ose = findViewById(R.id.ose);
		wed = findViewById(R.id.w);
		dpied = findViewById(R.id.dpied);
		tos = findViewById(R.id.tos);
		los = findViewById(R.id.los);
		ros = findViewById(R.id.ros);
		bos = findViewById(R.id.bos);
		ml = findViewById(R.id.ml);
		if (prefs.getBoolean(getString(R.string.font_change), false))
		{
			String custom_font = "fonts/DewberryBold.ttf";
			Typeface CF = Typeface.createFromAsset(getAssets(), custom_font);
			FontUtil.applyCustomFont(ml, CF);
		}
    }
	public void a() throws IOException
	{
		File file = new File(Path + apath + osz);
		if (file == null || !file.isFile())
		{
			output = ShellUtil.shout(command);
			FileUtils.writeFileSD(apath, osz, output);
			FileUtils.TextReplace(Path + apath + osz, "Physical size: ", "");
		}
		WindowManager wima = getWindowManager();
		int aa = wima.getDefaultDisplay().getHeight();
		int bb = wima.getDefaultDisplay().getWidth();
		String height = "" + aa;
		String width = "" + bb;
		String rfs = FileUtils.readFileD("log", apath, osz);
		TextView os = findViewById(R.id.osize);
		os.setText("Оригинальный размер: " + rfs);
		TextView ns = findViewById(R.id.nsize);
		ns.setText("Текущий размер: " + width + "x" + height);
	}

	@Override
	public void onBackPressed()
	{
		SharedPreferences prefs = PreferenceManager
			.getDefaultSharedPreferences(this);
		if (prefs.getBoolean(getString(R.string.pref_sec), false))
		{

			File file = new File(Path + apath + "S");
			File file2 = new File(Path + apath + "D");
			File file3 = new File(Path + apath + "O");
			if (file.isFile() || file2.isFile() || file3.isFile())
			{
				if (file.isFile())
				{
					ResetSize();
				}
				if (file2.isFile())
				{
					ResetDensity();
				}
				if (file3.isFile())
				{
					ResetOverscan();
				}
			}
			else
			{
				super.onBackPressed();
			}
		}
		else
		{
			super.onBackPressed();
		}
	}

	public void setpx(View v)
	{
		SetSize();
	}

	public void setdens(View v)
	{
		SetDensity();
	}

	public void setos(View v)
	{
		SetOverscan();
	}
	public void opset(View v)
	{
		Intent intent = new Intent(); 
		intent.setClass(this, SettingsActivity.class); 
		startActivity(intent); 
	}
	public void abset(View v)
	{
		Intent intent = new Intent(); 
		intent.setClass(this, About.class); 
		startActivity(intent); 
	}
	public void helpp(View v)
	{
		int a = ll.getVisibility();
		String b = "" + a;
		if (b.contains("8"))
		{
			ll.setVisibility(View.VISIBLE);
			Animation anim = AnimationUtils.loadAnimation(this, R.anim.helpanim);
			ll.startAnimation(anim);
		}
		text = findViewById(R.id.helpText);
		subtext = findViewById(R.id.helpSubtext);
		text.setText(R.string.helpTextPx);
		subtext.setText(R.string.helpSubtextPx);
	}
	public void helpd(View v)
	{
		int a = ll.getVisibility();
		String b = "" + a;
		if (b.contains("8"))
		{
			ll.setVisibility(View.VISIBLE);
			Animation anim = AnimationUtils.loadAnimation(this, R.anim.helpanim);
			ll.startAnimation(anim);
		}
		text = findViewById(R.id.helpText);
		subtext = findViewById(R.id.helpSubtext);
		text.setText(R.string.helpTextDpi);
		subtext.setText(R.string.helpSubtextDpi);
	}
	public void helpo(View v)
	{
		int a = ll.getVisibility();
		String b = "" + a;
		if (b.contains("8"))
		{
			ll.setVisibility(View.VISIBLE);
			Animation anim = AnimationUtils.loadAnimation(this, R.anim.helpanim);
			ll.startAnimation(anim);
		}
		text = findViewById(R.id.helpText);
		subtext = findViewById(R.id.helpSubtext);
		text.setText(R.string.helpTextOs);
		subtext.setText(R.string.helpSubtextOs);
	}
	public void resetall(View v)
	{
		RestartAll();
	}
	public void resetpx(View v)
	{
		ResetSize();
	}
	public void resetdpi(View v)
	{
		ResetDensity();
	}
	public void resetos(View v)
	{
		ResetOverscan();
	}

	public void showpxed(View v)
	{
		de.setVisibility(View.GONE);
		ose.setVisibility(View.GONE);
		pxe.setVisibility(View.VISIBLE);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		pxe.startAnimation(anim);
	}
	public void showdensed(View v)
	{
		pxe.setVisibility(View.GONE);
		ose.setVisibility(View.GONE);
		de.setVisibility(View.VISIBLE);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		de.startAnimation(anim);
	}
	public void showosed(View v)
	{
		pxe.setVisibility(View.GONE);
		de.setVisibility(View.GONE);
		ose.setVisibility(View.VISIBLE);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		ose.startAnimation(anim);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		SharedPreferences prefs = PreferenceManager
			.getDefaultSharedPreferences(this);
		try
		{
			a();
		}
		catch (IOException e)
		{}
		if (prefs.getBoolean(getString(R.string.pref_dpix), false))
		{
			hed.setHint("ВЫСОТА (dp)");
			wed.setHint("ШИРИНА (dp)");
			dp = "dp";
		}

		/*if (prefs.getBoolean(getString(R.string.pref_dpix), true))
		 {

		 }*/
		// читаем установленное значение из CheckBoxPreference


		// читаем размер шрифта из EditTextPreference
		//float fSize = Float.parseFloat(prefs.getString(
		//								   getString(R.string.pref_size), "10"));
		// применяем настройки в текстовом поле
		//hed.setTextSize(fSize);
		//wed.setTextSize(fSize);
		// читаем стили текста из ListPreference 
		/*String colors = prefs.getString(getString(R.string.pref_color), "");
		 if (colors.contains("Red")) 
		 theme = R.style.RTheme);
		 if (colors.contains("DeepOrange")) 
		 theme = R.style.DOTheme);
		 if (colors.contains("BlueGrey")) 
		 theme = R.style.BGTheme);
		 if (colors.contains("Yellow")) 
		 theme = R.style.YTheme);
		 if (colors.contains("Grey")) 
		 theme = R.style.GTheme);
		 if (colors.contains("Pink")) 
		 theme = R.style.PTheme);
		 if (colors.contains("White")) 
		 theme = R.style.WTheme);*/
	}

	private void RestartAll()
	{
		ResetSize();
		ResetDensity();
		ResetOverscan();
	}
	private void ResetSize()
	{
		ShellUtil.sh("wm size reset");
		File file = new File(Path + apath + "S");
		file.delete();
	}
	private void ResetDensity()
	{
		ShellUtil.sh("wm density reset");
		File file = new File(Path + apath + "D");
		file.delete();
	}
	private void ResetOverscan()
	{
		ShellUtil.sh("wm overscan reset");
		File file = new File(Path + apath + "O");
		file.delete();
	}

	//Изменение плотности экрана
	private void SetDensity()
	{
		String dpi = dpied.getText().toString();
		ShellUtil.sh("wm density " + dpi);
		FileUtils.writeFileSD(apath, "D", dpi);
	}

	//Изменение разрешения экрана
	private void SetSize()
	{
		String h = hed.getText().toString();
		String w = wed.getText().toString();
		ShellUtil.sh("wm size " + w + dp + "x" + h + dp);
		FileUtils.writeFileSD(apath, "S", w + "x" + h);
	}

	//Добавление отступов по краям
	private void SetOverscan()
	{
		String t = tos.getText().toString();
		String l  = los.getText().toString();
		String r = ros.getText().toString();
		String b  = bos.getText().toString();
		ShellUtil.sh("wm overscan " + l + "," + t + "," + r + "," + b);
		FileUtils.writeFileSD(apath, "O", l + "," + t + "," + r + "," + b);
	}
}
