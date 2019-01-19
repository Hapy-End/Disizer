package ru.udreams.disizer;

import android.os.*;
import android.util.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

public class FileUtils
{
	public static void TextReplace(String fileName, String search, String replace)
	{
		Charset charset = StandardCharsets.UTF_8;
		Path path = Paths.get(fileName);
		try
		{
			Files.write(path,
						new String(Files.readAllBytes(path), charset).replace(search, replace)
						.getBytes(charset));
		}
		catch (IOException e)
		{}
	}

	public static void writeFileSD(String dir, String fileName, String text)
	{
		File sdPath = Environment.getExternalStorageDirectory();
		sdPath = new File(sdPath.getAbsolutePath() + "/" + dir);
		sdPath.mkdirs();
		File sdFile = new File(sdPath, fileName);
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
			bw.write(text);
			bw.close();
		}
		catch (IOException e)
		{}
	}
	public static String readFileSD(String LOG_TAG, String dir, String fileName) throws IOException
	{
		String stre = null;
		File sdPath = Environment.getExternalStorageDirectory();
		sdPath = new File(sdPath.getAbsolutePath() + "/" + dir);
		File sdFile = new File(sdPath, fileName);
		BufferedReader br = new BufferedReader(new FileReader(sdFile));
		stre = br.readLine();
		return new String(stre);
	}
	public static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
	public static String readFileD(String LOG_TAG, String dir, String fileName) throws IOException
	{
		String stre = null;
		File sdPath = Environment.getExternalStorageDirectory();
		sdPath = new File(sdPath.getAbsolutePath() + "/" + dir);
		File sdFile = new File(sdPath, fileName);
		BufferedReader br = new BufferedReader(new FileReader(sdFile));
		stre = br.readLine();
		return new String(stre);
	}
}
