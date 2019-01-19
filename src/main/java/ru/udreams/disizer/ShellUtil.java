package ru.udreams.disizer;

import android.view.*;
import android.widget.*;
import java.io.*;
import ru.udreams.disizer.*;

public class ShellUtil
{
	public static void sh(String...strings) {
		try{
			Process su = Runtime.getRuntime().exec("su");
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

			for (String s : strings) {
				outputStream.writeBytes(s+"\n");
				outputStream.flush();
			}

			outputStream.writeBytes("exit\n");
			outputStream.flush();
			try {
				su.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			outputStream.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return;
	}
	public static String shout(String...strings)
	{
		StringBuffer output = null;
		try
		{
			Process su = Runtime.getRuntime().exec("su");
			DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

			for (String s : strings)
			{
				outputStream.writeBytes(s + "\n");
				outputStream.flush();
			}

			outputStream.writeBytes("exit\n");
			outputStream.flush();
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(su.getInputStream()));
			int read;
			char[] buffer = new char[4096];
			output = new StringBuffer();
			read = reader.read(buffer);
			output.append(buffer, 0, read);
			
			try
			{
				su.waitFor();
			}
			catch (InterruptedException e)
			{}
			reader.close();
		}
		catch (IOException e)
		{}

		return new String(output);
	}
}
