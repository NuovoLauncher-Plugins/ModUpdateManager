package io.nl.updater;

public class UpdateInfo {
	private String repo;
	private String[] names;
	private String[] versions;
	private String[] URLs;
	
	public void setRepo(String r) {
		this.repo = r;
	};
	
	public String getRepo() {
		return this.repo;
	};
	
	public void setName(String[] name) {
		this.names = name;
	};
	
	public String[] getName() {
		return this.names;
	};
	
	public void setVersion(String[] version) {
		this.versions = version;
	};
	
	public String[] getVersion() {
		return this.versions;
	};
	
	public void setURL(String[] URL) {
		this.URLs = URL;
	};
	
	public String[] getURL() {
		return this.URLs;
	}
	
	public String[] dump() {
		String[] values = new String[13];
		int i = 0;
		int x = 0;
		int y = 0;
		
		for(i=0; i < names.length; i++) {
			try {
				values[i] = this.names[i];
			} catch(ArrayIndexOutOfBoundsException ai) {
				System.out.println("Loop One: Out of bounds.");
				break;
			}
		}
		
		
		System.out.format("Integer value i: %d\n", i);
		
		
		for(x = i + 1; x > i; x++) {
			try {
				values[x] = this.versions[x - 5];
			} catch(ArrayIndexOutOfBoundsException ai2) {
				System.out.println("Loop Two: Out of bounds.");
				break;
			}
		}
		
		System.out.format("Integer value x: %d\n", x);
		
		for(y = x + 1; y > x; y++) {
			try {
				values[y] = this.URLs[y - 9];
			} catch(ArrayIndexOutOfBoundsException ai3) {
				System.out.println("Loop Three: Out of bounds.");
				break;
			}
		}
		
		return values;
	}
}
