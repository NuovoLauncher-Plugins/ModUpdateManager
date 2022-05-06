package io.nl.updater;

import java.util.List;

import org.kohsuke.github.*;

import org.apache.commons.lang3.ArrayUtils;

@SuppressWarnings("deprecation")
public class GetUpdateInfo {
	public static UpdateInfo getInfo() throws Exception {
		UpdateInfo ui = new UpdateInfo();
		
		String login = "JavaIsNotMagic";
		String password = "IfMSoS21!@#$";
		String oauth = "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";
		
		String version = "";
		String url = "";
		
		String[] urls = {};
		String[] names = {};
		String[] versions = {};
		//Check to make sure we have enough connections left
		String[] value = GetConnectionAttempts.getAttempts();
		if(value[0].equals("0")) {
			throw new Exception("No more connections availiable! We can connect at: " + value[1]);
		} else {
			;
		}
		GitHub github = GitHubBuilder.fromEnvironment(login, password, oauth).build();
		//Get the repo name from the organization
		GHOrganization gho = github.getOrganization("NuovoLauncher-Mods");
		PagedIterable<GHRepository> repos = gho.listRepositories();
		List<GHRepository> repos_list = repos.asList();
		for(int i=0; i < repos_list.size(); i++) {
			GHRepository repo_test = repos_list.get(i);
			GHRelease latest = repo_test.getLatestRelease();
			
			names = ArrayUtils.add(names, latest.getName());
			
			List<GHAsset> assets = latest.getAssets();
			for( int x = 0; x < assets.size(); x++ ) {
				GHAsset asset = assets.get(x);
				url = asset.getBrowserDownloadUrl();
				version = url.split("/")[7];
				
				System.out.format("URL: %s, Name: %s, Latest Release: %s. Version %s\n", url, latest.getName(), latest, version);
				
				urls = ArrayUtils.add(urls, url);
				versions = ArrayUtils.add(versions, version);
				
			}
		}
		//Add all the values
		ui.setName(names);
		ui.setURL(urls);
		ui.setVersion(versions);
		
		return ui;
	}
	
	public static void main(String[] args) throws Exception {	
		UpdateInfo ui = GetUpdateInfo.getInfo();
		String[] values = ui.dump();
		for(int x = 0; x < values.length; x++) {
			System.out.println("Value: " + values[x]);
		}
	}
}

