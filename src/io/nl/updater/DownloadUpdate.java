package io.nl.updater;

import java.net.URL;
import java.io.File;

import org.apache.commons.io.FileUtils;

public class DownloadUpdate {
	public static void runner() throws Exception {
		String system_type = System.getProperty("os.name");
		File fpath = new File("");
		UpdateInfo ui = GetUpdateInfo.getInfo();
		String[] values = ui.dump();
		for(int x = 0; x < values.length; x++) {
			System.out.println("Value: " + values[x]);
		}
		
		for(int i = 0; i < ui.getVersion().length; i++) {
			System.out.format("URL: %s, Name %s, Version, %s\n", ui.getURL()[i], ui.getName()[i], ui.getVersion()[i]);
			System.out.format("Downloading %s-%s\n", ui.getName()[i], ui.getVersion()[i]);

			if(system_type.equals("Linux")) {
				fpath = new File(System.getProperty("user.home") + "/.minecraft/mods/" + ui.getName()[i] + "-" + ui.getVersion()[i] + ".jar");
			} else if(system_type.equals("Windows")) {
				fpath = new File(System.getProperty("user.home") + "/AppData/Roaming/.minecraft/mods" + ui.getName()[i] + "-" + ui.getVersion()[i] + ".jar");
			} else {
				fpath = new File(System.getProperty("user.home") + "/.minecraft/mods/" + ui.getName()[i] + "-" + ui.getVersion()[i] + ".jar");
			}

			String url = ui.getURL()[i];
			FileUtils.copyURLToFile(new URL(url), fpath);
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println("DEBUG START");
		DownloadUpdate.runner();
	}
}
